package com.mds.app.services;

public abstract class Address {

	private final static String URL_BASE = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?";
	private final static String EQUAL = "=";
	private final static String AND = "&";
	private final static String KIND_OF_PROJECT_ACRONYM = "sigla";
	private final static String NUMBER = "numero";
	private final static String YEAR = "ano";
	private final static String INITIAL_DATE = "datApresentacaoIni";
	private final static String FINAL_DATE = "datApresentacaoFim";
	private final static String AUTHOR = "autor";
	private final static String AUTHOR_NAME = "parteNomeAutor";
	private final static String POLITICAL_PARTY_ACRONYM = "siglaPartidoAutor";
	private final static String STATE_ABBREVIATION = "siglaUFAutor";
	private final static String AUTHOR_GENDER = "generoAutor";
	private final static String STATE_CODE = "codEstado";
	private final static String ORGAN_STATE_CODE = "codOrgaoEstado";
	private final static String PROCESSING = "emTramitacao";
	public static String kindOfProjectAcronym;
	public static String number;
	public static String year;
	public static String initialDate;
	public static String finalDate;
	public static String author;
	public static String authorName;
	public static String politicalPartyAcronym;
	public static String stateAbbreviation;
	public static String authorGender;
	public static String stateCode;
	public static String organStateCode;

	private final static String KIND_OF_AUTHOR_ID = "idTipoAutor";

	public static String construirEndereco() {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(URL_BASE);
		stringBuffer.append(KIND_OF_PROJECT_ACRONYM);
		stringBuffer.append(EQUAL);
		stringBuffer.append(kindOfProjectAcronym);
		stringBuffer.append(AND);
		stringBuffer.append(NUMBER);
		stringBuffer.append(EQUAL);
		stringBuffer.append(number);
		stringBuffer.append(AND);
		stringBuffer.append(YEAR);
		stringBuffer.append(EQUAL);
		stringBuffer.append(year);
		stringBuffer.append(AND);
		stringBuffer.append(INITIAL_DATE);
		stringBuffer.append(EQUAL);
		stringBuffer.append(initialDate);
		stringBuffer.append(AND);
		stringBuffer.append(FINAL_DATE);
		stringBuffer.append(EQUAL);
		stringBuffer.append(finalDate);
		stringBuffer.append(AND);
		stringBuffer.append(AUTHOR);
		stringBuffer.append(EQUAL);
		stringBuffer.append(author);
		stringBuffer.append(AND);
		stringBuffer.append(AUTHOR_NAME);
		stringBuffer.append(EQUAL);
		stringBuffer.append(authorName);
		stringBuffer.append(AND);
		stringBuffer.append(POLITICAL_PARTY_ACRONYM);
		stringBuffer.append(EQUAL);
		stringBuffer.append(politicalPartyAcronym);
		stringBuffer.append(AND);
		stringBuffer.append(STATE_ABBREVIATION);
		stringBuffer.append(EQUAL);
		stringBuffer.append(stateAbbreviation);
		stringBuffer.append(AND);
		stringBuffer.append(AUTHOR_GENDER);
		stringBuffer.append(EQUAL);
		stringBuffer.append(authorGender);
		stringBuffer.append(AND);
		stringBuffer.append(STATE_CODE);
		stringBuffer.append(EQUAL);
		stringBuffer.append(stateCode);

		// Temporary while the chamber page is wrong
		stringBuffer.append(AND);
		stringBuffer.append(KIND_OF_AUTHOR_ID);
		stringBuffer.append(EQUAL);
		stringBuffer.append("");
		/* ---------------- */

		stringBuffer.append(AND);
		stringBuffer.append(ORGAN_STATE_CODE);
		stringBuffer.append(EQUAL);
		stringBuffer.append(organStateCode);
		stringBuffer.append(AND);
		stringBuffer.append(PROCESSING);
		stringBuffer.append(EQUAL);
		stringBuffer.append("1");

		return stringBuffer.toString();
	}

}
