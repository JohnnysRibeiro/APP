package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForParliamentaryController;
import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryControllerTest extends AndroidTestCase {

	private SearchForParliamentaryController procuraParlamentarController;

	@Before
	public void setUp() throws Exception {
		procuraParlamentarController = new SearchForParliamentaryController();
	}

	@After
	public void tearDown() throws Exception {
		procuraParlamentarController = null;
	}

	@Test
	public void testInstance() {
		procuraParlamentarController = new SearchForParliamentaryController();
		assertNotNull(procuraParlamentarController);
	}

	@Test
	public void testarNomeDaClasse() {
		SearchForParliamentaryController procura = new SearchForParliamentaryController();
		assertEquals("ProcuraParlamentarController", procura.getClass().getSimpleName());
	}

	@Test
	public void testAtualizarDadosPesquisaParlamentar() {
		SearchForParliamentaryController.updateDataFromParliamentarySearch("Joao");
		assertEquals("Joao", SearchForParliamentaryModel.getName());

	}

}
