package com.intercom.customer.finder;

import java.util.ArrayList;
import java.util.List;

import com.intercom.customer.domain.Customer;
import com.intercom.location.domain.Location;

public class CustomerFinder {

	private static final String INVALID_CUSTOMERS = "Invalid customers";
	private static final String INVALID_DISTANCE = "Invalid distance";
	private static final String INVALID_LOCATION = "Invalid location";
	private double earthRadius = 6371.0; // earths mean radius in km

	public List<Customer> find(List<Customer> customers, Integer distance,
			Location location) {
		validations(customers, distance, location);

		List<Customer> customersWithinDistance = new ArrayList<Customer>();
		for (Customer customer : customers) {
			evaluateCustomerDistance(distance, location,
					customersWithinDistance, customer);
		}
		
		return customersWithinDistance;
	}

	private void validations(List<Customer> customers, Integer distance,
			Location location) {
		validateCustomers(customers);
		validateDistance(distance);
		validateLocation(location);
	}

	private void validateLocation(Location location) {
		if (location.getLatitude() == null || location.getLongitude() == null) {
			throw new IllegalArgumentException(INVALID_LOCATION);
		}
	}

	private void validateDistance(Integer distance) {
		if (distance == null) {
			throw new IllegalArgumentException(INVALID_DISTANCE);
		}
	}

	private void validateCustomers(List<Customer> customers) {
		if (customers == null || customers.isEmpty()) {
			throw new IllegalArgumentException(INVALID_CUSTOMERS);
		}
	}

	private void evaluateCustomerDistance(int distance, Location location,
			List<Customer> customersWithinDistance, Customer customer) {
		float distanceFromLocation = calculateDistanceFromLocation(customer,
				location);
		if (distanceFromLocation <= distance) {
			customersWithinDistance.add(customer);
		}
	}

	private float calculateDistanceFromLocation(Customer customer,
			Location location) {

		double latitudeDistance = Math.toRadians(customer.getLatitude()
				- location.getLatitude());
		double longitudeDistance = Math.toRadians(customer.getLongitude()
				- location.getLongitude());
		double angle = Math.sin(latitudeDistance / 2)
				* Math.sin(latitudeDistance / 2)
				+ Math.cos(Math.toRadians(location.getLatitude()))
				* Math.cos(Math.toRadians(customer.getLatitude()))
				* Math.sin(longitudeDistance / 2)
				* Math.sin(longitudeDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle));
		return (float) (earthRadius * c);

	}
}
