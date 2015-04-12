package com.timetracker.main;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.dao.JsonDAO;
import com.timetracker.model.Cliente;
import com.timetracker.model.Empleado;
import com.timetracker.model.ErrorDesc;
import com.timetracker.model.Perfil;
import com.timetracker.model.Proyecto;

@RestController
public class TimeTrackerController {
	
	private static JsonDAO jsonDao = new JsonDAO();
	
	private String getBackLink = "<br><br><a href='http://localhost:8080/'>Volver</a>";

	@RequestMapping(value = "/", method = RequestMethod.GET)
    private Object listarEmpleados() {
		/*
		System.out.println("GET COLLECTION");
		Query query = new Query();
		List<Person> p = this.mongoOps.find(query, Person.class, PERSON_COLLECTION);
		*/
		List<Empleado> listaEmpleados = TimeTrackerMain.interfazEmpleados.readAll();
		/*if (personList.isEmpty()){
			return "Empty collection";
		} else {
			return personList;
		}*/
		//List<Cliente> listaClientes = interfazClientes.readAll();
		return listaEmpleados;
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addEmpleados(@RequestParam(value="id", defaultValue="") String id) {
		Empleado nuevoEmpleado = new Empleado(id, "Anakin Skywalker", Perfil.padawanId, "anakin");
		ErrorDesc error = TimeTrackerMain.interfazEmpleados.create(nuevoEmpleado);
		
		if (error.getErrorCode()==0){
			return "New person added--> " + nuevoEmpleado + getBackLink;
		} else {
			error.getException().printStackTrace();
			return error.getErrorDesc() + getBackLink;
		}
    }
	
	@RequestMapping(value = "/drop", method = RequestMethod.GET)
    private String dropMongo() {
		TimeTrackerMain.dropDBs();
		return "MongoDB droped" + getBackLink;
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    private String deleteEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		
		int error = TimeTrackerMain.interfazEmpleados.deleteById(id);
		
		if (error==1){
			return "Persona borrada" + getBackLink;
		} else {
			return "Error durante el borrado-->" + error + getBackLink;
		}
    }
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
    private String getEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		Empleado empleado = TimeTrackerMain.interfazEmpleados.readById(id);
		if (empleado != null){
			String json = jsonDao.objToJson(empleado);
			return json + getBackLink;
		}else {
			return "Empleado no encontrado" + getBackLink;
		}
    }
	
	@RequestMapping(value = "/getClientes", method = RequestMethod.GET)
    private Object listarClientes() {
		
		List<Cliente> listaClientes = TimeTrackerMain.interfazClientes.readAll();
		return listaClientes;
    }
	
	@RequestMapping(value = "/getCliente", method = RequestMethod.GET)
    private String getCliente(@RequestParam(value="id", defaultValue="") String id) {
		Cliente cliente = TimeTrackerMain.interfazClientes.readById(id);
		if (cliente != null){
			String json = jsonDao.objToJson(cliente);
			return json + getBackLink;
		}else {
			return "Cliente no encontrado" + getBackLink;
		}
    }
	
	@RequestMapping(value = "/getProyectos", method = RequestMethod.GET)
    private Object listarProyectos() {
		
		List<Proyecto> listaProyectos = TimeTrackerMain.interfazProyectos.readAll();
		return listaProyectos;
    }
	
	@RequestMapping(value = "/getProyecto", method = RequestMethod.GET)
    private String getProyecto(@RequestParam(value="id", defaultValue="") String id) {
		Proyecto proyecto = TimeTrackerMain.interfazProyectos.readById(id);
		if (proyecto != null){
			String json = jsonDao.objToJson(proyecto);
			return json + getBackLink;
		}else {
			return "Proyecto no encontrado" + getBackLink;
		}
    }
	
	@RequestMapping(value = "/addTarea", method = RequestMethod.GET)
    private String addTareaProyecto(@RequestParam(value="id") String idProyecto,
    		@RequestParam(value="idUser") String idEmpleado,
    		@RequestParam(value="tarea") String tarea) {
		
		ErrorDesc error = TimeTrackerMain.interfazProyectos.addTareaProyecto(idProyecto, idEmpleado, tarea);
		
		return error.getErrorDesc() + getBackLink;
    }
	
	@RequestMapping(value = "/finishTarea", method = RequestMethod.GET)
    private String finishTareaProyecto(@RequestParam(value="id") String idEmpleado) {
		
		ErrorDesc error = TimeTrackerMain.interfazProyectos.finishTareaProyecto(idEmpleado);
		
		return error.getErrorDesc() + getBackLink;
    }
}
