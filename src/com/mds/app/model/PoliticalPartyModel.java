/* File: PoliticalPartyModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class from a Political Party with all its atributes like name, acronym, abbreviation, etc.
 * 
 */

package com.mds.app.model;

public class PoliticalPartyModel {
	
	/*
	 * A Political Party has an acronym and an origin state(defined by an abbreviation). 
	 * This model basically defines it and its getters and setters.
	 */

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
