package org.unitedstollutions.coreace;

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;

import java.io.*;
import java.util.*;

/**
 * @author yurchyshyna
 *
 */
public class IfcReader {

	private XMLReader parser;
	private RuleExtractor handler;
	private List<AnnotationRegle> someList;  // this is temporary - replace by a relevant list
	
	//const for testing
	private String ifcUri;
	private String ifcFile;
	private String ifcPath;
	
	public IfcReader(String ifcPath) {
		this.ifcPath = ifcPath;
		
	}
	
	public IfcReader(String ifcUri, String ifcFile) {
		this.ifcUri = ifcUri;
		this.ifcFile = ifcFile;
		this.ifcPath = ifcUri + "/" + ifcFile; 
	}
	

	
	/**
	 * Sets the path to the ifc 
	 * @param uri
	 */
	public void setIfcUri(String uri) {
		this.ifcUri = uri;
	}
	
	
	/**
	 * Sets the ifc file to examine
	 * 
	 * @param ifc
	 */
	public void setIfcFile(String ifc) {
		//TO DO apply xslt transformation class XSLTransf
		this.ifcFile = ifc; 
	}
	
	
	/**
	 * Sets the path complete of the ifc file to examine
	 * 
	 * @param path
	 */
	public void setIfcPath(String path) {
		this.ifcPath = path;
	}
	
	/**
	 * Create a parser object, depending on which parser is available.
	 * 
	 */
	private void createReader() {

		try {
			parser = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
		} catch (SAXException e1) {
			try {
				parser = XMLReaderFactory.createXMLReader();
			} catch (SAXException e2) {
				throw new NoClassDefFoundError("No SAX parser is available");
				// or whatever exception your method is declared to throw
			}
		}

	}

	/**
	 * Creates a content handler
	 * 
	 * @param out
	 */
	private void registerContentHandler(Writer out) {

		handler = new RuleExtractor();

	}

	/**
	 * 
	 * @param out
	 */
	private void registerErrorHandler(Writer out) {

		//handler = new RuleExtractor(out);
		
	}

	
	/**
	 *  Creates an XML parser and reads instance's specified ifc 
	 * 
	 */
	public void parse() {

		// create a parser instance
		createReader();


		// Since this just writes onto the console, it's best
		// to use the system default encoding, which is what
		// we get by not specifying an explicit encoding here.
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

		// register a content handler
		registerContentHandler(out);

		// register a error handler
		// registerErrorHandler(out);

		parser.setContentHandler(handler);

		try {

			parser.parse(ifcPath);
			
			out.flush();

		} catch (IOException e) {
			System.out.println("Parsing IO error occurred");
			e.printStackTrace(System.out);
		} catch (SAXException e) {
			System.out.println("SAX exception");
			System.out.println(ifcPath + " is not well-formed.");
		}

	}
	public void parse(String fileToParse) {

		createReader();
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
		registerContentHandler(out);
		parser.setContentHandler(handler);
		try {
			parser.parse(fileToParse);			
			out.flush();
		} catch (IOException e) {
			System.out.println("Parsing IO error occurred"+e.getMessage());
			e.printStackTrace(System.out);
		} catch (SAXException e) {
			System.out.println("SAX exception");
			System.out.println(fileToParse + " is not well-formed.");
		}
	}
	
    /*small function indicating 
     * if annoFilePath contains the tagName which value is tagValue 
     * 
     * */

	
	private boolean hasThisTag(String tagName) {
    	boolean hasTag = false; 
    	// open file for parsing 
    	//if start element which has getLocalName = tagName 
    	// then hasTag = true; 
		return hasTag;

	}

	private boolean hasThisTagValue(String tagName, String tagValue) {
    	boolean hasTag = false; 
    	// open file for parsing 
    	// if start element which has getLocalName(element) = tagName && atts.getLocalName(element) = tagValue
    	// then hasTag = true; 
		return hasTag;

	}
	
}
