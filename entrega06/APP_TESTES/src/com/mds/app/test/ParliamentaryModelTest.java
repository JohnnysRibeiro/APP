package com.mds.app.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;

public class ParliamentaryModelTest extends AndroidTestCase {

	private ParliamentaryModel parliamentaryModel;
	private PoliticalPartyModel politicalPartyModel;

	@Before
	public void setUp() throws Exception {
		politicalPartyModel = new PoliticalPartyModel("PMDS", "DF");
		parliamentaryModel = new ParliamentaryModel("NomeParlamentar", politicalPartyModel);
	}

	@After
	public void tearDown() throws Exception {
		politicalPartyModel = null;
		parliamentaryModel = null;
	}

	@Test
	public void testInstanceForPoliticalPartyModel() {
		PoliticalPartyModel politicalParty = new PoliticalPartyModel();
		assertNotNull(politicalParty);
	}

	@Test
	public void nameNameOfPoliticalPartyModelClass() {
		Assert.assertEquals("PoliticalPartyModel", politicalPartyModel.getClass().getSimpleName());
	}

	@Test
	public void testNameOfParliamentaryModelClass() {
		Assert.assertEquals("ParliamentaryModel", parliamentaryModel.getClass().getSimpleName());
	}

	@Test
	public void testInstanceForParliamentaryModel() {
		ParliamentaryModel parlamentar = new ParliamentaryModel();
		assertNotNull(parlamentar);
	}

	@Test
	public void testGetNameofAParliamentary() {
		assertEquals("NomeParlamentar", parliamentaryModel.getName());
	}

	@Test
	public void testSetNameOfAParliamentary() {
		parliamentaryModel.setName("NomeParlamentar");
		assertEquals("NomeParlamentar", parliamentaryModel.getName());
	}

	@Test
	public void testGetAPoliticalParty() {
		assertSame(politicalPartyModel, parliamentaryModel.getPoliticalParty());
	}

	@Test
	public void testSetAPoliticalParty() {
		parliamentaryModel.setPoliticalParty(politicalPartyModel);
		assertSame(politicalPartyModel, parliamentaryModel.getPoliticalParty());
	}

}