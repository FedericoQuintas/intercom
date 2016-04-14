package com.intercom.customer.service;

import java.util.Collections;
import java.util.List;

import com.intercom.customer.domain.Customer;
import com.intercom.customer.finder.CustomerFinder;
import com.intercom.location.domain.Location;

public class FindCustomerServiceImpl implements FindCustomerService {

	private CustomerFinder customerFinder;

	public FindCustomerServiceImpl(CustomerFinder customerFinder) {
		this.customerFinder = customerFinder;
	}

	public List<Customer> retrieveSorted(List<Customer> customers,
			int distanceRequiredInKm, Location officeLocation) {
		List<Customer> customersWithinDistance = customerFinder.find(customers,
				distanceRequiredInKm, officeLocation);
		Collections.sort(customersWithinDistance);

		return customersWithinDistance;
	}

}
