package com.mds.app.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjetoModel;
import com.mds.app.persistence.Persistence;

public class HistoryController implements ModifyFilesController {

	private static final int MAX_NUMBER_OF_PROJECTS = 10;
	private static ArrayList<ProjetoModel> historyOfProjects = new ArrayList<ProjetoModel>(MAX_NUMBER_OF_PROJECTS);
	private static ArrayList<String> historyOfProjectsCompleteString = new ArrayList<String>();
	private Persistence persistence;

	public HistoryController(Context context) {
		persistence = new Persistence(context);
	}

	public HistoryController() {

	}

	@Override
	public void addProject(ProjetoModel project, String content) {
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
			 * Project that has already been seen so the code 
			 * goes to the end of the line
			 */
			Log.i("LOGGER", "ELSE ADICIONAR HISTORICO");
			historyOfProjects.remove(project);
			ArrayList<ProjetoModel> historyOfProjectsUpdated = new ArrayList<ProjetoModel>(MAX_NUMBER_OF_PROJECTS);
			historyOfProjectsUpdated.add(0, project);
			for (int i = 1; i < historyOfProjects.size(); i++) {
				historyOfProjectsUpdated.add(i, historyOfProjects.get(i - 1));
			}
			setHistoryOfProjects(historyOfProjectsUpdated);
		}
	}

	@Override
	public void removeProject(ProjetoModel project, String projectString) {
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

	@Override
	public String transformProjectsIntoString() {
		String historyOfProjectsContent = "";

		for (int i = 0; i < historyOfProjectsCompleteString.size(); i++) {
			historyOfProjectsContent += historyOfProjectsCompleteString.get(i);
		}

		return historyOfProjectsContent;
	}

	@Override
	public void populateProjects(String historyContentString) {
		ArrayList<String> splitParts;

		Log.i("POPPROJ-H", "Conteudo historico:");

		final int separatorsPerProject = 11;
		final int numberOfProjectsInTheFile;
		int numberOfSeparators = 0;
		historyOfProjects = new ArrayList<ProjetoModel>();

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
				ProjetoModel project = new ProjetoModel(projectYear, projectName, projectAcronym, projectDate,
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

	@Override
	public void populateListWithProjects() {
		if (!(historyOfProjects == null)) {
			for (int i = 0; i < historyOfProjects.size(); i++) {
				String projectString = "";
				projectString += historyOfProjects.get(i).getNome();
				projectString += "\nNumero: ";
				projectString += historyOfProjects.get(i).getNumero();
				projectString += "\nAno:  ";
				projectString += historyOfProjects.get(i).getAno();
				projectString += "\nSigla: ";
				projectString += historyOfProjects.get(i).getSigla();
				projectString += "\nData de Apresentação: ";
				projectString += historyOfProjects.get(i).getData();
				projectString += "\nDescrição: ";
				projectString += historyOfProjects.get(i).getExplicacao();
				projectString += "\nParlamentar: ";
				projectString += historyOfProjects.get(i).getParlamentar().getName();
				projectString += "\nPartido: ";
				projectString += historyOfProjects.get(i).getParlamentar().getPoliticalParty().getPoliticalPartyAcronym();
				projectString += "\nEstado: ";
				projectString += historyOfProjects.get(i).getParlamentar().getPoliticalParty().getStateAbbreviation();
				historyOfProjectsCompleteString.add(i, projectString);
			}
		}
		else {
			// Log.i("POPSTR-H", "Historico esta vazio");
		}
	}

	public static ArrayList<ProjetoModel> getHistoryOfProjects() {
		return historyOfProjects;
	}

	public static void setHistoryOfProjects(ArrayList<ProjetoModel> historyOfProjects) {
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

	public static ProjetoModel getOldestProject() throws NullPointerException {
		ProjetoModel project = historyOfProjects.get(0);
		project = historyOfProjects.get(0);
		return project;
	}

	public static String getOldestProjectAsString() throws IndexOutOfBoundsException {
		String projetoString = "";
		projetoString = historyOfProjectsCompleteString.get(0);
		return projetoString;
	}
}
