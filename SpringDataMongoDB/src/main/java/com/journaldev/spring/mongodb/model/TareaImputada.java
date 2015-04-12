package com.journaldev.spring.mongodb.model;

import java.util.Date;

public class TareaImputada {

	private String idUsuario;
	private Date inicioTarea;
	private Date finTarea;
	private String tarea;

	public TareaImputada(String idUsuario, String tarea) {
		super();
		this.idUsuario = idUsuario;
		this.inicioTarea = new Date();
		this.finTarea = null;
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
	/*public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}*/

	/**
	 * @return the inicioTarea
	 */
	public Date getinicioTarea() {
		return inicioTarea;
	}

	/**
	 * @param inicioTarea the inicioTarea to set
	 */
	/*public void setinicioTarea(Date inicioTarea) {
		this.inicioTarea = inicioTarea;
	}*/

	/**
	 * @return the finTarea
	 */
	public Date getfinTarea() {
		return finTarea;
	}

	/**
	 * @param finTarea the finTarea to set
	 */
	public void setfinTarea(Date finTarea) {
		this.finTarea = finTarea;
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
	/*public void setTarea(String tarea) {
		this.tarea = tarea;
	}*/
}
