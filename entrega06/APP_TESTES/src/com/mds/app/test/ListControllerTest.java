package com.mds.app.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.ListController;
import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjectModel;

public class ListControllerTest extends AndroidTestCase {

	public ListController listController;
	public ArrayList<String> arrayOfStrings;
	public ArrayList<ProjectModel> projects;
	public ProjectModel projectModel;
	public ParliamentaryModel parliamentaryModel;
	public PoliticalPartyModel politicalPartyModel;

	@Before
	public void setUp() throws Exception {
		arrayOfStrings = new ArrayList<String>();
		projects = new ArrayList<ProjectModel>();
		politicalPartyModel = new PoliticalPartyModel("PMDS", "AC");
		parliamentaryModel = new ParliamentaryModel("Ranger", politicalPartyModel);
		projectModel = new ProjectModel("2013", "Zordon", "PL", "12/01/2013", "6663", "explicacao marota",
				parliamentaryModel);
		projectModel.setId("1");
		projectModel.setStatus("Tramitando");
		projects.add(projectModel);

		listController = new ListController(projects);
	}

	@After
	public void tearDown() throws Exception {
		listController = null;
		arrayOfStrings = null;
		projects = null;
		projectModel = null;
		parliamentaryModel = null;
		politicalPartyModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(listController);
	}

	@Test
	public void testIntanceWithEmptyConstructor() {
		ListController listControllerForTestingPurposes = new ListController();
		assertNotNull(listControllerForTestingPurposes);
	}

	@Test
	public void testTranformAProjectListIntoAnArray() {
		ArrayList<String> returnArray = listController.transformAProjectListIntoAnArrayList();
		String expectedString = "[Zordon - 6663 - Ranger]";
		String returnedString = returnArray.toString();
		assertEquals(expectedString, returnedString);
	}

	@Test
	public void testGetAListOfProjects() {
		assertEquals(projects, ListController.getProjectsList());
	}

	@Test
	public void testSetAListOfProjects() {
		ArrayList<ProjectModel> newProjects = new ArrayList<ProjectModel>();
		ProjectModel newProjectModel = new ProjectModel("2012", "DIFF", "PEC", "12/01/2013", "6263",
				"explicacao", parliamentaryModel);
		newProjects.add(newProjectModel);
		ListController.setProjectsList(newProjects);
		assertSame(newProjects, ListController.getProjectsList());
	}

	@Test
	public void testGetActualProject() {
		ProjectModel secondProjectModel = new ProjectModel("2012", "nomedois", "PRC", "12/01/2013", "6263",
				"explicacao marota", parliamentaryModel);
		ListController.setActualProject(secondProjectModel);
		assertSame(secondProjectModel, ListController.getActualProject());
	}

	@Test
	public void testTransformAProjectListIntoAnArrayPassingANullList() {
		ListController listControllerForTestingPurposes = new ListController();
		ListController.setProjectsList(null);
		ArrayList<String> returnedArray = listControllerForTestingPurposes.transformAProjectListIntoAnArrayList();
		String expectedReturn = "[Nada encontrado.]";
		String actualReturn = returnedArray.toString();
		assertEquals(expectedReturn, actualReturn);

	}

	@Test
	public void testGetCompleteStringForAProfile() {
		ListController.setActualProject(projectModel);
		String actualReturn = listController.getCompleteStringForProfile();
		String expectedReturn = "Zordon\nNumero: 6663\nAno: 2013\nSigla: PL\nData de Apresentacao: 12/01/2013\nDescricao: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetCompleteStringForAProfilePassingAnEmptyList() {
		ListController.setActualProject(null);
		String actualReturn = listController.getCompleteStringForProfile();
		String expectedReturn = "Nada encontrado.";
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetCompleteStringForAFile() {
		ListController.setActualProject(projectModel);
		String actualReturn = listController.getCompleteStringForAFile();
		System.out.println("Oiee " + actualReturn);
		String expectedReturn = "Zordon~6663~2013~PL~12/01/2013~explicacao marota~Tramitando~Ranger~PMDS~AC~1~";
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetCompleteStringForAFilePassingANullList() {
		ListController.setActualProject(null);
		String actualReturn = listController.getCompleteStringForAFile();
		String espectedReturn = null;
		assertEquals(espectedReturn, actualReturn);
	}

	@Test
	public void testSeparator() {
		String expectedSeparator = "~";
		String actualSeparator = ListController.getSeparator();
		assertEquals(expectedSeparator, actualSeparator);
	}

	@Test
	public void testNameOfTheClass() {
		String expectedName = "ListController";
		String actualName = listController.getClass().getSimpleName();
		assertEquals(expectedName, actualName);
	}

}
