/* File: ModifyFilesController.java
 * 
 * Package: com.mds.app.controller
 * 
 * Description: This is a controller class to modify project files, such as add, remove, etc.
 * 
 * Author: 
 * 
 * Creation date:
 * 
 * Version: 2.0
 * 
 */

package com.mds.app.controller;

import com.mds.app.model.ProjectModel;

public interface ModifyFilesController {

	public void addProject(ProjectModel projeto, String conteudo);

	public void removeProject(ProjectModel projeto, String conteudo);

	public String transformProjectsIntoString();

	public void populateProjects(String conteudoArquivo);

	public void populateListWithProjects();

}
