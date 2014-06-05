package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchForProjectController;
import com.mds.app.model.SearchForProjectModel;

public class SearchForProjectControllerTest extends AndroidTestCase {

	private SearchForProjectController searchForProjectController;

	@Before
	public void setUp() throws Exception {
		searchForProjectController = new SearchForProjectController();

		SearchForProjectController.updateDataFromProjectSearch("2013", "PL", "6555", "14/03/2013");
	}

	@After
	public void tearDown() throws Exception {
		searchForProjectController = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(searchForProjectController);
	}

	@Test
	public void testNameOfTheClass() {
		SearchForProjectController searchForProjectInstance = new SearchForProjectController();
		assertEquals("SearchForProjectController", searchForProjectInstance.getClass().getSimpleName());
	}

	@Test
	public void testUpdateDataForASearchAndGetTheYearOfTheProject() {
		assertEquals("2013", SearchForProjectModel.getYear());

	}

	@Test
	public void testUpdateDataForASearchAndGetTheAcronymOfTheProject() {
		assertEquals("PL", SearchForProjectModel.getKindOfProjectAcronym());

	}

	@Test
	public void testUpdateDataForASearchAndGetTheNumberIdOfTheProject() {
		assertEquals("6555", SearchForProjectModel.getId());
	}

	@Test
	public void testUpdateDataForASearchAndGetTheInitialDateOfTheProject() {
		assertEquals("14/03/2013", SearchForProjectModel.getInitialDate());
	}

}
