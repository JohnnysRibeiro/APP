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
import com.mds.app.controller.FavoritosController;
import com.mds.app.controller.HistoricoController;
import com.mds.app.controller.ListaController;
import com.mds.app.persistencia.Persistencia;

public class MainMenuView extends Activity {

	Context context = this;
	ImageButton searchButton;
	ImageButton aboutButton;
	ImageButton favoritesButton;
	ImageButton historicButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);

		Persistencia persistencia = new Persistencia(context);
		String conteudoHistorico = persistencia.lerDoArquivo(Persistencia.getFileNameHistorico());
		String conteudoFavoritos = persistencia.lerDoArquivo(Persistencia.getFileNameFavoritos());

		FavoritosController favoritosController = new FavoritosController(context);
		favoritosController.popularProjetos(conteudoFavoritos);

		HistoricoController historicoController = new HistoricoController(context);
		historicoController.popularProjetos(conteudoHistorico);

		busca_addListener();
		sobre_addListener();
		favoritos_addListener();
		historico_addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void busca_addListener() {
		searchButton = (ImageButton) findViewById(R.id.search_button_id);
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuView.this, SearchView.class);
				startActivity(i);
			}
		});
	}

	private void sobre_addListener() {
		aboutButton = (ImageButton) findViewById(R.id.about_button_id);
		aboutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuView.this, AboutApplicationView.class);
				startActivity(i);
			}
		});
	}

	private void favoritos_addListener() {
		favoritesButton = (ImageButton) findViewById(R.id.favorites_button_id);
		favoritesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistencia persistencia = new Persistencia(context);

				String strConteudoFavoritos = persistencia.lerDoArquivo(Persistencia.getFileNameFavoritos());
				Log.i("LOGGER", "Conteudo historico: " + strConteudoFavoritos);

				ListaController.setListaProjetos(FavoritosController.getProjetosFavoritados());
				Log.i("ADDL-F", FavoritosController.getProjetosFavoritados().toString());
				Log.i("ADDL-F", ListaController.getListaProjetos().toString());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

	private void historico_addListener() {
		historicButton = (ImageButton) findViewById(R.id.historic_button_id);
		historicButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Persistencia persistencia = new Persistencia(context);

				String strConteudoHistorico = persistencia.lerDoArquivo(Persistencia.getFileNameHistorico());
				Log.i("LOGGER", "Conteudo historico: " + strConteudoHistorico);

				ListaController.setListaProjetos(HistoricoController.getProjetosHistorico());
				Intent i = new Intent(MainMenuView.this, ProjectListView.class);
				startActivity(i);
			}
		});
	}

}
