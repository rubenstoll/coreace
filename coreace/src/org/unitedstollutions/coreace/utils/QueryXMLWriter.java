/**
 * 
 */
package org.unitedstollutions.coreace.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

		// define an output directory
		File outputDir = new File("output");
		if(outputDir.mkdir()) {
			// only goes in once - upon directory creation
			System.out.println("Created output directory ...");
		}
		
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

			// write to the output file with directory specified
			// NOTE: the .txt from the file name is dropped and replaced by .xml

			// set the file with directory prepended
			String outputFileName = outputDir.toString() + File.separator
					+ qf.getFileName().replaceFirst(".txt", "") + ".xml";

			// start writing
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(
						outputFileName));
				XMLOutputter serializer = new XMLOutputter(Format
						.getPrettyFormat());
				serializer.output(doc, out);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		System.out.println("Done!");
		System.out.println("Output written to directory: " + outputDir);
	}

	/**
	 * Writes all the Query File's IFC lists to ONE xml file.
	 * 
	 * @param queryFileList
	 */
	public void writeOneIFC(ArrayList<QueryFile> queryFileList) {

		// define an output directory
		File outputDir = new File("output");
		if(outputDir.mkdir()) {
			// only goes in once - upon directory creation
			System.out.println("Created output directory ...");
		}
		
		Element root = new Element("query");
//		Element filename = new Element("filename");
//		filename.setText(qf.getFileName());
//		root.addContent(filename);
		Element concepts = new Element("concepts");
		root.addContent(concepts);

		for (QueryFile qf : queryFileList) {

			for (String currConcept : qf.getIfcList()) {
				Element concept = new Element("concept");
				// concept.setAttribute("index", String.valueOf(i));
				concept.setText(currConcept);
				concepts.addContent(concept);
			}

		}

		Document doc = new Document(root);

		// write to the output file with directory specified
		// NOTE: the .txt from the file name is dropped and replaced by .xml

		// set the file with directory prepended
		String outputFileName = outputDir.toString() + File.separator
				+  "queries.xml";

		// start writing
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					outputFileName));
			XMLOutputter serializer = new XMLOutputter(Format
					.getPrettyFormat());
			serializer.output(doc, out);
		} catch (IOException e) {
			System.err.println(e);
		}

		System.out.println("Done!");
		System.out.println("Output written to directory: " + outputDir);
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
		
		// now write one big one
		qwr.writeOneIFC(qryFileList);
		

	}

}
