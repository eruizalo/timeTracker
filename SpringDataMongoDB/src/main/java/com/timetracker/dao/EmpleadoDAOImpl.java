package com.timetracker.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.timetracker.model.Empleado;
import com.timetracker.model.ErrorDesc;

public class EmpleadoDAOImpl implements EmpleadoDAO {

	private MongoOperations mongoOps;
	private static final String COLECCION_EMPLEADOS = "Empleados";
	
	public EmpleadoDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	//@Override
	public ErrorDesc create(Empleado p) {
		//this.mongoOps.insert(p, PERSON_COLLECTION);
		try {
			this.mongoOps.insert(p, COLECCION_EMPLEADOS);
			//System.out.println("New Person inserted:" + p);
	        return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(1, e.getCause().toString(), e);
		}
	}

	//@Override
	public Empleado readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Empleado.class, COLECCION_EMPLEADOS);
		//return this.mongoOps.findById(id, Person.class);
	}

	//@Override
	public ErrorDesc update(Empleado p) {
		try {
			this.mongoOps.save(p, COLECCION_EMPLEADOS);
			return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(3, e.getCause().toString(), e);
		}
	}

	//@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Empleado.class, COLECCION_EMPLEADOS);
		return result.getN();
	}
	
	public List<Empleado> readAll() {
		//System.out.println("GET COLLECTION");
		//Query query = new Query();
		return this.mongoOps.findAll(Empleado.class, COLECCION_EMPLEADOS);
		//return this.mongoOps.find(query, Person.class, PERSON_COLLECTION);
	}

}
