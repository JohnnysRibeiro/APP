/* File: SearchForProjectModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class for the search of a project object
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.model;

public abstract class SearchForProjectModel {

	private static String year;
	private static String id;
	private static String kindOfProjectAcronym;
	private static String initialDate;

	public static String getYear() {
		return year;
	}

	public static void setYear(String year) {
		SearchForProjectModel.year = year;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		SearchForProjectModel.id = id;
	}

	public static String getKindOfProjectAcronym() {
		return kindOfProjectAcronym;
	}

	public static void setKindOfProjectAcronym(String kindOfProjectAcronym) {
		SearchForProjectModel.kindOfProjectAcronym = kindOfProjectAcronym;
	}

	public static String getInitialDate() {
		return initialDate;
	}

	public static void setInitialDate(String initialDate) {
		SearchForProjectModel.initialDate = initialDate;
	}

}
