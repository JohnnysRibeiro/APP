/* File: ProjectListView.java
 * 
 * Package: com.mds.app.view
 * 
 * Description: This is a view class responsible for showing a list of projects from the application.
 *
 */

package com.mds.app.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mds.app.R;
import com.mds.app.controller.ListController;
import com.mds.app.util.StableArrayAdapter;

public class ProjectListView extends Activity {

	private ListController listController;
	private ArrayList<String> stringOfAProject;

	/*
	 * Basically prepares a list of projects to be shown after a search and opens its
	 * on a Description View if requested by the user or show that nothing was found if
	 * it wasnt.
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListView listView = new ListView(this);
		listController = new ListController();

		stringOfAProject = listController.transformAProjectListIntoAnArrayList();
		Log.i("LIST", stringOfAProject.toString());

		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1,
				stringOfAProject);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
				view.animate().setDuration(1).alpha(1).withEndAction(new Runnable() {
					@Override
					public void run() {
						if (!stringOfAProject.get(position).equals("Nada encontrado.")) {
							ListController.setActualProject(ListController.getProjectsList().get(position));
							Intent i = new Intent(ProjectListView.this, ProjectDescriptionView.class);
							startActivity(i);
						}
						else {
							longToast("Nenhuma proposicao encontrada.");
						}
					}
				});
			}
		});

		listView.setBackgroundResource(R.drawable.app_background);

		setContentView(listView);

	}

	/*
	 * Inflate the menu; this adds items to the action bar if it is present.
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void longToast(CharSequence message) {
		Toast.makeText(ProjectListView.this, message, Toast.LENGTH_LONG).show();
	}

}