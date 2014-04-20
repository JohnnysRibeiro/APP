package com.mds.app.model;

public class ParliamentaryModel {

	private String name;
	private PartidoModel politicalParty;

	public ParliamentaryModel() {

	}

	public ParliamentaryModel(String name, PartidoModel politicalParty) {
		this.name = name;
		this.politicalParty = politicalParty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartidoModel getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(PartidoModel politicalParty) {
		this.politicalParty = politicalParty;
	}

}
