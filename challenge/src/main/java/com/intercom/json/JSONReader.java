package com.intercom.json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JSONReader {

	public <T> List<T> read(String path, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		List<T> items = new ArrayList<T>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		try {
			String line = bufferedReader.readLine();
			while (line != null) {
				obtainItem(clazz, mapper, items, line);
				line = bufferedReader.readLine();
			}
			return items;
		} finally {
			bufferedReader.close();
		}
	}

	private <T> void obtainItem(Class<T> clazz, ObjectMapper mapper,
			List<T> items, String line) throws IOException, JsonParseException,
			JsonMappingException {
		T item = mapper.readValue(line, clazz);
		items.add(item);
	}
}
