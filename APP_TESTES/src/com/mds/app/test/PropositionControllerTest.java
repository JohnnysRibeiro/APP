package com.mds.app.test;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import android.test.AndroidTestCase;

import com.mds.app.controller.PropositionController;
import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjectModel;

public class PropositionControllerTest extends AndroidTestCase {

	private PropositionController propositionController;

	@Before
	public void setUp() throws Exception {
		propositionController = new PropositionController();
	}

	@After
	public void tearDown() throws Exception {
		propositionController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(propositionController);
	}

	@Test
	public void testIfPropositionIsPercurringAllTheCharacters() {
		String inputStr = "abcteste";
		char input[] = inputStr.toCharArray();
		propositionController.characters(input, 0, 8);
		String retornado = new String(propositionController.getBuffer());
		assertEquals(inputStr, retornado);
	}

	@Test
	public void testStartMethodOfAPropositionGettingAParliamentary() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(propositionController.getParliamentary());
	}

	@Test
	public void testNameOfTheClass() {
		PropositionController p = new PropositionController();
		Assert.assertEquals("PropositionController", p.getClass().getSimpleName());
	}

	@Test
	public void testStartMethodOfAPropositionGettingAProject() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(propositionController.getProject());
	}

	@Test
	public void testStartMethodOfAPropositionGettingAPoliticalParty() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(propositionController.getPoliticalParty());
	}

	@Test
	public void testStartMethodOfAPropositionGettingAListOfProjects() {
		try {
			propositionController.startElement("a", "proposicoes", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(propositionController.getListOfProjects());
	}

	@Test
	public void testEndMethodGettingAProject() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.startElement("a", "proposicoes", "q", null);
			propositionController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(propositionController.getProject(), propositionController.getListOfProjects().get(0));
	}

	@Test
	public void testEndMethodGettingAParliamentaryFromAProject() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.startElement("a", "proposicoes", "q", null);
			propositionController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(propositionController.getParliamentary(), propositionController.getProject().getParliamentary());
	}

	@Test
	public void testEndMethodGettingAPoliticalPartyFromAProject() {
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.startElement("a", "proposicoes", "q", null);
			propositionController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(propositionController.getPoliticalParty(), propositionController.getParliamentary().getPoliticalParty());
	}

	@Test
	public void testEndMethodGettingAYearFromAProject() {
		String esperado = "testeano";
		int esperadoLength = esperado.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(esperado.toCharArray(), 0, esperadoLength);
			propositionController.endElement("a", "ano", "q");
		} catch (SAXException saxe) {
		}
		String retornado = propositionController.getProject().getYear();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndMethodGettingAnIdFromAProject() {
		String expectedReturn = "testeid";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "id", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getId();
		assertEquals(expectedReturn, actualReturn);
	}
	
	@Test
	public void testEndMethodGettingAStatusFromAProject() {
		String expectedReturn = "testestatus";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "descricao", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getStatus();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingANameFromAProject() {
		String expectedReturn = "testenomeproj";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "nome", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getName();
		System.out.println("Oiee " + actualReturn);
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingTheKindOfProjectAcronymFromAProject() {
		String expectedReturn = "testesigla";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "sigla", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getKindOfProjectAcronym();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingAnTheNumberFromAProject() {
		String expectedReturn = "testenumero";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "numero", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getNumber();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingTheDateFromAProject() {
		String expectedReturn = "testedataapresentacao";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "datApresentacao", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getDate();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingTheExplanationTextFromAProject() {
		String expectedReturn = "testeementa";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "txtEmenta", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getProject().getExplanation();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingTheParliamentaryNameOrAuthorFromAProject() {
		String expectedReturn = "testenomeautor";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "txtNomeAutor", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getParliamentary().getName();
		assertEquals(expectedReturn, actualReturn);
	}
	
	@Test
	public void testIfTheStartAndAndMethodsWorksWithDifferentNames() {
		try {
			propositionController.startElement("a", "localnamediferente1", "q", null);
			propositionController.endElement("a", "localnamediferente2", "q");
		} catch (SAXException saxe) {
		}
		assertTrue(true);
	}

	@Test
	public void testEndMethodGettingTheAcronymFromThePoliticalPartyNameFromAProject() {
		String expectedReturn = "testesiglapartido";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "txtSiglaPartido", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getPoliticalParty().getPoliticalPartyAcronym();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testEndMethodGettingTheStateAbbreviationFromAPoliticalParty() {
		String expectedReturn = "testeUF";
		int expectedReturnLength = expectedReturn.length();
		try {
			propositionController.startElement("a", "proposicao", "q", null);
			propositionController.characters(expectedReturn.toCharArray(), 0, expectedReturnLength);
			propositionController.endElement("a", "txtSiglaUF", "q");
		} catch (SAXException saxe) {
		}
		String actualReturn = propositionController.getPoliticalParty().getStateAbbreviation();
		assertEquals(expectedReturn, actualReturn);
	}

	/* ====================GETS E SETS=================== */

	@Test
	public void testGetBuffer() {
		StringBuffer bufferTest = new StringBuffer();
		assertEquals(bufferTest.toString(), propositionController.getBuffer().toString());
	}

	@Test
	public void testSetBuffer() {
		StringBuffer bufferTest = new StringBuffer();
		propositionController.setBuffer(bufferTest);
		assertSame(bufferTest, propositionController.getBuffer());
	}

	@Test
	public void testGetAProject() {
		ProjectModel project = new ProjectModel();
		propositionController.setProject(project);
		assertSame(project, propositionController.getProject());
	}

	@Test
	public void testGetAParliamentary() {
		ParliamentaryModel parliamentary = new ParliamentaryModel();
		propositionController.setParliamentary(parliamentary);
		assertSame(parliamentary, propositionController.getParliamentary());
	}

	@Test
	public void testGetAPoliticalParty() {
		PoliticalPartyModel politicalParty = new PoliticalPartyModel();
		propositionController.setPoliticalParty(politicalParty);
		assertSame(politicalParty, propositionController.getPoliticalParty());
	}

	@Test
	public void testGetAListOfProjects() {
		ArrayList<ProjectModel> listOfProjects = new ArrayList<ProjectModel>();
		propositionController.setListOfProjects(listOfProjects);
		assertSame(listOfProjects, propositionController.getListOfProjects());
	}


}
