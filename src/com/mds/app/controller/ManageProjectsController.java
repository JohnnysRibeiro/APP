/* File: ManageProjectsController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class responsible for managing projects from the application. It removes, adds, 
 * populates the projects description views, populates a list with projects or simple transforms it into a String.
 * 
 */

package com.mds.app.controller;

import com.mds.app.model.ProjectModel;

/*
 * Here we define the interfaces that needs to be defined by
 * each class that manages a project. There is a series of 
 * methods for adding, removing or manipulating the projects.
 */

public interface ManageProjectsController {

	public void addProject(ProjectModel projeto, String conteudo);

	public void removeProject(ProjectModel projeto, String conteudo);

	public String transformProjectsIntoString();

	public void populateProjects(String conteudoArquivo);

	public void populateListWithProjects();

}
