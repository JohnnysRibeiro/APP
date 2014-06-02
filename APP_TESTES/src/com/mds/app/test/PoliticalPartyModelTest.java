package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.PoliticalPartyModel;

public class PoliticalPartyModelTest extends AndroidTestCase {

	private PoliticalPartyModel partidoModel;

	@Before
	public void setUp() throws Exception {
		partidoModel = new PoliticalPartyModel("PMDS", "DF");
	}

	@After
	public void tearDown() throws Exception {
		partidoModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(partidoModel);
	}

	@Test
	public void testGetSiglaPartido() {
		assertEquals("PMDS", partidoModel.getPoliticalPartyAcronym());
	}

	@Test
	public void testGetUf() {
		assertEquals("DF", partidoModel.getStateAbbreviation());
	}

	@Test
	public void testSetSiglaPartido() {
		partidoModel.setPoliticalPartyAcronym("PGPP");
		assertEquals("PGPP", partidoModel.getPoliticalPartyAcronym());
	}

	@Test
	public void testSetUf() {
		partidoModel.setStateAbbreviation("MG");
		assertEquals("MG", partidoModel.getStateAbbreviation());
	}

}
