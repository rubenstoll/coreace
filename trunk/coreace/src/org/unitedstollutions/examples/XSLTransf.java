/*package org.unitedstollutions.examples;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

*//**
 * @author Anastasiya
 *
 *//*
public class XSLTransf {
	// an identity copy stylesheet
	private static final String IDENTITY_XSLT = "<xsl:stylesheet xmlns:xsl='http://www.w3.org/1999/XSL/Transform'"
			+ " version='1.0'>"
			+ "<xsl:template match='/'><xsl:copy-of select='.'/>"
			+ "</xsl:template></xsl:stylesheet>";

	// the XML spec in XML format
	// (using an HTTP URL rather than a file URL)
	private static String xmlSystemId = "http://www.w3.org/TR/2000/REC-xml-20001006.xml";

	public static void main(String[] args) throws IOException,
			TransformerException {
		// show how to read from a system identifier and a Reader
		System.out.println("default settings");
		Source xmlSource = new StreamSource(
				"http://rainbow.essi.fr/~anastasiya/data/test_ifc.xml");
		//Source xsltSource = new StreamSource(new StringReader(IDENTITY_XSLT));
		// send the result to a file
		File resultFile = File.createTempFile("Streams", ".rdf");
		Result result = new StreamResult(resultFile);
		System.out.println("Results will go to: "
				+ resultFile.getAbsolutePath());
		// get the factory
		TransformerFactory transFact = TransformerFactory.newInstance();
		Source xsltSourceTest = new StreamSource(
				"http://rainbow.essi.fr/~anastasiya/data/test.xslt");
		// get a transformer for this particular stylesheet
		Transformer trans = transFact.newTransformer(xsltSourceTest);
		// do the transformation
		trans.transform(xmlSource, result);
	}
}*/