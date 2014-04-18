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

	private ListController listaController;
	private ArrayList<String> stringProjetos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ListView listView = new ListView(this);
		listaController = new ListController();

		stringProjetos = listaController.transformAProjectListIntoAnArrayList();
		Log.i("LISTA", stringProjetos.toString());

		final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1,
				stringProjetos);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
				view.animate().setDuration(1).alpha(1).withEndAction(new Runnable() {
					@Override
					public void run() {
						if (!stringProjetos.get(position).equals("Nada encontrado.")) {
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

		listView.setBackgroundResource(R.drawable.app_bg);

		setContentView(listView);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void longToast(CharSequence message) {
		Toast.makeText(ProjectListView.this, message, Toast.LENGTH_LONG).show();
	}

}