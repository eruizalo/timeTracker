package com.timetracker.main;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.dao.JsonDAO;
import com.timetracker.model.Proyecto;

@RestController
public class ProyectosController {

	
	@RequestMapping(value = "/getProyectos", method = RequestMethod.GET)
    private List<Proyecto> listarProyectos() {
		
		List<Proyecto> listaProyectos = TimeTrackerMain.interfazProyectos.readAll();
		return listaProyectos;
    }
	
	@RequestMapping(value = "/getProyecto", method = RequestMethod.GET)
    private String getProyecto(@RequestParam(value="id", defaultValue="") String id) {
		Proyecto proyecto = TimeTrackerMain.interfazProyectos.readById(Integer.parseInt(id)+"");
		/*if (proyecto != null){
			String json = jsonDao.objToJson(proyecto);
			return json + getBackLink;
		}else {
			return "Proyecto no encontrado" + getBackLink;
		}*/
		return JsonDAO.objToJson(proyecto);
    }
	
	@RequestMapping(value = "/getNombreProyecto", method = RequestMethod.GET)
    private String getNombreProyecto(@RequestParam(value="id", defaultValue="") String id) {
		Proyecto proyecto = TimeTrackerMain.interfazProyectos.readById(id);
		return proyecto.getNombre();
    }
	
	@RequestMapping(value = "/getNumProyectos", method = RequestMethod.GET)
    private long getNumProyectos() {
		return TimeTrackerMain.interfazProyectos.countProyectos();
    }

	@RequestMapping(value = "/getListaTareas", method = RequestMethod.GET)
    private List<String> getListaTareas(@RequestParam(value="id", defaultValue="") String id) {
		return TimeTrackerMain.interfazProyectos.readById(id).getListaTareas();
    }

	@RequestMapping(value = "/getImputaciones", method = RequestMethod.GET)
    private String getListaImputaciones(@RequestParam(value="id", defaultValue="") String id) {
		return JsonDAO.objToJson(TimeTrackerMain.interfazProyectos.readById(id).getListaHorasImputadas());
    }
}
