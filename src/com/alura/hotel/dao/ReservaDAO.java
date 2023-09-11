package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alura.hotel.controller.HuespedController;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.views.RegistroHuesped;

public class ReservaDAO {
	final private Connection con;
	HuespedController controller = new HuespedController();

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO Reservas(Fecha_de_entrada, Fecha_de_salida, Valor, Forma_de_pago)" + "VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
				statement.setDate(1, new java.sql.Date(reserva.getFechaDeEntrada().getTime()));
				statement.setDate(2, new java.sql.Date(reserva.getFechaDeSalida().getTime()));
				statement.setString(3, reserva.getValor());
				statement.setString(4, reserva.getFormaDePago());
				statement.execute();

				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while (resultSet.next()) {
						int generatedId = resultSet.getInt(1);
						reserva.setId(generatedId);
					}
				}
			}

			// Luego, guarda los huéspedes asociados a esta reserva.
			for (Huesped huesped : reserva.getHuespedes()) {
				huesped.setReservaId(reserva.getId()); // Establece el ID de reserva
				controller.guardar(huesped, reserva.getId());
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listar() {
		List<Reserva> resultado = new ArrayList<>();

		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.recuperaConexion();

		try (con) {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT id, Fecha_de_entrada, Fecha_de_salida, Valor, Forma_de_pago FROM Reservas");

			try (statement) {
				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("id"), resultSet.getDate("Fecha_de_entrada"),
								resultSet.getDate("Fecha_de_salida"), resultSet.getString("Valor"),
								resultSet.getString("Forma_de_pago"));

						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public int modificar(Integer id, Date fechaDeEntrada, Date fechaDeSalida, String valor, String formaDePago) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE Reservas SET " + " Fecha_de_entrada = ? "
					+ ", Fecha_de_salida = ?" + ", Valor = ?" + ", Forma_de_pago = ?" + "WHERE id =?");
			try (statement) {
				statement.setDate(1, (java.sql.Date) fechaDeEntrada);
				statement.setDate(2, (java.sql.Date) fechaDeSalida);
				statement.setString(3, valor);
				statement.setString(4, formaDePago);
				statement.setInt(5, id);
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
