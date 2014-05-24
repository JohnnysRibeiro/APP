/* File: FavoritesController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing the favorited projects from the application.
 *
 */

package com.mds.app.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjectModel;
import com.mds.app.persistence.Persistence;

public class FavoritesController implements ManageProjectsController {

	/*
	 * Arrays that will receive the favorited projects in two formats: as an object from ProjectModel class
	 * and as a String.
	 */
	
	private static ArrayList<ProjectModel> favoritedProjects = new ArrayList<ProjectModel>();
	private static ArrayList<String> favoritedProjectsCompleteString = new ArrayList<String>();

	/*
	 * Creates a variable for the persistence and instantiate it.
	 */
	
	private Persistence persistence;

	public FavoritesController(Context context) {
		persistence = new Persistence(context);
	}

	public FavoritesController() {

	}

	/*
	 * This method adds a received project into the favorites database
	 * only if its not there. If the project already exists on favorites
	 * database so the application does nothing.
	 */
	
	@Override
	public void addProject(ProjectModel project, String content) {
		if (!favoritedProjectsCompleteString.contains(content)) {
			if (!favoritedProjects.contains(project)) {
				favoritedProjectsCompleteString.add(content);
				favoritedProjects.add(project);

				persistence.writeInFile(Persistence.getFavoritesNameFile(), content);
			}
			else {
				System.out.println("ELSE DENTRO ADICIONAR FAVORITOS");
			}
		}
		else {
			
			/*
			 * Project already exists in favorited ArrayList, there is no need
			 * to add it again
			 */
			
			Log.i("ADICIONAR", "ELSE ADICIONAR FAVORITOS");
		}
	}

	/*
	 * This method removes a project from the favorites ArrayList. If the project
	 * does not exist at the favorites ArrayList so the application does nothing.
	 */
	
	@Override
	public void removeProject(ProjectModel project, String projectString) {
		if (favoritedProjectsCompleteString.contains(projectString)) {
			if (favoritedProjects.contains(project)) {
				favoritedProjectsCompleteString.remove(projectString);
				favoritedProjects.remove(project);
				String fileContent = transformProjectsIntoString();
				persistence.rewriteFile(Persistence.getFavoritesNameFile(), fileContent);
			}
			else {
				System.out.println("ELSE DENTRO REMOVER FAVORITOS");
			}
		}
		else {
			// The project does not exist here so no need to removing.
			Log.i("LOGGER", "ELSE REMOVER FAVORITOS");
		}
	}

	/*
	 * Iterates all the Array of projects and catches the content of each project
	 * and concatenates it with the favoritedProjectsContent variable that it is a 
	 * simple String. At the end the method returns this String with all the content
	 * found.
	 */
	
	@Override
	public String transformProjectsIntoString() {
		String favoritedProjectsContent = "";

		for (int i = 0; i < favoritedProjectsCompleteString.size(); i++) {
			favoritedProjectsContent += favoritedProjectsCompleteString.get(i);
		}

		return favoritedProjectsContent;
	}

	/*
	 * It receives a String with all the favorited projects and prepares it to be shown 
	 * when a user at the Main Menu requests a list with all the favorited projects clicking
	 * at the favoriteButton. The code looks for separators inside the string("~"), adds all
	 * the elements of each project into an object from ProjectModel and adds each of these
	 * objects into an ArrayList of ProjectModel's ready to be used by the populateListWithProjects
	 * method.   
	 */
	
