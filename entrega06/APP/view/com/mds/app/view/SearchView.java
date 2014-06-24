/* File: SearchView.java
 * 
 * Package: com.mds.app.view
 * 
 * Description: This is a view class responsible for showing the search page for looking for a project from 
 * the application.
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

	/*
	 * Variables that will be used by the search. The progressDialog shows a dialog saying that
	 * the search is being doing. The searchButton it is the button that it is supposed to be pressed
	 * when the user requests the search and the others variables will be used as instances for setting 
	 * up/checking connection and calling the controller.
	 */
	
	private ProgressDialog progressDialog;
	private ImageButton searchButton;
	private SearchController searchController;
	private InternetConnection connection;

	public SearchView() {

	}

	/*
	 * Actually it's here that we instantiate the connection and SearchController class and call the Listener
	 * responsible for the search. We still did not implemented what is supposed to be done if there is no
	 * connection so this is a bug. If the cellphone is offline and a search is requested so the app crashes.
	 */
	
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
			// Need to implement a new persistence or define what will be done if the cellphone is offline.
		}
	}

	/*
	 * The Listener responsible for catching the parameters for the search and calls the controller when it
	 * is pressed. It also validates the entries and returns a message saying that the information is wrong 
	 * if it is the case.
	 */
	
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

	/*
	 *  Inflate the menu; this adds items to the action bar if it is present.
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private class SearchForProjectsTask extends AsyncTask<Void, Void, List<ProjectModel>> {
		
		/*
		 * Here it is where we set the progressDialog with the message saying that the search
		 * it is in progress and offer to the user the option to cancel the actual search.
		 */
		
		@Override
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(SearchView.this, "Espere...", "Recebendo dados", true, true);
			progressDialog.setOnCancelListener(new CancelTaskOnCancelListener(this));
		}

		/*
		 *  Prepares a List with all the projects founds that comes from SearchController and returns it.
		 *  It is done in background while the progressDialog is on the screen and the user cant see it 
		 *  happening.
		 */
		
		@Override
		protected List<ProjectModel> doInBackground(Void... params) {
			Log.i("LOGGER", "Starting...doInBackground loadList");
			List<ProjectModel> projectsList = searchController.searchIntoXML();
			return projectsList;
		}

		/*
		 * After the search is done it will close the progressDialog window and call a new intent
		 * that takes the application to a List of Projects View(ProjectListView class)
		 */
		
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
