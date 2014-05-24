/* File: SearchForPoliticalPartyModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class responsible for managing a search for Political Party into the project. Basically manages the acronym and the state from 
 * the political party.
 *
 */

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
