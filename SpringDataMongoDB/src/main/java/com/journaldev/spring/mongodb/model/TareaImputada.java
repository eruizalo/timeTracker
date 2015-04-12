package com.journaldev.spring.mongodb.model;

import java.security.Timestamp;

public class TareaImputada {

	private String idUsuario;
	private Timestamp timestampInicio;
	private Timestamp timestampFin;
	private String tarea;

	public TareaImputada(String idUsuario, Timestamp timestampInicio,
			Timestamp timestampFin, String tarea) {
		super();
		this.idUsuario = idUsuario;
		this.timestampInicio = timestampInicio;
		this.timestampFin = timestampFin;
		this.tarea = tarea;
	}

	/**
	 * @return the idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * @return the timestampInicio
	 */
	public Timestamp getTimestampInicio() {
		return timestampInicio;
	}

	/**
	 * @param timestampInicio the timestampInicio to set
	 */
	public void setTimestampInicio(Timestamp timestampInicio) {
		this.timestampInicio = timestampInicio;
	}

	/**
	 * @return the timestampFin
	 */
	public Timestamp getTimestampFin() {
		return timestampFin;
	}

	/**
	 * @param timestampFin the timestampFin to set
	 */
	public void setTimestampFin(Timestamp timestampFin) {
		this.timestampFin = timestampFin;
	}

	/**
	 * @return the tarea
	 */
	public String getTarea() {
		return tarea;
	}

	/**
	 * @param tarea the tarea to set
	 */
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
}
