/**
 * 
 */
package org.unitedstollutions.examples;

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

public class ApplyXSLT {

    /**
     * Performs an XSLT transformation, sending the results
     * to System.out.
     */
    public ApplyXSLT(File xmlFile, File xsltFile) throws Exception {

        //File xmlFile = new File("F:/sept2007/test_ifc.xml");
        //File xsltFile = new File("F:/sept2007/test.xslt");

        // JAXP reads data using the Source interface
        Source xmlSource = new StreamSource(xmlFile);
        Source xsltSource = new StreamSource(xsltFile);

        // the factory pattern supports different XSLT processors
        TransformerFactory transFact =
                TransformerFactory.newInstance();
        Transformer trans = transFact.newTransformer(xsltSource);

        trans.transform(xmlSource, new StreamResult(System.out));
    }
}