/* File: ProjectDescriptionView.java
 * 
 * Package: com.mds.app.view
 * 
 * Description: This is a view class responsible for showing the description page of a project from the application.
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
import android.widget.TextView;
import android.widget.Toast;

//import com.facebook.FacebookOperationCanceledException;
//import com.facebook.Session;
//import com.facebook.SessionState;
//import com.facebook.UiLifecycleHelper;
//import com.facebook.widget.FacebookDialog;
import com.mds.app.R;
import com.mds.app.controller.FavoritesController;
import com.mds.app.controller.HistoryController;
import com.mds.app.controller.ListController;
import com.mds.app.model.ProjectModel;

public class ProjectDescriptionView extends Activity {

	/*
	 *  This block of code sets all the Buttons, TextViews, etc that will be used by showing a project.
	 */
	
	private ListController listController;
	private ProjectModel actualProject;
	
	private TextView nameOfTheProjectTextView;
	private TextView characteristicsOfTheProjectTextView;
	private TextView descriptionOfTheProjectTextView;
	private TextView parliamentaryOfTheProjectTextView;
	private TextView characteristicsFromParliamentaryTextView;
	private TextView moreInformationAboutProjectTextView;
	private TextView statusFromTheProjectTextView;
	
	private ImageButton favoriteStarImgButton;
	private ImageButton facebookShareButton;
	
	private boolean favoritedProject;
	private boolean isResumed = false;
	Context context = this;

	/*
	 *  UiLifecycleHelper is from Facebook SDK/Facebook integration
	 */
	
//	private UiLifecycleHelper uiHelper;
//	private Session.StatusCallback callback = new Session.StatusCallback() {
//		@Override
//		public void call(Session session, SessionState state, Exception exception) {
//			onSessionStateChange(session, state, exception);
//		}
//	};

	/*
	 *  This method instantiate all the elements that will be needed and call the Listeners for the buttons. It also calls the TextViews that
	 * 	will be shown on a Project Description View Page.
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_for_project_profile);
//
//		uiHelper = new UiLifecycleHelper(this, callback);
//		uiHelper.onCreate(savedInstanceState);

//		shareOnFacebook_addListener();

		actualProject = ListController.getActualProject();
		listController = new ListController();
		
		createDescriptionFormForAProject();
		
		favoriteAProject_addListener();
		
		addProjectIntoTheHistoryAfterViewed();
	}
	
	/*
	 * Adds a project into the history only if the project has not reached the maximum number permited.
	 * In case of the history file is full we delete the oldest project saved and use the new memory space
	 * for the new one.
	 */
	
	private void addProjectIntoTheHistoryAfterViewed(){
		final int numberOfProjectsIntoHistory = HistoryController.getNumberOfProjectsIntoHistory();
		final int maxNumberOfProjects = HistoryController.getMaxNumberOfProjects();
		String completeStringFromProjectFromHistory = listController.getCompleteStringForAFile();
		HistoryController historyController = new HistoryController(context);

		if (numberOfProjectsIntoHistory < maxNumberOfProjects) {
			historyController.addProject(actualProject, completeStringFromProjectFromHistory);
		}
		else {
			Log.i("LOGGER", "Remove from history: " + HistoryController.getOldestProject().getNumber());
			historyController.removeProject(HistoryController.getOldestProject(),
					HistoryController.getOldestProjectAsString());
			historyController.addProject(actualProject, completeStringFromProjectFromHistory);
		}
		
		Log.i("LOGGER", "Adding to the history: " + actualProject.getNumber());
	}
	
	/*
	 * Creates a form for showing the description of a determined project.
	 */

	private void createDescriptionFormForAProject(){
		nameOfTheProjectTextView = (TextView) findViewById(R.id.projectTypeText);
		nameOfTheProjectTextView.setText(actualProject.getName());
		
		characteristicsOfTheProjectTextView = (TextView) findViewById(R.id.projectCharacteristicsText);
		characteristicsOfTheProjectTextView.setText("Numero: " + actualProject.getNumber() + "\n" + "Ano: " 
				+ actualProject.getYear() + "\n" + "Sigla: " + actualProject.getKindOfProjectAcronym() + "\n" 
				+ "Data de Apresentacao: " + "\n" + actualProject.getDate());
		
		descriptionOfTheProjectTextView = (TextView) findViewById(R.id.descriptionText);
		descriptionOfTheProjectTextView.setText("Descricao:" + "\n" + actualProject.getExplanation());
		
		parliamentaryOfTheProjectTextView = (TextView) findViewById(R.id.parliamentaryText);
		parliamentaryOfTheProjectTextView.setText("Parlamentar");
		
		characteristicsFromParliamentaryTextView = (TextView) findViewById(R.id.parliamentaryCharacteristicsText);
		characteristicsFromParliamentaryTextView.setText("Nome: " + actualProject.getParliamentary().getName() + 
				"\n" + "Partido: " + actualProject.getParliamentary().getPoliticalParty().getPoliticalPartyAcronym());
		
		moreInformationAboutProjectTextView = (TextView) findViewById(R.id.moreText);
		moreInformationAboutProjectTextView.setText("Para visualizar o perfil completo do projeto acesse: "
				+ "http://www.camara.gov.br/proposicoesWeb/fichadetramitacao?idProposicao=" + actualProject.getId());
		
		statusFromTheProjectTextView = (TextView) findViewById(R.id.textStatus);
		statusFromTheProjectTextView.setText("Status: " + actualProject.getStatus());
	}
	
	/*
	 *  Register on log file if the Activity has failed or succeeded.
	 */
	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//
