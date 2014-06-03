package com.mds.app.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.HistoryController;
import com.mds.app.controller.ListController;
import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjectModel;

public class HistoryControllerTest extends AndroidTestCase {

	public HistoryController historyController;
	public ArrayList<String> arrayOfStringsOfProjects;
	public ArrayList<ProjectModel> projects;
	public ProjectModel projectModel;
	public ParliamentaryModel parliamentaryModel;
	public PoliticalPartyModel politicalPartyModel;

	@Before
	public void setUp() throws Exception {
		arrayOfStringsOfProjects = new ArrayList<String>();
		projects = new ArrayList<ProjectModel>();
		politicalPartyModel = new PoliticalPartyModel("PMDS", "AC");
		parliamentaryModel = new ParliamentaryModel("Ranger", politicalPartyModel);
		projectModel = new ProjectModel("2013", "Zordon", "PL", "12/01/2013", "6663", "explicacao marota",
				parliamentaryModel);
		projects.add(projectModel);

		historyController = new HistoryController();
		HistoryController.setHistoryOfProjects(projects);
		HistoryController.setHistoryOfProjectsCompleteString(new ArrayList<String>());
	}

	@After
	public void tearDown() throws Exception {
		historyController = null;
		arrayOfStringsOfProjects = null;
		projects = null;
		projectModel = null;
		parliamentaryModel = null;
		politicalPartyModel = null;
		HistoryController.setHistoryOfProjects(null);
		HistoryController.setHistoryOfProjectsCompleteString(null);
	}

	@Test
	public void testInstance() {
		assertNotNull(historyController);
	}

	@Test
	public void testProjectsIntoString() {
		historyController.populateListWithProjects();
		String expectedReturn = "Zordon\nNumero: 6663\nAno: 2013\nSigla: PL\nData de Apresentacao: 12/01/2013\nDescricao: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		String actualReturn = historyController.transformProjectsIntoString();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testPopulateAListWithProjects() {
		HistoryController.setHistoryOfProjects(projects);
		historyController.populateListWithProjects();
		String expectedReturn = "[Zordon\nNumero: 6663\nAno: 2013\nSigla: PL\nData de Apresentacao: 12/01/2013\nDescricao: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC]";
		String actualReturn = HistoryController.getHistoryOfProjectsCompleteString().toString();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetHistoryOfProjectsAsAnArrayOfString() {
		ArrayList<String> arrayForTestingPurposes = new ArrayList<String>();
		arrayForTestingPurposes.add("TESTANDO PROJETO");
		HistoryController.setHistoryOfProjectsCompleteString(arrayForTestingPurposes);
		assertSame(arrayForTestingPurposes, HistoryController.getHistoryOfProjectsCompleteString());
	}

	@Test
	public void testGetHistoryOfProjects() {
		HistoryController.setHistoryOfProjects(projects);
		assertSame(projects, HistoryController.getHistoryOfProjects());
	}

	@Test
	public void testNameOfTheClass() {
		String expectedNameReturn = "HistoryController";
		String actualNameReturn = historyController.getClass().getSimpleName();
		assertEquals(expectedNameReturn, actualNameReturn);
	}

	@Test
	public void testMaximumNumberOfProjects() {
		int expectedMaximumNumberOfProjects = 10;
		int actualMaximumNumberOfProjects = HistoryController.getMaxNumberOfProjects();
		assertEquals(expectedMaximumNumberOfProjects, actualMaximumNumberOfProjects);
	}

	@Test
	public void testGetNumberOfProjetsInHistory() {
		int expectedNumberOfProjectsInHistory = 1; // no caso do teste, pois so tem um projeto
		int actualNumberOfProjectsInHistory = HistoryController.getNumberOfProjectsIntoHistory();
		assertEquals(expectedNumberOfProjectsInHistory, actualNumberOfProjectsInHistory);
	}

	@Test
	public void testGetTheOldestProject() {
		ProjectModel oldestProjectReturn = HistoryController.getOldestProject();
		assertEquals(projectModel, oldestProjectReturn);
	}
	
//	@Test(expected=NullPointerException.class)
//	public void testGetProjetoMaisVelhoNull() throws NullPointerException{
//		HistoryController.setHistoryOfProjects(null);
//		ProjectModel retornado = HistoryController.getOldestProject();
//		System.out.println("Oiee " + retornado);
//	}
	
	@Test
	public void testPopulateAHistoryOfProject() {
		ListController listController = new ListController();
		ListController.setActualProject(projectModel);
		String inputString = listController.getCompleteStringForAFile();
		historyController.populateProjects(inputString);
		String expectedReturn = HistoryController.getHistoryOfProjects().get(0).toString();
		String actualReturn = projectModel.toString();

		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetTheOldestProjectAsAString() {
		String expectedReturn = "teste";
		HistoryController.getHistoryOfProjectsCompleteString().add(expectedReturn);
		String actualReturn = HistoryController.getOldestProjectAsString();
		assertEquals(expectedReturn, actualReturn);
	}

//	@Test(expected = IndexOutOfBoundsException.class)
//	public void testGetStringProjetoMaisVelhoNull() {
//		String retornado = HistoryController.getOldestProjectAsString();
//		fail("teste falhou");
//	}

}
