package com.mds.app.services;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.mds.app.controller.ProposicaoController;
import com.mds.app.model.ProjetoModel;

public class XMLParser {

	private XMLReader inicializarLeitor() throws ParserConfigurationException, SAXException {
		// Instanciando e criando o parser
		SAXParserFactory criaParser = SAXParserFactory.newInstance();
		SAXParser parser = criaParser.newSAXParser();

		// Criando o metodo de entrada para ler o XML
		XMLReader leitorXml = parser.getXMLReader();

		return leitorXml;
	}

	public ArrayList<ProjetoModel> parseProjeto(String xml) {

		try {
			XMLReader leitorXml;
			leitorXml = inicializarLeitor();

			ProposicaoController projeto = new ProposicaoController();

			// atribuir o nosso manipulador
			leitorXml.setContentHandler(projeto);

			// Sincronizando o parser com o XML
			leitorXml.parse(new InputSource(new StringReader(xml)));

			return projeto.getListaProjetos();

		} catch (ParserConfigurationException pce) {
			//
		} catch (SAXException saxe) {
			//
		} catch (IOException ioe) {
			//
		} catch (Exception e) {
			//
		}

		return null;

	}

}