package com.mds.app.controller;

import com.mds.app.model.ProcuraParlamentarModel;

public class SearchForParliamentaryController {

	public SearchForParliamentaryController() {
	}

	public static void updateDataFromParliamentarySearch(String authorName) {

		ProcuraParlamentarModel.setNome(authorName);

	}
	

}
