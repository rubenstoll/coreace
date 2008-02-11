

package org.unitedstollutions.coreace;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * @author yurchyshyna
 *
 */
public class Query {

	private ArrayList<String> queryFiles; //list of queryN.txt
	private ArrayList<String> queryList; //list of content (queryN.txt)
	private String queriesPath;

	/**
	 * Creates a query instance and creates a list of query files found under
	 * the give data path
	 * 
	 * @param Path
	 *            were query files can be found
	 * @see
	 */
	
	public Query(String queriesPath) {

		super();
		this.queriesPath = queriesPath;
		buildQueryFileList(queriesPath);
		buildQueryList();

	}

	
	/**
	 * Sets a new location where to get the queries and updates the query files list.
	 * 
	 * @param location
	 */
	public void updateQueriesPath(String location) {
		this.queriesPath = location;
		buildQueryFileList(queriesPath);
		buildQueryList();
	}
	
	/**
	 * Makes a list of query files.
	 * 
	 * @param location
	 * 
	 * @see
	 */
	private void buildQueryFileList(String location) {

		queryFiles = new ArrayList<String>();

		// replace this hard coded list creation by some directory reading
		// For example: ftp into a remote site, if remote, and get a directory
		// listing.
		// Do the same for a local directory.
		
/*		ArrayList<String> selectedQueries = null; // names of the selected queries
		// selectedQueries = list of values of contenuRegle	
		for (String query : selectedQueries){
			queryFiles.add(query);
		}*/
		// modify according to the annotation selection
		queryFiles.add("query1.txt");
		queryFiles.add("query2.txt");
		queryFiles.add("query3.txt");
		queryFiles.add("query4.txt");
		queryFiles.add("query5.txt");
		queryFiles.add("query_0060.txt");
		queryFiles.add("query_0100.txt");
		queryFiles.add("query_0190.txt");
		queryFiles.add("query_0240.txt");
		queryFiles.add("query_0260.txt");
		queryFiles.add("query_0530.txt");

	}

	/**
	 * Set a list with a give set of query files
	 * 
	 * @param queryFiles
	 *            
	 */
	public void setQueryFiles(ArrayList<String> queryFiles) {

		// if no list exists, create one
//		if (queryFiles == null) {
//			queryFiles = new ArrayList<String>();
//		}

		this.queryFiles = queryFiles;
		// when updating toe query files then re-do the query list
		buildQueryList();

	}

	/**
	 * Returns a list of query files.
	 * 
	 * @return array list of string query file names
	 * 
	 * @see
	 */
	public ArrayList<String> getQueryFiles() {

		return queryFiles;

	}
	
	/**
	 * Returns a list of query files from the given file path
	 * 
	 * @param queryFilesPath
	 * @return array list of string query file names
	 * 
	 * @see
	 */
	public ArrayList<String> getQueryFiles(String queryFilesPath) {

		buildQueryFileList(queryFilesPath);
		return getQueryFiles();

	}
	
	
	/**
	 * Returns a list of query files.
	 * 
	 * @return array list of queries
	 * 
	 * @see
	 */
	public ArrayList<String> getQueries() {

		return queryList;

	}

	/**
	 * Builds a list of string queries from a set of query files containing the
	 * strings. Each query file is read in and the query string is built and
	 * added to the list.
	 * 
	 * @exception Unknown
	 *                host exception
	 */
	private void buildQueryList() {

		URL urlOfQueryFolder = null;
		BufferedReader is;

		queryList = new ArrayList<String>();

		try {

			// build a query list from all the query files available
			for (String queryFile : getQueryFiles()) {

				if (queriesPath.startsWith("http")) {
					urlOfQueryFolder = new URL(queriesPath + queryFile);
					is = new BufferedReader(new InputStreamReader(urlOfQueryFolder
							.openStream()));
				} else {
					is = new BufferedReader(new FileReader(queriesPath
							+ queryFile));
				}

				String line;
				String queryString = "";
				while ((line = is.readLine()) != null) {
					queryString += line + "  ";
				}

				// close the file - buffered reader
				is.close();

				// System.out.println(queryString);

				// add the built query from above to the list
				// to be processed by the engine
				queryList.add(queryString);

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
	 * query file list through the use of an ftp API.
	 * 
	 * @see
	 */
	public void remoteListing() {

		File remoteDirectory = null;

		try {

			URL corese = new URL(queriesPath);
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(corese
			// .openStream()));

			remoteDirectory = new File(queriesPath);

			// String inputLine;

			// while ((inputLine = in.readLine()) != null)
			// System.out.println(inputLine);
			//
			// in.close();

			// remoteDirectory = new File(new URI(queriesPath));

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
	 * Debug method to print the query list
	 * 
	 * @see
	 */
	public void printQueryList() {

		for (String query : queryList) {
			System.out.println(query);
		}

	}	

} // end of class
