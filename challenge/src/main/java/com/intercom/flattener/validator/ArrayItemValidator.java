package com.intercom.flattener.validator;

public class ArrayItemValidator {

	private static final String EXCEPTION_MESSAGE = "Array contains null Items";

	public void validate(Object item) {
		if (item == null) {
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}

	}

}
