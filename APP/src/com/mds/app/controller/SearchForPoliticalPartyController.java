/* File: SearchForPoliticalPartyController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class for the political party search
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
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
