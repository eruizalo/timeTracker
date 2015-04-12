package com.timetracker.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.timetracker.dao.EmpleadoDAO;
import com.timetracker.model.Empleado;
import com.timetracker.model.Perfil;

public class SpringMongoDBXMLMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		EmpleadoDAO personDAO = ctx.getBean("personDAO", EmpleadoDAO.class);
		
		Empleado p = new Empleado("1", "Mace Windu", Perfil.maestroJediId, "mace");
		
		//create
		personDAO.create(p);
		System.out.println("Generated ID="+p.getId());
		
		//read
		Empleado p1 = personDAO.readById(p.getId());
		System.out.println("Retrieved Person="+p1);
		
		//update
		p1.setName("David");p1.setName("mace2");
		personDAO.update(p1);
		Empleado temp = personDAO.readById(p1.getId());
		System.out.println("Retrieved Person after update="+temp);
		
		//delete
		int count = personDAO.deleteById(p1.getId());
		System.out.println("Number of records deleted="+count);
		
		ctx.close();

	}

}
