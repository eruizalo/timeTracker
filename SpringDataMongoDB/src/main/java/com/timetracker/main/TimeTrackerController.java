package com.timetracker.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RestController
public class TimeTrackerController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    private String index(Model model) {
		return "index";
    }
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
    private String profile(ModelAndView model) {
		return "profile";
    }
	
	@RequestMapping(value = "/proyectos", method = RequestMethod.GET)
    private String proyectos(Model model) {
		return "proyectos";
    }
	
	@RequestMapping(value = "/proyecto", method = RequestMethod.GET)
    private String proyecto(@RequestParam(value="id", defaultValue="") String id, Model model) {
		if (!id.equals("")) {
			ProyectosController.proyectoSeleccionado = TimeTrackerMain.interfazProyectos.readById(id);
		}
		return "proyecto";
    }
	
	@RequestMapping(value = "empleados", method = RequestMethod.GET)
    private String empleados(Model model) {
		return "empleados";
    }
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    private String clientes(Model model) {
		return "clientes";
    }
	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
    private String cliente(@RequestParam(value="id", defaultValue="") String id, ModelMap model) {
		if (!id.equals("")) {
			ClientesController.clienteSeleccionado = TimeTrackerMain.interfazClientes.readById(id);
		}
		return "cliente";
    }
	
	@RequestMapping(value = "/tareasEnCurso", method = RequestMethod.GET)
    private String tareasActivas(Model model) {
		return "tareasEnCurso";
    }
	
	@RequestMapping(value = "/generarFactura", method = RequestMethod.GET)
    private String generarFactura(Model model) {
		return "generarFactura";
    }
	
	@RequestMapping(value = "/drop", method = RequestMethod.GET)
    private String dropMongo() {
		TimeTrackerMain.dropDBs();
		return "MongoDB droped";
    }
}
