package com.mds.app.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.FavoritesController;
import com.mds.app.controller.HistoryController;
import com.mds.app.controller.ListController;
import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjectModel;

public class FavoritesControllerTest extends AndroidTestCase {

	public FavoritesController favoritesController;
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

		favoritesController = new FavoritesController();
		FavoritesController.setFavoritedProjects(projects);
		FavoritesController.setFavoritedProjectsCompleteString(new ArrayList<String>());
	}

	@After
	public void tearDown() throws Exception {
		favoritesController = null;
		arrayOfStringsOfProjects = null;
		projects = null;
		projectModel = null;
		parliamentaryModel = null;
		politicalPartyModel = null;
		FavoritesController.setFavoritedProjects(null);
		FavoritesController.setFavoritedProjects(null);
	}

	@Test
	public void testInstance() {
		assertNotNull(favoritesController);
	}

	@Test
	public void testProjetosEmString() {
		favoritesController.populateListWithProjects();
		String expectedReturn = "Zordon\nNumero: 6663\nAno: 2013\nSigla: PL\nData de Apresentacao: 12/01/2013\nDescricao: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		String actualReturn = favoritesController.transformProjectsIntoString();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testPopularListaComProjetos() {
		FavoritesController.setFavoritedProjects(projects);
		favoritesController.populateListWithProjects();
		String expectedReturn = "[Zordon\nNumero: 6663\nAno: 2013\nSigla: PL\nData de Apresentacao: 12/01/2013\nDescricao: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC]";
		String actualReturn = FavoritesController.getFavoritedProjectsCompleteString().toString();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testPopularProjetos() {
		ListController listaController = new ListController();
		ListController.setActualProject(projectModel);
		String inputString = listaController.getCompleteStringForAFile();
		favoritesController.populateProjects(inputString);
		String expectedReturn = FavoritesController.getFavoritedProjects().get(0).toString();
		String actualReturn = projectModel.toString();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testSetThenGetProjetosFavoritadosCompletosStr() {
		ArrayList<String> arrayForTestingPurposes = new ArrayList<String>();
		arrayForTestingPurposes.add("TESTANDO PROJETO");
		FavoritesController.setFavoritedProjectsCompleteString(arrayForTestingPurposes);
		assertSame(arrayForTestingPurposes, FavoritesController.getFavoritedProjectsCompleteString());
	}

	@Test
	public void testSetThenGetProjetosFavoritados() {
		FavoritesController.setFavoritedProjects(projects);
		assertSame(projects, FavoritesController.getFavoritedProjects());
	}

	@Test
	public void testarNomeDaClasse() {
		String expectedNameReturn = "FavoritesController";
		String actualNameReturn = favoritesController.getClass().getSimpleName();
		assertEquals(expectedNameReturn, actualNameReturn);
	}

}
