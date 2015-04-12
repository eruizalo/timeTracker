package com.journaldev.spring.mongodb.model;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Proyecto {

	//id will be used for storing MongoDB _id
	@Id
	private String id;
		
	private String nombre;
	private String creador;
	private String cliente;
	private String responsable;
	private ArrayList<String> listaTareas;
	// ¿Donde guardo la imputacion de tareas?
	// Lista de tarifas del proyecto <Perfil>, <euros/hora>
	private ArrayList<Perfil> listaTarifasProyecto;
	private Date fechaInicio;
	private Date fechaFin;
	private String audUsuario;
	// Lista de tareas imputadas al proyecto
	private ArrayList<TareaImputada> listaHorasImputadas;
	
	public Proyecto(String id, String nombre, String creador, String cliente,
			String responsable, ArrayList<Perfil> listaTarifasProyecto,
			String audUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.creador = creador;
		this.cliente = cliente;
		this.responsable = responsable;
		this.listaTareas = null;
		this.listaTarifasProyecto = listaTarifasProyecto;
		this.fechaInicio = new Date();
		this.fechaFin = null;
		this.audUsuario = audUsuario;
		this.listaHorasImputadas = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public ArrayList<String> getListaTareas() {
		return listaTareas;
	}

	public void setListaTareas(ArrayList<String> listaTareas) {
		this.listaTareas = listaTareas;
	}

	public ArrayList<Perfil> getListaTarifasProyecto() {
		return listaTarifasProyecto;
	}

	public void setListaTarifasProyecto(ArrayList<Perfil> listaTarifasProyecto) {
		this.listaTarifasProyecto = listaTarifasProyecto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getAudUsuario() {
		return audUsuario;
	}

	public void setAudUsuario(String audUsuario) {
		this.audUsuario = audUsuario;
	}

	public ArrayList<TareaImputada> getListaHorasImputadas() {
		return listaHorasImputadas;
	}

	public void setListaHorasImputadas(ArrayList<TareaImputada> listaHorasImputadas) {
		this.listaHorasImputadas = listaHorasImputadas;
	}
}
