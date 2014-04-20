package com.mds.app.controller;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.mds.app.model.ParliamentaryModel;
import com.mds.app.model.PoliticalPartyModel;
import com.mds.app.model.ProjetoModel;

public class PropositionController extends DefaultHandler {

	private StringBuffer buffer;

	private ArrayList<ProjetoModel> listOfProjects;
	private ProjetoModel project;
	private ParliamentaryModel parliamentary;
	private PoliticalPartyModel politicalParty;

	public PropositionController() {
		buffer = new StringBuffer();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
			throws SAXException {

		buffer.setLength(0);

		if (localName.equals("proposicoes")) {
			listOfProjects = new ArrayList<ProjetoModel>();
		}
		else if (localName.equals("proposicao")) {
			project = new ProjetoModel();
			parliamentary = new ParliamentaryModel();
			politicalParty = new PoliticalPartyModel();
		}
		else {
			// throw new IllegalArgumentException("localName "+ localName +" invalida");
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (localName.equals("proposicao")) {
			listOfProjects.add(project);
			project.setParlamentar(parliamentary);
			parliamentary.setPoliticalParty(politicalParty);
		}
		else if (localName.equals("id")) {
			project.setId(buffer.toString());
		}
		else if (localName.equals("ano")) {
			project.setAno(buffer.toString());
		}
		else if (localName.equals("nome")) {
			project.setNome(buffer.toString());
		}
		else if (localName.equals("sigla")) {
			project.setSigla(buffer.toString());
		}
		else if (localName.equals("numero")) {
			project.setNumero(buffer.toString());
		}
		else if (localName.equals("datApresentacao")) {
			project.setData(buffer.toString());
		}
		else if (localName.equals("descricao")) {
			project.setStatus(buffer.toString());
		}
		else if (localName.equals("txtEmenta")) {
			project.setExplicacao(buffer.toString());
		}
		else if (localName.equals("txtNomeAutor")) {
			parliamentary.setName(buffer.toString());
		}
		else if (localName.equals("txtSiglaPartido")) {
			politicalParty.setPoliticalPartyAcronym(buffer.toString());
		}
		else if (localName.equals("txtSiglaUF")) {
			politicalParty.setStateAbbreviation(buffer.toString());
		}
		else {
			// throw new IllegalArgumentException("localName "+ localName +" invalida!");
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) {
		buffer.append(ch, start, length);
	}

	public ArrayList<ProjetoModel> getListOfProjects() {
		return listOfProjects;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public ProjetoModel getProject() {
		return project;
	}

	public void setProject(ProjetoModel project) {
		this.project = project;
	}

	public ParliamentaryModel getParliamentary() {
		return parliamentary;
	}

	public void setParliamentary(ParliamentaryModel parliamentary) {
		this.parliamentary = parliamentary;
	}

	public PoliticalPartyModel getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(PoliticalPartyModel politicalParty) {
		this.politicalParty = politicalParty;
	}

	public void setListOfProjects(ArrayList<ProjetoModel> listOfProjects) {
		this.listOfProjects = listOfProjects;
	}

}