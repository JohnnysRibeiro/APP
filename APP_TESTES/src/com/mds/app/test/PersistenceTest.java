package com.mds.app.test;

import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.persistence.Persistence;

public class PersistenceTest extends AndroidTestCase {

	Persistence persistencia;

	public PersistenceTest() {
		super();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		persistencia = new Persistence(mContext);
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		persistencia = null;
	}

	@Test
	public void testGetContext() {
		assertEquals(mContext, persistencia.getContext());
	}

	@Test
	public void testArquivoFavoritosComNomeCerto() {
		String esperado = Persistence.getFavoritesNameFile();
		String retornado = persistencia.getFavoritesFile().getName();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testArquivoHistoricoComNomeCerto() {
		String esperado = Persistence.getHistoryNameFile();
		String retornado = persistencia.getHistoryFile().getName();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetFileNameFavoritos() {
		String esperado = "favoritos";
		String retornado = Persistence.getFavoritesNameFile();
		assertEquals(esperado, retornado);
	}

	@Test
	public void testGetFileNameHistorico() {
		String esperado = "historico";
		String retornado = Persistence.getHistoryNameFile();
		assertEquals(esperado, retornado);
	}

}
