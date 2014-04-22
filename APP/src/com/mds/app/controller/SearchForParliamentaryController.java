/* File: SearchForParlamentayController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class for the parliamentary search
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.controller;

import com.mds.app.model.SearchForParliamentaryModel;

public class SearchForParliamentaryController {

	public SearchForParliamentaryController() {
	}

	public static void updateDataFromParliamentarySearch(String authorName) {

		SearchForParliamentaryModel.setName(authorName);

	}
	

}
