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

	public HistoryController historicoController;
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

		historicoController = new HistoryController();
		HistoryController.setHistoryOfProjects(projetos);
		HistoryController.setHistoryOfProjectsCompleteString(new ArrayList<String>());
	}

	@After
	public void tearDown() throws Exception {
		historicoController = null;
		projetosString = null;
		projetos = null;
		projetoModel = null;
		parlamentarModel = null;
		partidoModel = null;
		HistoryController.setHistoryOfProjects(null);
		HistoryController.setHistoryOfProjectsCompleteString(null);
	}

	@Test
	public void testInstance() {
		assertNotNull(historicoController);
	}

	@Test
	public void testProjetosEmString() {
		historicoController.populateListWithProjects();
		String esperado = "Zordon\nNumero: 6663\nAno:  2013\nSigla: PL\nData de Apresentação: 12/01/2013\nDescrição: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		String retornado = historicoController.transformProjectsIntoString();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testPopularListaComProjetos() {
		HistoryController.setHistoryOfProjects(projetos);
		historicoController.populateListWithProjects();
		String esperado = "[Zordon\nNumero: 6663\nAno:  2013\nSigla: PL\nData de Apresentação: 12/01/2013\nDescrição: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC]";
		String retornado = HistoryController.getHistoryOfProjectsCompleteString().toString();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testSetThenGetProjetosFavoritadosCompletosStr() {
		ArrayList<String> arrayTeste = new ArrayList<String>();
		arrayTeste.add("TESTANDO PROJETO");
		HistoryController.setHistoryOfProjectsCompleteString(arrayTeste);
		assertSame(arrayTeste, HistoryController.getHistoryOfProjectsCompleteString());
	}

	@Test
	public void testSetThenGetProjetosFavoritados() {
		HistoryController.setHistoryOfProjects(projetos);
		assertSame(projetos, HistoryController.getHistoryOfProjects());
	}

	@Test
	public void testarNomeDaClasse() {
		String nomeEsperado = "HistoricoController";
		String nomeRetornado = historicoController.getClass().getSimpleName();
		assertEquals(nomeEsperado, nomeRetornado);
	}

	@Test
	public void testGetMaxProjetos() {
		int esperado = 10;
		int retornado = HistoryController.getMaxNumberOfProjects();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetNumeroProjetosNoHistorico() {
		int esperado = 1; // no caso do teste, pois so tem um projeto
		int retornado = HistoryController.getNumberOfProjectsIntoHistory();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetProjetoMaisVelho() {
		ProjectModel retornado = HistoryController.getOldestProject();
		assertEquals(projetoModel, retornado);
	}

	@Test
	public void testPopularProjetos() {
		ListController listaController = new ListController();
		ListController.setActualProject(projetoModel);
		String stringInput = listaController.getCompleteStringForAFile();
		historicoController.populateProjects(stringInput);
		String esperado = HistoryController.getHistoryOfProjects().get(0).toString();
		String retornado = projetoModel.toString();

		assertEquals(esperado, retornado);
	}

	@Test(expected = NullPointerException.class)
	public void testGetProjetoMaisVelhoNull() {
		HistoryController.setHistoryOfProjects(null);
		ProjectModel retornado = HistoryController.getOldestProject();
	}

	@Test
	public void testGetStringProjetoMaisVelho() {
		String esperado = "teste";
		HistoryController.getHistoryOfProjectsCompleteString().add(esperado);
		String retornado = HistoryController.getOldestProjectAsString();
		assertEquals(esperado, retornado);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetStringProjetoMaisVelhoNull() {
		String retornado = HistoryController.getOldestProjectAsString();
		fail("teste falhou");
	}

}
