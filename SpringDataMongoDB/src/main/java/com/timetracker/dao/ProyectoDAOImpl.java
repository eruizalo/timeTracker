package com.timetracker.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import com.timetracker.model.Empleado;
import com.timetracker.model.ErrorDesc;
import com.timetracker.model.Proyecto;
import com.timetracker.model.TareaImputada;

public class ProyectoDAOImpl implements ProyectoDAO{

	private MongoOperations mongoOps;
	private static final String COLECCION_PROYECTOS = "Proyectos";
	
	public ProyectoDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	@Override
	public ErrorDesc create(Proyecto proyecto) {
		
		try {
			this.mongoOps.insert(proyecto, COLECCION_PROYECTOS);
	        return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(1, e.getCause().toString(), e);
		}
	}

	@Override
	public Proyecto readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Proyecto.class, COLECCION_PROYECTOS);
	}

	@Override
	public ErrorDesc update(Proyecto proyecto) {
		try {
			this.mongoOps.save(proyecto, COLECCION_PROYECTOS);
			return new ErrorDesc(0, "", null);
		} catch (Exception e) {
			// TODO: handle exception
			return new ErrorDesc(3, e.getCause().toString(), e);
		}
	}

	@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Proyecto.class, COLECCION_PROYECTOS);
		return result.getN();
	}

	@Override
	public List<Proyecto> readAll() {
		return this.mongoOps.findAll(Proyecto.class, COLECCION_PROYECTOS);
	}
	
	@Override
	public ErrorDesc addTareaProyecto (String idProyecto, String idEmpleado,
			String tarea){
		
		EmpleadoDAO interfazEmpleados;
		Empleado empleado;
		String idProyectoTarea;
		Proyecto proyecto;
		ArrayList<TareaImputada> listaTareasImputadas;
		int index;
		boolean encontrado;
		
		interfazEmpleados = new EmpleadoDAOImpl(mongoOps);
		empleado = interfazEmpleados.readById(idEmpleado);
		
		if (empleado == null){
			return new ErrorDesc(1, "Empleado no encontrado", null);
		}
		
		proyecto = readById(idProyecto);
		if (proyecto == null){
			return new ErrorDesc(2, "Proyecto no encontrado", null);
		}
		
		listaTareasImputadas = proyecto.getListaHorasImputadas();
		index = 0;
		encontrado = false;
		
		while (!encontrado && index < listaTareasImputadas.size()) {
			if (listaTareasImputadas.get(index).getIdUsuario().equals(idEmpleado)){
				if (listaTareasImputadas.get(index).getTarea().equals(tarea)) {
					if (null == listaTareasImputadas.get(index).getfinTarea()){
						encontrado = true;
						listaTareasImputadas.get(index).setfinTarea(new Date());
						empleado.setProyectoTareaEnCurso(null);
					}
				}
			}
			index++;
		}
		
		if (encontrado){
			return new ErrorDesc(3, "El empleado ya está imputando en esa tarea", null);
		}
		
		idProyectoTarea = empleado.getProyectoTareaEnCurso();
		if (idProyectoTarea != null){
			ErrorDesc error = finishTareaProyecto(idEmpleado);
			if (error.getErrorCode() != 0){
				error.setErrorDesc("Error while adding task:" + error.getErrorDesc());
				return error;
			}
		}
		 
		if (!proyecto.getListaTareas().contains(tarea)){
			return new ErrorDesc(4, "Tarea no encontrada en el proyecto", null);
		}
		
		listaTareasImputadas.add(new TareaImputada(idEmpleado, tarea));
		proyecto.sumaPerfilAImputar(empleado.getPerfil());
		update(proyecto);
		
		empleado.setProyectoTareaEnCurso(idProyecto);
		interfazEmpleados.update(empleado);
		
		return new ErrorDesc(0, "Horas imputadas a la tarea", null);
	}
	
	@Override
	public ErrorDesc finishTareaProyecto (String idEmpleado){
		
		EmpleadoDAO interfazEmpleados = new EmpleadoDAOImpl(mongoOps);
		
		Empleado empleado = interfazEmpleados.readById(idEmpleado);
		if (empleado == null){
			return new ErrorDesc(1, "Empleado no encontrado", null);
		}
		
		String idProyecto = empleado.getProyectoTareaEnCurso();
		if (idProyecto == null){
			return new ErrorDesc(2, "Empleado sin tareas en curso", null);
		}
		
		Proyecto proyecto = readById(idProyecto);
		if (proyecto == null){
			return new ErrorDesc(3, "Proyecto no encontrado", null);
		}
		
		ArrayList<TareaImputada> tareasImputadas = proyecto.getListaHorasImputadas();
		
		if (tareasImputadas.isEmpty()){
			return new ErrorDesc(4, "No se ha imputado ninguna tarea en el proyecto", null);
		}
		
		int index = 0;
		boolean encontrado = false;
		while (!encontrado && index < tareasImputadas.size()) {
			if(tareasImputadas.get(index).getIdUsuario().equals(idEmpleado)){
				if(null == tareasImputadas.get(index).getfinTarea()){
					encontrado = true;
					tareasImputadas.get(index).setfinTarea(new Date());
					empleado.setProyectoTareaEnCurso(null);
				}
			} else {
				index++;
			}
		}
		
		if (!encontrado){
			return new ErrorDesc(5, "El empleado no tiene tareas vivas en el proyecto", null);
		}
		
		update(proyecto);
		proyecto.restaPerfilAImputar(empleado.getPerfil());
		interfazEmpleados.update(empleado);
		
		return new ErrorDesc(0, "Tarea finalizada", null);
	}

	@Override
	public long countProyectos() {
		Query query = new Query();
		return this.mongoOps.count(query, COLECCION_PROYECTOS);
	}

	@Override
	public long countTareas() {
		Long contador = (long) 0;
		List<Proyecto> proyectos = this.mongoOps.findAll(Proyecto.class, COLECCION_PROYECTOS);
		int index = 0;
		ArrayList<TareaImputada> listaTareas;
		
		while (index < proyectos.size()){
			listaTareas = proyectos.get(index).getListaHorasImputadas();
			for (int i = 0; i < listaTareas.size(); i++) {
				if (listaTareas.get(index).getfinTarea() != null){
					contador++;
				}
			}
		}
		
		return contador;
	}

	@Override
	public List<Proyecto> readProyectosCliente(String id) {
		Query query = new Query(Criteria.where("_cliente").is(id));
		return this.mongoOps.find(query, Proyecto.class, COLECCION_PROYECTOS);
	}
}
