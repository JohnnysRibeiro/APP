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
	public void testIsTemConexao() {
		assertFalse(searchController.isThereConnection());
	}

	@Test
	public void testGetTextoOffline() {
		assertNull(searchController.getOfflineText());
	}

	@Test
	public void testSetTextoOffline() {
		String esperada = "textoffline";
		searchController.setOfflineText(esperada);
		String retornada = searchController.getOfflineText();
		assertEquals(esperada, retornada);
	}

	@Test
	public void testSetXmlParser() {
		XMLParser novoXmlParser = new XMLParser();
		searchController.setXmlParser(novoXmlParser);
		assertSame(novoXmlParser, searchController.getXmlParser());
	}

	@Test
	public void testSetTemConexao() {
		searchController.setConnection(true);
		assertTrue(searchController.isThereConnection());
	}

	@Test
	public void testReceberXml() {
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
	public void testReceberXmlSemConexao() {
		searchController.setConnection(false);
		String textoOfflineEsperado = null;
		String textoOfflineRetornado = searchController.receiveXML();
		assertEquals(textoOfflineEsperado, textoOfflineRetornado);
	}

	@Test
	public void testProcurar() {
		XMLParser xmlParser = new XMLParser();
		String responseEsperada = "<?xml version=\"1.0\" encoding=\"utf-8\"?><proposicoes>  <proposicao>    <id>596039</id>    <nome>PL 6555/2013</nome>    <tipoProposicao>      <id>139</id>      <sigla>PL</sigla>      <nome>Projeto de Lei</nome>    </tipoProposicao>    <numero>6555</numero>    <ano>2013</ano>    <orgaoNumerador>      <id>180</id>      <sigla>PLEN      </sigla>      <nome>PLENÁRIO</nome>    </orgaoNumerador>    <datApresentacao>10/10/2013 11:04:32</datApresentacao>    <txtEmenta>Institui o Dia Nacional do Blogueiro.</txtEmenta>    <txtExplicacaoEmenta>    </txtExplicacaoEmenta>    <regime>      <codRegime>99</codRegime>      <txtRegime>.</txtRegime>    </regime>    <apreciacao>      <id>99</id>      <txtApreciacao>.</txtApreciacao>    </apreciacao>    <autor1>      <txtNomeAutor>Andre Moura</txtNomeAutor>      <idecadastro>160543</idecadastro>      <codPartido>126</codPartido>      <txtSiglaPartido>PSC       </txtSiglaPartido>      <txtSiglaUF>SE</txtSiglaUF>    </autor1>    <qtdAutores>1</qtdAutores>    <ultimoDespacho>      <datDespacho>29/10/2013 14:44:00</datDespacho>      <txtDespacho>Devolva-se a proposição, com base no art. 137, §1º, inciso I, do Regimento Interno da Câmara dos Deputados, por contrariar o disposto no art. 4º da Lei nº 12.345/2010. Oficie-se ao Autor e, após, publique-se. </txtDespacho>    </ultimoDespacho>    <situacao>      <id>918</id>      <descricao>.</descricao>      <orgao>        <codOrgaoEstado>4</codOrgaoEstado>        <siglaOrgaoEstado>Diversos  </siglaOrgaoEstado>      </orgao>      <principal>        <codProposicaoPrincipal>0</codProposicaoPrincipal>        <proposicaoPrincipal>        </proposicaoPrincipal>      </principal>    </situacao>    <indGenero>o</indGenero>    <qtdOrgaosComEstado>2</qtdOrgaosComEstado>  </proposicao></proposicoes>";
		ArrayList<ProjectModel> listaEsperada = xmlParser.projectParser(responseEsperada);

		SearchForProjectController.updateDataFromProjectSearch("2013", "PL", "6555", "");
		SearchForPoliticalPartyController.updateDataForPoliticalPartySearch("SE", "");
		SearchForParliamentaryController.updateDataFromParliamentarySearch("");
		searchController.setConnection(true);
		ArrayList<ProjectModel> listaRetornada = searchController.searchIntoXML();

		assertNotNull(listaRetornada); /* O teste nao consegue fazer o metodo procurar retornar a lista, mesmo tendo o link correto */
		assertEquals(listaEsperada, listaRetornada);
	}

	@Test
	public void testAtualizarDadosDaPesquisa() {
		boolean retornado = searchController.updateDataInsideTheSearch("2013", "Projeto de Lei", "1234",
				"12/44/1234", "nomeautor", "pmds", "Distrito Federal");
		assertTrue(retornado);
	}

	@Test
	public void testAtualizarDadosDaPesquisaErros() {
		boolean retornado = searchController.updateDataInsideTheSearch("2013", "Projeto de Lei", "1234",
				"12/44/1234", "12345", "pmds", "Distrito Federal");
		assertFalse(retornado);
	}

	@Test
	public void testAtualizarDadosDaPesquisaVazios() {
		boolean validacao = searchController.updateDataInsideTheSearch("", "Projeto de Lei", "", "", "", "",
				"Todos os Estados");
		assertTrue(validacao);
	}

	@Test
	public void testAtualizarDadosDaPesquisaVaziosESiglaTodos() {
		boolean validacao = searchController.updateDataInsideTheSearch("", "Projeto de Lei", "", "", "",
				"Todos os Partidos", "Todos os Estados");
		assertTrue(validacao);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransformaUfCatchException() {
		String resultado = searchController.transformStateAbbreviation("uf invalida");
		fail("teste falhou");
	}

	@Test
	public void testTransformaUfTodos() {
		String ufEsperada = "";
		String resultado = searchController.transformStateAbbreviation("Todos os estados");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfAcre() {
		String ufEsperada = "AC";
		String resultado = searchController.transformStateAbbreviation("Acre");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfAlagoas() {
		String ufEsperada = "AL";
		String resultado = searchController.transformStateAbbreviation("Alagoas");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfAmapa() {
		String ufEsperada = "AP";
		String resultado = searchController.transformStateAbbreviation("Amapá");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfAmazonas() {
		String ufEsperada = "AM";
		String resultado = searchController.transformStateAbbreviation("Amazonas");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfBahia() {
		String ufEsperada = "BA";
		String resultado = searchController.transformStateAbbreviation("Bahia");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfCeara() {
		String ufEsperada = "CE";
		String resultado = searchController.transformStateAbbreviation("Ceará");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfDistritoFederal() {
		String ufEsperada = "DF";
		String resultado = searchController.transformStateAbbreviation("Distrito Federal");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfEspIritoSanto() {
		String ufEsperada = "ES";
		String resultado = searchController.transformStateAbbreviation("Espírito Santo");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfGoias() {
		String ufEsperada = "GO";
		String resultado = searchController.transformStateAbbreviation("Goiás");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfMaranhao() {
		String ufEsperada = "MA";
		String resultado = searchController.transformStateAbbreviation("Maranhão");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfMatoGrosso() {
		String ufEsperada = "MT";
		String resultado = searchController.transformStateAbbreviation("Mato Grosso");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfMatoGrossoDoSul() {
		String ufEsperada = "MS";
		String resultado = searchController.transformStateAbbreviation("Mato Grosso do Sul");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfMinasGerais() {
		String ufEsperada = "MG";
		String resultado = searchController.transformStateAbbreviation("Minas Gerais");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfPara() {
		String ufEsperada = "PA";
		String resultado = searchController.transformStateAbbreviation("Pará");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfParaiba() {
		String ufEsperada = "PB";
		String resultado = searchController.transformStateAbbreviation("Paraíba");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfParana() {
		String ufEsperada = "PR";
		String resultado = searchController.transformStateAbbreviation("Paraná");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfPernambuco() {
		String ufEsperada = "PE";
		String resultado = searchController.transformStateAbbreviation("Pernambuco");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfPiaui() {
		String ufEsperada = "PI";
		String resultado = searchController.transformStateAbbreviation("Piauí");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfRioDeJaneiro() {
		String ufEsperada = "RJ";
		String resultado = searchController.transformStateAbbreviation("Rio de Janeiro");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfRioGrandeDoNorte() {
		String ufEsperada = "RN";
		String resultado = searchController.transformStateAbbreviation("Rio Grande do Norte");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfRioGrandeDoSul() {
		String ufEsperada = "RS";
		String resultado = searchController.transformStateAbbreviation("Rio Grande do Sul");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfRondonia() {
		String ufEsperada = "RO";
		String resultado = searchController.transformStateAbbreviation("Rondônia");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfRoraima() {
		String ufEsperada = "RR";
		String resultado = searchController.transformStateAbbreviation("Roraima");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfSantaCatarina() {
		String ufEsperada = "SC";
		String resultado = searchController.transformStateAbbreviation("Santa Catarina");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfSaoPaulo() {
		String ufEsperada = "SP";
		String resultado = searchController.transformStateAbbreviation("São Paulo");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfSergipe() {
		String ufEsperada = "SE";
		String resultado = searchController.transformStateAbbreviation("Sergipe");
		assertEquals(ufEsperada, resultado);
	}

	@Test
	public void testTransformaUfTocantins() {
		String ufEsperada = "TO";
		String resultado = searchController.transformStateAbbreviation("Tocantins");
		assertEquals(ufEsperada, resultado);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTransformaSiglaCatchException() {
		String retornado = searchController.transformAcronym("sigla invalida");
		fail("teste falhou");
	}

	@Test
	public void testTransformaSiglaPL() {
		String siglaEsperada = "PL";
		String retornado = searchController.transformAcronym("Projeto de Lei");
		assertEquals(siglaEsperada, retornado);
	}

	@Test
	public void testTransformaSiglaPEC() {
		String siglaEsperada = "PEC";
		String retornado = searchController.transformAcronym("Projeto de Emenda à Constituição");
		assertEquals(siglaEsperada, retornado);
	}

	@Test
	public void testTransformaSiglaPLP() {
		String siglaEsperada = "PLP";
		String retornado = searchController.transformAcronym("Projeto de Lei Complementar");
		assertEquals(siglaEsperada, retornado);
	}

	@Test
	public void testTransformaSiglaPDC() {
		String siglaEsperada = "PDC";
		String retornado = searchController.transformAcronym("Projetos de Decreto Legislativo");
		assertEquals(siglaEsperada, retornado);
	}

	@Test
	public void testTransformaSiglaPRC() {
		String siglaEsperada = "PRC";
		String retornado = searchController.transformAcronym("Projeto de Resolução");
		assertEquals(siglaEsperada, retornado);
	}

	@Test
	public void testarNomeDaClasse() {
		assertEquals("BuscaController", searchController.getClass().getSimpleName());
	}

}
