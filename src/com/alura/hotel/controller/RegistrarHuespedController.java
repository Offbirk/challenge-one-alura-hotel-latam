package com.alura.hotel.controller;

import java.util.List;

import com.alura.hotel.dao.RegistrarHuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.RegistrarHuesped;

public class RegistrarHuespedController {
	
	private RegistrarHuespedDAO registrarHuespedDAO;

	public RegistrarHuespedController() {
		this.registrarHuespedDAO = new RegistrarHuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(RegistrarHuesped registrarHuesped, Integer reservaId) {
		registrarHuesped.setReservaId(reservaId);
		registrarHuespedDAO.guardar(registrarHuesped, reservaId);
	}

	public List<RegistrarHuesped> listar() {
		return registrarHuespedDAO.listar();
	}

	public int modificar(Integer id, String nombre, String apellido, String fechaDeNacimiento, String nacionalidad,
			long telefono, Integer reservaId) {
		return registrarHuespedDAO.modificar(id, nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, reservaId);
	}

	public int eliminar(Integer id) {
		return registrarHuespedDAO.eliminar(id);
	}
}
