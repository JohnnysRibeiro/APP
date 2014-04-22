/* File: ProjectModel.java
 * 
 * Package: com.mds.app.model
 * 
 * Description: This is a model class for the project object
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.model;

public class ProjectModel {

	private String id;
	private String year;
	private String number;
	private String name;
	private String kindOfProjectAcronym;
	private String date;
	private String explanation;
	private String status;
	private ParliamentaryModel parliamentary;
	private int counter = 0;
	private int counterId = 0;
	

	public ProjectModel() {
	}

	public ProjectModel(String year, String name, String kindOfProjectAcronym, String data, String number, String explanation,
			ParliamentaryModel parliamentary) {
		this.year = year;
		this.name = name;
		this.kindOfProjectAcronym = kindOfProjectAcronym;
		this.date = data;
		this.number = number;
		this.explanation = explanation;
		this.parliamentary = parliamentary;

		this.counter = 1;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String string) {
		this.number = string;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (this.counter == 1) {
			this.name = name;
		}
		else {
			// do nothing
		}
		this.counter++;
	}

	public void setId(String id) {
		if (this.counterId == 0) {
			this.id = id;
			this.counterId++;
		}
		else {
			// do nothing
		}
	}

	public String getKindOfProjectAcronym() {
		return kindOfProjectAcronym;
	}

	public String getId() {
		return id;
	}

	public void setKindOfProjectAcronym(String kindOfProjectAcronym) {
		this.kindOfProjectAcronym = kindOfProjectAcronym;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public ParliamentaryModel getParliamentary() {
		return parliamentary;
	}

	public void setParliamentary(ParliamentaryModel parliamentary) {
		this.parliamentary = parliamentary;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	@Override
	public String toString() {
		return "ProjetoModel [ano=" + year + ", numero=" + number + ", nome=" + name + ", sigla=" + kindOfProjectAcronym
				+ ", data=" + date + ", explicacao=" + explanation + ", parlamentar=" + parliamentary.getName() + "]";
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
		
	}

}
