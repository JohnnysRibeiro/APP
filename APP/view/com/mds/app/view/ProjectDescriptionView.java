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
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.mds.app.R;
import com.mds.app.controller.FavoritesController;
import com.mds.app.controller.HistoryController;
import com.mds.app.controller.ListController;
import com.mds.app.model.ProjetoModel;

public class ProjectDescriptionView extends Activity {

	private ListController listController;
	private ProjetoModel actualProject;
	private String completeProjectAsString;
	private TextView text1;
	private TextView text2;
	private TextView text3;
	private TextView text4;
	private TextView text5;
	private TextView text6;
	private TextView text7;
	private ImageButton favoriteStarImgButton;
	private ImageButton facebookShareButton;
	private boolean favoritedProject;
	Context context = this;
	private boolean isResumed = false;

	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state, Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_for_project_profile);

		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		shareOnFacebook_addListener();

		actualProject = ListController.getActualProject();
		listController = new ListController();

		completeProjectAsString = listController.getCompleteStringForProfile();

		text1 = (TextView) findViewById(R.id.textoTipoProjeto);
		text1.setText(actualProject.getNome());
		text2 = (TextView) findViewById(R.id.textoCarcteristicasProjeto);
		text2.setText("Número: " + actualProject.getNumero() + "\n" + "Ano: " + actualProject.getAno() + "\n"
				+ "Sigla: " + actualProject.getSigla() + "\n" + "Data de Apresentação: " + "\n"
				+ actualProject.getData());
		text3 = (TextView) findViewById(R.id.textoDescricao);
		text3.setText("Descrição:" + "\n" + actualProject.getExplicacao());
		text4 = (TextView) findViewById(R.id.textoParlamentar);
		text4.setText("Parlamentar");
		text5 = (TextView) findViewById(R.id.textoCarcteristicasParlamentar);
		text5.setText("Nome: " + actualProject.getParlamentar().getNome() + "\n" + "Partido: "
				+ actualProject.getParlamentar().getPartido().getSiglaPartido());
		text6 = (TextView) findViewById(R.id.textoMais);
		text6.setText("Para visualizar o perfil completo do projeto acesse: "
				+ "http://www.camara.gov.br/proposicoesWeb/fichadetramitacao?idProposicao=" + actualProject.getId());
		text7 = (TextView) findViewById(R.id.textoStatus);
		text7.setText("Status: " + actualProject.getStatus());

		favoriteAProject_addListener();

		final int numberOfProjectsIntoHistory = HistoryController.getNumberOfProjectsIntoHistory();
		final int maxNumberOfProjects = HistoryController.getMaxNumberOfProjects();
		String completeStringFromProjectFromHistory = listController.getCompleteStringForAFile();
		HistoryController historyController = new HistoryController(context);

		/*
		 * Fix removing if higher than the allowed limit at HistoryController
		 * and dont add the same project more than one time.
		 */
		
		if (numberOfProjectsIntoHistory < maxNumberOfProjects) {
			historyController.addProject(actualProject, completeStringFromProjectFromHistory);
		}
		else {
			Log.i("LOGGER", "Removendo do historico: " + HistoryController.getOldestProject().getNumero());
			historyController.removeProject(HistoryController.getOldestProject(),
					HistoryController.getOldestProjectAsString());
			historyController.addProject(actualProject, completeStringFromProjectFromHistory);
		}
		Log.i("LOGGER", "Adicionando ao historico: " + actualProject.getNumero());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
			@Override
			public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
				Log.e("Activity", String.format("Error: %s", error.toString()));
			}

			@Override
			public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
				Log.i("Activity", "Success!");
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	private void shareOnFacebook_addListener() {
		final Activity activity = this;
		facebookShareButton = (ImageButton) findViewById(R.id.logoFacebook);

		facebookShareButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(activity).setLink(
						"http://www.camara.gov.br/proposicoesWeb/fichadetramitacao?idProposicao="
								+ actualProject.getId()).build();
				uiHelper.trackPendingDialogCall(shareDialog.present());
			}
		});

	}

	private void favoriteAProject_addListener() {
		favoriteStarImgButton = (ImageButton) findViewById(R.id.notFavoritedProjectStar);

		String stringFromFavoritedProject = listController.getCompleteStringForAFile();
		if (FavoritesController.getFavoritedProjectsCompleteString().contains(stringFromFavoritedProject)) {
			favoriteStarImgButton.setImageResource(R.drawable.favorited_star_img);
			favoritedProject = true;
		}
		else {
			favoriteStarImgButton.setImageResource(R.drawable.not_favorited_star_img);
			favoritedProject = false;
		}

		favoriteStarImgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				FavoritesController favoritesController = new FavoritesController(context);
				String stringFromProjectToBeFavorited = listController.getCompleteStringForAFile();

				if (!favoritedProject) {
					favoriteStarImgButton.setImageResource(R.drawable.favorited_star_img);
					favoritedProject = true;
					favoritesController.addProject(actualProject, stringFromProjectToBeFavorited);
					Log.i("LOGGER", "Favoritando: " + actualProject.getNumero());
				}
				else {
					favoriteStarImgButton.setImageResource(R.drawable.not_favorited_star_img);
					favoritedProject = false;
					favoritesController.removeProject(actualProject, stringFromProjectToBeFavorited);
					Log.i("LOGGER", "Desfavoritando: " + actualProject.getNumero());
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		uiHelper.onResume();
		isResumed = true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
		isResumed = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (isResumed) {
			if (exception != null && !(exception instanceof FacebookOperationCanceledException)) {
				Toast.makeText(this, "ERRO!", Toast.LENGTH_SHORT).show();
				return;
			}
		}
	}

}
