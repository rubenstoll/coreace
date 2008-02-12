/**
 * 
 */
package org.unitedstollutions.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.unitedstollutions.coreace.URLUtils;

/**
 * @author 
 * 
 */
public class UrlFetchingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String fileUrl = "http://rainbow.essi.fr/~anastasiya/data/annotationsRegles/annotationRegle0020.rdf";

		URLUtils fetcher = new URLUtils();
		File fetchedFile = fetcher.readFetchFile(fileUrl);

		System.out.println("File fetched: " + fetchedFile.getName());
		System.out.println("Located under: " + fetchedFile.getAbsoluteFile());

		System.out.println("Contents of the file:");

		try {
			BufferedReader br = new BufferedReader(new FileReader(fetchedFile));
			
			String inputLine = null;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}	
			
		} catch (FileNotFoundException fnfe) {
			System.out.println("ERROR: File " + fetchedFile + " was not found");
		} catch (IOException ioe) {
			System.out.println("ERROR: a reading error occured");
		}
		System.out.println(fetchedFile);
	}

}
