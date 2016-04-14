package com.intercom.customer.service;

import java.util.List;

import com.intercom.customer.domain.Customer;
import com.intercom.location.domain.Location;

public interface FindCustomerService {

	public List<Customer> retrieveSorted(List<Customer> customers,
			int distanceRequiredInKm, Location officeLocation);

}
