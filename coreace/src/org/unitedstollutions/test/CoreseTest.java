/**
 * 
 */
package org.unitedstollutions.test;

import java.util.ArrayList;

import fr.inria.acacia.corese.api.EngineFactory;
import fr.inria.acacia.corese.api.IEngine;
import fr.inria.acacia.corese.api.IResults;
import fr.inria.acacia.corese.exceptions.EngineException;
import fr.inria.acacia.corese.exceptions.QueryLexicalException;
import fr.inria.acacia.corese.exceptions.QuerySemanticException;
import fr.inria.acacia.corese.exceptions.QuerySolvingException;
import fr.inria.acacia.corese.exceptions.QuerySyntaxException;

/**
 * @author yurchyshyna
 *
 */
public class CoreseTest {

	/**
	 * @param args
	 */
	private IEngine engine;
	// create an EMPTY engine
	private EngineFactory ef = new EngineFactory();
	
	// trying not to use it 
	private String dataPath = "http://rainbow.essi.fr/~anastasiya/data/";
	//private String propertiesFile = "prop/corese.properties";
	//private String engineLog4j = "prop/log4j.properties";
	
	//here all path are said to be relative
	private String engineSchema = "data/ifc.rdfs ontology/owlOntology.owl onto";
	private String engineData = dataPath + "ifc02.rdf"; //ddefault value from server
	private String engineRule = "data/rules/ac.rul";
	private String engineRuleRun = "true";
	
	
	/**
	 * Constructor
	 * 
	 * @param Data path to where the engine will run and where all the files 
	 *        needed to run the engine can be found.
	 * 
	 * @see
	 */
	
	public CoreseTest() {
		super();
		//this.dataPath = dataPath;		"http://rainbow.essi.fr/~anastasiya/data/";
		//engineData is not changed =dataPath + "ifc02.rdf"
		
		// initialize the engine
		setupEngine();
		
	}
	
	public CoreseTest(String engineDataFile) {
		super();
		//this.dataPath = dataPath;		
		
		this.engineData = engineDataFile;		
		// initialize the engine
		setupEngine();
		
	}
	
	public CoreseTest(String dataPath, String engineDataFile) {
		super();
		this.dataPath = dataPath;
		//change value of enginedata
		this.engineData = engineDataFile;
		// initialize the engine
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
	
	
	/**
	 * Similar to the run method, but it validates the queries.
	 * 
	 * @param query String
	 * 
	 * @see
	 */
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
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}
	
	
	/**
	 * Sets the data path in case the default one is not wanted.
	 * 
	 * @param Data path to where the engine should run
	 * @see
	 */
	public void updateDataPath(String dataPath) {
		this.dataPath = dataPath;
		updateEngine();
	}

	/**
	 * Sets up the corese Engine
	 * 
	 * @see
	 */
	private void setupEngine() {

		
		System.out.println("data path is " + dataPath + "\n\n");
		
		//ef.setProperty(EngineFactory.DATAPATH, dataPath);			//"http...
		//ef.setProperty(EngineFactory.PROPERTY_FILE, propertiesFile); //"corese.properties"
		//ef.setProperty(EngineFactory.ENGINE_LOG4J, engineLog4j);	// "prop/log4j.properties"
		ef.setProperty(EngineFactory.ENGINE_SCHEMA, dataPath + "ifc.rdfs");	//"data/ifc.rdfs ontology/owlOntology.owl onto"
		ef.setProperty(EngineFactory.ENGINE_DATA, engineData);		//"data/ifc02.rdf"
		ef.setProperty(EngineFactory.ENGINE_RULE, dataPath + "rul/");		//"data/ac.rul"
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, engineRuleRun);
		
		//what for??
		//ef.setURIFromPath();
		engine = ef.newInstance();	
		try {
			engine.load(dataPath); //loadDir is deprecated
			engine.load(engineData);
			//
			//engine.load(queryFromAnnotationsList);
			
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//engine.load("path_to_which_file");
		System.out.println(ef.ENGINE_SCHEMA);
	}	
	
	/**
	 * Update the engine.  Usually used when a engine's parameter has changed and needs
	 * to be updated
	 * 
	 * @see
	 */
	private void updateEngine() {
		
		ef.setProperty(EngineFactory.DATAPATH, dataPath);
		//ef.setProperty(EngineFactory.PROPERTY_FILE, propertiesFile);
		//ef.setProperty(EngineFactory.ENGINE_LOG4J, engineLog4j);
		ef.setProperty(EngineFactory.ENGINE_SCHEMA,engineSchema);
		ef.setProperty(EngineFactory.ENGINE_DATA, engineData);
		ef.setProperty(EngineFactory.ENGINE_RULE, engineRule);
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, engineRuleRun);
		
		engine = ef.newInstance();


	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("corese test: ");
		CoreseTest ct = new CoreseTest();

	}

}

	