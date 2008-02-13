/**
 * 
 */
package org.unitedstollutions.coreace.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ruben
 * 
 */
public class QueryParser {

	private ArrayList<File> queryFiles;

	/**
	 * Gets the Query files. Returns null if not query file list not set
	 * 
	 * @return ArraryList - list of files
	 */
	public ArrayList<File> getQueryFiles() {

		return this.queryFiles;

	}

	/**
	 * Sets the list of query files to parse or for whatever else
	 * 
	 * @param queryFiles
	 */
	public void setQueryFiles(ArrayList<File> queryFiles) {

		this.queryFiles = queryFiles;

	}

	/**
	 * Finds the query files in a
	 * 
	 * @param directory
	 * @return
	 */
	public void findQueryFiles(File directory) {

		ArrayList<File> queryFilesFound = new ArrayList<File>();

		for (File f : getFileList(directory)) {
			String fileName = f.getName();
			if (fileName.startsWith("query")) {
				// just add the name and not the full path if any
				queryFilesFound.add(new File(fileName));
			}
		}

		this.queryFiles = queryFilesFound;

	}

	/**
	 * Parses a list of query files in the specified directory to find query
	 * file properties.
	 * 
	 * @param queryFiles
	 * @return A list of query file objects with properties set
	 */
	public ArrayList<QueryFile> parse(ArrayList<File> queryFiles, File directory) {

		Pattern pattern = null;
		Matcher matcher = null;
		ArrayList<QueryFile> qryFiles = new ArrayList<QueryFile>();

		for (File qf : queryFiles) {

			// create a query file object and make an array of IFC
			// properties. More variables can be added here depending
			// on what information is needed.
			QueryFile qryFile = new QueryFile();
			ArrayList<String> ifcProperties = new ArrayList<String>();

			// prepend the directory name where the query files can be found
			File currFile = new File(directory.toString() + File.separator
					+ qf.toString());

			// set the name of the query file
			qryFile.setFileName(qf.toString());
			
			// read each file and look for patterns
			try {
				
				// open file for reading - buffered reader
				BufferedReader in = new BufferedReader(new FileReader(currFile));
				String str;

				// one sweep through the file for pattern "ifc:<something>"
				// only up to the space character or the } which some have.
				pattern = Pattern.compile("ifc:[_a-zA-Z]+[^ }]?");

				// start reading the file line by line
				while ((str = in.readLine()) != null) {

					// apply the pattern to the line
					matcher = pattern.matcher(str);
					// and see if it matched
					if (matcher.find()) {
						// since we are looking for ifc rules
						// and we found one, we added to the list
						ifcProperties.add(matcher.group().substring(4));
					}
				}
				// finally query file object ifc list member
				qryFile.setIfcList(ifcProperties);
				
				// add more file sweeps(while) here to search for another
				// pattern in the file - copy the while above here below

				in.close();
			} catch (IOException e) {
				System.out.println("NO FILE FOUND!!");
			}

			// add the query file object to the list
			qryFiles.add(qryFile);
			
		}

		// return collected query files
		return qryFiles;

	}

	/**
	 * Get a list of files in the current directory
	 * 
	 * @param directory
	 * @return
	 */
	private ArrayList<File> getFileList(File directory) {

		ArrayList<File> fl = new ArrayList<File>();

		File[] files = directory.listFiles();

		for (File f : files) {
			fl.add(f);
		}

		return fl;
	}

	/**
	 * Lists the file list being passed to stdout
	 * 
	 * @param fileList
	 */
	private void listFiles(ArrayList<File> fileList) {

		System.out.println("Files found:");
		if (fileList != null) {
			for (File currFile : fileList) {
				System.out.println(currFile.toString());
			}
		} else {
			System.err.println("ERROR: file list passed in is null!");
		}
	}

	/**
	 * FOR TESTING PURPOSES ONLY!
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// equivalent way of separating file path
		File dir = new File("data/queries");
		dir = new File("data" + File.separator + "queries");

		QueryParser qp = new QueryParser();

		qp.findQueryFiles(dir);

		// qp.listFiles(qp.getQueryFiles());

		// parse returns a list of QueryFile objects
		ArrayList<QueryFile> qryFileList = qp.parse(qp.getQueryFiles(), dir);

		// print information about each qry file
		for(QueryFile q : qryFileList) {
			System.out.println("\nQuery file name: " + q.getFileName());
			if(q.getIfcList().isEmpty()) {
				System.out.println("This query file has not IFC properties");
			} else {
				String ifcProps = "";
				for(String ifcProp : q.getIfcList()) {
					ifcProps += ifcProp + " ";
				}
				System.out.println("IFC properties: " + ifcProps);
			}
		}
		
	}

}
