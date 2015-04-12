package com.journaldev.spring.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Cliente {
	
	//id will be used for storing MongoDB _id
	@Id
	private String id;
			
	private String nombre;
	private String cif;
	private String direccion;
	private Date fechaInicio;
	private String audUsuario;
	private ArrayList<Perfil> listaTarifasCliente;
	private ArrayList<String> listaProyectosActivos;
	private String ultimoProyecto;
	private Date fechaUltimoProyecto;
	
	public Cliente(String id, String nombre, String cif, String direccion,
			String audUsuario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cif = cif;
		this.direccion = direccion;
		this.fechaInicio = new Date();
		this.audUsuario = audUsuario;
		
		this.listaTarifasCliente = new ArrayList<Perfil>();
		Perfil perfil = new Perfil();
		perfil = new Perfil(Perfil.padawanId, perfil.getTarifa(Perfil.padawanId));
		this.listaTarifasCliente.add(perfil);
		perfil = new Perfil(Perfil.jediId, perfil.getTarifa(Perfil.jediId));
		this.listaTarifasCliente.add(perfil);
		perfil = new Perfil(Perfil.maestroJediId, perfil.getTarifa(Perfil.maestroJediId));
		this.listaTarifasCliente.add(perfil);
		
		this.listaProyectosActivos = new ArrayList<String>();
		this.ultimoProyecto = null;
		this.fechaUltimoProyecto = null;
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

	public String getCIF() {
		return cif;
	}

	public void setCIF(String cif) {
		this.cif = cif;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getAudUsuario() {
		return audUsuario;
	}

	public void setAudUsuario(String audUsuario) {
		this.audUsuario = audUsuario;
	}

	public ArrayList<Perfil> getListaTarifasCliente() {
		return listaTarifasCliente;
	}

	public void setListaTarifasCliente(ArrayList<Perfil> listaTarifasCliente) {
		this.listaTarifasCliente = listaTarifasCliente;
	}

	public List<String> getListaProyectosActivos() {
		return listaProyectosActivos;
	}

	public void setListaProyectosActivos(ArrayList<String> listaProyectosActivos) {
		this.listaProyectosActivos = listaProyectosActivos;
	}

	public String getUltimoProyecto() {
		return ultimoProyecto;
	}

	public void setUltimoProyecto(String ultimoProyecto) {
		this.ultimoProyecto = ultimoProyecto;
	}

	public Date getFechaUltimoProyecto() {
		return fechaUltimoProyecto;
	}

	public void setFechaUltimoProyecto(Date fechaUltimoProyecto) {
		this.fechaUltimoProyecto = fechaUltimoProyecto;
	}
}
