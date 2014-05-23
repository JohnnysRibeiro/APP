/* File: SearchForParliamentaryModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class responsible for managing a search for parliamentaries. Basically it manages it using his/her name.
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
