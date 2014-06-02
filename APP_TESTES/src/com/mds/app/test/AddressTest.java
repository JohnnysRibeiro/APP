package com.mds.app.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.AndroidTestCase;

import com.mds.app.services.Address;

public class AddressTest extends AndroidTestCase {

	Address address;

	@Before
	public void setUp() throws Exception {
		address = new Address() {
		};
	}

	@After
	public void tearDown() throws Exception {
		address = null;
	}

	@Test
	public void testBuildAnAndress() {
		//String enderecoEsperado = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PL&numero=&ano=2011&datApresentacaoIni=14/11/2011&datApresentacaoFim=16/11/2011&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=1";
		//hotfix
		
		String expectedAddress = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PL&numero=&ano=2011&datApresentacaoIni=14/11/2011&datApresentacaoFim=16/11/2011&autor=&parteNomeAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&idTipoAutor=&codOrgaoEstado=&emTramitacao=1";

		
		Address.kindOfProjectAcronym = "PL";
		Address.number = "";
		Address.year = "2011";
		Address.initialDate = "14/11/2011";
		Address.finalDate = "16/11/2011";
		Address.author = "";
		Address.authorName = "";
		Address.politicalPartyAcronym = "";
		Address.stateAbbreviation = "";
		Address.authorGender = "";
		Address.stateCode = "";
		Address.organStateCode = "";
		String returnedAddress = Address.buildAddress();
		System.out.println(returnedAddress);

		assertEquals(expectedAddress, returnedAddress);
	}
}
