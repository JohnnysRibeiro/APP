package com.mds.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.mds.app.model.ProjetoModel;

public class ListController {

	private static final String SEPARATOR = "~";
	private static List<ProjetoModel> projectsList;
	private static ProjetoModel actualProject;

	public ListController() {
	}

	public ListController(List<ProjetoModel> result) {
		setProjectsList(result);
	}

	/* Transforms List<ProjetoModel> ProjectList into an ArrayList<String> */
	public ArrayList<String> transformAProjectListIntoAnArrayList() {
		ArrayList<String> projectsString = new ArrayList<String>();

		if (getProjectsList() != null) {
			for (int i = 0; i < getProjectsList().size(); i++) {
				String projectString = "";
				projectString += getProjectsList().get(i).getNome();
				projectString += " - ";
				projectString += getProjectsList().get(i).getNumero();
				projectString += " - ";
				projectString += getProjectsList().get(i).getParlamentar().getName();
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
			projectString += getActualProject().getNome();
			projectString += "\nNumero: ";
			projectString += getActualProject().getNumero();
			projectString += "\nAno:  ";
			projectString += getActualProject().getAno();
			projectString += "\nSigla: ";
			projectString += getActualProject().getSigla();
			projectString += "\nData de Apresentação: ";
			projectString += getActualProject().getData();
			projectString += "\nDescrição: ";
			projectString += getActualProject().getExplicacao();
			projectString += "\nParlamentar: ";
			projectString += getActualProject().getParlamentar().getName();
			projectString += "\nPartido: ";
			projectString += getActualProject().getParlamentar().getPoliticalParty().getSiglaPartido();
			projectString += "\nEstado: ";
			projectString += getActualProject().getParlamentar().getPoliticalParty().getUf();
		}
		else {
			projectString = "Nada encontrado.";
		}

		return projectString;

	}

	public String getCompleteStringForAFile() {
		String projectString = "";

		if (getActualProject() != null) {
			projectString += getActualProject().getNome();
			projectString += SEPARATOR;
			projectString += getActualProject().getNumero();
			projectString += SEPARATOR;
			projectString += getActualProject().getAno();
			projectString += SEPARATOR;
			projectString += getActualProject().getSigla();
			projectString += SEPARATOR;
			projectString += getActualProject().getData();
			projectString += SEPARATOR;
			projectString += getActualProject().getExplicacao();
			projectString += SEPARATOR;
			projectString += getActualProject().getStatus();
			projectString += SEPARATOR;
			projectString += getActualProject().getParlamentar().getName();
			projectString += SEPARATOR;
			projectString += getActualProject().getParlamentar().getPoliticalParty().getSiglaPartido();
			projectString += SEPARATOR;
			projectString += getActualProject().getParlamentar().getPoliticalParty().getUf();
			projectString += SEPARATOR;
			projectString += getActualProject().getId();
			projectString += SEPARATOR;
		}
		else {
			projectString = null;
		}

		return projectString;
	}

	public static List<ProjetoModel> getProjectsList() {
		return projectsList;
	}

	public static void setProjectsList(List<ProjetoModel> newProjectsList) {
		projectsList = newProjectsList;
	}

	public static ProjetoModel getActualProject() {
		return actualProject;
	}

	public static void setActualProject(ProjetoModel actualProject) {
		ListController.actualProject = actualProject;
	}

	public static String getSeparator() {
		return SEPARATOR;
	}

}
