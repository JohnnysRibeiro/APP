package com.mds.app.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.ProjectModel;

public class ProjectModelTest extends AndroidTestCase {

	private ProjectModel projectModel;
	private ParliamentaryModel parliamentaryModel;

	@Before
	public void setUp() throws Exception {
		parliamentaryModel = new ParliamentaryModel();
		projectModel = new ProjectModel("2013", "NomeProjeto", "PL", "16/10/2013", "66", "ExplicacaoProjeto",
				parliamentaryModel);
	}

	@After
	public void tearDown() throws Exception {
		parliamentaryModel = null;
		projectModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(projectModel);
	}

	@Test
	public void testParliamentaryModelNameOfClass() {
		Assert.assertEquals("ParliamentaryModel", parliamentaryModel.getClass().getSimpleName());
	}

	@Test
	public void testProjectModelNameOfClass() {
		Assert.assertEquals("ProjectModel", projectModel.getClass().getSimpleName());
	}

	@Test
	public void testGetNameOfProject() {
		assertEquals("NomeProjeto", projectModel.getName());
	}

	@Test
	public void testGetYearOfTheProject() {
		assertEquals("2013", projectModel.getYear());
	}

	@Test
	public void testGetKindOfProjectAcronym() {
		assertEquals("PL", projectModel.getKindOfProjectAcronym());
	}

	@Test
	public void testGetDateFromAProject() {
		assertEquals("16/10/2013", projectModel.getDate());
	}

	@Test
	public void testGetNumberOfAProject() {
		assertEquals("66", projectModel.getNumber());
	}

	@Test
	public void testGetAnExplanationFromAProject() {
		assertEquals("ExplicacaoProjeto", projectModel.getExplanation());
	}

	@Test
	public void testGetTheParliamentaryFromAProject() {
		/* ParlamentarModel outroParlamentar = new ParlamentarModel(); */
		assertEquals(parliamentaryModel, projectModel.getParliamentary());
	}

	@Test
	public void testGetTheCounterFromAProjectFirstCase() {
		assertEquals(1, projectModel.getCounter());
	}

	@Test
	public void testGetTheCounterFromAProjectSecondCase() {
		projectModel.setName("TesteCont");
		assertEquals(2, projectModel.getCounter());
	}

	@Test
	public void testSetNameForAProject() {
		projectModel.setName("SetNomeProjeto");
		assertEquals("SetNomeProjeto", projectModel.getName());
	}

	@Test
	public void testSetYearForAProject() {
		projectModel.setYear("2012");
		assertEquals("2012", projectModel.getYear());
	}

	@Test
	public void testSetAnAcronymOfAKindOfProjectForAProject() {
		projectModel.setKindOfProjectAcronym("PDS");
		assertEquals("PDS", projectModel.getKindOfProjectAcronym());
	}

	@Test
	public void testSetADataForAProject() {
		projectModel.setDate("15/10/2013");
		assertEquals("15/10/2013", projectModel.getDate());
	}

	@Test
	public void testSetANumberForAProject() {
		projectModel.setNumber("67");
		assertEquals("67", projectModel.getNumber());
	}
	
	@Test
	public void testSetAStatusForAProject(){
		String esperado = "statusteste";
		projectModel.setStatus(esperado);
		assertEquals(esperado, projectModel.getStatus());
	}

	@Test
	public void testSetAnExplanationAProject() {
		projectModel.setExplanation("SetExplicacaoProjeto");
		assertEquals("SetExplicacaoProjeto", projectModel.getExplanation());
	}

	@Test
	public void testSetAParliamentaryForAProject() {
		ParliamentaryModel outroParlamentar = new ParliamentaryModel();
		projectModel.setParliamentary(outroParlamentar);
		assertEquals(outroParlamentar, projectModel.getParliamentary());
	}

	@Test
	public void testGetAProjectAsAString() {
		ProjectModel outroProjetoModel = new ProjectModel("2013", "NomeProjeto", "PL", "16/10/2013", "66",
				"ExplicacaoProjeto", parliamentaryModel);
		assertEquals(projectModel.toString(), outroProjetoModel.toString());
	}
	
	@Test
	public void testSetAnIdForAProject() {
		String esperado = "idset";
		projectModel.setId(esperado);
		assertEquals(esperado, projectModel.getId());

	}

	@Test
	public void testGetACounterIdFromAProject() {
		projectModel.setId("qualquer coisa");
		assertEquals(1, projectModel.getCounterId());
	}

	@Test
	public void testSetACounterIdForAProject() {
		int esperado = 3;
		projectModel.setCounterId(esperado);
		assertEquals(esperado, projectModel.getCounterId());
	}

	@Test
	public void testSetACounterForAProject() {
		int esperado = 5;
		projectModel.setCounter(esperado);
		assertEquals(esperado, projectModel.getCounter());
	}

	@Test
	public void testCreateAProjectWithAnEmptyConstructor() {
		ProjectModel projetoVazio = new ProjectModel();
		assertNotNull(projetoVazio);
	}

	@Test
	public void testSetAnIdWhenTheCounterIsBiggerThanZero() {
		projectModel.setCounterId(3);
		projectModel.setId("IDZORDON");
		assertEquals(3, projectModel.getCounterId());
	}

	@Test
	public void testSetANameWithAnCounterBiggerThan1() {
		projectModel.setCounter(5);
		projectModel.setName("NOMEZORDON");
		assertEquals(6, projectModel.getCounter());
	}	

}
