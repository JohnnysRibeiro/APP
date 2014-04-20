package com.mds.app.model;

public abstract class SearchForPoliticalPartyModel {

	private static String stateAbbreviation;
	private static String politicalpartyAcronym;

	public static String getUf() {
		return stateAbbreviation;
	}

	public static void setUf(String stateAbbreviation) {
		SearchForPoliticalPartyModel.stateAbbreviation = stateAbbreviation;
	}

	public static String getPoliticalpartyAcronym() {
		return politicalpartyAcronym;
	}

	public static void setPoliticalpartyAcronym(String politicalpartyAcronym) {
		SearchForPoliticalPartyModel.politicalpartyAcronym = politicalpartyAcronym;
	}

}
