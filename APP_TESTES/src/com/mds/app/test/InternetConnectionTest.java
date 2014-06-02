package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.util.InternetConnection;

public class InternetConnectionTest extends AndroidTestCase {

	InternetConnection conexaoInternet;

	@Before
	public void setUp() throws Exception {
		conexaoInternet = new InternetConnection(mContext);
	}

	@After
	public void tearDown() throws Exception {
		conexaoInternet = null;
	}

	@Test
	public void testInstancia() {
		assertNotNull(conexaoInternet);
	}

	@Test
	public void testInstanciaConstrutorVazio() {
		InternetConnection conexaoInternetConstrutorVazio = new InternetConnection();
		assertNotNull(conexaoInternetConstrutorVazio);
	}

	@Test
	public void testGetContext() {
		assertEquals(mContext, conexaoInternet.getContext());
	}

	@Test
	public void testNomeClasse() {
		assertEquals("ConexaoInternet", conexaoInternet.getClass().getSimpleName());
	}

	@Test
	public void testChecarConexaoInternet() {
		boolean conexao = conexaoInternet.checkInternetConnection();
		assertTrue(conexao);
	}

}
