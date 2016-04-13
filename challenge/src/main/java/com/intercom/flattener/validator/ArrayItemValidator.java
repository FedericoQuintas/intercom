package com.intercom.flattener.validator;

public class ArrayItemValidator {

	private static final String NULL_EXCEPTION_MESSAGE = "Array contains null Items";
	private static final String IS_NUMERIC_EXCEPTION_MESSAGE = "Array contains not numeric Items";

	public void validateNotNumeric(Object item) {
		boolean isNumeric = item instanceof Number;
		if (!isNumeric) {
			throw new IllegalArgumentException(IS_NUMERIC_EXCEPTION_MESSAGE);
		}

	}

	public void validateNull(Object item) {
		if (item == null) {
			throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
		}
	}

}
