package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryModelTest extends AndroidTestCase {

	public SearchForParliamentaryModel searchForParliamentaryModel;

	@Before
	public void setUp() throws Exception {
		searchForParliamentaryModel = new SearchForParliamentaryModel() {
		};
	}

	@After
	public void tearDown() throws Exception {
		searchForParliamentaryModel = null;
	}

	@Test
	public void testGetNameOfAParliamentary() {
		SearchForParliamentaryModel.setName("NomeProcuraParlamentarModel");
		assertEquals("NomeProcuraParlamentarModel", SearchForParliamentaryModel.getName());
	}

}
