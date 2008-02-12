/**
 * 
 */
package org.unitedstollutions.coreace;
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
public class XSLTransf {
	//File resultFile = File.createTempFile("Streams", ".rdf");
	File resultFile = new File("extractedIFC.rdf");
	//File resultReportFile = new File("conformityReport.html");
	
	public XSLTransf(String xmlFileName) throws TransformerException {
		
		String xsltFileName = "http://rainbow.essi.fr/~anastasiya/data/test.xslt";

		// take the File corresponding to this FileName
		File xmlFile = new File(xmlFileName);
		URLUtils fetcher = new URLUtils();
		File xsltFile = fetcher.readFetchFile(xsltFileName);

				
        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

		// send the result to a file
		Result result = new StreamResult(resultFile);
		System.out.println("Results will go to: "+ resultFile.getAbsolutePath());
        
        // the factory pattern supports different XSLT processors
        TransformerFactory transFact =
                TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, result);
    }	
	
	public XSLTransf(String xmlFileName,String xsltFileName) throws TransformerException {
		
		// take the File corresponding to this FileName
		File xmlFile = new File(xmlFileName);
		File xsltFile = new File(xsltFileName);
				
        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

		// send the result to a file
        Result result = new StreamResult(resultFile);
		System.out.println("Results will go to: "+ resultFile.getAbsolutePath());
        
        // the factory pattern supports different XSLT processors
        TransformerFactory transFact =
                TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, result);
    }
       
	public XSLTransf(String xmlFileName,String xsltFileName, String outputFileName) throws TransformerException {
		
		// take the File corresponding to this FileName
		File xmlFile = new File(xmlFileName);
		File xsltFile = new File(xsltFileName);
		File resultReportFile = new File(outputFileName);
				
        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

		// send the result to a file
        Result result = new StreamResult(resultReportFile);
		System.out.println("Results will go to: "+ resultReportFile.getAbsolutePath());
        
        // the factory pattern supports different XSLT processors
        TransformerFactory transFact =
                TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, result);
    }
	
	public String getTransformedPath() {
		return resultFile.getAbsolutePath();
	}
	public File getTransformedFile() {
		return resultFile;
	}
}
