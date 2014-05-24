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

	/*
	 * The pattern for separators used when we pass a series of projects as an unique
	 * String is "~". 
	 */
	private static final String SEPARATOR = "~";
	private static List<ProjectModel> projectsList;
	private static ProjectModel actualProject;

	public ListController() {
	}

	public ListController(List<ProjectModel> result) {
		setProjectsList(result);
	}

	/* 
	 * Transforms List<ProjetoModel> ProjectList into an ArrayList<String>
	 * If there is nothing at the projectsString Array so the code adds a
	 * String saying that not was found at the projectsString Array.
	 */
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

	/*
	 * Get the actual project and adds a series of parameters, as name, number, year, kind
	 * of project, date, brief explanation, parliamentary, etc and concatenates it into a
	 * projectString String. If there is no actual project so the projectString String 
	 * receives a string saying that nothing was found.
	 */
	
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

	/*
	 * Does basically the same thing as getCompleteStringForProfile() method but
	 * with the separator between each parameter. It also returns a String with
	 * all these elements or a null string if nothing was found.
	 */
	
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

	/*
	 * Getters and Setters for the ArrayList's used by the previous methods
	 */
	
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
