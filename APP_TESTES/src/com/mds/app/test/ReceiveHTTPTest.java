package com.mds.app.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.services.ReceiveHTTP;

public class ReceiveHTTPTest extends AndroidTestCase {

	public ReceiveHTTP receiveHTTP;
	public HttpGet getHTTP;
	public BufferedReader buffer;
	public HttpClient clientHTTP;

	@Before
	public void setUp() throws Exception {
		System.out.println("=============");
		receiveHTTP = new ReceiveHTTP() {
		};
	}

	@After
	public void tearDown() throws Exception {
		receiveHTTP = null;
		System.out.println("=============");
	}

//	@Test(expected = ClientProtocolException.class)
//	public void testCatchClientProtocolException() {
//		recebeHTTP.receive("ftp://192.168.1.1");
//	}
//
//	@Test(expected = NullPointerException.class)
//	public void testRecebeNull() {
//		recebeHTTP.receive(null);
//	}
//
//	@Test(expected = URISyntaxException.class)
//	public void testRecebeMalformedURI() {
//		/* Ele recebe a URISyntaxException, mas logo em seguida o NullPointerException, o que causa a falha do teste */
//		recebeHTTP.receive("http://www.^@!$#!&#*@!*asda");
//	}

	@Test
	public void testReceivingURL() {
		String urlForTesting = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PL&numero=6555&ano=2013&datApresentacaoIni=&datApresentacaoFim=&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=SE&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=1";
		String actualReturn = receiveHTTP.receive(urlForTesting);
		String expectedReturn = "<?xml version=\"1.0\" encoding=\"utf-8\"?><proposicoes>  <proposicao>    <id>596039</id>    <nome>PL 6555/2013</nome>    <tipoProposicao>      <id>139</id>      <sigla>PL</sigla>      <nome>Projeto de Lei</nome>    </tipoProposicao>    <numero>6555</numero>    <ano>2013</ano>    <orgaoNumerador>      <id>180</id>      <sigla>PLEN      </sigla>      <nome>PLENÃ�RIO</nome>    </orgaoNumerador>    <datApresentacao>10/10/2013 11:04:32</datApresentacao>    <txtEmenta>Institui o Dia Nacional do Blogueiro.</txtEmenta>    <txtExplicacaoEmenta>    </txtExplicacaoEmenta>    <regime>      <codRegime>99</codRegime>      <txtRegime>.</txtRegime>    </regime>    <apreciacao>      <id>99</id>      <txtApreciacao>.</txtApreciacao>    </apreciacao>    <autor1>      <txtNomeAutor>Andre Moura</txtNomeAutor>      <idecadastro>160543</idecadastro>      <codPartido>126</codPartido>      <txtSiglaPartido>PSC       </txtSiglaPartido>      <txtSiglaUF>SE</txtSiglaUF>    </autor1>    <qtdAutores>1</qtdAutores>    <ultimoDespacho>      <datDespacho>29/10/2013 14:44:00</datDespacho>      <txtDespacho>Devolva-se a proposiÃ§Ã£o, com base no art. 137, Â§1Âº, inciso I, do Regimento Interno da CÃ¢mara dos Deputados, por contrariar o disposto no art. 4Âº da Lei nÂº 12.345/2010. Oficie-se ao Autor e, apÃ³s, publique-se. </txtDespacho>    </ultimoDespacho>    <situacao>      <id>918</id>      <descricao>.</descricao>      <orgao>        <codOrgaoEstado>4</codOrgaoEstado>        <siglaOrgaoEstado>Diversos  </siglaOrgaoEstado>      </orgao>      <principal>        <codProposicaoPrincipal>0</codProposicaoPrincipal>        <proposicaoPrincipal>        </proposicaoPrincipal>      </principal>    </situacao>    <indGenero>o</indGenero>    <qtdOrgaosComEstado>2</qtdOrgaosComEstado>  </proposicao></proposicoes>";
		/* as falhas deste teste estao atribuidas a coisas como ex: PLENÁRIO > PLENÃ�RIO */
		assertEquals(expectedReturn, actualReturn);
	}

//	@Test(expected = IOException.class)
//	public void testCatchIOException() {
//		recebeHTTP.receive("http://www.fghjkauydqgvweudvwqd.com");
//	}

}
