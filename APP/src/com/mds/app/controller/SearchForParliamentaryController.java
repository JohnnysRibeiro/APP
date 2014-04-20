package com.mds.app.controller;

import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryController {

	public SearchForParliamentaryController() {
	}

	public static void updateDataFromParliamentarySearch(String authorName) {

		SearchForParliamentaryModel.setName(authorName);

	}
	

}
