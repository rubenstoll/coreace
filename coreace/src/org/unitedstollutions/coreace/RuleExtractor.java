/**
 * 
 */
package org.unitedstollutions.coreace;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.util.*;

/**
 * @author yurchyshyna
 * 
 */
public class RuleExtractor extends DefaultHandler {

	private List<AnnotationRegle> annotationRules;
	private String data;
	private AnnotationRegle currAnnoRegle;
	

	/**
	 * 
	 * @return collected rules
	 */
	public ArrayList<AnnotationRegle> getCollectedRules() {
		return (ArrayList<AnnotationRegle>)annotationRules;
	}
	
	public AnnotationRegle getCurrentAnnotation() {
		return currAnnoRegle;
	}
	
	
	/*
	 * ***********************************************************************
	 * Overriden methods
	 * ***********************************************************************
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */
	public void characters(char[] text, int start, int length)
			throws SAXException {

		data = new String(text, start, length);

	}

	/**
	 * 
	 */
	public void endDocument() throws SAXException {

//		System.out.println("Rule parsing done!");

	}

	/**
	 * 
	 */
	public void endElement(String uri, String localName, String name)
			throws SAXException {

		// add more compare here to add more properties to the AnnotationRegle
		// instance
		if (name.compareTo("domaineApplication") == 0) {
			currAnnoRegle.setDomaineApplication(data);
		} else if (name.compareTo("actualiteRegle") == 0) {
			currAnnoRegle.setActualiteRegle(data);
		} else if (name.compareTo("thematiqueRegle") == 0) {
			currAnnoRegle.setThematiqueRegle(data);
		} else if (name.compareTo("exigenceRegle") == 0) {
			currAnnoRegle.setExigenceRegle(data);
		} else if (name.compareTo("thematiqueRegle") == 0) {
			currAnnoRegle.setThematiqueRegle(data);
		} else if (name.compareTo("contenuRegle") == 0) {
			currAnnoRegle.setContenuRegle(data);
		} else if (name.compareTo("destinationRegle") == 0) {
			currAnnoRegle.setDestinationRegle(data);
		} else if (name.compareTo("AnnotationRegle") == 0) {
			// the the filled AnnotationRegle instance to the list
			annotationRules.add(currAnnoRegle);
		}

	}

	/**
	 * 
	 */
	public void startDocument() throws SAXException {

//		 System.out.println("Start of the document");
		if(annotationRules == null) {
			annotationRules = new ArrayList<AnnotationRegle>();
		}

	}

	/**
	 * 
	 */
	public void startElement(String uri, String localName, String name,
			Attributes atts) throws SAXException {

		if (name.compareTo("AnnotationRegle") == 0) {
			currAnnoRegle = new AnnotationRegle();
		}

	}

}
