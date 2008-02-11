/**
 * 
 */
package org.unitedstollutions.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author yurchyshyna
 *
 */
public class AnnotationRegleOld {
	private ArrayList<String> annotationFiles;
	private ArrayList<String> annotationList;
	private String annotationsPath;

	/**
	 * Creates a annotation instance and creates a list of annotation files found under
	 * the give data path
	 * 
	 * @param Path
	 *            were annotation files can be found
	 * @see
	 */
	public AnnotationRegleOld(String annotationsPath) {

		super();
		this.annotationsPath = annotationsPath;
		buildannotationFileList(annotationsPath);
		buildannotationList();

	}

	
	/**
	 * Sets a new location where to get the annotations and updates the annotation files list.
	 * 
	 * @param location
	 */
	public void updateannotationsPath(String location) {
		this.annotationsPath = location;
		buildannotationFileList(annotationsPath);
		buildannotationList();
	}
	
	/**
	 * Makes a list of annotation files.
	 * 
	 * @param location
	 * 
	 * @see
	 */
	private void buildannotationFileList(String location) {

		annotationFiles = new ArrayList<String>();

		// replace this hard coded list creation by some directory reading
		// For example: ftp into a remote site, if remote, and get a directory
		// listing.
		// Do the same for a local directory.
		annotationFiles.add("annotation100.rdf");
		annotationFiles.add("annotation110.rdf");


	}

	/**
	 * Set a list with a give set of annotation files
	 * 
	 * @param annotationFiles
	 *            
	 */
	public void setannotationFiles(ArrayList<String> annotationFiles) {

		// if no list exists, create one
//		if (annotationFiles == null) {
//			annotationFiles = new ArrayList<String>();
//		}

		this.annotationFiles = annotationFiles;
		// when updating toe annotation files then re-do the annotation list
		buildannotationList();

	}

	/**
	 * Returns a list of annotation files.
	 * 
	 * @return array list of string annotation file names
	 * 
	 * @see
	 */
	public ArrayList<String> getannotationFiles() {

		return annotationFiles;

	}
	
	/**
	 * Returns a list of annotation files from the given file path
	 * 
	 * @param annotationFilesPath
	 * @return array list of string annotation file names
	 * 
	 * @see
	 */
	public ArrayList<String> getannotationFiles(String annotationFilesPath) {

		buildannotationFileList(annotationFilesPath);
		return getannotationFiles();

	}
	
	
	/**
	 * Returns a list of annotation files.
	 * 
	 * @return array list of annotations
	 * 
	 * @see
	 */
	public ArrayList<String> getannotations() {

		return annotationList;

	}

	/**
	 * Builds a list of string annotations from a set of annotation files containing the
	 * strings. Each annotation file is read in and the annotation string is built and
	 * added to the list.
	 * 
	 * @exception Unknown
	 *                host exception
	 */
	private void buildannotationList() {

		URL asiya = null;
		BufferedReader is;

		annotationList = new ArrayList<String>();

		try {

			// build a annotation list from all the annotation files available
			for (String annotationFile : getannotationFiles()) {

				if (annotationsPath.startsWith("http")) {
					asiya = new URL(annotationsPath + annotationFile);
					is = new BufferedReader(new InputStreamReader(asiya
							.openStream()));
				} else {
					is = new BufferedReader(new FileReader(annotationsPath
							+ annotationFile));
				}

				String line;
				String annotationString = "";
				while ((line = is.readLine()) != null) {
					annotationString += line + "  ";
				}

				// close the file - buffered reader
				is.close();

				// System.out.println(annotationString);

				// add the built annotation from above to the list
				// to be processed by the engine
				annotationList.add(annotationString);

			}
			
		} catch (UnknownHostException e) {
			System.out
					.println("ERROR:  Could not connect to external host.  Try to changing path or running locally");
			// e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("ERROR: connection was not established");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: Reading error occurred\n");
			e.printStackTrace();
		}

	}

	/**
	 * NOT WORKING YET Get a remote directory listing in order to build the
	 * annotation file list through the use of an ftp API.
	 * 
	 * @see
	 */
	public void remoteListing() {

		File remoteDirectory = null;

		try {

			URL corese = new URL(annotationsPath);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(corese
			// .openStream()));

			remoteDirectory = new File(annotationsPath);

			// String inputLine;

			// while ((inputLine = in.readLine()) != null)
			// System.out.println(inputLine);
			//
			// in.close();

			// remoteDirectory = new File(new URI(annotationsPath));

			// } catch (URISyntaxException e) {
			// e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// } catch (URISyntaxException e) {
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Debug method to print the annotation list
	 * 
	 * @see
	 */
	public void printannotationList() {

		for (String annotation : annotationList) {
			System.out.println(annotation);
		}

	}
	


}
