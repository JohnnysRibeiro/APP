package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryModelTest extends AndroidTestCase {

	public SearchForParliamentaryModel procuraParlamentarModel;

	@Before
	public void setUp() throws Exception {
		procuraParlamentarModel = new SearchForParliamentaryModel() {
		};
	}

	@After
	public void tearDown() throws Exception {
		procuraParlamentarModel = null;
	}

	@Test
	public void testSetThenGetNome() {
		SearchForParliamentaryModel.setName("NomeProcuraParlamentarModel");
		assertEquals("NomeProcuraParlamentarModel", SearchForParliamentaryModel.getName());
	}

}
