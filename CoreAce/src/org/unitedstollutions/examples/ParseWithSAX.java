
/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import org.xml.sax.helpers.DefaultHandler;

public class ParseWithSAX extends DefaultHandler{

	ArrayList<String> myRDF;
	
	private String tempVal;
	
	//to maintain context
	private AnnotationRegle tempAnno;
	
	
	public ParseWithSAX(){
		myRDF = new ArrayList();
	}
	
	public void runExample() {
		parseDocument();
		printData();
	}
	
	public class AnnotationRegle {

		private String name;

		private String origineRegle;
		
		private String actualite;

		
		public AnnotationRegle(){
			
		}

		public AnnotationRegle(String name, String origineRegle, String actualite) {
			this.name = name;
			this.origineRegle = origineRegle;
			this.actualite  = actualite;
			
			
		}
		public String getOrigineRegle() {
			return origineRegle;
		}

		public String getActualite() {
			return actualite;
		}
		public void setActualite(String actualite) {
			this.actualite = actualite;
		}

		public void setOrigineRegle(String origineRegle) {
			this.origineRegle = origineRegle;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


		/*public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}	

		
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append("AnnotationRegle Details - ");
			sb.append("Name:" + getName());
			sb.append(", ");

			sb.append("origineRegle:" + getOrigineRegle());
			sb.append(", ");
			sb.append("actualite:" + getActualite());
			sb.append(".");
			
			return sb.toString();
		}
	}

	private void parseDocument() {
		
		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
		
			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();
			
			//parse the file and also register this class for call backs
			sp.parse("http://rainbow.essi.fr/~anastasiya/data/annotationsRegles/annotationRegle20.rdf", this);
			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	/**
	 * Iterate through the list and print
	 * the contents

	private void printData(){
		
		System.out.println("No of AnnotationRegle'" + myRDF.size() + "'.");
		
		Iterator it = myRDF.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
	

	//Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//reset
		tempVal = "";
		if(qName.equalsIgnoreCase("AnnotationRegle")) {
			//create a new instance of AnnotationRegle
			tempAnno = new AnnotationRegle();
			//tempAnno.setType(attributes.getValue("type"));
		}
	}
	

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch,start,length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if(qName.equalsIgnoreCase("AnnotationRegle")) {
			//add it to the list
			myRDF.add(tempAnno);
			
		}else if (qName.equalsIgnoreCase("Name")) {
			tempAnno.setName(tempVal);
		}else if (qName.equalsIgnoreCase("origineRegle")) {
			tempAnno.setOrigineRegle(tempVal);
		}else if (qName.equalsIgnoreCase("actualite")) {
			tempAnno.setActualite(tempVal);
		}
		
	}
	
	public static void main(String[] args){
		ParseWithSAX spe = new ParseWithSAX();
		spe.runExample();
	}
	
}*/





