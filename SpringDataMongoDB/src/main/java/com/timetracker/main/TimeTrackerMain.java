package com.timetracker.main;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.timetracker.dao.ClienteDAO;
import com.timetracker.dao.ClienteDAOImpl;
import com.timetracker.dao.EmpleadoDAO;
import com.timetracker.dao.EmpleadoDAOImpl;
import com.timetracker.dao.ProyectoDAO;
import com.timetracker.dao.ProyectoDAOImpl;
import com.timetracker.model.Cliente;
import com.timetracker.model.Empleado;
import com.timetracker.model.Perfil;
import com.timetracker.model.Proyecto;

@ComponentScan
@EnableAutoConfiguration
public class TimeTrackerMain {

	static final String DB_NAME = "TimeTracker";
	static final String COLECCION_EMPLEADOS = "Empleados";
	static final String COLECCION_CLIENTES = "Clientes";
	static final String COLECCION_PROYECTOS = "Proyectos";
	static final String MONGO_HOST = "localhost";
	static final int MONGO_PORT = 27017;
	
	static MongoClient mongo = null;
	static MongoOperations mongoOps = null;
	
	static EmpleadoDAO interfazEmpleados;
	static ClienteDAO interfazClientes;
	static ProyectoDAO interfazProyectos;
	
	public static void main(String[] args) {
		
		try {
			mongo = new MongoClient(
					MONGO_HOST, MONGO_PORT);
			mongoOps = new MongoTemplate(mongo, DB_NAME);
			interfazEmpleados = new EmpleadoDAOImpl(mongoOps);
			interfazClientes = new ClienteDAOImpl(mongoOps);
			interfazProyectos = new ProyectoDAOImpl(mongoOps);
			
			// POR DEFECTO VACIAMOS LA BBDD
			dropDBs();
			
			//AÑADIMOS POR DEFECTO
			Empleado empleado1 = new Empleado("1", "Mace Windu", Perfil.maestroJediId, "mace");
			Empleado empleado2 = new Empleado("2", "Qui-Gon Jinn", Perfil.jediId, "quigon");
			Empleado empleado3 = new Empleado("3", "Obi-Wan Kenobi", Perfil.padawanId, "obiwan");
			
			Cliente cliente1 = new Cliente("1", "Cliente1", "B00939173", "Calle falsa 1", empleado1.getId());
			Cliente cliente2 = new Cliente("2", "Cliente2", "A73964751", "Calle falsa 2", empleado1.getId());
			
			Proyecto proyecto1 = new Proyecto("1", "Proyecto1", empleado1.getId(), cliente1.getId(), empleado1.getId(),
					cliente1.getListaTarifasCliente(), empleado1.getId());
			proyecto1.getListaTareas().add("DYD");
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
		
		SpringApplication.run(TimeTrackerMain.class, args);
	}
	
	static void dropDBs() {
		mongoOps.dropCollection(COLECCION_EMPLEADOS);
		mongoOps.dropCollection(COLECCION_CLIENTES);
		mongoOps.dropCollection(COLECCION_PROYECTOS);
	}
}
