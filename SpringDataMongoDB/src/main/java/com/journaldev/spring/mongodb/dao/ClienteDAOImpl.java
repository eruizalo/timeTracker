package com.journaldev.spring.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.journaldev.spring.mongodb.model.Cliente;
import com.journaldev.spring.mongodb.model.ErrorDesc;
import com.mongodb.WriteResult;

public class ClienteDAOImpl implements ClienteDAO{

	private MongoOperations mongoOps;
	private static final String COLECCION_CLIENTES = "Clientes";
	
	public ClienteDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	//@Override
	public ErrorDesc create(Cliente cliente) {
		
		try {
			this.mongoOps.insert(cliente, COLECCION_CLIENTES);
	        return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(1, e.getCause().toString(), e);
		}
	}

	//@Override
	public Cliente readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Cliente.class, COLECCION_CLIENTES);
	}

	//@Override
	public ErrorDesc update(Cliente cliente) {
		try {
			this.mongoOps.save(cliente, COLECCION_CLIENTES);
			return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(3, e.getCause().toString(), e);
		}
	}

	//@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Cliente.class, COLECCION_CLIENTES);
		return result.getN();
	}
	
	public List<Cliente> readAll() {
		return this.mongoOps.findAll(Cliente.class, COLECCION_CLIENTES);
	}
}
