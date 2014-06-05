package com.mds.app.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.controller.SearchController;
import com.mds.app.controller.SearchForParliamentaryController;
import com.mds.app.controller.SearchForPoliticalPartyController;
import com.mds.app.controller.SearchForProjectController;
import com.mds.app.model.ProjectModel;
import com.mds.app.services.XMLParser;

public class SearchControllerTest extends AndroidTestCase {

	public SearchController searchController;

	@Before
	public void setUp() throws Exception {
		searchController = new SearchController();
	}

	@After
	public void tearDown() throws Exception {
		searchController = null;

	}

	@Test
	public void testInstance() {
		assertNotNull(searchController);
	}

	@Test
	public void testXmlParserInstance() {
		assertNotNull(searchController.getXmlParser());
	}

	@Test
	public void testIsThereConnection() {
		assertFalse(searchController.isThereConnection());
	}

	@Test
	public void testGetOfflineText() {
		assertNull(searchController.getOfflineText());
	}

	@Test
	public void testSetOfflineText() {
		String expectedReturn = "textoffline";
		searchController.setOfflineText(expectedReturn);
		String actualReturn = searchController.getOfflineText();
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testSetXmlParser() {
		XMLParser newXMLParser = new XMLParser();
		searchController.setXmlParser(newXMLParser);
		assertSame(newXMLParser, searchController.getXmlParser());
	}

	@Test
	public void testSetIsThereConnection() {
		searchController.setConnection(true);
		assertTrue(searchController.isThereConnection());
	}

	@Test
	public void testReceiveXML() {
		String responseEsperada = "<?xml version=\"1.0\" encoding=\"utf-8\"?><proposicoes>  <proposicao>    <id>596039</id>    <nome>PL 6555/2013</nome>    <tipoProposicao>      <id>139</id>      <sigla>PL</sigla>      <nome>Projeto de Lei</nome>    </tipoProposicao>    <numero>6555</numero>    <ano>2013</ano>    <orgaoNumerador>      <id>180</id>      <sigla>PLEN      </sigla>      <nome>PLENÁRIO</nome>    </orgaoNumerador>    <datApresentacao>10/10/2013 11:04:32</datApresentacao>    <txtEmenta>Institui o Dia Nacional do Blogueiro.</txtEmenta>    <txtExplicacaoEmenta>    </txtExplicacaoEmenta>    <regime>      <codRegime>99</codRegime>      <txtRegime>.</txtRegime>    </regime>    <apreciacao>      <id>99</id>      <txtApreciacao>.</txtApreciacao>    </apreciacao>    <autor1>      <txtNomeAutor>Andre Moura</txtNomeAutor>      <idecadastro>160543</idecadastro>      <codPartido>126</codPartido>      <txtSiglaPartido>PSC       </txtSiglaPartido>      <txtSiglaUF>SE</txtSiglaUF>    </autor1>    <qtdAutores>1</qtdAutores>    <ultimoDespacho>      <datDespacho>29/10/2013 14:44:00</datDespacho>      <txtDespacho>Devolva-se a proposição, com base no art. 137, §1º, inciso I, do Regimento Interno da Câmara dos Deputados, por contrariar o disposto no art. 4º da Lei nº 12.345/2010. Oficie-se ao Autor e, após, publique-se. </txtDespacho>    </ultimoDespacho>    <situacao>      <id>918</id>      <descricao>.</descricao>      <orgao>        <codOrgaoEstado>4</codOrgaoEstado>        <siglaOrgaoEstado>Diversos  </siglaOrgaoEstado>      </orgao>      <principal>        <codProposicaoPrincipal>0</codProposicaoPrincipal>        <proposicaoPrincipal>        </proposicaoPrincipal>      </principal>    </situacao>    <indGenero>o</indGenero>    <qtdOrgaosComEstado>2</qtdOrgaosComEstado>  </proposicao></proposicoes>";

		SearchForProjectController.updateDataFromProjectSearch("2013", "PL", "6555", "");
		SearchForPoliticalPartyController.updateDataForPoliticalPartySearch("SE", "");
		SearchForParliamentaryController.updateDataFromParliamentarySearch("");
		searchController.setConnection(true);
		String responseRetornada = searchController.receiveXML();

		/* as falhas deste teste estao atribuidas a coisas como ex: PLENÁRIO > PLENÃ�RIO */
		assertEquals(responseEsperada, responseRetornada);
	}

	@Test
	public void testReceiveXMLWithoutConnection() {
		searchController.setConnection(false);
		String textoOfflineEsperado = null;
		String textoOfflineRetornado = searchController.receiveXML();
		assertEquals(textoOfflineEsperado, textoOfflineRetornado);
	}

	@Test
	public void testSearchAndReturnAListOfProjects() {
		XMLParser xmlParser = new XMLParser();
		String expectedResponseAsXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?><proposicoes>  <proposicao>    <id>596039</id>    <nome>PL 6555/2013</nome>    <tipoProposicao>      <id>139</id>      <sigla>PL</sigla>      <nome>Projeto de Lei</nome>    </tipoProposicao>    <numero>6555</numero>    <ano>2013</ano>    <orgaoNumerador>      <id>180</id>      <sigla>PLEN      </sigla>      <nome>PLENÁRIO</nome>    </orgaoNumerador>    <datApresentacao>10/10/2013 11:04:32</datApresentacao>    <txtEmenta>Institui o Dia Nacional do Blogueiro.</txtEmenta>    <txtExplicacaoEmenta>    </txtExplicacaoEmenta>    <regime>      <codRegime>99</codRegime>      <txtRegime>.</txtRegime>    </regime>    <apreciacao>      <id>99</id>      <txtApreciacao>.</txtApreciacao>    </apreciacao>    <autor1>      <txtNomeAutor>Andre Moura</txtNomeAutor>      <idecadastro>160543</idecadastro>      <codPartido>126</codPartido>      <txtSiglaPartido>PSC       </txtSiglaPartido>      <txtSiglaUF>SE</txtSiglaUF>    </autor1>    <qtdAutores>1</qtdAutores>    <ultimoDespacho>      <datDespacho>29/10/2013 14:44:00</datDespacho>      <txtDespacho>Devolva-se a proposição, com base no art. 137, §1º, inciso I, do Regimento Interno da Câmara dos Deputados, por contrariar o disposto no art. 4º da Lei nº 12.345/2010. Oficie-se ao Autor e, após, publique-se. </txtDespacho>    </ultimoDespacho>    <situacao>      <id>918</id>      <descricao>.</descricao>      <orgao>        <codOrgaoEstado>4</codOrgaoEstado>        <siglaOrgaoEstado>Diversos  </siglaOrgaoEstado>      </orgao>      <principal>        <codProposicaoPrincipal>0</codProposicaoPrincipal>        <proposicaoPrincipal>        </proposicaoPrincipal>      </principal>    </situacao>    <indGenero>o</indGenero>    <qtdOrgaosComEstado>2</qtdOrgaosComEstado>  </proposicao></proposicoes>";
		ArrayList<ProjectModel> expectedList = xmlParser.projectParser(expectedResponseAsXML);

		SearchForProjectController.updateDataFromProjectSearch("2013", "PL", "6555", "");
		SearchForPoliticalPartyController.updateDataForPoliticalPartySearch("SE", "");
		SearchForParliamentaryController.updateDataFromParliamentarySearch("");
		searchController.setConnection(true);
		ArrayList<ProjectModel> actualReturnedList = searchController.searchIntoXML();
		assertNotNull(actualReturnedList); /* O teste nao consegue fazer o metodo procurar retornar a lista, mesmo tendo o link correto */
		assertEquals(expectedList, actualReturnedList);
	}

	@Test
	public void testUpdateDataForASearch() {
		boolean returnedValue = searchController.updateDataInsideTheSearch("2013", "Projeto de Lei", "1234",
				"12/44/1234", "nomeautor", "pmds", "Distrito Federal");
		assertTrue(returnedValue);
	}

	@Test
	public void testUpdateDataForASearchWithErrors() {
		boolean returnedValue = searchController.updateDataInsideTheSearch("2013", "Projeto de Lei", "1234",
				"12/44/1234", "12345", "pmds", "Distrito Federal");
		assertFalse(returnedValue);
	}

	@Test
	public void testUpdateDataForASearchWithEmptyFields() {
		boolean dataToBeValidated = searchController.updateDataInsideTheSearch("", "Projeto de Lei", "", "", "", "",
				"Todos os Estados");
		assertTrue(dataToBeValidated);
	}

	@Test 
	public void testUpdateDataForASearchWithKindOfProjectButOtherFieldsEmpty() {
		boolean dataToBeValidated = searchController.updateDataInsideTheSearch("", "Projeto de Lei", "", "", "",
				"Todos os Partidos", "Todos os Estados");
		assertTrue(dataToBeValidated);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testTransformUFCatchingAnException() {
		String resultFromTransformation;
		searchController.transformStateAbbreviation("uf invalida");
	}

	@Test
	public void testTransformUfPassingTodosOsEstadosAsArgument() {
		String expectedReturn = "";
		String actualReturn = searchController.transformStateAbbreviation("Todos os estados");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingAcreAsArgument() {
		String expectedReturn = "AC";
		String actualReturn = searchController.transformStateAbbreviation("Acre");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingAlagoasAsArgument() {
		String expectedReturn = "AL";
		String actualReturn = searchController.transformStateAbbreviation("Alagoas");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingAmapaAsArgument() {
		String expectedReturn = "AP";
		String actualReturn = searchController.transformStateAbbreviation("Amapa");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingAmazonasAsArgument() {
		String expectedReturn = "AM";
		String actualReturn = searchController.transformStateAbbreviation("Amazonas");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingBahiaAsArgument() {
		String expectedReturn = "BA";
		String actualReturn = searchController.transformStateAbbreviation("Bahia");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingCearaAsArgument() {
		String expectedReturn = "CE";
		String actualReturn = searchController.transformStateAbbreviation("Ceara");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingDistritoFederalAsArgument() {
		String expectedReturn = "DF";
		String actualReturn = searchController.transformStateAbbreviation("Distrito Federal");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassinEspiritoSantoAsArgument() {
		String expectedReturn = "ES";
		String actualReturn = searchController.transformStateAbbreviation("Espirito Santo");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingGoiasAsArgument() {
		String expectedReturn = "GO";
		String actualReturn = searchController.transformStateAbbreviation("Goias");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingMaranhaoAsArgument() {
		String expectedReturn = "MA";
		String actualReturn = searchController.transformStateAbbreviation("Maranhao");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingMatoGrossoAsArgument() {
		String expectedReturn = "MT";
		String actualReturn = searchController.transformStateAbbreviation("Mato Grosso");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingMatoGrossoDoSulAsArgument() {
		String expectedReturn = "MS";
		String actualReturn = searchController.transformStateAbbreviation("Mato Grosso do Sul");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingMinasGeraisAsArgument() {
		String expectedReturn = "MG";
		String actualReturn = searchController.transformStateAbbreviation("Minas Gerais");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingParaAsArgument() {
		String expectedReturn = "PA";
		String actualReturn = searchController.transformStateAbbreviation("Para");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingParaibaAsArgument() {
		String expectedReturn = "PB";
		String actualReturn = searchController.transformStateAbbreviation("Paraiba");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingParanaAsArgument() {
		String expectedReturn = "PR";
		String actualReturn = searchController.transformStateAbbreviation("Parana");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingPernambucoAsArgument() {
		String expectedReturn = "PE";
		String actualReturn = searchController.transformStateAbbreviation("Pernambuco");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingPiauiAsArgument() {
		String expectedReturn = "PI";
		String actualReturn = searchController.transformStateAbbreviation("Piaui");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingRioDeJaneiroAsArgument() {
		String expectedReturn = "RJ";
		String actualReturn = searchController.transformStateAbbreviation("Rio de Janeiro");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingRioGrandeDoNorteAsArgument() {
		String expectedReturn = "RN";
		String actualReturn = searchController.transformStateAbbreviation("Rio Grande do Norte");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingRioGrandeDoSulAsArgument() {
		String expectedReturn = "RS";
		String actualReturn = searchController.transformStateAbbreviation("Rio Grande do Sul");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingRondoniaAsArgument() {
		String expectedReturn = "RO";
		String actualReturn = searchController.transformStateAbbreviation("Rondonia");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingRoraimaAsArgument() {
		String expectedReturn = "RR";
		String actualReturn = searchController.transformStateAbbreviation("Roraima");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingSantaCatarinaAsArgument() {
		String expectedReturn = "SC";
		String actualReturn = searchController.transformStateAbbreviation("Santa Catarina");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingSaoPauloAsArgument() {
		String expectedReturn = "SP";
		String actualReturn = searchController.transformStateAbbreviation("Sao Paulo");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingSergipeAsArgument() {
		String expectedReturn = "SE";
		String actualReturn = searchController.transformStateAbbreviation("Sergipe");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformUfPassingTocantinsAsArgument() {
		String expectedReturn = "TO";
		String actualReturn = searchController.transformStateAbbreviation("Tocantins");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testTransformAcronymCathingException() {
		String actualReturn = searchController.transformAcronym("sigla invalida");
		fail("teste falhou");
	}

	@Test
	public void testTransformAcronymPassingProjetoDeLeiAsArgument() {
		String expectedReturn = "PL";
		String actualReturn = searchController.transformAcronym("Projeto de Lei");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformAcronymPassingProjetoDeEmendaConstitucionalAsArgument() {
		String expectedReturn = "PEC";
		String actualReturn = searchController.transformAcronym("Projeto de Emenda Constitucional");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformAcronymPassingProjetoDeLeiComplementarAsArgument() {
		String expectedReturn = "PLP";
		String actualReturn = searchController.transformAcronym("Projeto de Lei Complementar");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformAcronymPassingProjetosDeDecretoLegislativoAsArgument() {
		String expectedReturn = "PDC";
		String actualReturn = searchController.transformAcronym("Projetos de Decreto Legislativo");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testTransformAcronymPassingProjetoDeResolucaoAsArgument() {
		String expectedReturn = "PRC";
		String actualReturn = searchController.transformAcronym("Projeto de Resolucao");
		assertEquals(expectedReturn, actualReturn);
	}

	@Test
	public void testNameOfTheClass() {
		assertEquals("SearchController", searchController.getClass().getSimpleName());
	}

}
