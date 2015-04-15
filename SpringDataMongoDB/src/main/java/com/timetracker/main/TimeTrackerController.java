package com.timetracker.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.timetracker.dao.JsonDAO;

@Controller
//@RestController
public class TimeTrackerController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    private String index(Model model) {
		EmpleadosController.empleadoLogueado = JsonDAO.objToJson(TimeTrackerMain.interfazEmpleados.readById("1"));
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
	
	@RequestMapping(value = "empleados", method = RequestMethod.GET)
    private String empleados(Model model) {
		return "empleados";
    }
	
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
    private String clientes(Model model) {
		return "clientes";
    }
	
	@RequestMapping(value = "/tareasEnCurso", method = RequestMethod.GET)
    private String tareasActivas(Model model) {
		return "tareasEnCurso";
    }
	
	@RequestMapping(value = "/drop", method = RequestMethod.GET)
    private String dropMongo() {
		TimeTrackerMain.dropDBs();
		return "MongoDB droped";
    }
}
