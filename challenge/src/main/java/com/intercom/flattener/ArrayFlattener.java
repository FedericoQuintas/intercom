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
			validateNullItem(item);
			flattenItem(flattenedArray, item);
		}
		return flattenedArray;
	}

	private void flattenItem(List<Integer> flattenedArray, Object item) {
		if (item instanceof List<?>) {
			flattenArray((List<?>) item, flattenedArray);
		} else {
			validateNotNumericItem(item);
			flattenedArray.add(Integer.valueOf(item.toString()));
		}
	}

	private void validateNullItem(Object item) {
		arrayValidator.validateNull(item);
	}
	
	private void validateNotNumericItem(Object item) {
		arrayValidator.validateNotNumeric(item);
	}
}
