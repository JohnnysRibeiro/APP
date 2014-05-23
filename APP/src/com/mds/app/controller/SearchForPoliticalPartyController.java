/* File: SearchForPoliticalPartyController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing a search of any Political Party from the project.
 *
 */

package com.mds.app.controller;

import com.mds.app.model.SearchForPoliticalPartyModel;

public class SearchForPoliticalPartyController {

	public SearchForPoliticalPartyController() {

	}

	public static void updateDataForPoliticalPartySearch(String stateAbbreviation, String politicalPartyAcronym) {

		SearchForPoliticalPartyModel.setUf(stateAbbreviation);
		SearchForPoliticalPartyModel.setPoliticalpartyAcronym(politicalPartyAcronym);

	}

}
