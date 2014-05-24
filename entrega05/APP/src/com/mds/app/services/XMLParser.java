/* File: XMLParser.java
 * 
 * Package: com.mds.app.services
 * 
 * Description: This is a service class responsible for parsing an XML information to something useful for the application. It is used
 * for passing informations about projects, parliamentaries, etc.
 *
 */

package com.mds.app.services;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.mds.app.controller.PropositionController;
import com.mds.app.model.ProjectModel;

public class XMLParser {

	public XMLReader readerStarter() throws ParserConfigurationException, SAXException {
		SAXParserFactory createParser = SAXParserFactory.newInstance();

		// Instancing and creating parser
		SAXParser parser = createParser.newSAXParser();

		// Creating the enter method to read XML file
		XMLReader xmlReader = parser.getXMLReader();

		return xmlReader;
	}

	public ArrayList<ProjectModel> projectParser(String xml) {

		try {

			XMLReader xmlReader = readerStarter();

			PropositionController project = new PropositionController();

			// assign our manipulator
			xmlReader.setContentHandler(project);
			// Sync parser with XML
			xmlReader.parse(new InputSource(new StringReader(xml)));

			return project.getListOfProjects();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}