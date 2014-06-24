/* File: SearchForParliamentaryModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class responsible for managing a search for parliamentaries. Basically it manages it using his/her name.
 *
 */

package com.mds.app.model;

public abstract class SearchForParliamentaryModel {
	
	/*
	 * A search for a Parliamentary basically requests its name. This Model defines
	 * it and its getters and setters.
	 */

	private static String name;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		SearchForParliamentaryModel.name = name;
	}

}
