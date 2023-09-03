package com.alura.hotel.controller;

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
}