//		uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
//			@Override
//			public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
//				Log.e("Activity", String.format("Error: %s", error.toString()));
//			}
//
//			@Override
//			public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
//				Log.i("Activity", "Success!");
//			}
//		});
//	}
	
	/*
	 *  Inflate the menu; this adds items to the action bar if it is present.
	 */
	
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	/*
	 *  Listener for the Facebook share button. It shows the icon button and if pressed it redirects the application to the
	 *  Facebook app/Post on the wall feature so the user can share the Project webpage at Facebook with friends.
	 */
	
//	private void shareOnFacebook_addListener() {
//		final Activity activity = this;
//		facebookShareButton = (ImageButton) findViewById(R.id.logoFacebook);
//
//		facebookShareButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(activity).setLink(
//						"http://www.camara.gov.br/proposicoesWeb/fichadetramitacao?idProposicao="
//								+ actualProject.getId()).build();
//				uiHelper.trackPendingDialogCall(shareDialog.present());
//			}
//		});
//
//	}

	/*
	 * If the Favorite Button(a little star) is pressed this is the method that will call its listener and calls the
	 * controllers responsible for adding or removing the actual project to the favorites file. It also sets the new
	 * Favorite Button(the little star) according to the actual state of the project(showing if its faved or not).
	 */
	
	private void favoriteAProject_addListener() {
		setImageResourceForFavoriteStarImgButton();
		onClickListenerForFavoriteStarImgButton();
	}
	
	/*
	 *  Block of code that changes the Favorite Button Image according to the actual state of the project
	 *  (showing if its faved or not).
	 */
	
	private void setImageResourceForFavoriteStarImgButton(){
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
	}
	
	private void onClickListenerForFavoriteStarImgButton(){
		favoriteStarImgButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				FavoritesController favoritesController = new FavoritesController(context);
				String stringFromProjectToBeFavorited = listController.getCompleteStringForAFile();

				if (!favoritedProject) {
					favoriteStarImgButton.setImageResource(R.drawable.favorited_star_img);
					favoritedProject = true;
					favoritesController.addProject(actualProject, stringFromProjectToBeFavorited);
					Log.i("LOGGER", "Favoriting: " + actualProject.getNumber());
				}
				else {
					favoriteStarImgButton.setImageResource(R.drawable.not_favorited_star_img);
					favoritedProject = false;
					favoritesController.removeProject(actualProject, stringFromProjectToBeFavorited);
					Log.i("LOGGER", "Unfavoriting: " + actualProject.getNumber());
				}
			}
		});
	}

	/*
	 *  Android and Facebook stuff for managing the resources/application. It was created 
	 *  by default.
	 */
	
	@Override
	protected void onResume() {
		super.onResume();
//		uiHelper.onResume();
		isResumed = true;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
//		uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
		super.onPause();
//		uiHelper.onPause();
		isResumed = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		uiHelper.onDestroy();
	}

//	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
//		if (isResumed) {
//			if (exception != null && !(exception instanceof FacebookOperationCanceledException)) {
//				Toast.makeText(this, "ERRO!", Toast.LENGTH_SHORT).show();
//				return;
//			}
//		}
//	}

}
