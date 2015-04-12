package com.journaldev.spring.mongodb.dao;

import java.util.List;

import com.journaldev.spring.mongodb.model.Proyecto;
import com.journaldev.spring.mongodb.model.ErrorDesc;

public interface ProyectoDAO {

	public ErrorDesc create(Proyecto p);
	
	public Proyecto readById(String id);
	
	public ErrorDesc update(Proyecto p);
	
	public int deleteById(String id);
	
	public List<Proyecto> readAll();
	
	public ErrorDesc addTareaProyecto (String idProyecto, String idEmpleado,
			String tarea, String horas);
	
	public ErrorDesc finishTareaProyecto (String idEmpleado);
}
