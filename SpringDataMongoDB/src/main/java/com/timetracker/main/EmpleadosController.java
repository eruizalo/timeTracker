package com.timetracker.main;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.model.Empleado;

@RestController
@RequestMapping(value = "/getEmpleados")
public class EmpleadosController {

	
	@RequestMapping(method = RequestMethod.GET)
    private List<Empleado> listarEmpleados(Model model) {
		List<Empleado> listaEmpleados = TimeTrackerMain.interfazEmpleados.readAll();
		return listaEmpleados;
    }
}
