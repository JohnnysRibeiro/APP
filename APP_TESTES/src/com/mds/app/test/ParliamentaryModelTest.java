package com.mds.app.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;

public class ParliamentaryModelTest extends AndroidTestCase {

	private ParliamentaryModel parlamentarModel;
	private PoliticalPartyModel partidoModel;

	@Before
	public void setUp() throws Exception {
		partidoModel = new PoliticalPartyModel("PMDS", "DF");
		parlamentarModel = new ParliamentaryModel("NomeParlamentar", partidoModel);
	}

	@After
	public void tearDown() throws Exception {
		partidoModel = null;
		parlamentarModel = null;
	}

	@Test
	public void testIntanciaPartido() {
		PoliticalPartyModel partido = new PoliticalPartyModel();
		assertNotNull(partido);
	}

	@Test
	public void testarNomeClasse() {
		Assert.assertEquals("PoliticalPartyModel", partidoModel.getClass().getSimpleName());
	}

	@Test
	public void testarNomeClasse2() {
		Assert.assertEquals("ParliamentaryModel", parlamentarModel.getClass().getSimpleName());
	}

	@Test
	public void testIntanciaParlamentar() {
		PoliticalPartyModel parlamentar = new PoliticalPartyModel();
		assertNotNull(parlamentar);
	}

	@Test
	public void testGetNome() {
		assertEquals("NomeParlamentar", parlamentarModel.getName());
	}

	@Test
	public void testSetNome() {
		parlamentarModel.setName("NomeParlamentar");
		assertEquals("NomeParlamentar", parlamentarModel.getName());
	}

	@Test
	public void testGetPartido() {
		assertSame(partidoModel, parlamentarModel.getPoliticalParty());
	}

	@Test
	public void testSetPartido() {
		parlamentarModel.setPoliticalParty(partidoModel);
		assertSame(partidoModel, parlamentarModel.getPoliticalParty());
	}

}