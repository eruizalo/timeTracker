package com.journaldev.spring.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.journaldev.spring.mongodb.model.Person;
import com.mongodb.WriteResult;

public class PersonDAOImpl implements PersonDAO {

	private MongoOperations mongoOps;
	private static final String PERSON_COLLECTION = "Person";
	
	public PersonDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	//@Override
	public String create(Person p) {
		//this.mongoOps.insert(p, PERSON_COLLECTION);
		try {
			this.mongoOps.insert(p, PERSON_COLLECTION);
			System.out.println("New Person inserted:" + p);
	        return "New Person inserted:" + p;
		} catch (org.springframework.dao.DuplicateKeyException m) {
			// TODO: handle exception
			return "DUPLICATE KEY EXCEPTION!!!!";
		}
	}

	//@Override
	public Person readById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, Person.class, PERSON_COLLECTION);
	}

	//@Override
	public void update(Person p) {
		this.mongoOps.save(p, PERSON_COLLECTION);
	}

	//@Override
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, Person.class, PERSON_COLLECTION);
		return result.getN();
	}
	
	public List<Person> readAll() {
		System.out.println("GET COLLECTION");
		Query query = new Query();
		return this.mongoOps.find(query, Person.class, PERSON_COLLECTION);
	}

}
