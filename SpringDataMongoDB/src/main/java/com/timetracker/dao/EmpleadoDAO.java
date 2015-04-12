package com.timetracker.dao;

import java.util.List;

import com.timetracker.model.Empleado;
import com.timetracker.model.ErrorDesc;

public interface EmpleadoDAO {

	public ErrorDesc create(Empleado p);
	
	public Empleado readById(String id);
	
	public ErrorDesc update(Empleado p);
	
	public int deleteById(String id);
	
	public List<Empleado> readAll();
}
