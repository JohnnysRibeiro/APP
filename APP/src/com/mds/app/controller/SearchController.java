/* File: SearchController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing a search of a project from the application.
 * It receives some params related to the kind of project, state, political party, date, author, etc.
 *   
 */

package com.mds.app.controller;

import java.util.ArrayList;

import android.util.Log;

import com.mds.app.exception.ValidateEntry;
import com.mds.app.model.SearchForParliamentaryModel;
import com.mds.app.model.SearchForPoliticalPartyModel;
import com.mds.app.model.SearchForProjectModel;
import com.mds.app.model.ProjectModel;
import com.mds.app.services.Address;
import com.mds.app.services.ReceiveHTTP;
import com.mds.app.services.XMLParser;

public class SearchController {

	private ReceiveHTTP receiveHTTP;
	private XMLParser xmlParser;
	private boolean thereIsConnection;
	private String offlineText;

	public SearchController() {
		xmlParser = new XMLParser();
	}

	public String transformAcronym(String kindOfProjectAcronym) {
		
		if (kindOfProjectAcronym.equalsIgnoreCase("Projeto de Lei")) {
			kindOfProjectAcronym = "PL";
		}
		else if (kindOfProjectAcronym.equalsIgnoreCase("Projeto de Emenda Constitucional")) {
			kindOfProjectAcronym = "PEC";
		}
		else if (kindOfProjectAcronym.equalsIgnoreCase("Projeto de Lei Complementar")) {
			kindOfProjectAcronym = "PLP";
		}
		else if (kindOfProjectAcronym.equalsIgnoreCase("Projetos de Decreto Legislativo")) {
			kindOfProjectAcronym = "PDC";
		}
		else if (kindOfProjectAcronym.equalsIgnoreCase("Projeto de Resolucao")) {
			kindOfProjectAcronym = "PRC";
		}
		else {
			// n�o tem outras op��es
			throw new IllegalArgumentException("Sigla inserida invalida!");
		}
		return kindOfProjectAcronym;
	}

	/*
	 * If a State is named Distrito Federal so it's State Abbreviation is
	 * 'DF'. In portuguese we call it "UF".
	 */
	public String transformStateAbbreviation(String stateAbbreviation) {
		System.out.println(stateAbbreviation);

		if (stateAbbreviation.equalsIgnoreCase("Todos os Estados")) {
			stateAbbreviation = "";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Acre")) {
			stateAbbreviation = "AC";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Alagoas")) {
			stateAbbreviation = "AL";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Amapa")) {
			stateAbbreviation = "AP";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Amazonas")) {
			stateAbbreviation = "AM";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Bahia")) {
			stateAbbreviation = "BA";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Ceara")) {
			stateAbbreviation = "CE";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Distrito Federal")) {
			stateAbbreviation = "DF";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Espirito Santo")) {
			stateAbbreviation = "ES";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Goias")) {
			stateAbbreviation = "GO";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Maranhao")) {
			stateAbbreviation = "MA";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Mato Grosso")) {
			stateAbbreviation = "MT";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Mato Grosso do Sul")) {
			stateAbbreviation = "MS";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Minas Gerais")) {
			stateAbbreviation = "MG";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Para")) {
			stateAbbreviation = "PA";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Paraiba")) {
			stateAbbreviation = "PB";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Parana")) {
			stateAbbreviation = "PR";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Pernambuco")) {
			stateAbbreviation = "PE";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Piaui")) {
			stateAbbreviation = "PI";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Rio de Janeiro")) {
			stateAbbreviation = "RJ";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Rio Grande do Norte")) {
			stateAbbreviation = "RN";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Rio Grande do Sul")) {
			stateAbbreviation = "RS";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Rondonia")) {
			stateAbbreviation = "RO";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Roraima")) {
			stateAbbreviation = "RR";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Santa Catarina")) {
			stateAbbreviation = "SC";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Sao Paulo")) {
			stateAbbreviation = "SP";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Sergipe")) {
			stateAbbreviation = "SE";
		}
		else if (stateAbbreviation.equalsIgnoreCase("Tocantins")) {
			stateAbbreviation = "TO";
		}
		else {
			// There is no other option
			throw new IllegalArgumentException("uf invalida");
		}

		return stateAbbreviation;
	}

