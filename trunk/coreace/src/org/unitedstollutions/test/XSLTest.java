package org.unitedstollutions.test;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.unitedstollutions.coreace.XSLTransf;

public class XSLTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xml = "C:/Documents and Settings/yurchyshyna/Bureau/oct2007/data/" + "test_ifc.ifcxml";
		String xslt = "C:/Documents and Settings/yurchyshyna/Bureau/oct2007/data/" + "test.xslt";
		try {
			XSLTransf xc = new XSLTransf(xml, xslt);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
       


}
