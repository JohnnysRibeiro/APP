package com.mds.app.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mds.app.model.ParlamentarModel;
import com.mds.app.model.PartidoModel;
import com.mds.app.model.ProjetoModel;
import com.mds.app.persistence.Persistence;

public class HistoryController implements AlteraArquivos {

	private static final int MAX_PROJECTS = 10;
	private static ArrayList<ProjetoModel> historyOfProjects = new ArrayList<ProjetoModel>(MAX_PROJECTS);
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
			ArrayList<ProjetoModel> historicOfProjectsUpdated = new ArrayList<ProjetoModel>(MAX_PROJECTS);
			historicOfProjectsUpdated.add(0, project);
			for (int i = 1; i < historyOfProjects.size(); i++) {
				historicOfProjectsUpdated.add(i, historyOfProjects.get(i - 1));
			}
			setProjetosHistorico(historicOfProjectsUpdated);
		}
	}

	@Override
	public void removeProject(ProjetoModel projeto, String stringProjeto) {
		if (historyOfProjectsCompleteString.contains(stringProjeto)) {
			if (historyOfProjects.contains(projeto)) {
				historyOfProjectsCompleteString.remove(stringProjeto);
				historyOfProjects.remove(projeto);
				String conteudoArquivo = transformProjectsIntoString();
				persistence.rewriteFile(Persistence.getHistoryNameFile(), conteudoArquivo);
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
		String conteudoProjetosHistorico = "";

		for (int i = 0; i < historyOfProjectsCompleteString.size(); i++) {
			conteudoProjetosHistorico += historyOfProjectsCompleteString.get(i);
		}

		return conteudoProjetosHistorico;
	}

	@Override
	public void populateProjects(String strConteudoHistorico) {
		ArrayList<String> splitParts;

		Log.i("POPPROJ-H", "Conteudo historico:");

		final int separadoresPorProjeto = 11;
		final int numeroDeProjetosNoArquivo;
		int numeroDeSeparadores = 0;
		historyOfProjects = new ArrayList<ProjetoModel>();

		if (strConteudoHistorico.contains("~")) {
			for (int i = 0; i < strConteudoHistorico.length(); i++) {
				if (strConteudoHistorico.charAt(i) == '~') {
					numeroDeSeparadores++;
				}
			}
			Log.i("POPPROJ-H", "Separadores: " + numeroDeSeparadores);

			numeroDeProjetosNoArquivo = (numeroDeSeparadores / separadoresPorProjeto);
			Log.i("POPPROJ-H", "Numero de projetos: " + numeroDeProjetosNoArquivo);

			for (int i = 0; i < numeroDeProjetosNoArquivo; i++) {
				splitParts = new ArrayList<String>(numeroDeSeparadores);
				String[] parts = strConteudoHistorico.split("~");

				for (int j = 0; j < separadoresPorProjeto; j++) {
					splitParts.add(j, parts[j + (separadoresPorProjeto * i)]);
				}

				String siglaPartido = splitParts.get(8);
				String ufPartido = splitParts.get(9);
				String nomeParlamentar = splitParts.get(7);
				String nomeProjeto = splitParts.get(0);
				String numeroProjeto = splitParts.get(1);
				String anoProjeto = splitParts.get(2);
				String siglaProjeto = splitParts.get(3);
				String dataProjeto = splitParts.get(4);
				String explicacaoProjeto = splitParts.get(5);
				String statusProjeto = splitParts.get(6);
				String idProjeto = splitParts.get(10);

				PartidoModel partido = new PartidoModel(siglaPartido, ufPartido);
				ParlamentarModel parlamentar = new ParlamentarModel(nomeParlamentar, partido);
				ProjetoModel projeto = new ProjetoModel(anoProjeto, nomeProjeto, siglaProjeto, dataProjeto,
						numeroProjeto, explicacaoProjeto, parlamentar);
				projeto.setStatus(statusProjeto);
				projeto.setId(idProjeto);

				historyOfProjects.add(projeto);

				Log.i("POPPROJ-H", "Adicionando: " + projeto.toString());
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
				String stringProjeto = "";
				stringProjeto += historyOfProjects.get(i).getNome();
				stringProjeto += "\nNumero: ";
				stringProjeto += historyOfProjects.get(i).getNumero();
				stringProjeto += "\nAno:  ";
				stringProjeto += historyOfProjects.get(i).getAno();
				stringProjeto += "\nSigla: ";
				stringProjeto += historyOfProjects.get(i).getSigla();
				stringProjeto += "\nData de Apresentação: ";
				stringProjeto += historyOfProjects.get(i).getData();
				stringProjeto += "\nDescrição: ";
				stringProjeto += historyOfProjects.get(i).getExplicacao();
				stringProjeto += "\nParlamentar: ";
				stringProjeto += historyOfProjects.get(i).getParlamentar().getNome();
				stringProjeto += "\nPartido: ";
				stringProjeto += historyOfProjects.get(i).getParlamentar().getPartido().getSiglaPartido();
				stringProjeto += "\nEstado: ";
				stringProjeto += historyOfProjects.get(i).getParlamentar().getPartido().getUf();
				historyOfProjectsCompleteString.add(i, stringProjeto);
			}
		}
		else {
			// Log.i("POPSTR-H", "Historico esta vazio");
		}
	}

	public static ArrayList<ProjetoModel> getProjetosHistorico() {
		return historyOfProjects;
	}

	public static void setProjetosHistorico(ArrayList<ProjetoModel> projetosHistorico) {
		HistoryController.historyOfProjects = projetosHistorico;
	}

	public static ArrayList<String> getProjetosHistoricoCompletoStr() {
		return historyOfProjectsCompleteString;
	}

	public static void setProjetosHistoricoCompletoStr(ArrayList<String> projetosHistoricoCompletoStr) {
		HistoryController.historyOfProjectsCompleteString = projetosHistoricoCompletoStr;
	}

	public static int getNumeroDeProjetosNoHistorico() {
		return historyOfProjects.size();
	}

	public static int getMaxProjetos() {
		return MAX_PROJECTS;
	}

	public static ProjetoModel getProjetoMaisVelho() throws NullPointerException {
		ProjetoModel projeto = historyOfProjects.get(0);
		projeto = historyOfProjects.get(0);
		return projeto;
	}

	public static String getStringProjetoMaisVelho() throws IndexOutOfBoundsException {
		String projetoString = "";
		projetoString = historyOfProjectsCompleteString.get(0);
		return projetoString;
	}
}
