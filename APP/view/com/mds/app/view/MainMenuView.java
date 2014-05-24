/* File: MainMenuView.java
 * 
 * Package: com.mds.app.view
 * 
 * Description: This is a view class responsible for showing the Main Menu from the application.
 * 
 */

package com.mds.app.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.mds.app.R;
import com.mds.app.controller.FavoritesController;
import com.mds.app.controller.HistoryController;
import com.mds.app.controller.ListController;
import com.mds.app.persistence.Persistence;

public class MainMenuView extends Activity {

	// These are the buttons(ImageButtons because they seem like images) that will be shown at the Main Menu.
	Context context = this;
	ImageButton searchButton;
	ImageButton aboutButton;
	ImageButton favoritesButton;
	ImageButton historyButton;

	// This method instantiate all the elements that will be needed(like persistence and controllers) and call the Listeners for the buttons.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_main_menu);

		Persistence persistence = new Persistence(context);
		String historyContent = persistence.readFromFile(Persistence.getHistoryNameFile());
		String favoritesContent = persistence.readFromFile(Persistence.getFavoritesNameFile());

		FavoritesController favoritesController = new FavoritesController(context);
		favoritesController.populateProjects(favoritesContent);

		HistoryController historyController = new HistoryController(context);
		historyController.populateProjects(historyContent);

		search_addListener();
		about_addListener();
		favorites_addListener();
		history_addListener();
	}

	// This is the method that inflates/shows the Main Menu.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	// If the Search Button is pressed this is the method that will call its Listener and intent.
	private void search_addListener() {
		searchButton = (ImageButton) findViewById(R.id.search_button_id);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuView.this, SearchView.class);
				startActivity(i);
			}
		});
	}

	// If the About Button is pressed this is the method that will call its Listener and intent.
	private void about_addListener() {
		aboutButton = (ImageButton) findViewById(R.id.about_button_id);
		aboutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuView.this, AboutApplicationView.class);
				startActivity(i);
			}
		});
	}

	// If the Favorite Button is pressed this is the method that will call its Listener and intent.
	private void favorites_addListener() {
		favoritesButton = (ImageButton) findViewById(R.id.favorites_button_id);
		favoritesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistence persistence = new Persistence(context);

				String favoritesContentString = persistence.readFromFile(Persistence.getFavoritesNameFile());
				Log.i("LOGGER", "History Content: " + favoritesContentString);

				ListController.setProjectsList(FavoritesController.getFavoritedProjects());
				Log.i("ADDL-F", FavoritesController.getFavoritedProjects().toString());
				Log.i("ADDL-F", ListController.getProjectsList().toString());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

	// If the History Button is pressed this is the method that will call its Listener, intent and sets its persistence.
	private void history_addListener() {
		historyButton = (ImageButton) findViewById(R.id.history_button_id);
		historyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistence persistence = new Persistence(context);

				String historyContentString = persistence.readFromFile(Persistence.getHistoryNameFile());
				Log.i("LOGGER", "History Content: " + historyContentString);

				ListController.setProjectsList(HistoryController.getHistoryOfProjects());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

}
