package com.alura.hotel.modelo;

import java.util.Date;
import java.util.List;

public class Reserva {
	private Integer id;
	private Date fechaDeEntrada;
	private String Valor;
	private Date fechaDeSalida;
	private String formaDePago;
	private List<Huesped> huespedes;		

	public Reserva() {
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
	
	public List<Huesped> getHuespedes() {
	    return huespedes;
	}

	public void setHuespedes(List<Huesped> huespedes) {
	    this.huespedes = huespedes;
	}
	
	@Override
	public String toString() {
		return String.format("id: %s%nfechaDeEntrada: %s%nfechaDeSalida: %s%nValor: %s%nformaDePago: %s" , 
				this.id, 
				this.fechaDeEntrada,
				this.fechaDeSalida,
				this.Valor,
				this.formaDePago);
	}
}
