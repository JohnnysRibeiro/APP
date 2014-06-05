package com.mds.app.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.mds.app.util.StableArrayAdapter;

public class StableArrayAdapterTest extends AndroidTestCase {

	StableArrayAdapter stableArrayAdapter;
	ArrayList<String> arrayListOfString;
	Context context;

	public StableArrayAdapterTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		arrayListOfString = new ArrayList<String>();
		arrayListOfString.add("teste");
		context = mContext;
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
		stableArrayAdapter = null;
		arrayListOfString = null;
		context = null;
	}

	@Test
	public void testWithNullContext() {
		try {
			stableArrayAdapter = new StableArrayAdapter(null, 20, arrayListOfString);
		} catch (NullPointerException e) {
		}
		assertNull(stableArrayAdapter);
	}

	@Test
	public void testGetItem() {
		stableArrayAdapter = new StableArrayAdapter(context, 77, arrayListOfString);

		String str = stableArrayAdapter.getItem(0);
		assertEquals("teste", str);
	}

	@Test
	public void testGetItemId() {
		stableArrayAdapter = new StableArrayAdapter(context, 80, arrayListOfString);
		long esperado = 0;
		long retornado = stableArrayAdapter.getItemId(0);
		assertEquals(esperado, retornado);
	}

	@Test
	public void testHasStableIds() {
		stableArrayAdapter = new StableArrayAdapter(context, 80, arrayListOfString);
		assertTrue(stableArrayAdapter.hasStableIds());
	}

}
