package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.SearchForProjectModel;

public class SearchForProjectModelTest extends AndroidTestCase {

	private SearchForProjectModel procuraProjetoModel;

	@Before
	public void setUp() throws Exception {
		procuraProjetoModel = new SearchForProjectModel() {
		};
	}

	@After
	public void tearDown() throws Exception {
		procuraProjetoModel = null;
	}

	@Test
	public void testIntanciaProjeto() {
		assertNotNull(procuraProjetoModel);
	}

	@Test
	public void testSetThenGetAno() {
		SearchForProjectModel.setYear("2013");
		assertEquals("2013", SearchForProjectModel.getYear());
	}

	@Test
	public void testSetThenGetDataInicio() {
		SearchForProjectModel.setInitialDate("16/10/2013");
		assertEquals("16/10/2013", SearchForProjectModel.getInitialDate());
	}

	@Test
	public void testSetThenGetId() {
		SearchForProjectModel.setId("99");
		assertEquals("99", SearchForProjectModel.getId());
	}

	@Test
	public void testSetThenGetNome() {
		SearchForProjectModel.setKindOfProjectAcronym("PL");
		assertEquals("PL", SearchForProjectModel.getKindOfProjectAcronym());
	}

}
