package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.modelo.RegistrarHuesped;

public class RegistrarHuespedDAO {
	final private Connection con;

	public RegistrarHuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(RegistrarHuesped registrarHuesped, int reservaId) {
	    try {
	        final PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO huespedes(Nombre, Apellido, Fecha_de_nacimiento, Nacionalidad, Telefono, Id_Reserva)" + "VALUES(?,?,?,?,?,?)",
	                Statement.RETURN_GENERATED_KEYS);

	        try (statement) {
	            statement.setString(1, registrarHuesped.getNombre());
	            statement.setString(2, registrarHuesped.getApellido());
	            statement.setDate(3, new java.sql.Date(registrarHuesped.getFechaDeNacimiento().getTime()));
	            statement.setString(4, registrarHuesped.getNacionalidad());
	            statement.setLong(5, registrarHuesped.getTelefono());
	            statement.setInt(6, reservaId); // Utiliza el ID de reserva
	            statement.execute();
	        }

	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	}
}
