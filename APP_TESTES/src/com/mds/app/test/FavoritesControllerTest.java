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

	public FavoritesController favoritosController;
	public ArrayList<String> projetosString;
	public ArrayList<ProjectModel> projetos;
	public ProjectModel projetoModel;
	public ParliamentaryModel parlamentarModel;
	public PoliticalPartyModel partidoModel;

	@Before
	public void setUp() throws Exception {
		projetosString = new ArrayList<String>();
		projetos = new ArrayList<ProjectModel>();
		partidoModel = new PoliticalPartyModel("PMDS", "AC");
		parlamentarModel = new ParliamentaryModel("Ranger", partidoModel);
		projetoModel = new ProjectModel("2013", "Zordon", "PL", "12/01/2013", "6663", "explicacao marota",
				parlamentarModel);
		projetos.add(projetoModel);

		favoritosController = new FavoritesController();
		FavoritesController.setFavoritedProjects(projetos);
		FavoritesController.setFavoritedProjectsCompleteString(new ArrayList<String>());
	}

	@After
	public void tearDown() throws Exception {
		favoritosController = null;
		projetosString = null;
		projetos = null;
		projetoModel = null;
		parlamentarModel = null;
		partidoModel = null;
		FavoritesController.setFavoritedProjects(null);
		FavoritesController.setFavoritedProjects(null);
	}

	@Test
	public void testInstance() {
		assertNotNull(favoritosController);
	}

	@Test
	public void testProjetosEmString() {
		favoritosController.populateListWithProjects();
		String esperado = "Zordon\nNumero: 6663\nAno:  2013\nSigla: PL\nData de Apresentação: 12/01/2013\nDescrição: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		String retornado = favoritosController.transformProjectsIntoString();

		assertEquals(esperado, retornado);
	}

	@Test
	public void testPopularListaComProjetos() {
		FavoritesController.setFavoritedProjects(projetos);
		favoritosController.populateListWithProjects();
		String esperado = "[Zordon\nNumero: 6663\nAno:  2013\nSigla: PL\nData de Apresentação: 12/01/2013\nDescrição: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC]";
		String retornado = FavoritesController.getFavoritedProjectsCompleteString().toString();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testPopularProjetos() {
		ListController listaController = new ListController();
		ListController.setActualProject(projetoModel);
		String stringInput = listaController.getCompleteStringForAFile();
		favoritosController.populateProjects(stringInput);
		String esperado = FavoritesController.getFavoritedProjects().get(0).toString();
		String retornado = projetoModel.toString();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testSetThenGetProjetosFavoritadosCompletosStr() {
		ArrayList<String> arrayTeste = new ArrayList<String>();
		arrayTeste.add("TESTANDO PROJETO");
		FavoritesController.setFavoritedProjectsCompleteString(arrayTeste);
		assertSame(arrayTeste, FavoritesController.getFavoritedProjectsCompleteString());
	}

	@Test
	public void testSetThenGetProjetosFavoritados() {
		FavoritesController.setFavoritedProjects(projetos);
		assertSame(projetos, FavoritesController.getFavoritedProjects());
	}

	@Test
	public void testarNomeDaClasse() {
		String nomeEsperado = "FavoritosController";
		String nomeRetornado = favoritosController.getClass().getSimpleName();
		assertEquals(nomeEsperado, nomeRetornado);
	}

}
