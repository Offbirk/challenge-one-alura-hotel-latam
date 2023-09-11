package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;

public class HuespedDAO {
	final private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped registrarHuesped, int reservaId) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huespedes(Nombre, Apellido, Fecha_de_nacimiento, Nacionalidad, Telefono, Id_Reserva)"
							+ "VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setString(1, registrarHuesped.getNombre());
				statement.setString(2, registrarHuesped.getApellido());
				statement.setDate(3, new java.sql.Date(registrarHuesped.getFechaDeNacimiento().getTime()));
				statement.setString(4, registrarHuesped.getNacionalidad());
				statement.setLong(5, registrarHuesped.getTelefono());
				statement.setInt(6, reservaId); // Utiliza el ID de reserva
				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while (resultSet.next()) {
						registrarHuesped.setReservaId(resultSet.getInt(1));
					}
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> listar() {
		List<Huesped> resultado = new ArrayList<>();

		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, Nombre, Apellido, Fecha_de_nacimiento, Nacionalidad, Telefono, Id_Reserva FROM huespedes");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Huesped fila = new Huesped(
								resultSet.getInt("id"),
								resultSet.getString("Nombre"),
								resultSet.getString("Apellido"), 
								resultSet.getDate("Fecha_de_nacimiento"),
								resultSet.getString("Nacionalidad"), 
								resultSet.getLong("Telefono"),
								resultSet.getInt("Id_Reserva"));
						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad,
			long telefono, Integer reservaId) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET " + " Nombre = ? " + ", Apellido = ?" + ", Fecha_de_nacimiento = ?"
		+ ", Nacionalidad = ?" + ", Telefono = ?" + "Id_Reserva = ?" + "WHERE id = ?");
			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, (java.sql.Date) fechaDeNacimiento);
				statement.setString(4, nacionalidad);
				statement.setLong(5, telefono);
				statement.setInt(6, reservaId);
				statement.setInt(7, id);
				statement.execute();
				
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public int eliminar(Integer id) {
		try (con) {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM Reservas WHERE id = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
