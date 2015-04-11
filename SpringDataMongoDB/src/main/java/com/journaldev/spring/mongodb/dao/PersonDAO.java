package com.journaldev.spring.mongodb.dao;

import java.util.List;

import com.journaldev.spring.mongodb.model.ErrorDesc;
import com.journaldev.spring.mongodb.model.Person;

public interface PersonDAO {

	public ErrorDesc create(Person p);
	
	public Person readById(String id);
	
	public ErrorDesc update(Person p);
	
	public int deleteById(String id);
	
	public List<Person> readAll();
}
