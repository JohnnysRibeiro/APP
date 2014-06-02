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

	public ListController listaController;
	public ArrayList<String> stringLista;
	public ArrayList<ProjectModel> projetos;
	public ProjectModel projetoModel;
	public ParliamentaryModel parlamentarModel;
	public PoliticalPartyModel partidoModel;

	@Before
	public void setUp() throws Exception {
		stringLista = new ArrayList<String>();
		projetos = new ArrayList<ProjectModel>();
		partidoModel = new PoliticalPartyModel("PMDS", "AC");
		parlamentarModel = new ParliamentaryModel("Ranger", partidoModel);
		projetoModel = new ProjectModel("2013", "Zordon", "PL", "12/01/2013", "6663", "explicacao marota",
				parlamentarModel);
		projetos.add(projetoModel);

		listaController = new ListController(projetos);
	}

	@After
	public void tearDown() throws Exception {
		listaController = null;
		stringLista = null;
		projetos = null;
		projetoModel = null;
		parlamentarModel = null;
		partidoModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(listaController);
	}

	@Test
	public void testInstanceConstrutorVazio() {
		ListController listaControllerTeste = new ListController();
		assertNotNull(listaControllerTeste);
	}

	@Test
	public void testTransformarLista() {
		ArrayList<String> retornado = listaController.transformAProjectListIntoAnArrayList();

		String stringEsperada = "[Zordon - 6663 - Ranger]";
		String stringRetornada = retornado.toString();

		assertEquals(stringEsperada, stringRetornada);
	}

	@Test
	public void testGetListaProjetos() {
		assertEquals(projetos, ListController.getProjectsList());
	}

	@Test
	public void testSetListaProjetos() {
		ArrayList<ProjectModel> novoProjetos = new ArrayList<ProjectModel>();
		ProjectModel novoProjetoModel = new ProjectModel("2012", "DIFF", "PEC", "12/01/2013", "6263",
				"explicacao", parlamentarModel);
		novoProjetos.add(novoProjetoModel);
		ListController.setProjectsList(novoProjetos);
		assertSame(novoProjetos, ListController.getProjectsList());
	}

	@Test
	public void testSetThenGetProjetoAtual() {
		ProjectModel projetoModel2 = new ProjectModel("2012", "nomedois", "PRC", "12/01/2013", "6263",
				"explicacao marota", parlamentarModel);
		ListController.setActualProject(projetoModel2);
		assertSame(projetoModel2, ListController.getActualProject());
	}

	@Test
	public void testTransformarListaNull() {
		ListController listaControllerTeste = new ListController();
		ListController.setProjectsList(null);
		ArrayList<String> arrayRetornado = listaControllerTeste.transformAProjectListIntoAnArrayList();
		String esperado = "[Nada encontrado.]";
		String retornado = arrayRetornado.toString();
		assertEquals(esperado, retornado);

	}

	@Test
	public void testGetStringCompletaParaPerfil() {
		ListController.setActualProject(projetoModel);
		String retornado = listaController.getCompleteStringForProfile();
		String esperado = "Zordon\nNumero: 6663\nAno:  2013\nSigla: PL\nData de Apresentação: 12/01/2013\nDescrição: explicacao marota\nParlamentar: Ranger\nPartido: PMDS\nEstado: AC";
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetStringCompletaParaPerfilNull() {
		ListController.setActualProject(null);
		String retornado = listaController.getCompleteStringForProfile();
		String esperado = "Nada encontrado.";
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetStringCompletaParaArquivo() {
		ListController.setActualProject(projetoModel);
		String retornado = listaController.getCompleteStringForAFile();
		System.out.println(retornado);
		String esperado = "Zordon~6663~2013~PL~12/01/2013~explicacao marota~Ranger~PMDS~AC~";
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetStringCompletaParaArquivoNull() {
		ListController.setActualProject(null);
		String retornado = listaController.getCompleteStringForAFile();
		String esperado = null;
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetSeparador() {
		String esperado = "~";
		String retornado = ListController.getSeparator();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testarNomeDaClasse() {
		String nomeEsperado = "ListaController";
		String nomeRetornado = listaController.getClass().getSimpleName();
		assertEquals(nomeEsperado, nomeRetornado);
	}

}
