package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForPoliticalPartyController;
import com.mds.app.model.SearchForPoliticalPartyModel;

public class SearchForPoliticalPartyControllerTest extends AndroidTestCase {

	private SearchForPoliticalPartyController searchForPoliticalPartyController;

	@Before
	public void setUp() throws Exception {
		searchForPoliticalPartyController = new SearchForPoliticalPartyController();
		SearchForPoliticalPartyController.updateDataForPoliticalPartySearch("DF", "PMDB");
	}

	@After
	public void tearDown() throws Exception {
		searchForPoliticalPartyController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(searchForPoliticalPartyController);
	}

	@Test
	public void testNameOfTheClass() {
		SearchForPoliticalPartyController procura = new SearchForPoliticalPartyController();
		assertEquals("SearchForPoliticalPartyController", procura.getClass().getSimpleName());
	}

	@Test
	public void testUpdateDataForASearchAndGetTheStateAbbreviation() {
		assertEquals("DF", SearchForPoliticalPartyModel.getStateAbbreviation());
	}

	@Test
	public void testUpdateDataForASearchAndGetThePoliticalPartyAcronym() {
		assertEquals("PMDB", SearchForPoliticalPartyModel.getPoliticalpartyAcronym());
	}

}
