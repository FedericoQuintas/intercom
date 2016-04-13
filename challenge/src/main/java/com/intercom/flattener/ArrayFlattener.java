package com.intercom.flattener;

import java.util.List;

public class ArrayFlattener {

	private static final String EXCEPTION_MESSAGE = "Array contains null elements";

	public List<Integer> flattenArray(List<?> arrayToFlat, List<Integer> flattenedArray) {
		for (Object item : arrayToFlat) {
			validateItem(item);
			if (item instanceof List<?>) {
				flattenArray((List<?>) item, flattenedArray);
			} else {
				flattenedArray.add(Integer.valueOf(item.toString()));
			}
		}
		return flattenedArray;
	}

	private void validateItem(Object item) {
		if(item == null){
			throw new IllegalArgumentException(EXCEPTION_MESSAGE);
		}
	}
}
