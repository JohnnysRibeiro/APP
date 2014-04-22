/* File: SearchForPoliticalPartyModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class for the search of a political party object
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
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