	@Override
	public void populateProjects(String favoritedContentString) {
		ArrayList<String> splitParts;

		Log.i("POPPROJ-F", "Conteudo favoritos:");

		final int separatorsPerProject = 11;
		final int numberOfProjectsInTheFile;
		int numberOfSeparators = 0;
		favoritedProjects = new ArrayList<ProjectModel>();

		if (favoritedContentString.contains("~")) {
			for (int i = 0; i < favoritedContentString.length(); i++) {
				if (favoritedContentString.charAt(i) == '~') {
					numberOfSeparators++;
				}
			}
			Log.i("POPPROJ-F", "Separadores: " + numberOfSeparators);

			numberOfProjectsInTheFile = (numberOfSeparators / separatorsPerProject);
			Log.i("POPPROJ-F", "Numero de projetos: " + numberOfProjectsInTheFile);

			for (int i = 0; i < numberOfProjectsInTheFile; i++) {
				splitParts = new ArrayList<String>(numberOfSeparators);
				String[] parts = favoritedContentString.split("~");

				for (int j = 0; j < separatorsPerProject; j++) {
					splitParts.add(j, parts[j + (separatorsPerProject * i)]);
				}

				String politicalPartyAcronym = splitParts.get(8);
				String politicalPartyStateAbbreviation = splitParts.get(9);
				String parliamentaryName = splitParts.get(7);
				String projectName = splitParts.get(0);
				String projectNumber = splitParts.get(1);
				String projectYear = splitParts.get(2);
				String projectAcronym = splitParts.get(3);
				String projectDate = splitParts.get(4);
				String projectExplanation = splitParts.get(5);
				String projectStatus = splitParts.get(6);
				String projectId = splitParts.get(10);

				PoliticalPartyModel politicalParty = new PoliticalPartyModel(politicalPartyAcronym, politicalPartyStateAbbreviation);
				ParliamentaryModel parliamentary = new ParliamentaryModel(parliamentaryName, politicalParty);
				ProjectModel project = new ProjectModel(projectYear, projectName, projectAcronym, projectDate,
						projectNumber, projectExplanation, parliamentary);
				project.setStatus(projectStatus);
				project.setId(projectId);

				favoritedProjects.add(project);

				Log.i("POPPROJ-F", "Adicionando: " + project.toString());
			}
		}
		else {
			Log.i("POPPROJ-F", "Favoritos esta vazio");
		}

		populateListWithProjects();
	}

	/*
	 * Iterates all the ArrayList created by the populateProjects method, concatenate everything
	 * into an String and adds it at the favoritedProjectsCompleteString ArrayList ready to
	 * show each project as an String. If the ArrayList is empty so the code does nothing.
	 */
	
	@Override
	public void populateListWithProjects() {
		if (!(favoritedProjects == null)) {
			for (int i = 0; i < favoritedProjects.size(); i++) {
				String projectString = "";
				projectString += favoritedProjects.get(i).getName();
				projectString += "\nNumero: ";
				projectString += favoritedProjects.get(i).getNumber();
				projectString += "\nAno:  ";
				projectString += favoritedProjects.get(i).getYear();
				projectString += "\nSigla: ";
				projectString += favoritedProjects.get(i).getKindOfProjectAcronym();
				projectString += "\nData de Apresenta��o: ";
				projectString += favoritedProjects.get(i).getDate();
				projectString += "\nDescri��o: ";
				projectString += favoritedProjects.get(i).getExplanation();
				projectString += "\nParlamentar: ";
				projectString += favoritedProjects.get(i).getParliamentary().getName();
				projectString += "\nPartido: ";
				projectString += favoritedProjects.get(i).getParliamentary().getPoliticalParty().getPoliticalPartyAcronym();
				projectString += "\nEstado: ";
				projectString += favoritedProjects.get(i).getParliamentary().getPoliticalParty().getStateAbbreviation();
				favoritedProjectsCompleteString.add(i, projectString);
			}
		}
		else {
			// Log.i("POPSTR-F", "Favoritos esta vazio");
		}
	}

	/*
	 * Getters and Setters for the ArrayList's used by the previous methods
	 */
	
	public static ArrayList<ProjectModel> getFavoritedProjects() {
		return favoritedProjects;
	}

	public static void setFavoritedProjects(ArrayList<ProjectModel> favoritedProjects) {
		FavoritesController.favoritedProjects = favoritedProjects;
	}

	public static ArrayList<String> getFavoritedProjectsCompleteString() {
		return favoritedProjectsCompleteString;
	}

	public static void setFavoritedProjectsCompleteString(ArrayList<String> favoritedProjectsCompleteString) {
		FavoritesController.favoritedProjectsCompleteString = favoritedProjectsCompleteString;
	}

}
