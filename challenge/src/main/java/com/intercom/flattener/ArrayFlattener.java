package com.intercom.flattener;

import java.util.List;

import com.intercom.flattener.validator.ArrayItemValidator;

public class ArrayFlattener {

	private ArrayItemValidator arrayValidator;

	public ArrayFlattener(ArrayItemValidator arrayValidator) {
		this.arrayValidator = arrayValidator;
	}

	public List<Integer> flattenArray(List<?> arrayToFlat,
			List<Integer> flattenedArray) {

		for (Object item : arrayToFlat) {
			validateItem(item);
			flatItem(flattenedArray, item);
		}
		return flattenedArray;
	}

	private void flatItem(List<Integer> flattenedArray, Object item) {
		if (item instanceof List<?>) {
			flattenArray((List<?>) item, flattenedArray);
		} else {
			flattenedArray.add(Integer.valueOf(item.toString()));
		}
	}

	private void validateItem(Object item) {
		arrayValidator.validate(item);
	}
}
