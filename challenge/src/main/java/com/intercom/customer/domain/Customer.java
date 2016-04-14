package com.intercom.customer.domain;

import org.codehaus.jackson.annotate.JsonProperty;

public class Customer implements Comparable<Customer> {

	@JsonProperty("user_id")
	private Long id;
	private Double latitude;
	private Double longitude;
	private String name;

	public Customer() {
		
	}
	
	public Customer(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return this.id;
	}

	public Double getLatitude() {
		return this.latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public String getName() {
		return this.name;
	}

	public int compareTo(Customer otherCustomer) {
		return (int) (this.getId() - otherCustomer.getId());
	}

}
