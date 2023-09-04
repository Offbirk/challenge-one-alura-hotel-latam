package com.alura.hotel.modelo;

import java.util.Date;
import java.util.List;

public class Reserva {
	private Integer id;
	private Date fechaDeEntrada;
	private String Valor;
	private Date fechaDeSalida;
	private String formaDePago;
	private List<RegistrarHuesped> huespedes;		

	public Reserva() {
	}
	
	public Reserva(int id, Date fechaDeEntrada, Date fechaDeSalida, String formaDePago) {
		this.setId(id);
		this.fechaDeEntrada = fechaDeEntrada;
		this.fechaDeSalida = fechaDeSalida;
		this.formaDePago = formaDePago;
	}

	public Reserva(Integer id, Date fechaDeEntrada, Date fechaDeSalida, String Valor, String formaDePago) {
		this.id = id;
		this.fechaDeEntrada = fechaDeEntrada;
		this.fechaDeSalida = fechaDeSalida;
		this.Valor = Valor;
		this.formaDePago = formaDePago;
	}

	public Date getFechaDeEntrada() {
		return fechaDeEntrada;
	}

	public void setFechaDeEntrada(Date fechaDeEntrada) {
		this.fechaDeEntrada = fechaDeEntrada;
	}

	public Date getFechaDeSalida() {
		return fechaDeSalida;
	}

	public void setFechaDeSalida(Date fechaDeSalida) {
		this.fechaDeSalida = fechaDeSalida;
	}

	public String getValor() {
		return Valor;
	}
	
	public String getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<RegistrarHuesped> getHuespedes() {
	    return huespedes;
	}

	public void setHuespedes(List<RegistrarHuesped> huespedes) {
	    this.huespedes = huespedes;
	}
}
