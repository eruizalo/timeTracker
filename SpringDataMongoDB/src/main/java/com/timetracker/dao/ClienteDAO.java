package com.timetracker.dao;

import java.util.List;

import com.timetracker.model.Cliente;
import com.timetracker.model.ErrorDesc;

public interface ClienteDAO {

public ErrorDesc create(Cliente p);
	
	public Cliente readById(String id);
	
	public ErrorDesc update(Cliente p);
	
	public int deleteById(String id);
	
	public List<Cliente> readAll();
	
	public long countClientes();
}
