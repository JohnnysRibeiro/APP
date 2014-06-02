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

	private PropositionController proposicaoController;

	@Before
	public void setUp() throws Exception {
		proposicaoController = new PropositionController();
	}

	@After
	public void tearDown() throws Exception {
		proposicaoController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(proposicaoController);
	}

	@Test
	public void testCharacters() {
		String inputStr = "abcteste";
		char input[] = inputStr.toCharArray();
		proposicaoController.characters(input, 0, 8);
		String retornado = new String(proposicaoController.getBuffer());
		assertEquals(inputStr, retornado);
	}

	@Test
	public void testStartElementProposicaoParlamentar() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(proposicaoController.getParliamentary());
	}

	@Test
	public void testarNomeDaClasse() {
		PropositionController p = new PropositionController();
		Assert.assertEquals("ProposicaoController", p.getClass().getSimpleName());
	}

	@Test
	public void testStartElementProposicaoProjeto() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(proposicaoController.getProject());
	}

	@Test
	public void testStartElementProposicaoPartido() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(proposicaoController.getPoliticalParty());
	}

	@Test
	public void testStartElementProposicoes() {
		try {
			proposicaoController.startElement("a", "proposicoes", "q", null);
		} catch (SAXException saxe) {

		}
		assertNotNull(proposicaoController.getListOfProjects());
	}

	@Test
	public void testEndElementProposicaoProjeto() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.startElement("a", "proposicoes", "q", null);
			proposicaoController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(proposicaoController.getProject(), proposicaoController.getListOfProjects().get(0));
	}

	@Test
	public void testEndElementProposicaoParlamentar() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.startElement("a", "proposicoes", "q", null);
			proposicaoController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(proposicaoController.getParliamentary(), proposicaoController.getProject().getParliamentary());
	}

	@Test
	public void testEndElementProposicaoPartido() {
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.startElement("a", "proposicoes", "q", null);
			proposicaoController.endElement("a", "proposicao", "q");
		} catch (SAXException saxe) {

		}

		assertEquals(proposicaoController.getPoliticalParty(), proposicaoController.getParliamentary().getPoliticalParty());
	}

	@Test
	public void testEndElementProposicaoAno() {
		String esperado = "testeano";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "ano", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getYear();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoId() {
		String esperado = "testeid";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "id", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getId();
		assertEquals(esperado, retornado);
	}
	
	@Test
	public void testEndElementProposicaoStatus() {
		String esperado = "testestatus";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "descricao", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getStatus();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoNome() {
		String esperado = "testenomeproj";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "nome", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getName();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoSigla() {
		String esperado = "testesigla";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "sigla", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getKindOfProjectAcronym();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoNumero() {
		String esperado = "testenumero";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "numero", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getNumber();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoDataApresentacao() {
		String esperado = "testedataapresentacao";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "datApresentacao", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getDate();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoTextoEmenta() {
		String esperado = "testeementa";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "txtEmenta", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getProject().getExplanation();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoTextoNomeAutor() {
		String esperado = "testenomeautor";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "txtNomeAutor", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getParliamentary().getName();
		assertEquals(esperado, retornado);
	}
	
	@Test
	public void testEndElementProposicaoDiferente() {
		try {
			proposicaoController.startElement("a", "localnamediferente1", "q", null);
			proposicaoController.endElement("a", "localnamediferente2", "q");
		} catch (SAXException saxe) {
		}
		/* Testando os ramo elses, que nao fazem nada */
		assertTrue(true);
	}

	@Test
	public void testEndElementProposicaoTextoSiglaPartido() {
		String esperado = "testesiglapartido";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "txtSiglaPartido", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getPoliticalParty().getPoliticalPartyAcronym();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testEndElementProposicaoTextoSiglaUf() {
		String esperado = "testeUF";
		int esperadoLength = esperado.length();
		try {
			proposicaoController.startElement("a", "proposicao", "q", null);
			proposicaoController.characters(esperado.toCharArray(), 0, esperadoLength);
			proposicaoController.endElement("a", "txtSiglaUF", "q");
		} catch (SAXException saxe) {
		}
		String retornado = proposicaoController.getPoliticalParty().getStateAbbreviation();
		assertEquals(esperado, retornado);
	}

	/* ====================GETS E SETS=================== */

	@Test
	public void testGetBuffer() {
		StringBuffer bufferTest = new StringBuffer();
		assertEquals(bufferTest.toString(), proposicaoController.getBuffer().toString());
	}

	@Test
	public void testSetBuffer() {
		StringBuffer bufferTest = new StringBuffer();
		proposicaoController.setBuffer(bufferTest);
		assertSame(bufferTest, proposicaoController.getBuffer());
	}

	@Test
	public void testSetThenGetProjeto() {
		ProjectModel projeto = new ProjectModel();
		proposicaoController.setProject(projeto);
		assertSame(projeto, proposicaoController.getProject());
	}

	@Test
	public void testSetThenGetParlamentar() {
		ParliamentaryModel parlamentar = new ParliamentaryModel();
		proposicaoController.setParliamentary(parlamentar);
		assertSame(parlamentar, proposicaoController.getParliamentary());
	}

	@Test
	public void testSetThenGetParitdo() {
		PoliticalPartyModel partido = new PoliticalPartyModel();
		proposicaoController.setPoliticalParty(partido);
		assertSame(partido, proposicaoController.getPoliticalParty());
	}

	@Test
	public void testSetThenGetListaProjetos() {
		ArrayList<ProjectModel> lista = new ArrayList<ProjectModel>();
		proposicaoController.setListOfProjects(lista);
		assertSame(lista, proposicaoController.getListOfProjects());
	}


}
