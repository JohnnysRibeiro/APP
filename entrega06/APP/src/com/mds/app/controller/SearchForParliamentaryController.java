/* File: SearchForParlamentayController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing a search from any parliamentary from the project.
 *
 */

package com.mds.app.controller;

import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryController {

	/*
	 * Basically updates the name of a parliamentary.
	 */
	
	public SearchForParliamentaryController() {
	}
	
	public static void updateDataFromParliamentarySearch(String authorName) {

		SearchForParliamentaryModel.setName(authorName);

	}
	

}
