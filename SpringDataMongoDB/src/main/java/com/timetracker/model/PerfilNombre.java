package com.timetracker.model;


public class PerfilNombre {
	
	private byte id;
	private String nombre;
	
	public PerfilNombre(byte id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public byte getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
}
