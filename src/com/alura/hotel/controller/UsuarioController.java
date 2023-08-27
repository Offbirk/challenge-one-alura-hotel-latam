package com.alura.hotel.controller;

import com.alura.hotel.dao.UsuarioDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Usuario;

public class UsuarioController {
	
	private UsuarioDAO usuarioDAO;

	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().recuperaConexion());		
	}
	
	public void guardar(Usuario usuario) {
		usuarioDAO.guardar(usuario);
	}
	
}
