package com.alura.hotel.controller;

import java.util.Date;
import java.util.List;

import com.alura.hotel.dao.HuespedDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Huesped;

public class HuespedController {
	
	private HuespedDAO registrarHuespedDAO;

	public HuespedController() {
		this.registrarHuespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Huesped registrarHuesped, Integer reservaId) {
		registrarHuesped.setReservaId(reservaId);
		registrarHuespedDAO.guardar(registrarHuesped, reservaId);
	}

	public List<Huesped> buscarPorCriterio(String criterio) {
	    return registrarHuespedDAO.buscarPorCriterio(criterio);
	}

	public List<Huesped> listar() {
		return registrarHuespedDAO.listar();
	}

	public int modificar(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad,
			long telefono, Integer reservaId) {
		return registrarHuespedDAO.modificar(id, nombre, apellido, fechaDeNacimiento, nacionalidad, telefono, reservaId);
	}

	public int eliminar(Integer id) {
		return registrarHuespedDAO.eliminar(id);
	}
}
