package org.unitedstollutions.coreace;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class TestXMLParsing {
	private List<AnnotationRegle> annotationRegles; //list of annotations objects
	private File path;
	
	public TestXMLParsing(String fileToParse) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document= builder.parse(new File(fileToParse));
			if(document!=null){
				System.out.println("OK");
				NodeList list = document.getElementsByTagName("IfcProject");
				if(list!=null){
					System.out.println("list.getLength()" + list.getLength());
					Node  node = list.item(0);
					System.out.println(node.getNodeName());
							//getTextContent()
				}
			}	
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TestXMLParsing(String fileToParse, String tagName, String tagValue) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document= builder.parse(new File(fileToParse));
			if(document!=null){
				System.out.println("OK");
				NodeList list = document.getElementsByTagName(tagName);
				if(list!=null){
/*					Boolean isHere = false;
					String currentValue = document.getNodeValue(tagName).toString();
					if (currentValue = tagValue) {
						isHere = true;
					}*/
					System.out.println("list.getLength()" + list.getLength());
					Node  node = list.item(0);
					System.out.println(node.getNodeName());
					System.out.println(node.getNodeValue());
							//getTextContent()
				}
			}	
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getRdfFiles(File path) {

		ArrayList<String> rdfFiles = null;
		
		this.path = path;

		// DEBUG - remove later
		// System.out.println("\n\n");

		if (path.exists()) {
			rdfFiles = new ArrayList<String>();
			String[] directoryListing = path.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					

					rdfFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		// System.out.println("\n\nNumber of RDF found: " + rdfFiles.size());
		// System.out.println("\n\n");
		return rdfFiles;
	}
	
/*	Parsing an XML document from a requested URL
*/	
	// Using the given document builder object,
	// construct and return an XML DOM
	// from the given URL.
	
	public static Document getDocument
	  ( DocumentBuilder db, String urlString ) {
	   try {
	     URL url = new URL( urlString );
	     
	     try {
	       URLConnection URLconnection =
	          url.openConnection ( ) ;
	       HttpURLConnection httpConnection =
	          (HttpURLConnection)
	         URLconnection;
	         
	       int responseCode =
	        httpConnection.getResponseCode ( ) ;
	       if ( responseCode ==
	        HttpURLConnection.HTTP_OK) {
	           InputStream in =
	            httpConnection.getInputStream ( ) ;
	            
	           try {
	             Document doc = db.parse( in );
	             return doc;
	           } catch(
	            org.xml.sax.SAXException e ) {
	                e.printStackTrace ( ) ;
	           }
	        } else {
	            System.out.println
	               ( "HTTP connection response != HTTP_OK" );
	               
	        } 
	     } catch ( IOException e ) { 
	          e.printStackTrace ( ) ;
	     } // Catch
	  } catch ( MalformedURLException e ) {  
	      e.printStackTrace ( ) ;
	  } // Catch
	  return null;
	} // getDocument
	
	
/*	Matching XML elements and attributes*/

	// Given an XML document,
	// return the values of all attributes
	// for the given element.

	public static Node [] getAttributes
	   ( Document document,
	    String elementName, String [] attrNames ) {
	    
	    // Get elements with the given tag name
	    // (matches on * too)
	    NodeList nodes = document.getElementsByTagName
	       ( elementName );
	    if ( nodes.getLength() < 1) {
	      return null;
	    }
	    
	    Node firstElement = nodes.item( 0 );
	    NamedNodeMap nnm =
	       firstElement.getAttributes ( ) ;
	   
	    if (nnm != null) {
	      // Test the value of each attribute
	      Node [] matchNodes = new Node
	         [ attrNames.length ];
	        
	       for (int i = 0; i < attrNames.length; i++){
	          boolean all =
	            attrNames[ i ].equalsIgnoreCase("all");
	           if (all) {
	             // named node map
	             int nnmLength = nnm.getLength();
	             matchNodes = new Node[ nnmLength ];
	             
	             for ( int j = 0; j < nnmLength; j++){
	                matchNodes[ j ] = nnm.item( j );
	             }
	             return matchNodes;
	           } else {
	               matchNodes[ i ] = nnm.getNamedItem
	                 ( attrNames[ i ] );
	               if ( matchNodes[ i ] == null ) {
	                 try {
						matchNodes[ i ] =
						     document.createAttribute
						     ( attrNames[ i ] );
						  ((Attr)matchNodes[ i ]).setValue
						    (attrNames[ i ]);
					} catch (DOMException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               }
	           } // if
	      } // for
	      
	      return matchNodes;
	   } // if
	   
	   return null;
	} // printDocumentAttrs



	
/*	printing the XML attribute data
*/
	// Given a set of nodes,
	// print the values of the nodes.
	// The nodes may be given a title.
	// The nodes may be printed on one line
	// (grouped) or many.
	
	public static void printNodes
	  ( String title, Node [] nodes,
	       boolean grouped ) {
	       
	       // Report the name value of each node
	       System.out.print( title );
	       
	       if (grouped){
	          System.out.println ( ) ;
	       }
	       // Walk through the nodes.
	       for ( int i = 0; i < nodes.length; i++ ) {
	           Node node = nodes[ i ];
	           System.out.print
	              ( grouped ? " " : ", " );
	           System.out.print
	              ( node.getNodeName() + "=" +
	              node.getNodeValue() );
	              
	            if ( grouped ) {
	               System.out.println ( ) ;
	            }  
	    } // for
	    
	    if (!grouped) {
	        System.out.println ( ) ;
	    }  
	} // printAttributeValues

	
}
