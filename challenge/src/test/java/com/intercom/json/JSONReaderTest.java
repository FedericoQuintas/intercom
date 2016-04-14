package com.intercom.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.intercom.customer.domain.Customer;
import com.intercom.json.JSONReader;

public class JSONReaderTest {

	private static final String FILE_PATH = "src/test/resources/customers-test.txt";
	private static JSONReader jsonReader;
	private static List<Customer> customers;

	@BeforeClass
	public static void before() throws JsonParseException,
			JsonMappingException, IOException {

		jsonReader = new JSONReader();
		customers = jsonReader.read(FILE_PATH,
				Customer.class);

	}

	@Test
	public void whenCustomerIsReadFromJSONThenCustomerHasID() {

		Assert.assertNotNull(customers.get(0).getId());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenCustomerHasLatitude() {

		Assert.assertNotNull(customers.get(0).getLatitude());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenCustomerHasLongitude() {

		Assert.assertNotNull(customers.get(0).getLongitude());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenCustomerHasName() {

		Assert.assertNotNull(customers.get(0).getName());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenFirstCustomerHasCorrectName() {

		Assert.assertEquals("Christina McArdle", customers.get(0).getName());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenFirstCustomerHasCorrectID() {

		Assert.assertEquals(new Long(12), customers.get(0).getId());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenFirstCustomerHasCorrectLatitude() {

		Assert.assertEquals(new Double(52.986375), customers.get(0)
				.getLatitude());
	}

	@Test
	public void whenCustomerIsReadFromJSONThenFirstCustomerHasCorrectLongitude() {

		Assert.assertEquals(new Double(-6.043701), customers.get(0)
				.getLongitude());
	}

	@Test
	public void whenJSONIsReadThenImportsAllTheCustomers() {

		Assert.assertEquals(32, customers.size());
	}

}
