package com.mds.app.view;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.mds.app.R;
import com.mds.app.controller.SearchController;
import com.mds.app.controller.ListController;
import com.mds.app.model.ProjetoModel;
import com.mds.app.util.CancelTaskOnCancelListener;
import com.mds.app.util.ConexaoInternet;

public class SearchView extends Activity {

	private ProgressDialog progressDialog;
	private ImageButton searchButton;
	private SearchController searchController;
	private ConexaoInternet connection;

	public SearchView() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_busca);

		searchController = new SearchController();
		connection = new ConexaoInternet(this);

		searchButton_addListener();

		if (connection.ChecarConexaoInternet()) {
			searchController.setTemConexao(true);
		}
		else {
			/* implement a new persistence */
		}
	}

	private void searchButton_addListener() {
		searchButton = (ImageButton) findViewById(R.id.search_button);
		searchButton.setOnClickListener(new OnClickListener() {

			Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
			Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
			EditText textNumber = (EditText) findViewById(R.id.textNumero);
			EditText textYear = (EditText) findViewById(R.id.textAno);
			EditText textInitialDate = (EditText) findViewById(R.id.textDataIni);
			EditText textAuthorName = (EditText) findViewById(R.id.textNomeAutor);
			Spinner textPoliticalPartyAcronym = (Spinner) findViewById(R.id.textSiglaPartido);

			@Override
			public void onClick(View v) {

				boolean validation = searchController.atualizarDadosDaPesquisa(textYear.getText().toString(),
						String.valueOf(spinner1.getSelectedItem()), textNumber.getText().toString(),
						textInitialDate.getText().toString(), textAuthorName.getText().toString(),
						String.valueOf(textPoliticalPartyAcronym.getSelectedItem()),
						String.valueOf(spinner2.getSelectedItem()));
				if (validation) {
					new SearchForProjectsTask().execute();
				}
				else {
					Toast.makeText(SearchView.this, "Dados Invalidos", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private class SearchForProjectsTask extends AsyncTask<Void, Void, List<ProjetoModel>> {

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(SearchView.this, "Aguarde...", "Recebendo dados", true, true);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(this));
		}

		@Override
		protected List<ProjetoModel> doInBackground(Void... params) {
			Log.i("LOGGER", "Starting...doInBackground loadList");
			List<ProjetoModel> projectsList = searchController.procurar();
			return projectsList;
		}

		@Override
		protected void onPostExecute(final List<ProjetoModel> result) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					progressDialog.dismiss();
					ListController.setProjectsList(result);
					Intent intent = new Intent(SearchView.this, ProjectListView.class);
					startActivity(intent);
				}
			});
		}

	}
}
