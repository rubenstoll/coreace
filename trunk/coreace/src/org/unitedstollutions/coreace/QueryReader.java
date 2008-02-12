/**
 * 
 */
package org.unitedstollutions.coreace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * @author Anastasiya
 *
 */
public class QueryReader {
	String defUrlDir = "http://rainbow.essi.fr/~anastasiya/data/queries/";
	ArrayList<String> listQueryFullUrl = new ArrayList<String>();
	ArrayList<String> listQueryContents = new ArrayList<String>();
	
		// returns URL long names and contents
	public QueryReader(ArrayList<String> listOfQueryUrl) {
		buildQueryContentList(listOfQueryUrl);
		//System.out.println(getQueryFullUri());
		for (String curQ : listOfQueryUrl) {
			listQueryFullUrl.add(curQ);
		}	
		System.out.println("list query full url");
		for (String curQ : listQueryFullUrl) {
			System.out.println("full " + curQ);
			
		}	
	}
	
	public ArrayList<String> getQueryContents() {
		return listQueryContents;
	}
	
	public ArrayList<String> getQueryFullUri() {
		return listQueryFullUrl;
	}
	
	private void buildQueryContentList(ArrayList<String> listQueryUrl) {
		
		URL urlOfQuery = null;
		BufferedReader is;
		ArrayList<String> queryListC = new ArrayList<String>();
		
		try {
			for (String qFile : listQueryUrl) {
				urlOfQuery = new URL(qFile);
				is = new BufferedReader(new InputStreamReader(urlOfQuery.openStream()));
				String line;
				String qString = "";
				while ((line = is.readLine())!= null) {
					qString += line + "  ";
				}

				// close the file - buffered reader
				is.close();
				queryListC.add(qString);
			}
			
/*			// build a query list from all the query files available
			for (String qFile : listQueryUrl) {

				if (qFile.startsWith("http")) {
					urlOfQueryFolder = new URL(qFile);
					is = new BufferedReader(new InputStreamReader(urlOfQueryFolder
							.openStream()));
				} else { // not really possible
					is = new BufferedReader(new FileReader(qFile));
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
				listQueryContents.add(queryString);

			}*/
			listQueryContents = queryListC;
			
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
	
}
