package com.timetracker.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonDAO {

	public String objToJson (Object obj) {
		
		String json = "";
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			json = ow.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
}
