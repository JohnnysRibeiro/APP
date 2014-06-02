package com.mds.app.test;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.test.AndroidTestCase;

import com.mds.app.services.XMLParser;

public class XMLParserTest extends AndroidTestCase {

	private XMLParser xmlParser;

	@Before
	public void setUp() throws Exception {
		xmlParser = new XMLParser();
	}

	@After
	public void tearDown() throws Exception {
		xmlParser = null;
	}

	@Test
	public void testInicializarLeitor() throws ParserConfigurationException, SAXException {
		XMLParser teste = new XMLParser();
		XMLReader leitorXml = teste.readerStarter();
		assertNotNull(leitorXml);
	}

	@Test
	public void testarNomeClasse() {
		Assert.assertEquals("XMLParser", xmlParser.getClass().getSimpleName());
	}

	@Test
	public void testParseProjeto() {
		/* Melhorar */
		String entradaXML = "<proposicoes>  <proposicao>    <id>596039</id>    <nome>PL 6555/2013</nome>    <tipoProposicao>      <id>139</id>      <sigla>PL</sigla>      <nome>Projeto de Lei</nome>    </tipoProposicao>    <numero>6555</numero>    <ano>2013</ano>    <orgaoNumerador>      <id>180</id>      <sigla>PLEN      </sigla>      <nome>PLEN�RIO</nome>    </orgaoNumerador>    <datApresentacao>10/10/2013 11:04:32</datApresentacao>    <txtEmenta>Institui o Dia Nacional do Blogueiro.</txtEmenta>    <txtExplicacaoEmenta>    </txtExplicacaoEmenta>    <regime>      <codRegime>99</codRegime>      <txtRegime>.</txtRegime>    </regime>    <apreciacao>      <id>99</id>      <txtApreciacao>.</txtApreciacao>    </apreciacao>    <autor1>      <txtNomeAutor>Andre Moura</txtNomeAutor>      <idecadastro>160543</idecadastro>      <codPartido>126</codPartido>      <txtSiglaPartido>PSC       </txtSiglaPartido>      <txtSiglaUF>SE</txtSiglaUF>    </autor1>    <qtdAutores>1</qtdAutores>    <ultimoDespacho>      <datDespacho>29/10/2013 14:44:00</datDespacho>      <txtDespacho>Devolva-se a proposi��o, com base no art. 137, �1�, inciso I, do Regimento Interno da C�mara dos Deputados, por contrariar o disposto no art. 4� da Lei n� 12.345/2010. Oficie-se ao Autor e, ap�s, publique-se. </txtDespacho>    </ultimoDespacho>    <situacao>      <id>918</id>      <descricao>.</descricao>      <orgao>        <codOrgaoEstado>4</codOrgaoEstado>        <siglaOrgaoEstado>Diversos  </siglaOrgaoEstado>      </orgao>      <principal>        <codProposicaoPrincipal>0</codProposicaoPrincipal>        <proposicaoPrincipal>        </proposicaoPrincipal>      </principal>    </situacao>    <indGenero>o</indGenero>    <qtdOrgaosComEstado>2</qtdOrgaosComEstado>  </proposicao></proposicoes>";
		assertNotNull(xmlParser.projectParser(entradaXML));
	}

	@Test
	public void testExceptionParseProjeto() {
		assertNull(xmlParser.projectParser(null));
	}

}
