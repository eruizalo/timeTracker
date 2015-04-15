package com.timetracker.main;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.model.Empleado;
import com.timetracker.model.ErrorDesc;
import com.timetracker.model.Perfil;
import com.timetracker.dao.JsonDAO;

@RestController
public class EmpleadosController {

	
	@RequestMapping(value = "/getEmpleados", method = RequestMethod.GET)
    private List<Empleado> listarEmpleados(Model model) {
		List<Empleado> listaEmpleados = TimeTrackerMain.interfazEmpleados.readAll();
		return listaEmpleados;
    }
	
	@RequestMapping(value = "/getEmpleado", method = RequestMethod.GET)
    private String getEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		Empleado empleado = TimeTrackerMain.interfazEmpleados.readById(id);
		/*if (empleado != null){
			String json = JsonDAO.objToJson(empleado);
			return json + getBackLink;
		}else {
			return "Empleado no encontrado" + getBackLink;
		}*/
		return JsonDAO.objToJson(empleado);
    }
	
	@RequestMapping(value = "/getNombrePerfil", method = RequestMethod.GET)
    private String getNombrePerfil(@RequestParam(value="id", defaultValue="") byte id) {
		return Perfil.getTarifaName(id);
    }
	
	@RequestMapping(value = "/getNumEmpleados", method = RequestMethod.GET)
    private long getNumEmpleados() {
		return TimeTrackerMain.interfazEmpleados.countEmpleados();
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    private String addEmpleados(@RequestParam(value="id", defaultValue="") String id) {
		Empleado nuevoEmpleado = new Empleado(id, "Anakin Skywalker", Perfil.padawanId, "anakin");
		ErrorDesc error = TimeTrackerMain.interfazEmpleados.create(nuevoEmpleado);
		
		/*if (error.getErrorCode()==0){
			return "New person added--> " + nuevoEmpleado + getBackLink;
		} else {
			error.getException().printStackTrace();
			return error.getErrorDesc() + getBackLink;
		}*/
		return JsonDAO.objToJson(error);
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
    private int deleteEmpleado(@RequestParam(value="id", defaultValue="") String id) {
		
		int error = TimeTrackerMain.interfazEmpleados.deleteById(id);
		
		/*if (error==1){
			return "Persona borrada" + getBackLink;
		} else {
			return "Error durante el borrado-->" + error + getBackLink;
		}*/
		return error;
    }
}
