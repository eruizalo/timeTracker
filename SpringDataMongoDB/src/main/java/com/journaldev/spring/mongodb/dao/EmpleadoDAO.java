package com.journaldev.spring.mongodb.dao;

import java.util.List;

import com.journaldev.spring.mongodb.model.ErrorDesc;
import com.journaldev.spring.mongodb.model.Empleado;

public interface EmpleadoDAO {

	public ErrorDesc create(Empleado p);
	
	public Empleado readById(String id);
	
	public ErrorDesc update(Empleado p);
	
	public int deleteById(String id);
	
	public List<Empleado> readAll();
}
