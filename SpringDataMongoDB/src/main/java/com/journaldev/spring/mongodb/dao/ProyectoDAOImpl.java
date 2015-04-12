package com.journaldev.spring.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.journaldev.spring.mongodb.model.ErrorDesc;
import com.journaldev.spring.mongodb.model.Proyecto;
import com.mongodb.WriteResult;

public class ProyectoDAOImpl implements ProyectoDAO{

	private MongoOperations mongoOps;
	private static final String COLECCION_PROYECTOS = "Proyectos";
	
	public ProyectoDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	//@Override
	public ErrorDesc create(Proyecto proyecto) {
		
		try {
			this.mongoOps.insert(proyecto, COLECCION_PROYECTOS);
	        return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(1, e.getCause().toString(), e);
		}
	}

	//@Override
	public Proyecto readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Proyecto.class, COLECCION_PROYECTOS);
	}

	//@Override
	public ErrorDesc update(Proyecto proyecto) {
		try {
			this.mongoOps.save(proyecto, COLECCION_PROYECTOS);
			return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(3, e.getCause().toString(), e);
		}
	}

	//@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Proyecto.class, COLECCION_PROYECTOS);
		return result.getN();
	}
	
	public List<Proyecto> readAll() {
		return this.mongoOps.findAll(Proyecto.class, COLECCION_PROYECTOS);
	}
}
