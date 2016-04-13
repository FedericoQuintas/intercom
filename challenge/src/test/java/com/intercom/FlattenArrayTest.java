package com.intercom;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.intercom.flattener.ArrayFlattener;
import com.intercom.flattener.validator.ArrayItemValidator;

public class FlattenArrayTest {

	private static final String WORD = "Word";
	private ArrayFlattener arrayFlattener;
	private List<Integer> flattenArray;
	private List<Object> arrayToFlatten;

	@Before
	public void before() {
		arrayFlattener = new ArrayFlattener(new ArrayItemValidator());
		arrayToFlatten = new ArrayList<Object>();
		flattenArray = new ArrayList<Integer>();
	}

	@Test
	public void whenArrayIsEmptyThenTheResultIsAnEmptyArray() {
		List<Integer> flattenArray = new ArrayList<Integer>();
		arrayFlattener.flattenArray(arrayToFlatten, flattenArray);
		Assert.assertTrue(flattenArray.isEmpty());

	}

	@Test
	public void whenArrayHasAnItemThenTheResultIsAnArrayOfOneItem() {
		arrayToFlatten.add(1);
		flattenArray = arrayFlattener
				.flattenArray(arrayToFlatten, flattenArray);
		Assert.assertEquals(1, flattenArray.size());
	}

	@Test
	public void whenArrayHasAnItemThenTheResultIsAnArrayOfSameItem() {

		arrayToFlatten.add(1);
		flattenArray = arrayFlattener
				.flattenArray(arrayToFlatten, flattenArray);
		Assert.assertEquals(arrayToFlatten.get(0), flattenArray.get(0));
	}

	@Test
	public void whenArrayHasANullItemThenIllegalArgumentExceptionIsThrown() {

		String exceptionMessage = "Array contains null Items";
		arrayToFlatten.add(null);
		try {
			flattenArray = arrayFlattener.flattenArray(arrayToFlatten,
					flattenArray);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(exceptionMessage, exception.getMessage());
		}
	}

	@Test
	public void whenArrayHasANotNumericItemThenIllegalArgumentExceptionIsThrown() {

		String exceptionMessage = "Array contains not numeric Items";
		arrayToFlatten.add(WORD);
		try {
			flattenArray = arrayFlattener.flattenArray(arrayToFlatten,
					flattenArray);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(exceptionMessage, exception.getMessage());
		}
	}
	
	@Test
	public void whenArrayIsComposedByTwoArraysAndOneContainsNullThenIllegalArgumentExceptionIsThrown() {

		String exceptionMessage = "Array contains null Items";
		List<Integer> firstArray = new ArrayList<Integer>();
		firstArray.add(1);
		List<Integer> secondArray = new ArrayList<Integer>();
		secondArray.add(null);
		List<Object> arrayToFlatten = new ArrayList<Object>();
		arrayToFlatten.add(firstArray);
		arrayToFlatten.add(secondArray);

		try {
			flattenArray = arrayFlattener.flattenArray(arrayToFlatten,
					flattenArray);
		} catch (IllegalArgumentException exception) {
			Assert.assertEquals(exceptionMessage, exception.getMessage());
		}
	}

	@Test
	public void whenArrayIsComposedByTwoArraysThenFlattenArrayHasBothArraysFlattened() {

		List<Integer> firstArray = new ArrayList<Integer>();
		firstArray.add(1);
		List<Integer> secondArray = new ArrayList<Integer>();
		secondArray.add(2);
		List<Object> arrayToFlatten = new ArrayList<Object>();
		arrayToFlatten.add(firstArray);
		arrayToFlatten.add(secondArray);

		flattenArray = arrayFlattener
				.flattenArray(arrayToFlatten, flattenArray);

		Assert.assertEquals(2, flattenArray.size());
		Assert.assertEquals(firstArray.get(0), flattenArray.get(0));
		Assert.assertEquals(secondArray.get(0), flattenArray.get(1));

	}

	@Test
	public void whenArrayIsComposedByArraysOfArraysThenFlattenArrayHasAllTheArraysFlattened() {

		List<Integer> firstArray = new ArrayList<Integer>();
		firstArray.add(1);
		List<Integer> secondArray = new ArrayList<Integer>();
		secondArray.add(1);
		List<Integer> thirdArray = new ArrayList<Integer>();
		thirdArray.add(1);

		List<List<Integer>> firstComposedArray = new ArrayList<List<Integer>>();
		firstComposedArray.add(secondArray);
		firstComposedArray.add(thirdArray);
		List<List<Integer>> secondComposedArray = new ArrayList<List<Integer>>();
		secondComposedArray.add(firstArray);
		secondComposedArray.add(thirdArray);

		List<Object> arrayToFlatten = addItemsToArray(firstArray, secondArray,
				thirdArray, firstComposedArray, secondComposedArray);

		flattenArray = arrayFlattener
				.flattenArray(arrayToFlatten, flattenArray);

		Assert.assertEquals(7, flattenArray.size());
	}

	private List<Object> addItemsToArray(List<Integer> firstArray,
			List<Integer> secondArray, List<Integer> thirdArray,
			List<List<Integer>> firstComposedArray,
			List<List<Integer>> secondComposedArray) {
		List<Object> arrayToFlatten = new ArrayList<Object>();
		arrayToFlatten.add(firstArray);
		arrayToFlatten.add(secondArray);
		arrayToFlatten.add(thirdArray);
		arrayToFlatten.add(firstComposedArray);
		arrayToFlatten.add(secondComposedArray);
		return arrayToFlatten;
	}
}
