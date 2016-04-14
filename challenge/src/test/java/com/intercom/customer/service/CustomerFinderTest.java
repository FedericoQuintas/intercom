package com.intercom.customer.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.intercom.customer.domain.Customer;
import com.intercom.customer.finder.CustomerFinder;
import com.intercom.json.JSONReader;
import com.intercom.location.domain.Location;

public class CustomerFinderTest {
	private static final String INVALID_CUSTOMERS = "Invalid customers";
	private static final String INVALID_DISTANCE = "Invalid distance";
	private static final String INVALID_LOCATION = "Invalid location";
	private static JSONReader jsonReader;
	private static List<Customer> customers;
	private CustomerFinder customerFinder;
	private Location officeLocation;

	@BeforeClass
	public static void beforeClass() throws JsonParseException,
			JsonMappingException, IOException {

		jsonReader = new JSONReader();
		customers = jsonReader.read("src/test/resources/customers-test.txt",
				Customer.class);

	}

	@Before
	public void before() {
		customerFinder = new CustomerFinder();
		officeLocation = new Location(new Double(53.3381985), new Double(
				-6.2592576));
	}

	@Test
	public void whenAsksForCustomersWithinDistanceThenReturnsAResult() {
		int distanceRequiredInKm = 100;
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Assert.assertNotNull(customersWithinDistance);
	}

	@Test
	public void whenAsksForCustomersWithinZeroKMThenReturnsAnEmptyList() {
		int distanceRequiredInKm = 0;
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Assert.assertTrue(customersWithinDistance.isEmpty());
	}

	@Test
	public void whenAsksForCustomersWithinTwentyKMThenReturnsACustomer() {
		int distanceRequiredInKm = 20;
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Assert.assertEquals(1, customersWithinDistance.size());
	}

	@Test
	public void whenAsksForCustomersWithinAHundredKMThenReturns16Customers() {
		int distanceRequiredInKm = 100;
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Assert.assertEquals(16, customersWithinDistance.size());
	}

	@Test
	public void whenAsksForCustomersWithinNegativeDistanceThenReturnsAnEmptyList() {
		int distanceRequiredInKm = -10;
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Assert.assertTrue(customersWithinDistance.isEmpty());
	}

	@Test
	public void whenAsksForCustomersWithinNullDistanceThenExceptionIsThrown() {
		Integer distanceRequiredInKm = null;
		try {
			customerFinder
					.find(customers, distanceRequiredInKm, officeLocation);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(INVALID_DISTANCE, exception.getMessage());
		}
	}

	@Test
	public void whenLocationHasNullLongitudeThenExceptionIsThrown() {
		Integer distanceRequiredInKm = 10;
		try {
			officeLocation = new Location(null, new Double(-6.2592576));
			customerFinder
					.find(customers, distanceRequiredInKm, officeLocation);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(INVALID_LOCATION, exception.getMessage());
		}
	}

	@Test
	public void whenLocationHasNullLatitudeThenExceptionIsThrown() {
		Integer distanceRequiredInKm = 10;
		try {

			officeLocation = new Location(new Double(53.3381985), null);
			customerFinder
					.find(customers, distanceRequiredInKm, officeLocation);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(INVALID_LOCATION, exception.getMessage());
		}
	}

	@Test
	public void whenAsksForNullCustomersThenExceptionIsThrown() {
		Integer distanceRequiredInKm = 10;
		try {
			customerFinder.find(null, distanceRequiredInKm, officeLocation);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(INVALID_CUSTOMERS, exception.getMessage());
		}
	}

	@Test
	public void whenAsksForEmptyCustomersThenExceptionIsThrown() {
		Integer distanceRequiredInKm = 10;
		try {
			customerFinder.find(new ArrayList<Customer>(),
					distanceRequiredInKm, officeLocation);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(INVALID_CUSTOMERS, exception.getMessage());
		}
	}
}
