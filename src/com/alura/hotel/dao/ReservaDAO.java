package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.controller.RegistrarHuespedController;
import com.alura.hotel.modelo.RegistrarHuesped;
import com.alura.hotel.modelo.Reserva;
import com.alura.hotel.views.RegistroHuesped;

public class ReservaDAO {
	final private Connection con;
	RegistrarHuespedController controller = new RegistrarHuespedController();
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
	        for (RegistrarHuesped huesped : reserva.getHuespedes()) {
	            huesped.setReservaId(reserva.getId()); // Establece el ID de reserva
	            controller.guardar(huesped, reserva.getId());
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}

}
