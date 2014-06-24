package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.util.InternetConnection;

public class InternetConnectionTest extends AndroidTestCase {

	InternetConnection internetConnection;

	@Before
	public void setUp() throws Exception {
		internetConnection = new InternetConnection(mContext);
	}

	@After
	public void tearDown() throws Exception {
		internetConnection = null;
	}

	@Test
	public void testIntance() {
		assertNotNull(internetConnection);
	}

	@Test 
	public void testIntanceWithEmptyConstructor() {
		InternetConnection internetConnectionWithEmptyConstructor = new InternetConnection();
		assertNotNull(internetConnectionWithEmptyConstructor);
	}

	@Test
	public void testGetContext() {
		assertEquals(mContext, internetConnection.getContext());
	}

	@Test
	public void testNomeClasse() {
		assertEquals("InternetConnection", internetConnection.getClass().getSimpleName());
	}

	@Test
	public void testChecarConexaoInternet() {
		boolean connection = internetConnection.checkInternetConnection();
		assertTrue(connection);
	}

}
