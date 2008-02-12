package org.unitedstollutions.coreace;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;


public class TestDOMXPath {
	private ArrayList<String> queryList = new ArrayList<String>();
	private String xpathexpr = "";
	private String resultQuery;
	
	// DEBUG constructor to check if XPath works => YES
	public TestDOMXPath() {
		String fileName = "C:/Documents and Settings/Anastasiya/Bureau/nov2007/data/test.xml";
		try {
			InputSource inputSource =  new InputSource(new FileInputStream(fileName));
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpathexpr = "//AnnotationRegle[origineRegle='validation']/contenuRegle";
			XPathExpression expression = xpath.compile(xpathexpr);
			resultQuery = (String)expression.evaluate(inputSource,XPathConstants.STRING);
			//System.out.println("result : "+ resultQuery + "toto");
			//this.queryList.add((String)result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * constructor 
	 * param : name of the file to be parsed
	 * selecting ALL queries (value of contenuRegle tag)
	 */
	public TestDOMXPath(String fileName) {
		try {
			InputSource inputSource =  new InputSource(new FileInputStream(fileName));
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpathexpr = "//AnnotationRegle/contenuRegle";
			XPathExpression expression = xpath.compile(xpathexpr);
			resultQuery = (String)expression.evaluate(inputSource,XPathConstants.STRING);
			//System.out.println("result : "+ resultQuery + "toto");
			//this.queryList.add((String)result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// NB TestDOMXPath(fileName) is equal to TestDOMXPath(fileName,"//AnnotationRegle/contenuRegle")
	
	public TestDOMXPath(String fileName, String queryString) {
		try {
			InputSource inputSource =  new InputSource(new FileInputStream(fileName));
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpathexpr = queryString;
			XPathExpression expression = xpath.compile(xpathexpr);
			resultQuery = (String)expression.evaluate(inputSource,XPathConstants.STRING);
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TestDOMXPath(String fileName, String tag, String tagValue) {
		try {
			InputSource inputSource =  new InputSource(new FileInputStream(fileName));
			XPath xpath = XPathFactory.newInstance().newXPath();
			xpathexpr = "//AnnotationRegle[" + tag + "='" + tagValue + "']/contenuRegle";
			XPathExpression expression = xpath.compile(xpathexpr);
			resultQuery = (String)expression.evaluate(inputSource,XPathConstants.STRING);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList <String> getQueryList(ArrayList <String> fileNameList, String tag, String tagValue) {
		ArrayList <String> qList = null;
		try {
			for (String curFile : fileNameList) {
				
					InputSource inputSource =  new InputSource(new FileInputStream(curFile));
					XPath xpath = XPathFactory.newInstance().newXPath();
					xpathexpr = "/RDF/AnnotationRegle[" + tag + "='" + tagValue + "']/contenuRegle";
					XPathExpression expression = xpath.compile(xpathexpr);
					resultQuery = (String)expression.evaluate(inputSource,XPathConstants.STRING);
					//System.out.println("result : "+ result);
					//this.queryList.add((String)result);
					if (!(resultQuery.equals(""))){
						qList.add(resultQuery);
					}
				}	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 	qList;
	}
	
	public void printList (ArrayList<String> list){
		for (String l : list)
		System.out.println(l);
	}
	
	public ArrayList<String> getQueryList(){
		return queryList;
	}
	
	public String getXPathExpression(){
		return xpathexpr;
	}
	
	public String getQuery(){
		return resultQuery;
	}
	
	public String getQueryName(){
		return resultQuery.substring(20);
	}
	
	public String formMultipleChoice(){
		String queryString = "";
		
		return queryString;
	}
	
}
