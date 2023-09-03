package com.alura.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.alura.hotel.modelo.Usuario;

public class UsuarioDAO {
	final private Connection con;

	public UsuarioDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Usuario usuario) {
		String consulta = "SELECT COUNT(*) FROM registro_usuario WHERE nombre_usuario = ?";
		try {
			PreparedStatement consultaUsuario = con.prepareStatement(consulta);
			consultaUsuario.setString(1, usuario.getNombreUsuario());

			ResultSet resultado = consultaUsuario.executeQuery();
			resultado.next();
			int count = resultado.getInt(1);
			//TODO
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "El usuario ya existe, ingrese otro nombre");				
			} else {
				try (PreparedStatement insertStatement = con
						.prepareStatement("INSERT INTO registro_usuario(nombre_usuario, clave) VALUES (?, ?)")) {

					insertStatement.setString(1, usuario.getNombreUsuario());
					insertStatement.setString(2, usuario.getClave());

					insertStatement.execute();
					JOptionPane.showMessageDialog(null, "Iniciando sesión");
				} catch (SQLException e) {
					throw new RuntimeException("Error al insertar el usuario", e);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error en la consulta", e);
		}
	}
}