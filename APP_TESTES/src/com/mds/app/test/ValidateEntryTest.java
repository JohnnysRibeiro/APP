package com.mds.app.test;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.exception.ValidateEntry;

public class ValidateEntryTest extends AndroidTestCase {

	public ValidateEntry validaEntradaTeste;

	@Before
	public void setUp() throws Exception {
		validaEntradaTeste = new ValidateEntry() {
		};
	}

	@After
	public void tearDown() throws Exception {
		validaEntradaTeste = null;
	}

	@Test
	public void testValidaDataVazia() {
		assertEquals(true, ValidateEntry.validateEntry(""));
	}

	@Test
	public void testValidaData() {
		assertEquals(true, ValidateEntry.validateEntry("16/04/2013"));
	}

	@Test
	public void testValidaAutorVazio() {
		assertEquals(true, ValidateEntry.validateAuthor(""));
	}

	@Test
	public void testValidaAutor() {
		assertEquals(true, ValidateEntry.validateAuthor("NomeDoAutor"));
	}

	@Test
	public void testValidaNumeroVazio() {
		assertEquals(true, ValidateEntry.validateNumber(""));
	}

	@Test
	public void testValidaNumero() {
		assertEquals(true, ValidateEntry.validateNumber("1234"));
	}

	@Test
	public void testValidaAno() {
		assertEquals(true, ValidateEntry.validateYear("2013"));
	}

	@Test
	public void testValidaSigla() {
		assertEquals(true, ValidateEntry.validateAcronym("PL"));
	}

	@Test
	public void testValidaUfVazia() {
		assertEquals(true, ValidateEntry.validateStateAbbreviation(""));
	}

	@Test
	public void testValidaUf() {
		assertEquals(true, ValidateEntry.validateStateAbbreviation("abc"));
	}

	@Test
	public void testValidandoEntradas() {
		boolean resultadoEsperado[] = { true, true, true, true, true, true, true };
		boolean retornoDoMetodo[] = ValidateEntry.validateEntries("asd1 d12e ", "asd 1d12e ", "asd 1d12e ",
				"asd 1d12e ", "asd1d 12e ", "asd 1d12e ", "asd 1d12e ");
		boolean teste = Arrays.equals(resultadoEsperado, retornoDoMetodo);
		assertTrue(teste);
	}

	@Test
	public void testIdentificarErros() {
		String todosErradosEsperado = " Ano invalido  Sigla Invalida  Numero invalido  Data inicial invalida  Autor invalido  Partido invalido  UF invalida ";
		String retornoDoMetodo = ValidateEntry.discoverErrors("asd1 d12e ", "asd 1d12e ", "asd 1d12e ",
				"asd 1d12e ", "asd1d 12e ", "asd 1d12e ", "asd 1d12e ");
		assertEquals(todosErradosEsperado, retornoDoMetodo);
	}

}