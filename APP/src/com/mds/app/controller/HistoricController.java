package com.mds.app.controller;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.mds.app.model.ParlamentarModel;
import com.mds.app.model.PartidoModel;
import com.mds.app.model.ProjetoModel;
import com.mds.app.persistence.Persistence;

public class HistoricController implements AlteraArquivos {

	private static final int MAX_PROJETOS = 10;
	private static ArrayList<ProjetoModel> projetosHistorico = new ArrayList<ProjetoModel>(MAX_PROJETOS);
	private static ArrayList<String> projetosHistoricoCompletoStr = new ArrayList<String>();
	private Persistence persistence;

	public HistoricController(Context context) {
		persistence = new Persistence(context);
	}

	public HistoricController() {

	}

	@Override
	public void addProject(ProjetoModel projeto, String conteudo) {
		if (!projetosHistoricoCompletoStr.contains(conteudo)) {
			if (!projetosHistorico.contains(projeto)) {
				projetosHistoricoCompletoStr.add(conteudo);
				projetosHistorico.add(projeto);
				persistence.writeInFile(Persistence.getHistoricNameFile(), conteudo);
			}
			else {
				Log.i("LOGGER", "ELSE DENTRO ADICIONAR HISTORICO");
			}
		}
		else {
			// projeto que ja existe foi visto, e portanto pula pro
			// "final da fila"
			Log.i("LOGGER", "ELSE ADICIONAR HISTORICO");
			projetosHistorico.remove(projeto);
			ArrayList<ProjetoModel> atualizadaProjetosHistorico = new ArrayList<ProjetoModel>(MAX_PROJETOS);
			atualizadaProjetosHistorico.add(0, projeto);
			for (int i = 1; i < projetosHistorico.size(); i++) {
				atualizadaProjetosHistorico.add(i, projetosHistorico.get(i - 1));
			}
			setProjetosHistorico(atualizadaProjetosHistorico);
		}
	}

	@Override
	public void removeProject(ProjetoModel projeto, String stringProjeto) {
		if (projetosHistoricoCompletoStr.contains(stringProjeto)) {
			if (projetosHistorico.contains(projeto)) {
				projetosHistoricoCompletoStr.remove(stringProjeto);
				projetosHistorico.remove(projeto);
				String conteudoArquivo = transformProjectsIntoString();
				persistence.rewriteFile(Persistence.getHistoricNameFile(), conteudoArquivo);
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

		for (int i = 0; i < projetosHistoricoCompletoStr.size(); i++) {
			conteudoProjetosHistorico += projetosHistoricoCompletoStr.get(i);
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
		projetosHistorico = new ArrayList<ProjetoModel>();

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

				projetosHistorico.add(projeto);

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
		if (!(projetosHistorico == null)) {
			for (int i = 0; i < projetosHistorico.size(); i++) {
				String stringProjeto = "";
				stringProjeto += projetosHistorico.get(i).getNome();
				stringProjeto += "\nNumero: ";
				stringProjeto += projetosHistorico.get(i).getNumero();
				stringProjeto += "\nAno:  ";
				stringProjeto += projetosHistorico.get(i).getAno();
				stringProjeto += "\nSigla: ";
				stringProjeto += projetosHistorico.get(i).getSigla();
				stringProjeto += "\nData de Apresenta��o: ";
				stringProjeto += projetosHistorico.get(i).getData();
				stringProjeto += "\nDescri��o: ";
				stringProjeto += projetosHistorico.get(i).getExplicacao();
				stringProjeto += "\nParlamentar: ";
				stringProjeto += projetosHistorico.get(i).getParlamentar().getNome();
				stringProjeto += "\nPartido: ";
				stringProjeto += projetosHistorico.get(i).getParlamentar().getPartido().getSiglaPartido();
				stringProjeto += "\nEstado: ";
				stringProjeto += projetosHistorico.get(i).getParlamentar().getPartido().getUf();
				projetosHistoricoCompletoStr.add(i, stringProjeto);
			}
		}
		else {
			// Log.i("POPSTR-H", "Historico esta vazio");
		}
	}

	public static ArrayList<ProjetoModel> getProjetosHistorico() {
		return projetosHistorico;
	}

	public static void setProjetosHistorico(ArrayList<ProjetoModel> projetosHistorico) {
		HistoricController.projetosHistorico = projetosHistorico;
	}

	public static ArrayList<String> getProjetosHistoricoCompletoStr() {
		return projetosHistoricoCompletoStr;
	}

	public static void setProjetosHistoricoCompletoStr(ArrayList<String> projetosHistoricoCompletoStr) {
		HistoricController.projetosHistoricoCompletoStr = projetosHistoricoCompletoStr;
	}

	public static int getNumeroDeProjetosNoHistorico() {
		return projetosHistorico.size();
	}

	public static int getMaxProjetos() {
		return MAX_PROJETOS;
	}

	public static ProjetoModel getProjetoMaisVelho() throws NullPointerException {
		ProjetoModel projeto = projetosHistorico.get(0);
		projeto = projetosHistorico.get(0);
		return projeto;
	}

	public static String getStringProjetoMaisVelho() throws IndexOutOfBoundsException {
		String projetoString = "";
		projetoString = projetosHistoricoCompletoStr.get(0);
		return projetoString;
	}
}
