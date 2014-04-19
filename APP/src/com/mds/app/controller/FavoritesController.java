package com.mds.app.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mds.app.model.ParlamentarModel;
import com.mds.app.model.PartidoModel;
import com.mds.app.model.ProjetoModel;
import com.mds.app.persistence.Persistence;

public class FavoritesController implements ModifyFilesController {

	private static ArrayList<ProjetoModel> favoritedProjects = new ArrayList<ProjetoModel>();
	private static ArrayList<String> favoritedProjectsCompleteString = new ArrayList<String>();

	private Persistence persistence;

	public FavoritesController(Context context) {
		persistence = new Persistence(context);
	}

	public FavoritesController() {

	}

	@Override
	public void addProject(ProjetoModel project, String content) {
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
			 * Project already exists in favorited file, there is no way
			 * to add it again
			 */
			Log.i("ADICIONAR", "ELSE ADICIONAR FAVORITOS");
		}
	}

	@Override
	public void removeProject(ProjetoModel project, String projectString) {
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
			// same thing
			Log.i("LOGGER", "ELSE REMOVER FAVORITOS");
		}
	}

	@Override
	public String transformProjectsIntoString() {
		String favoritedProjectsContent = "";

		for (int i = 0; i < favoritedProjectsCompleteString.size(); i++) {
			favoritedProjectsContent += favoritedProjectsCompleteString.get(i);
		}

		return favoritedProjectsContent;
	}

	@Override
	public void populateProjects(String favoritedContentString) {
		ArrayList<String> splitParts;

		Log.i("POPPROJ-F", "Conteudo favoritos:");

		final int separatorsPerProject = 11;
		final int numberOfProjectsInTheFile;
		int numberOfSeparators = 0;
		favoritedProjects = new ArrayList<ProjetoModel>();

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

				PartidoModel politicalParty = new PartidoModel(politicalPartyAcronym, politicalPartyStateAbbreviation);
				ParlamentarModel parliamentary = new ParlamentarModel(parliamentaryName, politicalParty);
				ProjetoModel project = new ProjetoModel(projectYear, projectName, projectAcronym, projectDate,
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

	@Override
	public void populateListWithProjects() {
		if (!(favoritedProjects == null)) {
			for (int i = 0; i < favoritedProjects.size(); i++) {
				String projectString = "";
				projectString += favoritedProjects.get(i).getNome();
				projectString += "\nNumero: ";
				projectString += favoritedProjects.get(i).getNumero();
				projectString += "\nAno:  ";
				projectString += favoritedProjects.get(i).getAno();
				projectString += "\nSigla: ";
				projectString += favoritedProjects.get(i).getSigla();
				projectString += "\nData de Apresentação: ";
				projectString += favoritedProjects.get(i).getData();
				projectString += "\nDescrição: ";
				projectString += favoritedProjects.get(i).getExplicacao();
				projectString += "\nParlamentar: ";
				projectString += favoritedProjects.get(i).getParlamentar().getNome();
				projectString += "\nPartido: ";
				projectString += favoritedProjects.get(i).getParlamentar().getPartido().getSiglaPartido();
				projectString += "\nEstado: ";
				projectString += favoritedProjects.get(i).getParlamentar().getPartido().getUf();
				favoritedProjectsCompleteString.add(i, projectString);
			}
		}
		else {
			// Log.i("POPSTR-F", "Favoritos esta vazio");
		}
	}

	public static ArrayList<ProjetoModel> getFavoritedProjects() {
		return favoritedProjects;
	}

	public static void setFavoritedProjects(ArrayList<ProjetoModel> favoritedProjects) {
		FavoritesController.favoritedProjects = favoritedProjects;
	}

	public static ArrayList<String> getFavoritedProjectsCompleteString() {
		return favoritedProjectsCompleteString;
	}

	public static void setFavoritedProjectsCompleteString(ArrayList<String> favoritedProjectsCompleteString) {
		FavoritesController.favoritedProjectsCompleteString = favoritedProjectsCompleteString;
	}

}
