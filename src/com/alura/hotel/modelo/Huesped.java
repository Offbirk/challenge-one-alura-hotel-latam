package com.alura.hotel.modelo;

import java.util.Date;

public class Huesped {
	
	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private Long telefono;
	private Integer reservaId;	

	public Huesped() {
		
	}
	
	public Huesped(Integer id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, long telefono, Integer reservaId) {
		this.id = id;
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
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	
	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}

	@Override
	public String toString() {
		return "Huesped [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaDeNacimiento="
				+ fechaDeNacimiento + ", nacionalidad=" + nacionalidad + ", telefono=" + telefono + ", reservaId="
				+ reservaId + "]";
	}
		
}
