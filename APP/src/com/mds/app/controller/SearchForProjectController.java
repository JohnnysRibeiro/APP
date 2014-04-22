/* File: SearchForProjectController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class for the project search
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.controller;

import com.mds.app.model.SearchForProjectModel;

public class SearchForProjectController {

	public SearchForProjectController() {
	}

	public static void updateDataFromProjectSearch(String year, String kindofProjectAcronym, String number, String initialDate) {

		SearchForProjectModel.setYear(year);
		SearchForProjectModel.setKindOfProjectAcronym(kindofProjectAcronym);
		SearchForProjectModel.setId(number);
		SearchForProjectModel.setInitialDate(initialDate);

	}

}
