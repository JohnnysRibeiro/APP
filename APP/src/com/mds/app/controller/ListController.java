/* File: ListController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing a list of projects from the application when
 * the user requests it. It receives the arguments, do the search and returns it for the view class. 
 *
 */

package com.mds.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.mds.app.model.ProjectModel;

public class ListController {

	private static final String SEPARATOR = "~";
	private static List<ProjectModel> projectsList;
	private static ProjectModel actualProject;

	public ListController() {
	}

	public ListController(List<ProjectModel> result) {
		setProjectsList(result);
	}

	/* Transforms List<ProjetoModel> ProjectList into an ArrayList<String> */
	public ArrayList<String> transformAProjectListIntoAnArrayList() {
		ArrayList<String> projectsString = new ArrayList<String>();

		if (getProjectsList() != null) {
			for (int i = 0; i < getProjectsList().size(); i++) {
				String projectString = "";
				projectString += getProjectsList().get(i).getName();
				projectString += " - ";
				projectString += getProjectsList().get(i).getNumber();
				projectString += " - ";
				projectString += getProjectsList().get(i).getParliamentary().getName();
				projectsString.add(projectString);
			}
		}
		else {
			String projectString = "Nada encontrado.";
			projectsString.add(projectString);
		}

		return projectsString;
	}

	public String getCompleteStringForProfile() {
		String projectString = "";

		if (getActualProject() != null) {
			projectString += getActualProject().getName();
			projectString += "\nNumero: ";
			projectString += getActualProject().getNumber();
			projectString += "\nAno:  ";
			projectString += getActualProject().getYear();
			projectString += "\nSigla: ";
			projectString += getActualProject().getKindOfProjectAcronym();
			projectString += "\nData de Apresenta��o: ";
			projectString += getActualProject().getDate();
			projectString += "\nDescri��o: ";
			projectString += getActualProject().getExplanation();
			projectString += "\nParlamentar: ";
			projectString += getActualProject().getParliamentary().getName();
			projectString += "\nPartido: ";
			projectString += getActualProject().getParliamentary().getPoliticalParty().getPoliticalPartyAcronym();
			projectString += "\nEstado: ";
			projectString += getActualProject().getParliamentary().getPoliticalParty().getStateAbbreviation();
		}
		else {
			projectString = "Nada encontrado.";
		}

		return projectString;

	}

	public String getCompleteStringForAFile() {
		String projectString = "";

		if (getActualProject() != null) {
			projectString += getActualProject().getName();
			projectString += SEPARATOR;
			projectString += getActualProject().getNumber();
			projectString += SEPARATOR;
			projectString += getActualProject().getYear();
			projectString += SEPARATOR;
			projectString += getActualProject().getKindOfProjectAcronym();
			projectString += SEPARATOR;
			projectString += getActualProject().getDate();
			projectString += SEPARATOR;
			projectString += getActualProject().getExplanation();
			projectString += SEPARATOR;
			projectString += getActualProject().getStatus();
			projectString += SEPARATOR;
			projectString += getActualProject().getParliamentary().getName();
			projectString += SEPARATOR;
			projectString += getActualProject().getParliamentary().getPoliticalParty().getPoliticalPartyAcronym();
			projectString += SEPARATOR;
			projectString += getActualProject().getParliamentary().getPoliticalParty().getStateAbbreviation();
			projectString += SEPARATOR;
			projectString += getActualProject().getId();
			projectString += SEPARATOR;
		}
		else {
			projectString = null;
		}

		return projectString;
	}

	public static List<ProjectModel> getProjectsList() {
		return projectsList;
	}

	public static void setProjectsList(List<ProjectModel> newProjectsList) {
		projectsList = newProjectsList;
	}

	public static ProjectModel getActualProject() {
		return actualProject;
	}

	public static void setActualProject(ProjectModel actualProject) {
		ListController.actualProject = actualProject;
	}

	public static String getSeparator() {
		return SEPARATOR;
	}

}
