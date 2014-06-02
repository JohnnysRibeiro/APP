package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForProjectController;
import com.mds.app.model.SearchForProjectModel;

public class SearchForProjectControllerTest extends AndroidTestCase {

	private SearchForProjectController procuraProjetoController;

	@Before
	public void setUp() throws Exception {
		procuraProjetoController = new SearchForProjectController();

		SearchForProjectController.updateDataFromProjectSearch("2013", "PL", "6555", "14/03/2013");
	}

	@After
	public void tearDown() throws Exception {
		procuraProjetoController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(procuraProjetoController);
	}

	@Test
	public void testarNomeDaClasse() {
		SearchForProjectController procura = new SearchForProjectController();
		assertEquals("ProcuraProjetoController", procura.getClass().getSimpleName());
	}

	@Test
	public void testAtualizarDadosPesquisaProjeto_Ano() {
		assertEquals("2013", SearchForProjectModel.getYear());

	}

	@Test
	public void testAtualizarDadosPesquisaProjeto_Sigla() {
		assertEquals("PL", SearchForProjectModel.getKindOfProjectAcronym());

	}

	@Test
	public void testAtualizarDadosPesquisaProjeto_Numero() {
		assertEquals("6555", SearchForProjectModel.getId());
	}

	@Test
	public void testAtualizarDadosPesquisaProjeto_DataInicio() {
		assertEquals("14/03/2013", SearchForProjectModel.getInitialDate());
	}

}
