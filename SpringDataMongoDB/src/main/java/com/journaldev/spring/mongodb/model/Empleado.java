package com.journaldev.spring.mongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Empleado {

	//id will be used for storing MongoDB _id
	@Id
	private String id;
	
	private String nombre;
	private byte perfil;
	private String username;
	private Date fechaIncorporacion;
	private Date fechaBaja;
	private String proyectoTareaEnCurso;
	
	//public Empleado(){}
	
	public Empleado(String id, String nombre, byte perfil, String username){
		this.id = id;
		this.nombre = nombre;
		this.perfil = perfil;
		this.username = username;
		this.fechaIncorporacion = new Date();
		this.fechaBaja = null;
		this.setProyectoTareaEnCurso(null);
	}
	public String getId() {
		return id;
	}
	/*public void setId(String id) {
		this.id = id;
	}*/
	public String getName() {
		return nombre;
	}
	public void setName(String name) {
		this.nombre = name;
	}
	public byte getPerfil() {
		return perfil;
	}
	public void setPerfil(byte perfil) {
		this.perfil = perfil;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the fechaIncorporacion
	 */
	public Date getFechaIncorporacion() {
		return fechaIncorporacion;
	}
	/**
	 * @param fechaIncorporacion the fechaIncorporacion to set
	 */
	/*public void setFechaIncorporacion(Date fechaIncorporacion) {
		this.fechaIncorporacion = fechaIncorporacion;
	}*/
	/**
	 * @return the fechaBaja
	 */
	public Date getFechaBaja() {
		return fechaBaja;
	}
	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	/**
	 * @return the proyectoTareaEnCurso
	 */
	public String getProyectoTareaEnCurso() {
		return proyectoTareaEnCurso;
	}
	/**
	 * @param proyectoTareaEnCurso the proyectoTareaEnCurso to set
	 */
	public void setProyectoTareaEnCurso(String proyectoTareaEnCurso) {
		this.proyectoTareaEnCurso = proyectoTareaEnCurso;
	}
}
