package com.mds.app.test;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.exception.ValidateEntry;

public class ValidateEntryTest extends AndroidTestCase {

	public ValidateEntry entryToBeValidated;

	@Before
	public void setUp() throws Exception {
		entryToBeValidated = new ValidateEntry() {
		};
	}

	@After
	public void tearDown() throws Exception {
		entryToBeValidated = null;
	}

	@Test
	public void testValidationWithEmptyDate() {
		assertEquals(true, ValidateEntry.validateEntry(""));
	}

	@Test
	public void testValidationWithValidDate() {
		assertEquals(true, ValidateEntry.validateEntry("16/04/2013"));
	}

	@Test
	public void testValidationWithEmptyAuthor() {
		assertEquals(true, ValidateEntry.validateAuthor(""));
	}

	@Test
	public void testValidationWithValidAuthor() {
		assertEquals(true, ValidateEntry.validateAuthor("NomeDoAutor"));
	}

	@Test
	public void testValidationWithEmptyNumber() {
		assertEquals(true, ValidateEntry.validateNumber(""));
	}

	@Test
	public void testValidationWithValidNumber() {
		assertEquals(true, ValidateEntry.validateNumber("1234"));
	}

	@Test
	public void testValidationWithValidYear() {
		assertEquals(true, ValidateEntry.validateYear("2013"));
	}

	@Test
	public void testValidationWithValidAcronym() {
		assertEquals(true, ValidateEntry.validateAcronym("PL"));
	}

	@Test
	public void testValidationWithEmptyStateAbbreviation() {
		assertEquals(true, ValidateEntry.validateStateAbbreviation(""));
	}

	@Test
	public void testValidationWithValidStateAbbreviation() {
		assertEquals(true, ValidateEntry.validateStateAbbreviation("abc"));
	}

	@Test
	public void testValidationWithFewEntires() {
		boolean expectedReturn[] = { true, true, true, true, true, true, true };
		boolean actualReturn[] = ValidateEntry.validateEntries("asd1 d12e ", "asd 1d12e ", "asd 1d12e ",
				"asd 1d12e ", "asd1d 12e ", "asd 1d12e ", "asd 1d12e ");
		boolean resultOfTheComparisionOfBothArrays = Arrays.equals(expectedReturn, actualReturn);
		assertTrue(resultOfTheComparisionOfBothArrays);
	}

	@Test
	public void testThatVerifiesErrorsInsideValidation() {
		String expectedReturnWithAllTheFoundErrors = " Ano invalido  Sigla Invalida  Numero invalido  Data inicial invalida  Autor invalido  Partido invalido  UF invalida ";
		String actualReturnWithAllTheFoundErrors = ValidateEntry.discoverErrors("asd1 d12e ", "asd 1d12e ", "asd 1d12e ",
				"asd 1d12e ", "asd1d 12e ", "asd 1d12e ", "asd 1d12e ");
		assertEquals(expectedReturnWithAllTheFoundErrors, actualReturnWithAllTheFoundErrors);
	}

}