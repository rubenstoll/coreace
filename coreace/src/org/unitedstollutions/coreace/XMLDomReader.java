/**
 * 
 */
package org.unitedstollutions.coreace;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLDomReader {

	/**
	 * Read the file by its name. Takes its elements
	 * 
	 */
 public XMLDomReader(String fileName) {

  try {
  File file = new File(fileName);
  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
  DocumentBuilder db = dbf.newDocumentBuilder();
  Document doc = db.parse(file);
  doc.getDocumentElement().normalize();
  System.out.println("Annotations are presented in : " + doc.getDocumentElement().getNodeName());
  NodeList nodeLst = doc.getElementsByTagName("AnnotationRegle");
  System.out.println("Useful Information of the Rule Annotation");

  for (int s = 0; s < nodeLst.getLength(); s++) {

    Node fstNode = nodeLst.item(s);
    
    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
  
           Element fstElmnt = (Element) fstNode;
      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("thematiqueRegle");
      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
      NodeList fstNm = fstNmElmnt.getChildNodes();
      System.out.println("thematiqueRegle : "  + ((Node) fstNm.item(0)).getNodeValue());
      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("destinationRegle");
      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
      NodeList lstNm = lstNmElmnt.getChildNodes();
      System.out.println("destinationRegle : " + ((Node) lstNm.item(0)).getNodeValue());
    }

  }
  } catch (Exception e) {
    e.printStackTrace();
  }
 }
 
 public XMLDomReader(String fileName, String rootName, String tagName) {

	  try {
	  File file = new File(fileName);
	  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	  DocumentBuilder db = dbf.newDocumentBuilder();
	  Document doc = db.parse(file);
	  doc.getDocumentElement().normalize();
	  System.out.println("Annotations are presented in : " + doc.getDocumentElement().getNodeName());
	  NodeList nodeLst = doc.getElementsByTagName(rootName);
	  System.out.println("Root is " + nodeLst);

	  for (int s = 0; s < nodeLst.getLength(); s++) {

	    Node fstNode = nodeLst.item(s);
	    
	    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
	  
	      Element fstElmnt = (Element) fstNode;
	      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagName);
	      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
	      NodeList fstNm = fstNmElmnt.getChildNodes();
	      System.out.println("the value of your tag name is : "  + ((Node) fstNm.item(0)).getNodeValue());
	    }

	  }
	  } catch (Exception e) {
	    e.printStackTrace();
	  }
	 }

 // If validating is true, the contents is validated against the DTD
 // specified in the file.
 public static Document parseXmlFile(String filename, boolean validating) {
     try {
         // Create a builder factory
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         factory.setValidating(validating);

         // Create the builder and parse the file
         Document doc = factory.newDocumentBuilder().parse(new File(filename));
         return doc;
     } catch (SAXException e) {
         // A parsing error occurred; the xml input is not valid
     } catch (ParserConfigurationException e) {
     } catch (IOException e) {
     }
     return null;
 } 
 
	/**
	 * Check if xml file has given tag. Returns boolean
	 * 
	 */
 public boolean hasValue(String fileName, String tagName){
	 boolean hasVal = true;
	 try {
		File file = new File(fileName);
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 DocumentBuilder db = dbf.newDocumentBuilder();
		 Document doc = db.parse(file);
		 doc.getDocumentElement().normalize();
		  
		 NodeList nodeLst = doc.getElementsByTagName(tagName);
		 if (nodeLst == null) {
			 hasVal = false;
		 }
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return hasVal;
 }
 
	/**
	 * Give the range of possible values of the given tag for a list of files
	 * 
	 */
 public ArrayList<String> rangeOfValues(ArrayList<String> fileNamesList, String tagName){
	 ArrayList<String> valuesList = new ArrayList<String>();
		try {
			for (String currentAnnoName : fileNamesList) {
				File file = new File(currentAnnoName);
				 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				 DocumentBuilder db = dbf.newDocumentBuilder();
				 Document doc = db.parse(file);
				 doc.getDocumentElement().normalize();			 
				 NodeList nodeLst = doc.getElementsByTagName(tagName);
				 
				 if (this.hasValue(currentAnnoName, tagName)) {
					 for (int s = 0; s < nodeLst.getLength(); s++) {
						    Node fstNode = nodeLst.item(s);
						    if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
						      Element fstElmnt = (Element) fstNode;
						      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName(tagName);
						      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
						      NodeList fstNm = fstNmElmnt.getChildNodes();
						      valuesList.add(((Node) fstNm.item(0)).getNodeValue());
						      System.out.println("the value of your tag name is : "  + ((Node) fstNm.item(0)).getNodeValue());
						    }
					 	}
				 }
			}
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return valuesList;
 }
 
}