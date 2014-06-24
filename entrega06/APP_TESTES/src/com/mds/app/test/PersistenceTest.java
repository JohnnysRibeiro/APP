package com.mds.app.test;

import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.persistence.Persistence;

public class PersistenceTest extends AndroidTestCase {

	Persistence persistence;

	public PersistenceTest() {
		super();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		persistence = new Persistence(mContext);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		persistence = null;
	}

	@Test
	public void testGetContext() {
		assertEquals(mContext, persistence.getContext());
	}

	@Test
	public void testNameFromAFavoriteFile() {
		String expectedReturn = Persistence.getFavoritesNameFile();
		String actualReturn = persistence.getFavoritesFile().getName();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetNameFromAHistoryFile() {
		String expectedReturn = Persistence.getHistoryNameFile();
		String actualReturn = persistence.getHistoryFile().getName();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetFileNameFromFavorites() {
		String expectedReturn = "favoritos";
		String actualReturn = Persistence.getFavoritesNameFile();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testGetFileNameFromHistory() {
		String expectedReturn = "historico";
		String actualReturn = Persistence.getHistoryNameFile();
		assertEquals(expectedReturn, actualReturn);
	}

}
