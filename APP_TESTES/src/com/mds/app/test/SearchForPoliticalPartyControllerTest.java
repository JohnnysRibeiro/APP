package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForPoliticalPartyController;
import com.mds.app.model.SearchForPoliticalPartyModel;

public class SearchForPoliticalPartyControllerTest extends AndroidTestCase {

	private SearchForPoliticalPartyController procuraPartidoController;

	@Before
	public void setUp() throws Exception {
		procuraPartidoController = new SearchForPoliticalPartyController();
		SearchForPoliticalPartyController.updateDataForPoliticalPartySearch("DF", "PMDB");
	}

	@After
	public void tearDown() throws Exception {
		procuraPartidoController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(procuraPartidoController);
	}

	@Test
	public void testarNomeDaClasse() {
		SearchForPoliticalPartyController procura = new SearchForPoliticalPartyController();
		assertEquals("ProcuraPartidoController", procura.getClass().getSimpleName());
	}

	@Test
	public void testAtualizaDadosPesquisaPartido_UF() {
		assertEquals("DF", SearchForPoliticalPartyModel.getStateAbbreviation());
	}

	@Test
	public void testAtualizaDadosPesquisaPartido_Sigla() {
		assertEquals("PMDB", SearchForPoliticalPartyModel.getPoliticalpartyAcronym());
	}

}
