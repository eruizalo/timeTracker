package com.timetracker.dao;

import java.util.ArrayList;
import java.util.List;

import com.timetracker.model.ErrorDesc;
import com.timetracker.model.Proyecto;

public interface ProyectoDAO {

	public ErrorDesc create(Proyecto p);
	
	public Proyecto readById(String id);
	
	public ErrorDesc update(Proyecto p);
	
	public int deleteById(String id);
	
	public List<Proyecto> readAll();
	
	public ErrorDesc addTareaProyecto (String idProyecto, String idEmpleado,
			String tarea);
	
	public ErrorDesc finishTareaProyecto (String idEmpleado);
	
	public long countProyectos();
	
	public long countTareas();

	public List<Proyecto> readProyectosCliente(String id);
}
