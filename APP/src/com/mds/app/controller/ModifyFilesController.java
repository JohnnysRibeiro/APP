package com.mds.app.controller;

import com.mds.app.model.ProjetoModel;

public interface ModifyFilesController {

	public void addProject(ProjetoModel projeto, String conteudo);

	public void removeProject(ProjetoModel projeto, String conteudo);

	public String transformProjectsIntoString();

	public void populateProjects(String conteudoArquivo);

	public void populateListWithProjects();

}
