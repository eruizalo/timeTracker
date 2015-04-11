package com.journaldev.spring.mongodb.main;

import java.net.UnknownHostException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.mongodb.dao.PersonDAO;
import com.journaldev.spring.mongodb.dao.PersonDAOImpl;
import com.journaldev.spring.mongodb.model.Person;
import com.mongodb.MongoClient;

@RestController
@EnableAutoConfiguration
public class SpringDataMongoDBMain {

	public static final String DB_NAME = "journaldev";
	public static final String PERSON_COLLECTION = "Person";
	public static final String MONGO_HOST = "localhost";
	public static final int MONGO_PORT = 27017;
	
	private static MongoClient mongo = null;
	private static MongoOperations mongoOps = null;
	private static PersonDAO personInterface;

	public static void main(String[] args) {
		
		try {
			mongo = new MongoClient(
					MONGO_HOST, MONGO_PORT);
			mongoOps = new MongoTemplate(mongo, DB_NAME);
			personInterface = new PersonDAOImpl(mongoOps);
			
			Person p = new Person("113", "PankajKr", "Bangalore, India");
			

			Person p1 = mongoOps.findOne(
					new Query(Criteria.where("name").is("PankajKr")),
					Person.class, PERSON_COLLECTION);
			
			if (null == p1){
				mongoOps.insert(p, PERSON_COLLECTION);
				System.out.println(p);
			}
			
		} catch (UnknownHostException e) {
			mongoOps.dropCollection(PERSON_COLLECTION);
			mongo.close();
			e.printStackTrace();
		} catch (org.springframework.dao.DuplicateKeyException m) {
			System.out.println("DUPLICATE KEY EXCEPTION!!!!");
			mongoOps.dropCollection(PERSON_COLLECTION);
			mongo.close();
			System.out.println("MongoDB droped and closed");
			m.printStackTrace();
		}
		
		SpringApplication.run(SpringDataMongoDBMain.class, args);
	}
	
	@RequestMapping("/")
    private Object personList() {
		/*
		System.out.println("GET COLLECTION");
		Query query = new Query();
		List<Person> p = this.mongoOps.find(query, Person.class, PERSON_COLLECTION);
		*/
		List<Person> p = personInterface.readAll();
		if (p.isEmpty()){
			return "Empty collection";
		} else {
			return p;
		}
    }
	
	@RequestMapping("/add")
    private String addPerson(@RequestParam(value="id", defaultValue="") String id) {
		Person p = new Person(id, "Edu", "Ruiz, España");
		try {
			mongoOps.insert(p, PERSON_COLLECTION);
			System.out.println("New Person inserted:" + p);
	        return "New Person inserted:" + p;
		} catch (org.springframework.dao.DuplicateKeyException m) {
			// TODO: handle exception
			return "DUPLICATE KEY EXCEPTION!!!!";
		}
    }
	
	@RequestMapping("/drop")
    private String dropMongo() {
		mongoOps.dropCollection(PERSON_COLLECTION);
		return "MongoDB droped";
    }

}
