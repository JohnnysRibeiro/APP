package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.PoliticalPartyModel;

public class PoliticalPartyModelTest extends AndroidTestCase {

	private PoliticalPartyModel politicalPartyModel;

	@Before
	public void setUp() throws Exception {
		politicalPartyModel = new PoliticalPartyModel("PMDS", "DF");
	}

	@After
	public void tearDown() throws Exception {
		politicalPartyModel = null;
	}

	@Test
	public void testInstance() {
		assertNotNull(politicalPartyModel);
	}

	@Test
	public void testGetPoliticalPartyAcronym() {
		assertEquals("PMDS", politicalPartyModel.getPoliticalPartyAcronym());
	}

	@Test
	public void testGetStateAbbreviationFromAPoliticalParty() {
		assertEquals("DF", politicalPartyModel.getStateAbbreviation());
	}

	@Test
	public void testSetAcronymForAPoliticalParty() {
		politicalPartyModel.setPoliticalPartyAcronym("PGPP");
		assertEquals("PGPP", politicalPartyModel.getPoliticalPartyAcronym());
	}

	@Test
	public void testSetStateAbbreviationForAPoliticalParty() {
		politicalPartyModel.setStateAbbreviation("MG");
		assertEquals("MG", politicalPartyModel.getStateAbbreviation());
	}

}
