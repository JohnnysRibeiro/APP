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
import com.mds.app.controller.HistoricController;
import com.mds.app.controller.ListController;
import com.mds.app.persistence.Persistence;

public class MainMenuView extends Activity {

	Context context = this;
	ImageButton searchButton;
	ImageButton aboutButton;
	ImageButton favoritesButton;
	ImageButton historicButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		Persistence persistence = new Persistence(context);
		String historicContent = persistence.readFromFile(Persistence.getHistoricNameFile());
		String favoritesContent = persistence.readFromFile(Persistence.getFavoritesNameFile());

		FavoritesController favoritesController = new FavoritesController(context);
		favoritesController.populateProjects(favoritesContent);

		HistoricController historicController = new HistoricController(context);
		historicController.populateProjects(historicContent);

		search_addListener();
		about_addListener();
		favorites_addListener();
		historic_addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

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

	private void favorites_addListener() {
		favoritesButton = (ImageButton) findViewById(R.id.favorites_button_id);
		favoritesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistence persistence = new Persistence(context);

				String favoritesContentString = persistence.readFromFile(Persistence.getFavoritesNameFile());
				Log.i("LOGGER", "Conteudo historico: " + favoritesContentString);

				ListController.setProjectsList(FavoritesController.getFavoritedProjects());
				Log.i("ADDL-F", FavoritesController.getFavoritedProjects().toString());
				Log.i("ADDL-F", ListController.getProjectsList().toString());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

	private void historic_addListener() {
		historicButton = (ImageButton) findViewById(R.id.historic_button_id);
		historicButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistence persistence = new Persistence(context);

				String historicContentString = persistence.readFromFile(Persistence.getHistoricNameFile());
				Log.i("LOGGER", "Conteudo historico: " + historicContentString);

				ListController.setProjectsList(HistoricController.getProjetosHistorico());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

}
