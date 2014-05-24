/* File: HistoryController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing the history of projects that was seen
 * favorited, searched, viewed, etc at the application.
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

public class HistoryController implements ManageProjectsController {

	/*
	 * Defines the max number of projects to be stored by the history, creates an ArrayList to be the
	 * History and another one to store the CompleteString for each project and sets variable for the 
	 * persistence.
	 */
	
	private static final int MAX_NUMBER_OF_PROJECTS = 10;
	private static ArrayList<ProjectModel> historyOfProjects = new ArrayList<ProjectModel>(MAX_NUMBER_OF_PROJECTS);
	private static ArrayList<String> historyOfProjectsCompleteString = new ArrayList<String>();
	private Persistence persistence;

	public HistoryController(Context context) {
		persistence = new Persistence(context);
	}

	public HistoryController() {

	}

	/*
	 * This is the method that places an project into the ArrayList that is used to store
	 * all the projects that was seen to be showed at the History. 
	 */
	
	@Override
	public void addProject(ProjectModel project, String content) {
		if (!historyOfProjectsCompleteString.contains(content)) {
			if (!historyOfProjects.contains(project)) {
				historyOfProjectsCompleteString.add(content);
				historyOfProjects.add(project);
				persistence.writeInFile(Persistence.getHistoryNameFile(), content);
			}
			else {
				Log.i("LOGGER", "ELSE DENTRO ADICIONAR HISTORICO");
			}
		}
		else {
			
			/*
			 * Project that has already been seen and stored so the code 
			 * removes the first reference of this project from the History ArrayList
			 * and replaces it with the latest one.
			 */
			
			Log.i("LOGGER", "ELSE ADICIONAR HISTORICO");
			historyOfProjects.remove(project);
			ArrayList<ProjectModel> historyOfProjectsUpdated = new ArrayList<ProjectModel>(MAX_NUMBER_OF_PROJECTS);
			historyOfProjectsUpdated.add(0, project);
			for (int i = 1; i < historyOfProjects.size(); i++) {
				historyOfProjectsUpdated.add(i, historyOfProjects.get(i - 1));
			}
			setHistoryOfProjects(historyOfProjectsUpdated);
		}
	}

	/*
	 * This method removes a project from the history ArrayList. If the project
	 * does not exist at the history file so the application does nothing.
	 */
	
	@Override
	public void removeProject(ProjectModel project, String projectString) {
		if (historyOfProjectsCompleteString.contains(projectString)) {
			if (historyOfProjects.contains(project)) {
				historyOfProjectsCompleteString.remove(projectString);
				historyOfProjects.remove(project);
				String fileContent = transformProjectsIntoString();
				persistence.rewriteFile(Persistence.getHistoryNameFile(), fileContent);
			}
			else {
				System.out.println("ELSE DENTRO REMOVER HISTORICO");
			}
		}
		else {
			// Log.i("LOGGER", "ELSE REMOVER HISTORICO");
			System.out.println("ELSE FORA REMOVER HISTORICO");
		}
	}

	/*
	 * Iterates all the Array of history and catches the content of each project
	 * and concatenates it with the historyOfProjectsContent variable that it is a 
	 * simple String. At the end the method returns this String with all the content
	 * found.
	 */
	
	@Override
	public String transformProjectsIntoString() {
		String historyOfProjectsContent = "";

		for (int i = 0; i < historyOfProjectsCompleteString.size(); i++) {
			historyOfProjectsContent += historyOfProjectsCompleteString.get(i);
		}

		return historyOfProjectsContent;
	}

	/*
	 * It receives a String with all the projects at the history and prepares it to be shown 
	 * when a user at the Main Menu requests a list with all the projects at the history clicking
	 * at the historyButton. The code looks for separators inside the string("~"), adds all
	 * the elements of each project into an object from ProjectModel and adds each of these
	 * objects into an ArrayList of ProjectModel's ready to be used by the populateListWithProjects
	 * method.   
	 */
	
	@Override
	public void populateProjects(String historyContentString) {
		ArrayList<String> splitParts;

		Log.i("POPPROJ-H", "Conteudo historico:");

		final int separatorsPerProject = 11;
		final int numberOfProjectsInTheFile;
		int numberOfSeparators = 0;
		historyOfProjects = new ArrayList<ProjectModel>();

		if (historyContentString.contains("~")) {
			for (int i = 0; i < historyContentString.length(); i++) {
				if (historyContentString.charAt(i) == '~') {
					numberOfSeparators++;
				}
			}
			Log.i("POPPROJ-H", "Separadores: " + numberOfSeparators);

			numberOfProjectsInTheFile = (numberOfSeparators / separatorsPerProject);
			Log.i("POPPROJ-H", "Numero de projetos: " + numberOfProjectsInTheFile);

			for (int i = 0; i < numberOfProjectsInTheFile; i++) {
				splitParts = new ArrayList<String>(numberOfSeparators);
				String[] parts = historyContentString.split("~");

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

				historyOfProjects.add(project);

				Log.i("POPPROJ-H", "Adicionando: " + project.toString());
			}
		}
		else {
			Log.i("POPPROJ-H", "Historico esta vazio");
		}

		populateListWithProjects();
	}

	/*
	 * Iterates all the ArrayList created by the populateProjects method, concatenate everything
	 * into an String and adds it at the historyOfProjectsCompleteString ArrayList ready to
	 * show each project as an String. If the ArrayList is empty so the code does nothing.
	 */
	
	@Override
	public void populateListWithProjects() {
		if (!(historyOfProjects == null)) {
			for (int i = 0; i < historyOfProjects.size(); i++) {
				String projectString = "";
				projectString += historyOfProjects.get(i).getName();
				projectString += "\nNumero: ";
				projectString += historyOfProjects.get(i).getNumber();
				projectString += "\nAno:  ";
				projectString += historyOfProjects.get(i).getYear();
				projectString += "\nSigla: ";
				projectString += historyOfProjects.get(i).getKindOfProjectAcronym();
				projectString += "\nData de Apresenta��o: ";
				projectString += historyOfProjects.get(i).getDate();
				projectString += "\nDescri��o: ";
				projectString += historyOfProjects.get(i).getExplanation();
				projectString += "\nParlamentar: ";
				projectString += historyOfProjects.get(i).getParliamentary().getName();
				projectString += "\nPartido: ";
				projectString += historyOfProjects.get(i).getParliamentary().getPoliticalParty().getPoliticalPartyAcronym();
				projectString += "\nEstado: ";
				projectString += historyOfProjects.get(i).getParliamentary().getPoliticalParty().getStateAbbreviation();
				historyOfProjectsCompleteString.add(i, projectString);
			}
		}
		else {
			// Log.i("POPSTR-H", "Historico esta vazio");
		}
	}

	/*
	 * Getters and Setters for the ArrayList's used by the previous methods
	 */
	
	public static ArrayList<ProjectModel> getHistoryOfProjects() {
		return historyOfProjects;
	}

	public static void setHistoryOfProjects(ArrayList<ProjectModel> historyOfProjects) {
		HistoryController.historyOfProjects = historyOfProjects;
	}

	public static ArrayList<String> getHistoryOfProjectsCompleteString() {
		return historyOfProjectsCompleteString;
	}

	public static void setHistoryOfProjectsCompleteString(ArrayList<String> historyOfProjectsCompleteString) {
		HistoryController.historyOfProjectsCompleteString = historyOfProjectsCompleteString;
	}

	public static int getNumberOfProjectsIntoHistory() {
		return historyOfProjects.size();
	}

	public static int getMaxNumberOfProjects() {
		return MAX_NUMBER_OF_PROJECTS;
	}

	public static ProjectModel getOldestProject() throws NullPointerException {
		ProjectModel project = historyOfProjects.get(0);
		project = historyOfProjects.get(0);
		return project;
	}

	public static String getOldestProjectAsString() throws IndexOutOfBoundsException {
		String projetoString = "";
		projetoString = historyOfProjectsCompleteString.get(0);
		return projetoString;
	}
}
