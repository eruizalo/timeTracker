package com.journaldev.spring.mongodb.main;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.mongodb.dao.ClienteDAO;
import com.journaldev.spring.mongodb.dao.ClienteDAOImpl;
import com.journaldev.spring.mongodb.dao.JsonDAO;
import com.journaldev.spring.mongodb.dao.EmpleadoDAO;
import com.journaldev.spring.mongodb.dao.EmpleadoDAOImpl;
import com.journaldev.spring.mongodb.dao.ProyectoDAO;
import com.journaldev.spring.mongodb.dao.ProyectoDAOImpl;
import com.journaldev.spring.mongodb.model.Cliente;
import com.journaldev.spring.mongodb.model.ErrorDesc;
import com.journaldev.spring.mongodb.model.Empleado;
import com.journaldev.spring.mongodb.model.Perfil;
import com.journaldev.spring.mongodb.model.Proyecto;
import com.mongodb.MongoClient;

@RestController
@EnableAutoConfiguration
public class SpringDataMongoDBMain {

	public static final String DB_NAME = "TimeTracker";
	public static final String COLECCION_EMPLEADOS = "Empleados";
	public static final String COLECCION_CLIENTES = "Clientes";
	public static final String COLECCION_PROYECTOS = "Proyectos";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;
	
	private static MongoClient mongo = null;
	private static MongoOperations mongoOps = null;
	private static JsonDAO jsonDao = new JsonDAO();
	
	private static EmpleadoDAO interfazEmpleados;
	private static ClienteDAO interfazClientes;
	private static ProyectoDAO interfazProyectos;
	
	private String getBackLink = "<br><br><a href='http://localhost:8080/'>Volver</a>";

	
	
	public static void main(String[] args) {
		
		try {
			mongo = new MongoClient(
					MONGO_HOST, MONGO_PORT);
			mongoOps = new MongoTemplate(mongo, DB_NAME);
			interfazEmpleados = new EmpleadoDAOImpl(mongoOps);
			interfazClientes = new ClienteDAOImpl(mongoOps);
			interfazProyectos = new ProyectoDAOImpl(mongoOps);
			
			// POR DEFECTO VACIAMOS LA BBDD
			mongoOps.dropCollection(COLECCION_EMPLEADOS);
			mongoOps.dropCollection(COLECCION_CLIENTES);
			mongoOps.dropCollection(COLECCION_PROYECTOS);
			
			
			//AÑADIMOS POR DEFECTO
			Empleado empleado1 = new Empleado("1", "Mace Windu", Perfil.maestroJediId, "mace");
			Empleado empleado2 = new Empleado("2", "Qui-Gon Jinn", Perfil.jediId, "quigon");
			Empleado empleado3 = new Empleado("3", "Obi-Wan Kenobi", Perfil.padawanId, "obiwan");
			
			Cliente cliente1 = new Cliente("1", "Cliente1", "B00939173", "Calle falsa 1", empleado1.getId());
			Cliente cliente2 = new Cliente("2", "Cliente2", "A73964751", "Calle falsa 2", empleado1.getId());
			
			Proyecto proyecto1 = new Proyecto("1", "Proyecto1", empleado1.getId(), cliente1.getId(), empleado1.getId(),
					cliente1.getListaTarifasCliente(), empleado1.getId());
			Proyecto proyecto2 = new Proyecto("2", "Proyecto2", empleado1.getId(), cliente1.getId(), empleado1.getId(),
					cliente1.getListaTarifasCliente(), empleado1.getId());
			Proyecto proyecto3 = new Proyecto("3", "Proyecto3", empleado1.getId(), cliente1.getId(), empleado1.getId(),
					cliente1.getListaTarifasCliente(), empleado1.getId());
			
			
			interfazEmpleados.create(empleado1);
			interfazEmpleados.create(empleado2);
			interfazEmpleados.create(empleado3);
			
			interfazClientes.create(cliente1);
			interfazClientes.create(cliente2);
			
			interfazProyectos.create(proyecto1);
			interfazProyectos.create(proyecto2);
			interfazProyectos.create(proyecto3);
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			//mongoOps.dropCollection(PERSON_COLLECTION);
			mongo.close();
		} catch (org.springframework.dao.DuplicateKeyException m) {
			System.out.println("DUPLICATE KEY EXCEPTION!!!!");
			//mongoOps.dropCollection(PERSON_COLLECTION);
			mongo.close();
			m.printStackTrace();
		}
		
		SpringApplication.run(SpringDataMongoDBMain.class, args);
	}
	
	@RequestMapping("/")
    private Object listarEmpleados() {
		/*
		System.out.println("GET COLLECTION");
		Query query = new Query();
		List<Person> p = this.mongoOps.find(query, Person.class, PERSON_COLLECTION);
		*/
		List<Empleado> listaEmpleados = interfazEmpleados.readAll();
		/*if (personList.isEmpty()){
			return "Empty collection";
		} else {
			return personList;
		}*/
		//List<Cliente> listaClientes = interfazClientes.readAll();
		return listaEmpleados;
    }
	
	@RequestMapping("/add")
    private String addEmpleados(@RequestParam(value="id", defaultValue="") String id) {
		Empleado nuevoEmpleado = new Empleado(id, "Anakin Skywalker", Perfil.padawanId, "anakin");
		ErrorDesc error = interfazEmpleados.create(nuevoEmpleado);
		
		if (error.getErrorCode()==0){
			return "New person added--> " + nuevoEmpleado + getBackLink;
		} else {
			error.getException().printStackTrace();
			return error.getErrorDesc() + getBackLink;
		}
    }
	
	@RequestMapping("/drop")
    private String dropMongo() {
		mongoOps.dropCollection(COLECCION_EMPLEADOS);
		mongoOps.dropCollection(COLECCION_CLIENTES);
		return "MongoDB droped" + getBackLink;
    }
	
	@RequestMapping("/delete")
    private String deleteEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		
		int error = interfazEmpleados.deleteById(id);
		
		if (error==1){
			return "Persona borrada" + getBackLink;
		} else {
			return "Error durante el borrado-->" + error + getBackLink;
		}
    }
	
	@RequestMapping("/get")
    private String getEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		Empleado empleado = interfazEmpleados.readById(id);
		if (empleado != null){
			String json = jsonDao.objToJson(empleado);
			return json + getBackLink;
		}else {
			return "Empleado no encontrado" + getBackLink;
		}
    }
	
	@RequestMapping("/getClientes")
    private Object listarClientes() {
		
		List<Cliente> listaClientes = interfazClientes.readAll();
		return listaClientes;
    }
	
	@RequestMapping("/getCliente")
    private String getCliente(@RequestParam(value="id", defaultValue="") String id) {
		Cliente cliente = interfazClientes.readById(id);
		if (cliente != null){
			String json = jsonDao.objToJson(cliente);
			return json + getBackLink;
		}else {
			return "Cliente no encontrado" + getBackLink;
		}
    }
	
	@RequestMapping("/getProyectos")
    private Object listarProyectos() {
		
		List<Proyecto> listaProyectos = interfazProyectos.readAll();
		return listaProyectos;
    }
	
	@RequestMapping("/getProyecto")
    private String getProyecto(@RequestParam(value="id", defaultValue="") String id) {
		Proyecto proyecto = interfazProyectos.readById(id);
		if (proyecto != null){
			String json = jsonDao.objToJson(proyecto);
			return json + getBackLink;
		}else {
			return "Cliente no encontrado" + getBackLink;
		}
    }
}
