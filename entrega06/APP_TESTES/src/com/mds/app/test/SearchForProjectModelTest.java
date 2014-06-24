package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.SearchForProjectModel;

public class SearchForProjectModelTest extends AndroidTestCase {

	private SearchForProjectModel searchForProjectModel;

	@Before
	public void setUp() throws Exception {
		searchForProjectModel = new SearchForProjectModel() {
		};
	}

	@After
	public void tearDown() throws Exception {
		searchForProjectModel = null;
	}

	@Test
	public void testIntanciaProjeto() {
		assertNotNull(searchForProjectModel);
	}

	@Test
	public void testSetTheYearForASearchAndGetItBack() {
		SearchForProjectModel.setYear("2013");
		assertEquals("2013", SearchForProjectModel.getYear());
	}

	@Test
	public void testSetTheInitialDateForASearchAndGetItBack() {
		SearchForProjectModel.setInitialDate("16/10/2013");
		assertEquals("16/10/2013", SearchForProjectModel.getInitialDate());
	}

	@Test
	public void testSetTheNumberIdForASearchAndGetItBack() {
		SearchForProjectModel.setId("99");
		assertEquals("99", SearchForProjectModel.getId());
	}

	@Test
	public void testSetTheKindOfProjectAcronymForASearchAndGetItBack() {
		SearchForProjectModel.setKindOfProjectAcronym("PL");
		assertEquals("PL", SearchForProjectModel.getKindOfProjectAcronym());
	}

}
