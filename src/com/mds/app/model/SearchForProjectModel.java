/* File: SearchForProjectModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class responsible for managing a search of projects into the application. Basically manages the year, id, kind of project of a project.
 *
 */

package com.mds.app.model;

public abstract class SearchForProjectModel {
	
	/*
	 * A Search for a project basically needs its year, the kind of project
	 * (in state of an acronym), its ID and its initial date. This model defines it
	 * and creates its getters and setters.
	 */

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