	public boolean updateDataInsideTheSearch(String year, String kindOfProjectAcronym, String number, String initialDate,
			String authorName, String politicalPartyAcronym, String stateAbbreviation) {

		if (year.isEmpty()) {
			year = "2013";
		}
		if (number.isEmpty()) {
			number = "";
		}
		if (initialDate.isEmpty()) {
			initialDate = "";
		}
		if (authorName.isEmpty()) {
			authorName = "";
		}
		if (politicalPartyAcronym.isEmpty()) {
			politicalPartyAcronym = "";
		}
		if (politicalPartyAcronym.equals("Todos os Partidos")) {
			politicalPartyAcronym = "";
		}

		if (kindOfProjectAcronym.isEmpty() || stateAbbreviation.isEmpty()) {
			Log.i("BSCC", "atencao");
		}

		stateAbbreviation = transformStateAbbreviation(stateAbbreviation);
		kindOfProjectAcronym = transformAcronym(kindOfProjectAcronym);

		String errors = "";
		errors = ValidateEntry.discoverErrors(year, kindOfProjectAcronym, number, initialDate, authorName, politicalPartyAcronym, stateAbbreviation);
		System.out.println(errors);
		System.out.println(initialDate);
		politicalPartyAcronym = ValidateEntry.ensurePoliticalPartyResult(stateAbbreviation,politicalPartyAcronym);
		politicalPartyAcronym = ValidateEntry.ensurePoliticalPartyResult(authorName,politicalPartyAcronym);

		if (errors == "") {
			SearchForProjectController.updateDataFromProjectSearch(year, kindOfProjectAcronym, number, initialDate);
			SearchForPoliticalPartyController.updateDataForPoliticalPartySearch(stateAbbreviation, politicalPartyAcronym);
			SearchForParliamentaryController.updateDataFromParliamentarySearch(authorName);
			return true;
		}
		else {
			return false;
		}

	}

	public String receiveXML() {

		String kindOfProjectAcronym = SearchForProjectModel.getKindOfProjectAcronym().toUpperCase();
		String year = SearchForProjectModel.getYear();
		String initialDate = SearchForProjectModel.getInitialDate();
		String number = SearchForProjectModel.getId();
		String authorName = SearchForParliamentaryModel.getName();
		String politicalPartyAcronym = SearchForPoliticalPartyModel.getPoliticalpartyAcronym();
		String stateAbbreviation = SearchForPoliticalPartyModel.getUf();

		Address.kindOfProjectAcronym = kindOfProjectAcronym;
		Address.number = number;
		Address.year = year;
		Address.initialDate = initialDate;
		Address.finalDate = "";
		Address.author = "";
		Address.authorName = authorName;
		Address.politicalPartyAcronym = politicalPartyAcronym;
		Address.stateAbbreviation = stateAbbreviation;
		Address.authorGender = "";
		Address.stateCode = "";
		Address.organStateCode = "";
		String url = Address.construirEndereco();
		System.out.println(url);

		String response = null;
		receiveHTTP = new ReceiveHTTP();
		if (thereIsConnection) {
			response = receiveHTTP.receive(url);
		}
		else {
			response = offlineText;
		}

		return response;

	}

	public ArrayList<ProjectModel> searchIntoXML() {
		String xmlProjeto = receiveXML();
		return xmlParser.projectParser(xmlProjeto);
	}

	public boolean isThereConnection() {
		return thereIsConnection;
	}

	public void setConnection(boolean thereIsConnection) {
		this.thereIsConnection = thereIsConnection;
	}

	public String getOfflineText() {
		return offlineText;
	}

	public void setOfflineText(String offlineText) {
		this.offlineText = offlineText;
	}

	public XMLParser getXmlParser() {
		return xmlParser;
	}

	public void setXmlParser(XMLParser xmlParser) {
		this.xmlParser = xmlParser;
	}

}
