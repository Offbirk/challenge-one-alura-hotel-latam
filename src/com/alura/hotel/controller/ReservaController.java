package com.alura.hotel.controller;

import java.sql.Date;
import java.util.List;

import com.alura.hotel.dao.ReservaDAO;
import com.alura.hotel.factory.ConnectionFactory;
import com.alura.hotel.modelo.Reserva;

public class ReservaController {
	private ReservaDAO reservaDAO;

	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}
	
	//TODO
	public List<Reserva> cargarTablaReserva() {
		return this.reservaDAO.listar();
	}
	
	/**
	 * 
	 * @return permite mostrar los datos de la reserva en el view busqueda
	 */
	public List<Reserva> listar() {
		return reservaDAO.listar();
	}
	/**
	 * 
	 * @param id
	 * @param fechaDeEntrada
	 * @param fechaDeSalida
	 * @param valor
	 * @param formaDePago
	 * @return ejecuta acciones en el view busqueda
	 */
	public int modificar(Integer id, Date fechaDeEntrada, Date fechaDeSalida, String valor, String formaDePago) {
		return reservaDAO.modificar(id, fechaDeEntrada, fechaDeSalida, valor, formaDePago);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

}
