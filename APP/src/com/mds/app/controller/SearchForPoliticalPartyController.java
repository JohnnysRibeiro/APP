package com.mds.app.controller;

import com.mds.app.model.ProcuraPartidoModel;

public class SearchForPoliticalPartyController {

	public SearchForPoliticalPartyController() {

	}

	public static void updateDataForPoliticalPartySearch(String stateAbbreviation, String politicalPartyAcronym) {

		ProcuraPartidoModel.setUf(stateAbbreviation);
		ProcuraPartidoModel.setSigla(politicalPartyAcronym);

	}

}
