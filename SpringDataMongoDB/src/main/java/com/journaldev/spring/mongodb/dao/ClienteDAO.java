package com.journaldev.spring.mongodb.dao;

import java.util.List;

import com.journaldev.spring.mongodb.model.Cliente;
import com.journaldev.spring.mongodb.model.ErrorDesc;

public interface ClienteDAO {

public ErrorDesc create(Cliente p);
	
	public Cliente readById(String id);
	
	public ErrorDesc update(Cliente p);
	
	public int deleteById(String id);
	
	public List<Cliente> readAll();
}
