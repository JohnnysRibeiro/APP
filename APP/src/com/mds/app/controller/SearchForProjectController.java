package com.mds.app.controller;

import com.mds.app.model.ProcuraProjetoModel;

public class SearchForProjectController {

	public SearchForProjectController() {
	}

	public static void updateDataFromProjectSearch(String year, String kindofProjectAcronym, String number, String initialDate) {

		ProcuraProjetoModel.setAno(year);
		ProcuraProjetoModel.setSigla(kindofProjectAcronym);
		ProcuraProjetoModel.setId(number);
		ProcuraProjetoModel.setDataInicio(initialDate);

	}

}
