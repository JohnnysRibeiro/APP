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
