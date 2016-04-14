package com.intercom.customer.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.intercom.customer.domain.Customer;
import com.intercom.customer.finder.CustomerFinder;
import com.intercom.location.domain.Location;

public class FindCustomerServiceTest {

	private static final String JOHN = "John";
	private static final String TOM = "Tom";
	private static final String ANNA = "Anna";
	private static final String PAT = "Pat";

	@Test
	public void whenAsksForSortedCustomersThenReturnsASortedResult() {
		CustomerFinder customerFinderMock = mock(CustomerFinder.class);
		List<Customer> mockPeopleList = generateMockedList();

		int distanceRequiredInKm = 100;
		Location officeLocation = new Location(new Double(53.3381985),
				new Double(-6.2592576));
		when(
				customerFinderMock.find(mockPeopleList, distanceRequiredInKm,
						officeLocation)).thenReturn(mockPeopleList);

		FindCustomerService findCustomerService = new FindCustomerServiceImpl(
				customerFinderMock);
		List<Customer> sortedCustomers = findCustomerService.retrieveSorted(
				mockPeopleList, distanceRequiredInKm, officeLocation);

		Assert.assertEquals(TOM, sortedCustomers.get(0).getName());
		Assert.assertEquals(ANNA, sortedCustomers.get(1).getName());
		Assert.assertEquals(JOHN, sortedCustomers.get(2).getName());
		Assert.assertEquals(PAT, sortedCustomers.get(3).getName());
	}

	private List<Customer> generateMockedList() {
		List<Customer> mockPeopleList = new ArrayList<Customer>();
		mockPeopleList.add(new Customer(10L, ANNA));
		mockPeopleList.add(new Customer(2L, TOM));
		mockPeopleList.add(new Customer(30L, PAT));
		mockPeopleList.add(new Customer(21L, JOHN));
		return mockPeopleList;
	}
}
