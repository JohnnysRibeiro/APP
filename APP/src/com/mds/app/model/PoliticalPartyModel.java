package com.mds.app.model;

public class PoliticalPartyModel {

	private String politicalPartyAcronym;
	private String stateAbbreviation;

	public PoliticalPartyModel() {

	}

	public PoliticalPartyModel(String politicalPartyAcronym, String stateAbbreviation) {
		this.politicalPartyAcronym = politicalPartyAcronym;
		this.stateAbbreviation = stateAbbreviation;
	}

	public String getPoliticalPartyAcronym() {
		return politicalPartyAcronym;
	}

	public void setPoliticalPartyAcronym(String politicalPartyAcronym) {
		this.politicalPartyAcronym = politicalPartyAcronym;
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public void setStateAbbreviation(String stateAbbreviation) {
		this.stateAbbreviation = stateAbbreviation;
	}

}
