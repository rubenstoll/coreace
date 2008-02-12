/**
 * 
 */
package org.unitedstollutions.coreace;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author Anastasiya
 *
 */
public class ConceptLister {
	private ArrayList<String> conceptList; //list of concepts from queries
	
	//default constructor
	public ConceptLister(){
		this.conceptList.add("IfcObject"); 
	}
	
	//constructor from 
	public ConceptLister(String pathToQueryFile){
		// open file
		BufferedReader is = null;
		try {
			if (pathToQueryFile.startsWith("http")) {
				URL urlOfQueryFolder = new URL(pathToQueryFile);
				is = new BufferedReader(new InputStreamReader(urlOfQueryFolder
						.openStream()));
			} else {
				is = new BufferedReader(new FileReader(pathToQueryFile));
			}

			String line, word;
			// TODO
			// for each word starts from "ifc:" do 
			// if new - add to list without "ifc:" 
			while ((line = is.readLine()) != null) {
				// for each word : define "word" 
				word = takeNextWord(pathToQueryFile);
				if (word.startsWith("ifc:"))
				conceptList.add(word);
				
			}

			// close the file - buffered reader
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.removeDublication(); 
	}
	
	public ArrayList<String> getConceptList(){
		return conceptList;
	}
	
	public void setConceptList(ArrayList<String> concepts) {
		this.conceptList = concepts;
	}
	
	public void updateConceptList(String newConcept) {
		this.conceptList.add(newConcept);
	}
	
	public void updateConceptList(ArrayList<String> newConcepts) {
		for (String currConcept : newConcepts){
			this.conceptList.add(currConcept);
		}		
	}
	
	private String takeNextWord(String fileName){
		// TO DO how to read word between spaces
		return "word"; 
	}
	
	private void removeDublication(){
		//find methode that removes identique elements from Array<String>
		//conceptList.thismethode();
	}

}
