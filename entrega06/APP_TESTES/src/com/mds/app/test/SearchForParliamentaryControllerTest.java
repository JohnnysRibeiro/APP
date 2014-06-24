package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForParliamentaryController;
import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryControllerTest extends AndroidTestCase {

	private SearchForParliamentaryController searchForParliamentaryController;

	@Before
	public void setUp() throws Exception {
		searchForParliamentaryController = new SearchForParliamentaryController();
	}

	@After
	public void tearDown() throws Exception {
		searchForParliamentaryController = null;
	}

	@Test
	public void testInstance() {
		searchForParliamentaryController = new SearchForParliamentaryController();
		assertNotNull(searchForParliamentaryController);
	}

	@Test
	public void testNameOfTheClass() {
		SearchForParliamentaryController searchForParliamentaryInstance = new SearchForParliamentaryController();
		assertEquals("SearchForParliamentaryController", searchForParliamentaryInstance.getClass().getSimpleName());
	}

	@Test
	public void testUpdateDataForAParliamentarySearch() {
		SearchForParliamentaryController.updateDataFromParliamentarySearch("Joao");
		assertEquals("Joao", SearchForParliamentaryModel.getName());

	}

}
