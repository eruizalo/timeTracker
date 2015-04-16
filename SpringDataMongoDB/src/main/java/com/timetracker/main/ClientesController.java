package com.timetracker.main;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.model.Cliente;

@RestController
public class ClientesController {
	
	static String clienteSeleccionado = "";

	@RequestMapping(value = "/getClientes", method = RequestMethod.GET)
    private List<Cliente> getClientes() {
		List<Cliente> listaClientes = TimeTrackerMain.interfazClientes.readAll();
		return listaClientes;
    }
	
	@RequestMapping(value = "/getCliente", method = RequestMethod.GET)
    private String getCliente(@RequestParam(value="id", defaultValue="") String id) {
		/*Cliente cliente = TimeTrackerMain.interfazClientes.readById(Integer.parseInt(id)+"");
		return JsonDAO.objToJson(cliente);*/
		return clienteSeleccionado;
    }
	
	@RequestMapping(value = "/getNumClientes", method = RequestMethod.GET)
    private long getNumClientes() {
		return TimeTrackerMain.interfazClientes.countClientes();
    }
}
