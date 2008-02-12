/**
 * 
 */
package org.unitedstollutions.coreace;

import java.util.ArrayList;
import java.util.Enumeration;
import fr.inria.acacia.corese.api.EngineFactory;
import fr.inria.acacia.corese.api.IEngine;
import fr.inria.acacia.corese.api.IResults;
import fr.inria.acacia.corese.api.*;
import fr.inria.acacia.corese.exceptions.EngineException;
import fr.inria.acacia.corese.exceptions.QueryLexicalException;
import fr.inria.acacia.corese.exceptions.QuerySemanticException;
import fr.inria.acacia.corese.exceptions.QuerySolvingException;
import fr.inria.acacia.corese.exceptions.QuerySyntaxException;

/**
 * @author yurchyshyna
 *
 */
public class CoreseEngine {

	private EngineFactory ef = new EngineFactory();
	private IEngine engine;
	
	// trying not to use it
	//here all path are said to be relative
	private String dataPathDef = "/media/STORENGO/nov2007/0911/data/";
	//private String dataPathDef = "C:/Documents and Settings/Anastasiya/Bureau/nov2007/data/";
	//	"C:/Documents and Settings/yurchyshyna/workspace/CoreAce/data";
	private String dataPath = dataPathDef;
	//private String propertiesFile = "prop/corese.properties";
	//private String engineLog4j = "prop/log4j.properties";
	
	// now the ontology is downloaded locally
	// HOW to access it remotedly ???
	private String engineSchema = dataPathDef + "ifc.rdfs"; 
	private String engineData;
	//private String engineRule = "data/rules/ac.rul";
	//private String engineRuleRun = "true";
	
	
	/**
	 * Constructor
	 * 
	 * @param Data path to where the engine will run and where all the files 
	 *        needed to run the engine can be found.
	 * 
	 * @see
	 */
	
	public CoreseEngine() {
		super();
		setupEngine();
		
	}
	
	public CoreseEngine(String engineDataFile) {
		super();
		this.engineData = engineDataFile;		
		setupEngine();
		
	}
	
	public CoreseEngine(String dataPath, String engineDataFile) {
		super();
		this.dataPath = dataPath;
		//change value of engine data
		this.engineData = engineDataFile;
		setupEngine();
		
	}
	/**
	 * Runs queries
	 * 
	 * @param List of queries to process
	 * @return results of the query run
	 * 
	 * @see
	 */
	
	// TO DO modify run 
	public IResults run(ArrayList<String> queries) {

		System.out.println("\nSetting up and running the query...\n\n");
		IResults res = null;
		
		System.out.println("Processing " + queries.size() + " queries\n\n");

		try {

			for (String query : queries) {
				res = engine.SPARQLQuery(query);
				
				//	 get the list of all the selected variables
			    String[] variables = res.getVariables();
			    // go through all results
			    for (Enumeration<IResult> en = res.getResults(); en.hasMoreElements();) {
			        // get a result
			  	IResult r = en.nextElement();
			  	// go through this result
			  	for (String var : variables) {
			  	    if (r.isBound(var)) {
				    	// get result values for each selected variable 
					IResultValue[] values = r.getResultValues(var);
			  	        for (int j = 0; j < values.length; j++)
					    System.out.println(var + " = " + values[j].getStringValue());
			  	    } else {
			  	        System.out.println(var + " = Not bound");
				    }
			        }
			    }
			}
		} catch (QueryLexicalException e) {
			System.out.println("Lexical Error: \n" + e.getMessage());
		} catch (QuerySyntaxException e) {
			System.out.println("Syntax Error: \n" + e.getMessage());
		} catch (QuerySemanticException e) {
			System.out.println("Semantic Error: \n" + e.getMessage());
		} catch (QuerySolvingException e) {
			System.out.println("Query Processing Error: \n" + e.getMessage());
		} catch (EngineException e) {
			System.out.println("Error: \n" + e.getMessage());
		}
		return res;
	}
	
/*	public String getCoreseResult(IResults res){
		return res.toCoreseResult();
	}
	
	public String getSparqlResult(IResults res){
		return res.toSPARQLResult();
	}*/

	// we don't really need validating 
	// all queries MUST be validated before loading them to the server
	public void validate(ArrayList<String> queries) {

		// validate the query
		System.out.println("\nValidating query(ies)...");

		try {
			
			for (String queryString : queries) {
				System.out.println("Validating the query " + queryString + " ...");
				engine.SPARQLQuery(queryString);
				System.out.println("...query validated");
			}

		} catch (QueryLexicalException e) {
			System.out.println("Lexical Error: \n" + e.getMessage());
		} catch (QuerySyntaxException e) {
			System.out.println("Syntax Error: \n" + e.getMessage());
		} catch (QuerySemanticException e) {
			System.out.println("Semantic Error: \n" + e.getMessage());
		} catch (QuerySolvingException e) {
			System.out.println("Query Processing Error: \n" + e.getMessage());
		} catch (EngineException e) {
			System.out.println("Error: \n" + e.getMessage());
		}
		
	}

	/**
	 * Sets the data path in case the default one is not wanted.
	 * 
	 * @see
	 */
/*	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}*/
	
	
	/**
	 * Sets the data path in case the default one is not wanted.
	 * 
	 * @param Data path to where the engine should run
	 * @see
	 */
/*	public void updateDataPath(String dataPath) {
		this.dataPath = dataPath;
		updateEngine();
	}*/

	/**
	 * Sets up the corese Engine
	 * 
	 * @see
	 */
	private void setupEngine() {

		System.out.println("launching corese locally. All data is in "+ dataPathDef + "\n\n");

		engine = ef.newInstance();	
		try {
			engine.load(engineData);
			engine.load(engineSchema);
			
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	/**
	 * Update the engine.  Usually used when a engine's parameter has changed and needs
	 * to be updated
	 * 
	 * @see
	 */
	private void updateEngine(String newEngineData) {
		try {
			engineData = newEngineData;
			engine.load(engineData);
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
