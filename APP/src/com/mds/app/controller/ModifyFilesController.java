package com.mds.app.controller;

import com.mds.app.model.ProjectModel;

public interface ModifyFilesController {

	public void addProject(ProjectModel projeto, String conteudo);

	public void removeProject(ProjectModel projeto, String conteudo);

	public String transformProjectsIntoString();

	public void populateProjects(String conteudoArquivo);

	public void populateListWithProjects();

}
