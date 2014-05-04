/* File: SearchView.java
 * 
 * Package: com.mds.app.view
 * 
 * Description: This is a view class with the search view interface
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

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
import com.mds.app.model.ProjectModel;
import com.mds.app.util.CancelTaskOnCancelListener;
import com.mds.app.util.InternetConnection;

public class SearchView extends Activity {

	private ProgressDialog progressDialog;
	private ImageButton searchButton;
	private SearchController searchController;
	private InternetConnection connection;

	public SearchView() {

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_for_search);

		searchController = new SearchController();
		connection = new InternetConnection(this);

		searchButton_addListener();

		if (connection.checkInternetConnection()) {
			searchController.setConnection(true);
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
			EditText textNumber = (EditText) findViewById(R.id.numberText);
			EditText textYear = (EditText) findViewById(R.id.yearText);
			EditText textInitialDate = (EditText) findViewById(R.id.startDateText);
			EditText textAuthorName = (EditText) findViewById(R.id.authorNameText);
			Spinner textPoliticalPartyAcronym = (Spinner) findViewById(R.id.politicalPartyAcronymText);

			@Override
			public void onClick(View v) {

				boolean validation = searchController.updateDataInsideTheSearch(textYear.getText().toString(),
						String.valueOf(spinner1.getSelectedItem()), textNumber.getText().toString(),
						textInitialDate.getText().toString(), textAuthorName.getText().toString(),
						String.valueOf(textPoliticalPartyAcronym.getSelectedItem()),
						String.valueOf(spinner2.getSelectedItem()));
				if (validation) {
					new SearchForProjectsTask().execute();
				}
				else {
					Toast.makeText(SearchView.this, "Dados inv‡lidos", Toast.LENGTH_SHORT).show();
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

	private class SearchForProjectsTask extends AsyncTask<Void, Void, List<ProjectModel>> {

		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(SearchView.this, "Espere...", "Recebendo dados", true, true);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(this));
		}

		@Override
		protected List<ProjectModel> doInBackground(Void... params) {
			Log.i("LOGGER", "Starting...doInBackground loadList");
			List<ProjectModel> projectsList = searchController.searchIntoXML();
			return projectsList;
		}

		@Override
		protected void onPostExecute(final List<ProjectModel> result) {
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
