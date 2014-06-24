package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.SearchForPoliticalPartyModel;

public class SearchForPoliticalPartyModelTest extends AndroidTestCase {

	private SearchForPoliticalPartyModel procuraPartidoModel;

	@Before
	public void setUp() throws Exception {

		procuraPartidoModel = new SearchForPoliticalPartyModel() {
		};

		SearchForPoliticalPartyModel.setStateAbbreviation("DF");
		SearchForPoliticalPartyModel.setPoliticalpartyAcronym("PMDS");

	}

	@After
	public void tearDown() throws Exception {
		procuraPartidoModel = null;
	}

	@Test
	public void testIntanciaPartido() {
		assertNotNull(procuraPartidoModel);
	}

	@Test
	public void testSetThenGetUf() {
		assertEquals("DF", SearchForPoliticalPartyModel.getStateAbbreviation());
	}

	@Test
	public void testSetThenGetSigla() {
		assertEquals("PMDS", SearchForPoliticalPartyModel.getPoliticalpartyAcronym());
	}

}
