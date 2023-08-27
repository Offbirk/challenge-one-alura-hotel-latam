package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.alura.hotel.modelo.Usuario;

public class UsuarioDAO {
	final private Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con = con;
	}
	
	public void guardar(Usuario usuario) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO registro_usuario(nombre_usuario, clave)" + "VALUES(?,?)",
					Statement.RETURN_GENERATED_KEYS);
			
			try(statement) {
				statement.setString(1, usuario.getNombreUsuario());
				statement.setString(2, usuario.getClave());
				statement.execute();			
			}
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
}
