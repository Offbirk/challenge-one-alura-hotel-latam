package com.alura.hotel.modelo;

import java.util.Date;

public class RegistrarHuesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private Integer telefono;
	private Integer reservaId;	

	public RegistrarHuesped() {
		
	}
	
	public RegistrarHuesped(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, Integer telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	public RegistrarHuesped(String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, Integer telefono,
			Integer reservaId) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}

	public Integer getid() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}
	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	
	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}
}
