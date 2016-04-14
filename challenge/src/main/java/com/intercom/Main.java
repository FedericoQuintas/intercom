package com.intercom;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.intercom.customer.domain.Customer;
import com.intercom.customer.finder.CustomerFinder;
import com.intercom.customer.service.FindCustomerServiceImpl;
import com.intercom.json.JSONReader;
import com.intercom.location.domain.Location;

public class Main {

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {

		JSONReader jsonReader = new JSONReader();

		String path = args[0];

		List<Customer> customers = jsonReader.read(path, Customer.class);

		List<Customer> customersWithinDistance = obtainCustomersWithinDistance(
				args, customers);

		printResults(customersWithinDistance);
	}

	private static List<Customer> obtainCustomersWithinDistance(String[] args,
			List<Customer> customers) {

		CustomerFinder customerFinder = new CustomerFinder();
		String longitude = args[1];
		String latitude = args[2];
		Location officeLocation = new Location(new Double(longitude),
				new Double(latitude));

		return new FindCustomerServiceImpl(customerFinder).retrieveSorted(
				customers, 100, officeLocation);
	}

	private static void printResults(List<Customer> customersWithinDistance) {
		for (Customer customer : customersWithinDistance) {
			StringBuilder stringBuilder = new StringBuilder()
					.append(customer.getName()).append(" ")
					.append(customer.getId());
			System.out.println(stringBuilder.toString());
		}
	}

}
