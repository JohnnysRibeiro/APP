package com.mds.app.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.ProjectModel;

public class ProjectModelTest extends AndroidTestCase {

	private ProjectModel projetoModel;
	private ParliamentaryModel parlamentarModel;

	@Before
	public void setUp() throws Exception {
		parlamentarModel = new ParliamentaryModel();
		projetoModel = new ProjectModel("2013", "NomeProjeto", "PL", "16/10/2013", "66", "ExplicacaoProjeto",
				parlamentarModel);
	}

	@After
	public void tearDown() throws Exception {
		parlamentarModel = null;
		projetoModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(projetoModel);
	}

	@Test
	public void testarNomeClasse() {
		Assert.assertEquals("ParlamentarModel", parlamentarModel.getClass().getSimpleName());
	}

	@Test
	public void testarNomeClasse2() {
		Assert.assertEquals("ProjetoModel", projetoModel.getClass().getSimpleName());
	}

	@Test
	public void testGetNome() {
		assertEquals("NomeProjeto", projetoModel.getName());
	}

	@Test
	public void testGetAno() {
		assertEquals("2013", projetoModel.getYear());
	}

	@Test
	public void testGetSigla() {
		assertEquals("PL", projetoModel.getKindOfProjectAcronym());
	}

	@Test
	public void testGetData() {
		assertEquals("16/10/2013", projetoModel.getDate());
	}

	@Test
	public void testGetNumero() {
		assertEquals("66", projetoModel.getNumber());
	}

	@Test
	public void testGetExplicacao() {
		assertEquals("ExplicacaoProjeto", projetoModel.getExplanation());
	}

	@Test
	public void testGetParlamentar() {
		/* ParlamentarModel outroParlamentar = new ParlamentarModel(); */
		assertEquals(parlamentarModel, projetoModel.getParliamentary());
	}

	@Test
	public void testGetContEqualsOne() {
		assertEquals(1, projetoModel.getCounter());
	}

	@Test
	public void testGetContMaiorQueUm() {
		projetoModel.setName("TesteCont");
		assertEquals(2, projetoModel.getCounter());
	}

	@Test
	public void testSetNome() {
		projetoModel.setName("SetNomeProjeto");
		assertEquals("SetNomeProjeto", projetoModel.getName());
	}

	@Test
	public void testSetAno() {
		projetoModel.setYear("2012");
		assertEquals("2012", projetoModel.getYear());
	}

	@Test
	public void testSetSigla() {
		projetoModel.setKindOfProjectAcronym("PDS");
		assertEquals("PDS", projetoModel.getKindOfProjectAcronym());
	}

	@Test
	public void testSetData() {
		projetoModel.setDate("15/10/2013");
		assertEquals("15/10/2013", projetoModel.getDate());
	}

	@Test
	public void testSetNumero() {
		projetoModel.setNumber("67");
		assertEquals("67", projetoModel.getNumber());
	}
	
	@Test
	public void testSetThenGetStatus(){
		String esperado = "statusteste";
		projetoModel.setStatus(esperado);
		assertEquals(esperado, projetoModel.getStatus());
	}

	@Test
	public void testSetExplicacao() {
		projetoModel.setExplanation("SetExplicacaoProjeto");
		assertEquals("SetExplicacaoProjeto", projetoModel.getExplanation());
	}

	@Test
	public void testSetParlamentar() {
		ParliamentaryModel outroParlamentar = new ParliamentaryModel();
		projetoModel.setParliamentary(outroParlamentar);
		assertEquals(outroParlamentar, projetoModel.getParliamentary());
	}

	@Test
	public void testToString() {
		ProjectModel outroProjetoModel = new ProjectModel("2013", "NomeProjeto", "PL", "16/10/2013", "66",
				"ExplicacaoProjeto", parlamentarModel);
		assertEquals(projetoModel.toString(), outroProjetoModel.toString());
	}
	
	@Test
	public void testSetThenGetId() {
		String esperado = "idset";
		projetoModel.setId(esperado);
		assertEquals(esperado, projetoModel.getId());

	}

	@Test
	public void testGetContId() {
		projetoModel.setId("qualquer coisa");
		assertEquals(1, projetoModel.getCounterId());
	}

	@Test
	public void testSetContId() {
		int esperado = 3;
		projetoModel.setCounterId(esperado);
		assertEquals(esperado, projetoModel.getCounterId());
	}

	@Test
	public void testSetCont() {
		int esperado = 5;
		projetoModel.setCounter(esperado);
		assertEquals(esperado, projetoModel.getCounter());
	}

	@Test
	public void testConstrutorVazioNaoNulo() {
		ProjectModel projetoVazio = new ProjectModel();
		assertNotNull(projetoVazio);
	}

	@Test
	public void testSetIdComContIdMaiorQueZero() {
		projetoModel.setCounterId(3);
		projetoModel.setId("IDZORDON");
		assertEquals(3, projetoModel.getCounterId());
	}

	@Test
	public void testSetNomeComContMaiorQueUm() {
		projetoModel.setCounter(5);
		projetoModel.setName("NOMEZORDON");
		assertEquals(6, projetoModel.getCounter());
	}	

}
