/* File: SearchForParliamentaryModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class for the the search of a parliamentary object
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.model;

public abstract class SearchForParliamentaryModel {

	private static String name;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		SearchForParliamentaryModel.name = name;
	}

}
