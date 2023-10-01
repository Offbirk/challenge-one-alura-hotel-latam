package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

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

	public List<Huesped> buscarPorCriterio(String criterio) {
	    List<Huesped> resultados = new ArrayList<>();
	    
	    try {
	        // Intenta parsear el criterio como un entero (ID de reserva)
	        int idReserva = Integer.parseInt(criterio);
	        
	        // Si no lanza una excepción, significa que es un número válido
	        // Realiza la búsqueda por ID de reserva en la base de datos
	        final String query = "SELECT * FROM huespedes WHERE Id_Reserva = ?";
	        final PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setInt(1, idReserva);
	        final ResultSet resultSet = stmt.executeQuery(); // Usar executeQuery en lugar de execute
	        try (resultSet) {
	            while (resultSet.next()) {
	                Huesped fila;
	                fila = new Huesped(
	                        resultSet.getInt("id"),
	                        resultSet.getString("Nombre"),
	                        resultSet.getString("Apellido"), 
	                        resultSet.getDate("Fecha_de_nacimiento"),
	                        resultSet.getString("Nacionalidad"), 
	                        resultSet.getLong("Telefono"),
	                        resultSet.getInt("Id_Reserva"));
	                resultados.add(fila);
	            }
	        }
	    } catch (NumberFormatException | SQLException e) {
	        throw new RuntimeException(e);
	    }
	    
	    return resultados;
	}

	// Método para buscar por apellido
	public List<Huesped> buscarPorApellido(String apellido) {
	    List<Huesped> resultados = new ArrayList<>();
	    
	    try {
	        final String query = "SELECT * FROM huespedes WHERE Apellido LIKE ?";
	        final PreparedStatement stmt = con.prepareStatement(query);
	        stmt.setString(1, "%" + apellido + "%"); // Usamos % para buscar coincidencias parciales
	        final ResultSet resultSet = stmt.executeQuery();
	        try (resultSet) {
	            while (resultSet.next()) {
	                Huesped fila;
	                fila = new Huesped(
	                        resultSet.getInt("id"),
	                        resultSet.getString("Nombre"),
	                        resultSet.getString("Apellido"), 
	                        resultSet.getDate("Fecha_de_nacimiento"),
	                        resultSet.getString("Nacionalidad"), 
	                        resultSet.getLong("Telefono"),
	                        resultSet.getInt("Id_Reserva"));
	                resultados.add(fila);
	            }
	        }
	    } catch (SQLException e) {
	    	throw new RuntimeException(e);
	    }
	    
	    return resultados;
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
