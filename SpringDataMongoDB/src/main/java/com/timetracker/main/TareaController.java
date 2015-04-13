package com.timetracker.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.dao.JsonDAO;
import com.timetracker.model.ErrorDesc;

@RestController
public class TareaController {

	
	@RequestMapping(value = "/addTarea", method = RequestMethod.GET)
    private String addTareaProyecto(@RequestParam(value="id") String idProyecto,
    		@RequestParam(value="idUser") String idEmpleado,
    		@RequestParam(value="tarea") String tarea) {
		
		ErrorDesc error = TimeTrackerMain.interfazProyectos.addTareaProyecto(idProyecto, idEmpleado, tarea);
		
		return JsonDAO.objToJson(error);
    }
	
	@RequestMapping(value = "/finishTarea", method = RequestMethod.GET)
    private String finishTareaProyecto(@RequestParam(value="id") String idEmpleado) {
		
		ErrorDesc error = TimeTrackerMain.interfazProyectos.finishTareaProyecto(idEmpleado);
		
		return JsonDAO.objToJson(error);
    }
}
