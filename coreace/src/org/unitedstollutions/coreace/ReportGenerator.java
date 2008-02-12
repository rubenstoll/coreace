/**
 * 
 */
package org.unitedstollutions.coreace;

/**
 * @author yurchyshyna
 *
 */

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
/**
 * @author yurchyshyna
 *
 */
public class ReportGenerator {

	
	public ReportGenerator(String ifcExtractedFile,String currentQuery,File reportBlock) throws Exception {
		
		// take the File corresponding to ifcExtractedFile
		File ifcFile = new File(ifcExtractedFile);
		
		// take the File corresponding to currentQuery
		File queryFile = new File(currentQuery);
				
        // JAXP reads data using the Source interface
        Source ifcSource = new StreamSource(ifcFile);
        Source querySource = new StreamSource(queryFile);
        
        //read Source from its name
    	//Source xsltSource = new StreamSource(new StringReader(IDENTITY_XSLT));

		// send the result to a file
		Result resultHTML = new StreamResult(reportBlock);
		System.out.println("Results will go to: "+ reportBlock.getName());
		
		
		//how to define the report
        
    }	

}
