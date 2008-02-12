package org.unitedstollutions.coreace;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLUtils {

	public File readFetchFile(String fileUrl) {

		BufferedReader in = null;
		String inputLine = null;
		File urlTail = null;
		
		try {
			
			URL url = new URL(fileUrl); 

			String localFile = new File(fileUrl).getName();
			
			in = new BufferedReader(new InputStreamReader(url.openStream()));

			urlTail = new File(localFile);
			
			BufferedWriter fw = new BufferedWriter(new FileWriter(urlTail
					.toString()));

			while ((inputLine = in.readLine()) != null) {
//				System.out.println(inputLine);
				fw.write(inputLine + "\n");
			}

			fw.close();
			in.close();

		} catch (MalformedURLException mue) {

		} catch (IOException ioe) {

		}
		
		return urlTail;
		
	}
	
}
