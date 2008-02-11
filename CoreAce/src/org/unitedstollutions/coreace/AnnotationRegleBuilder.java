/**
 * 
 */
package org.unitedstollutions.coreace;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author yurchyshyna
 * 
 */
public class AnnotationRegleBuilder {

	private List<AnnotationRegle> annotationRegles; // list of annotations
	private List<AnnotationRegle> annotationReglesSubSet = null;
	// objects
	private XMLReader parser;
	private RuleExtractor handler;
	// possible values: modifie or nouveau
	// this should be replaced by enumerator or something
	private String ruleStateSearch = "modifie";
	private File annotationReglePath;
	private URI annotationReglePathURL;
	private List<String> selectedThematicsRules;
	private List<String> selectedDestinationRules;

	/**
	 * Create a parser object, depending on which parser is available.
	 * 
	 */
	private void createReader() {

		try {
			parser = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
		} catch (SAXException e1) {
			try {
				parser = XMLReaderFactory.createXMLReader();
			} catch (SAXException e2) {
				throw new NoClassDefFoundError("No SAX parser is available");
				// or whatever exception the method is declared to throw
			}
		}

	}

	/**
	 * Get rdf files in a directory
	 * 
	 * @param path
	 * @return a list of rdf files all annotations are supposed to be stored at
	 *         the same directory
	 */
	public ArrayList<String> getRdfFiles(File path) {

		ArrayList<String> rdfFiles = new ArrayList<String>();
		this.annotationReglePath = path;

		if (path.exists()) {
			String[] directoryListing = path.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					rdfFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("\n\nNumber of RDF found: " + rdfFiles.size());
		System.out.println("\n\n");
		return rdfFiles;
	}

	/**
	 * Get rdf files in a directory
	 * 
	 * @param path
	 *            tagName
	 * @return a list of rdf files having the tag tagName all annotations are
	 *         supposed to be stored at the same directory
	 */
	public ArrayList<String> getThemRdfFiles(File path, String tagThemValue) {

		// take all rdf files
		ArrayList<String> rdfThemFiles = this.getRdfFiles(path);
		// this.annotationReglePath = path;

		String[] directoryListing = path.list();

		for (String currFile : directoryListing) {
			if (currFile.endsWith("rdf")) {
				parse(currFile);

				// DEBUG - remove later
				// System.out.println("Adding to list: " + currFile);

				rdfThemFiles.add(currFile);
			}
		}

		// DEBUG - remove later

		System.out.println("\n\nNumber of RDF thematics found: "
				+ rdfThemFiles.size());
		System.out.println("\n\n");
		return rdfThemFiles;
	}

	public void printAnnotationRulesList() {

		ArrayList<AnnotationRegle> rules = (ArrayList<AnnotationRegle>) this.annotationReglesSubSet;

		if (annotationRegles == null) {
			System.out
					.println("ERROR: annotation rules list was never created.");
		} else {

			System.out.println("\n\nMatching Annotation Rules found: "
					+ rules.size() + "/" + annotationRegles.size());

			for (AnnotationRegle annoRegle : rules) {

				System.out.println("\nAnno Regle: ");
				System.out.println("Actualite: "
						+ annoRegle.getActualiteRegle());

				System.out.println("Thematique Regle: "
						+ annoRegle.getThematiqueRegle());
				System.out.println("Domaine Regle: "
						+ annoRegle.getDomaineApplication());

				System.out.println("Destination Regle: "
						+ annoRegle.getDestinationRegle());
				// System.out.println("Thematique Regle: " +
				// annoRegle.getThematiqueRegle());
				System.out.println("Contenu Regle: "
						+ annoRegle.getContenuRegle());
				System.out.println("\n\n");
			}
		}

	}

	/**
	 * Add rule to a list of selected thematics rules to look for
	 * 
	 * @param rule
	 */
	public void addThematicsRule(String rule) {

		if (selectedThematicsRules == null) {
			selectedThematicsRules = new ArrayList<String>();
		}
		selectedThematicsRules.add(rule);

	}

	/**
	 * Remove the rule from the list of thematics selected rules to look for
	 * 
	 * @param rule
	 */
	public void removeThematicsRule(String rule) {

		if (selectedThematicsRules != null) {
			selectedThematicsRules.remove(rule);
		}
	}

	/**
	 * Add rule to a list of selected destination rules to look for
	 * 
	 * @param rule
	 */
	public void addDestinationRule(String rule) {

		if (selectedDestinationRules == null) {
			selectedDestinationRules = new ArrayList<String>();
		}
		selectedDestinationRules.add(rule);

	}

	/**
	 * Remove the rule from the list of selected destination rules to look for
	 * 
	 * @param rule
	 */
	public void removeDestinationRule(String rule) {

		if (selectedDestinationRules != null) {
			selectedDestinationRules.remove(rule);
		}

	}

	/**
	 * 
	 * @param state
	 */
	public void setRuleStateSearch(String state) {
		this.ruleStateSearch = state;
	}

	/**
	 * Build annotation rule list from the files. The files must be in RDF
	 * format so they can be parsed with and XML parser.
	 * 
	 * @param annoRegleRdfFiles
	 */
	public void build(ArrayList<String> annoRegleRdfFiles) {

		parse(annoRegleRdfFiles);

		// create a rules subset if there are any
		createRuleSubSet();

	}

	/**
	 * Creates a content handler
	 * 
	 * @param out
	 */
	private void registerContentHandler(Writer out) {

		handler = new RuleExtractor();

	}

	private void createRuleSubSet() {

		annotationReglesSubSet = new ArrayList<AnnotationRegle>();

		System.out.println("looking for: ");
		System.out.println("state: " + ruleStateSearch);
		System.out.println("thematic: " + selectedThematicsRules);
		System.out.println("destination: " + selectedDestinationRules);

		if (annotationRegles == null) {
			System.out
					.println("ERROR: annotation rules list was never created.");
		}

		// brute force for creating a subset. All this can be replaced by xpath
		for (AnnotationRegle rule : annotationRegles) {

			// does matches for either nouveau or modifie
			// System.out.println("\ncurrent rule");
			// System.out.println("state: " + rule.getActualiteRegle());
			// System.out.println("thematic: " + rule.getExigenceRegle());
			// System.out.println("domain app: " +
			// rule.getDomaineApplication());
			// System.out.println("\n");

			if (rule.getActualiteRegle().matches(ruleStateSearch)) {
				if (!selectedThematicsRules.isEmpty()
						|| !selectedDestinationRules.isEmpty()) {
					// matches things such as accessibilite, acoustique, etc
					if (selectedThematicsRules
							.contains(rule.getExigenceRegle())
							|| selectedDestinationRules.contains(rule
									.getDomaineApplication())) {
						annotationReglesSubSet.add(rule);
					}
				} else {
					annotationReglesSubSet.add(rule);
				}
			}
		}

	}

	/*
	 * Creates an XML parser and reads instance's specified ifc file
	 * 
	 */
	private void parse(List<String> annotationReglesFiles) {

		// create a parser instance
		createReader();

		// Since this just writes onto the console, it's best
		// to use the system default encoding, which is what
		// we get by not specifying an explicit encoding here.
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

		// register a content handler
		registerContentHandler(out);

		// register a error handler
		// registerErrorHandler(out);

		parser.setContentHandler(handler);

		for (String currAnnoRegleFile : annotationReglesFiles) {

			String currAnnoRegleFileAndPath = this.annotationReglePath
					.toString()
					+ File.separator + currAnnoRegleFile;

			// DEBUG
			// System.out.println("parsing: " + currAnnoRegleFileAndPath);
			try {

				// needs this strange long system depent path - this is not
				// needed in UNIX - crap windows!
				parser.parse(new File(currAnnoRegleFileAndPath).toURI()
						.toString());

				out.flush();

			} catch (IOException e) {
				System.out.println("Parsing IO error occurred");
				e.printStackTrace();
			} catch (SAXException e) {
				System.out.println("SAX exception");
				System.out.println(currAnnoRegleFileAndPath
						+ " is not well-formed.");
			}

		}

		this.annotationRegles = handler.getCollectedRules();

	}

	/*
	 * 
	 * @param fileToParse
	 */
	private void parse(String fileToParse) {

		createReader();
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
		registerContentHandler(out);
		parser.setContentHandler(handler);
		try {
			parser.parse(fileToParse);
			out.flush();
			this.annotationRegles = handler.getCollectedRules();
		} catch (IOException e) {
			System.out.println("Parsing IO error occurred");
			e.printStackTrace();

		} catch (SAXException e) {
			System.out.println("SAX exception");
			System.out.println(fileToParse + " is not well-formed.");
		}
	}

	/**
	 * Gets a list of queries from a set of annotation rules
	 * 
	 * @param annotationsRulesList
	 * @return
	 */
	public ArrayList<String> getQueryListFromAnnotations(
			ArrayList<AnnotationRegle> annotationsRulesList) {

		ArrayList<String> queryList = new ArrayList<String>();

		for (AnnotationRegle currentAnno : annotationsRulesList) {
			queryList.add(currentAnno.getContenuRegle());

		}

		return queryList;
	}

	/**
	 * returns a list of rules matching selected criteria or all if no specific
	 * selection was done.
	 * 
	 * @return
	 */
	public ArrayList<AnnotationRegle> getAnnotationRules() {

		return (ArrayList<AnnotationRegle>) this.annotationReglesSubSet;

	}

	/*
	 * private AnnotationRegle getParsedAnnotation (String fileToParse) {
	 * 
	 * createReader(); Writer out = new BufferedWriter(new
	 * OutputStreamWriter(System.out)); registerContentHandler(out);
	 * parser.setContentHandler(handler); try { parser.parse(fileToParse);
	 * out.flush(); this.currAnnoRegle = handler.getCurrentAnnotation(); return
	 * currAnnoRegle; } catch (IOException e) { System.out.println("Parsing IO
	 * error occurred"); e.printStackTrace(); } catch (SAXException e) {
	 * System.out.println("SAX exception"); System.out.println(fileToParse + "
	 * is not well-formed."); } }
	 */

}
