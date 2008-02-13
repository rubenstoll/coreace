/**
 * 
 */
package org.unitedstollutions.coreace.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * @author ruben
 * 
 */
public class QueryXMLWriter {

	/**
	 * Writes each Query File's IFC list to an xml file.
	 * 
	 * @param queryFileList
	 */
	public void writeIFC(ArrayList<QueryFile> queryFileList) {

		for (QueryFile qf : queryFileList) {

			Element root = new Element("query");
			Element filename = new Element("filename");
			filename.setText(qf.getFileName());
			root.addContent(filename);
			Element concepts = new Element("concepts");
			root.addContent(concepts);
			for (String currConcept : qf.getIfcList()) {
				Element concept = new Element("concept");
				// concept.setAttribute("index", String.valueOf(i));
				concept.setText(currConcept);
				concepts.addContent(concept);
			}

			Document doc = new Document(root);
			
			// serialize it onto System.out
			try {
				XMLOutputter serializer = new XMLOutputter(Format.getPrettyFormat());
				serializer.output(doc, System.out);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// equivalent way of separating file path
		File dir = new File("data/queries");
		dir = new File("data" + File.separator + "queries");

		QueryParser qp = new QueryParser();
		QueryXMLWriter qwr = new QueryXMLWriter();

		qp.findQueryFiles(dir);

		// parse returns a list of QueryFile objects
		ArrayList<QueryFile> qryFileList = qp.parse(qp.getQueryFiles(), dir);		
		
		qwr.writeIFC(qryFileList);
		
	}

}
