package com.mds.app.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;

import com.mds.app.R;

public class AboutApplicationView extends Activity {

	/*
	* Is this button being used for anybody? It seems to make no difference so I commented it for testing purposes.
	* ImageButton backButton;
	*/ 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_aboutapplicationview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_about, menu);
		return true;
	}

}
