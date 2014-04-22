/* File: ParliamentaryModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class for the parliamentary object
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.model;

public class ParliamentaryModel {

	private String name;
	private PoliticalPartyModel politicalParty;

	public ParliamentaryModel() {

	}

	public ParliamentaryModel(String name, PoliticalPartyModel politicalParty) {
		this.name = name;
		this.politicalParty = politicalParty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PoliticalPartyModel getPoliticalParty() {
		return politicalParty;
	}

	public void setPoliticalParty(PoliticalPartyModel politicalParty) {
		this.politicalParty = politicalParty;
	}

}
