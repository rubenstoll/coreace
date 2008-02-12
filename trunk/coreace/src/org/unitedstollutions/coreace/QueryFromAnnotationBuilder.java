/**
 * 
 */
package org.unitedstollutions.coreace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author yurchyshyna
 *
 */
public class QueryFromAnnotationBuilder {
	
	private ArrayList<String> queriesFull = new ArrayList<String>();
	private ArrayList<String> queriesShort = new ArrayList<String>();
	private ArrayList<String> queriesToCorese = new ArrayList<String>();
	
	//build from a list of annotations files names
	// tested => works fine
	public QueryFromAnnotationBuilder(ArrayList<String> listOfAnnotations){
		ArrayList<String> listSelectedQueriesFull = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShort = new ArrayList<String>();
		for (String annCurr : listOfAnnotations) {
			//String fullCurrName = namesFile + annCurr;
			//System.out.println("current rdf is " +fullCurrName);
			//condition is Selecting ALL queries "//AnnotationRegle/contenuRegle"
			TestDOMXPath tpFull = new TestDOMXPath(annCurr);
			String curQuery = tpFull.getQuery(); //taking full query name
			//System.out.println(curQuery);
			if (!(curQuery.equals(""))) {
				listSelectedQueriesFull.add(curQuery);
				listSelectedQueriesShort.add(curQuery.substring(48)); //taking queryN.txt
			}
		}
		queriesFull = listSelectedQueriesFull;
		queriesShort = listSelectedQueriesShort;
	}
	
	public QueryFromAnnotationBuilder(String namesFile) {
		ArrayList<String> listAnnotationNames = getFilesFromDir(namesFile);
		ArrayList<String> listSelectedQueries = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesNames = new ArrayList<String>();
		for (String annCurr : listAnnotationNames) {
			String fullCurrName = namesFile + annCurr;
			//System.out.println("current rdf is " +fullCurrName);
			TestDOMXPath tpFull = new TestDOMXPath(fullCurrName, "//AnnotationRegle/contenuRegle");
			String curQuery = tpFull.getQuery();
			//System.out.println(curQuery);
			if (!(curQuery.equals(""))) {
				listSelectedQueries.add(curQuery);
				listSelectedQueriesNames.add(curQuery.substring(48));
			}
		}
		//System.out.println("queries of the list : ");
		for (String q : listSelectedQueries){
			//System.out.println("q : " + q);
			//System.out.println("q : " + q.substring(48));
		}
		queriesFull = listSelectedQueries;
		queriesShort = listSelectedQueriesNames;
	}
	
	public ArrayList<String> getQueriesFull(){
		return queriesFull;
	}
	
	public ArrayList<String> getQueriesShort(){
		return queriesShort;
	}
	
	public ArrayList<String> getFilesFromDir(String pathToDir){
		ArrayList<String> dirFiles = new ArrayList<String>();
		File f = new File(pathToDir); 

		if (f.exists()) {
			String[] directoryListing = f.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					dirFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("\n\nNumber of files found: " + dirFiles.size());
		System.out.println("\n\n");
		return dirFiles;
	}

	
	public ArrayList<String> getQueriesToCorese() {
		QueryDialog queryListDialog = new QueryDialog(null, this.queriesShort);
		queriesToCorese = queryListDialog.getSelectedQueries();
		return queriesToCorese;
	}
	

	
	
}