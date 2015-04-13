package com.timetracker.main;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.timetracker.model.Cliente;
import com.timetracker.dao.JsonDAO;

@RestController
public class ClientesController {

	@RequestMapping(value = "/getClientes", method = RequestMethod.GET)
    private List<Cliente> listarClientes() {
		
		List<Cliente> listaClientes = TimeTrackerMain.interfazClientes.readAll();
		return listaClientes;
    }
	
	@RequestMapping(value = "/getCliente", method = RequestMethod.GET)
    private String getCliente(@RequestParam(value="id", defaultValue="") String id) {
		Cliente cliente = TimeTrackerMain.interfazClientes.readById(id);
		/*if (cliente != null){
			String json = JsonDAO.objToJson(cliente);
			return json + getBackLink;
		}else {
			return "Cliente no encontrado" + getBackLink;
		}*/
		return JsonDAO.objToJson(cliente);
    }
}
