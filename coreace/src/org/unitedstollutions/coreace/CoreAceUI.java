/**
 * Query the CG base, by projecting the query graph of the RDFQuery on
 * graphs loaded in Corese so "query graph" is premise RDF, the CG base is
 * CoreseGraph corresponding to the RDF of the construction project this one
 * I think RDFQuery(fr.inria.acacia.corese.rdf.RDFQuery query,
 * fr.inria.acacia.corese.rdf.RDFData rdf) Query the CG base, by projecting
 * the query graph of the RDFQuery on graph loaded from rdf in Corese
 * Generalization during projection are disabled No namespace specified for
 * the loading of rdf and also the modifications will be in query
 * (QueryGraph query, CoreseGraph cg) - which returns RDFResult
 */

/**
 * @author yurchyshyna
 *
 */
package org.unitedstollutions.coreace;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.xml.transform.TransformerException;

import org.jdesktop.layout.GroupLayout;
import org.jdesktop.layout.LayoutStyle;

import fr.inria.acacia.corese.api.IResults;

public class CoreAceUI extends JFrame {

	// non GUI properties
	// private File customDataPath;
	//private Query coreseQry;
	private CoreseEngine coreseEngine;
	private AnnotationRegleBuilder annoRegleBuilder;
	
	private String defaultDataPath = "http://rainbow.essi.fr/~anastasiya/data/";
	
	// DEBUG temporary variables to remove later
	
	//private String coreseHome = "C:/Documents and Settings/yurchyshyna/workspace/CoreAce/";	
	private String coreseHome = "/media/STORENGO/nov2007/0911/";

	//private String defaultDataPathLocal = "C:/Documents and Settings/yurchyshyna/Bureau/oct2007/data/";
//	private String defaultDataPathLocal = "C:/nov2007/data/";
	private String defaultDataPathLocal = "/media/STORENGO/nov2007/0911/data/";
	//private String defaultDataPathLocal = "C:/Documents and Settings/Anastasiya/Bureau/nov2007/data/";
	private String annotationReglePathLocal = defaultDataPathLocal + "annotationsRegles/";
		
	private String annotationReglePath = defaultDataPath + "annotationsRegles/";
	//private File annoReglesFilePath = new File("C:/oct2007/data/annotationsRegles/");
	//private File annoReglesFilePath = new File(annotationReglePathLocal);

	private String defaultIfcLoadedFile = defaultDataPathLocal + "ifc02.rdf";
	private String ifcLoadedFile = defaultIfcLoadedFile;
	private String ifcExtractedFile = "";
	 
	//XSLT to clean
	private String xsltExtractUtileIfcFile = defaultDataPathLocal + "test3.xslt";
	private String xsltReportGenerator = defaultDataPathLocal + "reportGenerator.xslt";
	
	//DEBUG
	private String resCoreseFileName = defaultDataPathLocal + "cor_results/res0180.xml";
	//private String pathAnno80 = annotationReglePathLocal + "annotationRegle0080test.rdf";
	// private File annoReglesFilePath = new File("data/annotationsRegles");
	private String resSparqlFileName = "";
	//XPath condition to select ALL queries from annotations (value of contenuRegle tag)
	private String conditionToSelectAnnotations = "//AnnotationRegle/contenuRegle";
	
	private QueryDialog queryListDialog;
	
	// content of the application of the Corese as string
	private String resCorese="";
	private String resSparql="";
	
	// list of ALL annotations from default local anno dir
	private ArrayList<String> defaultAnnoList = getFilesFullFromDir(annotationReglePathLocal);
	//list of full names of queries extracted from annotations
	private ArrayList<String> queries ;
	// list of short names of queries queryN.txt
	private ArrayList<String> queriesNames;
	// list of queries full names to be sent to Corese engine (after selection from the QueryDialog)
	private ArrayList<String> queriesToCorese = new ArrayList<String>();
	
	
	private ArrayList<String> destinationList = new ArrayList<String>();
	private ArrayList<String> thematics = new ArrayList<String>();
	
	// GUI variables
	// custom
	private Frame frame = null;
	private JMenuBar jMenuBar1;
	private JMenu jMenuConstructionProject;
	private JMenu jMenuConstructionRules;
	private JMenu typeSubMenuThem;
	private JMenu typeSubMenuDest;
	private JMenu jMenuHelp;
	
	//submenu of ConstructionProject
	private JMenuItem defaultDataPathRadioButtonMenuItem;
	private JMenuItem setIfcbyURIMenuItem;
	private JMenuItem setIfcLocalMenuItem;
	//private JMenuItem validateLoadMenuItem;
	private JMenuItem applyTransformXSLTMenuItem;
	private JMenuItem showCurrentIfcMenuItem;
	
	//submenu of ConstructionRule
	private JMenuItem buildRulesMenuItem;
	
	//submenu of Help
	private JMenuItem helpMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem helpInfoMenuItem;
	
	// main buttons
	private JButton runButton;
	private JButton reportButton;
	private JButton quitButton;
	// OK from Help
	private JButton aboutOKButton;
	private JButton helpOKButton;
	
	private JScrollPane jScrollPane1;
	private JPanel jPanel2;
	private JTextArea output;	
	private JFileChooser dataPathFileChooser;
	private JSeparator jSeparator1;
	private JSeparator jSeparator2;
	private JSeparator jSeparator3;
	private JSeparator jSeparator4;

	// dialogs
	private JDialog aboutDialogContainer;
	private JDialog helpDialogContainer;
	// private JDialog loadIfcDialogContainer;
	private JLabel aboutLabel;
	private JLabel helpLabel;

    // thematics check-boxes
	private JCheckBoxMenuItem acoustiqueCheckBox;
	private JCheckBoxMenuItem aerationCheckBox;
	private JCheckBoxMenuItem accessibiliteCheckBox;
	// destination check-boxes
	private JCheckBoxMenuItem erpCheckBox;
	private JCheckBoxMenuItem bhcnCheckBox;
	private JCheckBoxMenuItem chpCheckBox;
	
	//actuality radio-buttons (not working => MenuItems)
	private JMenuItem modifiedRadioButton;
	private JMenuItem newRadioButton;


	/**
	 * Listens to actions on the Construction Project Menu
	 * 
	 */
	class ConstructionProjectMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == defaultDataPathRadioButtonMenuItem) {
				System.out.println("Using default local data path " + defaultIfcLoadedFile + "\n");
				// no changes with default ifcLoadedFile 
			 	ifcLoadedFile = defaultIfcLoadedFile;
			} else if (e.getSource() == setIfcbyURIMenuItem) {
				InputDialog indial = new InputDialog();
				ifcLoadedFile = indial.getUrl();
				System.out.println("loaded file is : " + ifcLoadedFile);
				ifcLoadedFile = setIfcLoadedURLFile(ifcLoadedFile).getAbsolutePath();
				// TO DO add "cancel" checking
			} else if (e.getSource() == setIfcLocalMenuItem) {
				setIfcLoadedFile();
			}  else if (e.getSource() == applyTransformXSLTMenuItem) {
				System.out
						.println("\napplying the xslt transformation to the XML representation of the construction project  ...  to be implemented\n");
				System.out.println("it'll be applied to this file : "
						+ getIfcLoadedFile());
				try {
					XSLTransf xt = new XSLTransf(ifcLoadedFile,
							xsltExtractUtileIfcFile);
					ifcExtractedFile = xt.getTransformedPath();
					System.out.println(ifcExtractedFile);
					System.out.println("presenting the content of the transformed file");
					printFileContent(xt.getTransformedFile());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == showCurrentIfcMenuItem) {
				System.out.println("Current Ifc file is " + ifcLoadedFile);
				System.out.println("Printing the content of the file");
				printFileContent(ifcLoadedFile);
			}
		}
	}

	/**
	 * Listens to actions on the ConstructionRules menu item
	 * 
	 */
	class ConstructionRulesMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == buildRulesMenuItem) {
				
/*				DEBUG OK
				System.out.println("DEBUG");
				ArrayList <String> test = new ArrayList<String>();
				test.add(annotationReglePathLocal + "annotationRegle0010.rdf");
				test.add(annotationReglePathLocal + "annotationRegle0020.rdf");
				test.add(annotationReglePathLocal + "annotationRegle0050.rdf");
				
				QueryFromAnnotationBuilder qb = new QueryFromAnnotationBuilder(test);
				for (String s : qb.getQueriesShort())  {
					System.out.println(s);
				}*/
				
				//System.out.println(qb.getQueriesToCorese());
				
				//DEBUG one query execution is ok
				//TestDOMXPath tp2 = new TestDOMXPath(pathAnno80,"actualiteRegle","nouveau");
				//System.out.println("result query is :" + tp2.getQuery());
				
				// taking default query list
				QueryFromAnnotationBuilder qb = new QueryFromAnnotationBuilder(defaultAnnoList);
				/*for (String s : qb.getQueriesFull())  {
					System.out.println(s);
				}*/
/*				ArrayList<String> listAnnotationNames = getFilesFromDir(annotationReglePathLocal);
				ArrayList<String> listSelectedQueries = new ArrayList<String>();
				for (String annCurr : listAnnotationNames) {
					String fullCurrName = annotationReglePathLocal + annCurr;
					//System.out.println("current rdf is " +fullCurrName);
					TestDOMXPath tpFull = new TestDOMXPath(fullCurrName, "//AnnotationRegle/contenuRegle");
					String curQuery = tpFull.getQuery();
					//System.out.println(curQuery);
					if (!(curQuery.equals(""))) {
						listSelectedQueries.add(curQuery);
					}
				}*/
				/* DEBUG
				 * System.out.println("queries of the list : ");
				for (String q : listSelectedQueries){
					System.out.println("q : " + q);
				}*/
				queries = qb.getQueriesFull();
				//queryListDialog = new QueryDialog(null, qb.getQueriesShort());
				//queryListDialog.setQueryList(queries);
				QueryDialog qdA = new QueryDialog(frame, qb.getQueriesShort());
				qdA.refreshContents();
				qdA.setLocationRelativeTo(frame);
				qdA.setVisible(true);
				//queryListDialog = new QueryDialog(frame, this.queriesNames);
				queriesToCorese = qdA.getSelectedQueries();
/*				queriesToCorese = queryListDialog.getSelectedQueries();
				for (String q:queriesToCorese){
					System.out.println("going to corese : " + q);
				}*/
					
				/*System.out
						.println(queries
								+ " are the queries extracted from preselected annotations ...\n to be implemented by parsing the annotations; the link to the queries is the value of contenuRegle tag\n\n");
*/

				
			} else if (e.getSource() == newRadioButton) {
				ArrayList<String> newQueryList = getFilesFullFromDir(annotationReglePathLocal);
				QueryFromAnnotationBuilder qbN = new QueryFromAnnotationBuilder(newQueryList);
				
				queries = qbN.getQueriesFull();
				//queryListDialog = new QueryDialog(null, qb.getQueriesShort());
				//queryListDialog.setQueryList(queries);
				QueryDialog qdN = new QueryDialog(queries);
				qdN.refreshContents();
				qdN.setLocationRelativeTo(frame);
				qdN.setVisible(true);
				queriesToCorese = qdN.getSelectedQueries();
/*				ArrayList<String> listAnnotationNames = getFilesFromDir(annotationReglePathLocal);
				ArrayList<String> listSelectedQueries = new ArrayList<String>();
				for (String annCurr : listAnnotationNames) {
					String fullCurrName = annotationReglePathLocal + annCurr;
					//System.out.println(fullCurrName);
					TestDOMXPath tpFull = new TestDOMXPath(fullCurrName,"//AnnotationRegle[actualiteRegle='nouveau']/contenuRegle");
					String curQuery = tpFull.getQuery();
					System.out.println(curQuery);
					if (!(curQuery.equals(null))) {
						listSelectedQueries.add(curQuery);
					}
					}	
				queries = listSelectedQueries;
				System.out.println("there are " + listSelectedQueries.size() + " queries from NEW annotations");
				//annoRegleBuilder.setRuleStateSearch("nouveau");
*/			} else if (e.getSource() == modifiedRadioButton) {
			// TO check 
				TestDOMXPath tpM = new TestDOMXPath(annotationReglePathLocal,"//AnnotationRegle[actualiteRegle='modifie']/contenuRegle");
				
				QueryFromAnnotationBuilder qbM = new QueryFromAnnotationBuilder(tpM.getQueryList());
				
				queries = qbM.getQueriesFull();
				//queryListDialog = new QueryDialog(null, qb.getQueriesShort());
				//queryListDialog.setQueryList(queries);
				QueryDialog qdM = new QueryDialog(frame, qbM.getQueriesShort());
				qdM.refreshContents();
				qdM.setLocationRelativeTo(frame);
				qdM.setVisible(true);
				//queryListDialog = new QueryDialog(frame, this.queriesNames);
				queriesToCorese = qdM.getSelectedQueries();
				
/*				ArrayList<String> listAnnotationNames = getFilesFromDir(annotationReglePathLocal);
				ArrayList<String> listSelectedQueries = new ArrayList<String>();
				for (String annCurr : listAnnotationNames) {
					String fullCurrName = annotationReglePathLocal + annCurr;
					//System.out.println(fullCurrName);
					TestDOMXPath tpFull = new TestDOMXPath(fullCurrName,"//AnnotationRegle[actualiteRegle='modifie']/contenuRegle");
					String curQuery = tpFull.getQuery();
					System.out.println(curQuery);
					if (!(curQuery.equals(null))) {
						listSelectedQueries.add(curQuery);
					}
					}	
				queries = listSelectedQueries;*/

			}
		}

	}
	
	/**
	 * Listens for events on choose thematic rules sub-menu from the main
	 * Construction rules menu
	 * 
	 */
	class ThematicsRulesSubMenuListener implements ItemListener {
		public String getName(ItemEvent e){
			return ((JCheckBoxMenuItem)e.getItemSelectable()).getText();
		}
		public void itemStateChanged(ItemEvent e) {

			String rule = ((JCheckBoxMenuItem) e.getItemSelectable()).getText();

			// add or remove thematic rules from the list of selected rules to
			// process
			if (e.getStateChange() == ItemEvent.SELECTED) {
				
				System.out.println("selected " + getName(e));
				conditionToSelectAnnotations = "//AnnotationRegle[thematiqueRegle='" + getName(e)+ "']/contenuRegle";
				System.out.println(conditionToSelectAnnotations);					
				
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				System.out.println("everything unselected");
			}
			
			// build annotation list part
			//queries = getQueryList(annotationReglePathLocal, conditionToSelectAnnotations);

		}
	}

	/**
	 * Listens for events on choose destination rules sub-menu from the main
	 * Construction rules menu
	 * 
	 */
	class DestinationRulesSubMenuListener implements ItemListener {

		public String getName(ItemEvent e){
			return ((JCheckBoxMenuItem)e.getItemSelectable()).getText();
		}
		public void itemStateChanged(ItemEvent e) {

			String rule = ((JCheckBoxMenuItem) e.getItemSelectable()).getText();
			
			// adds or removes rules from the selected destination rules to
			// process
			if (e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("adding item to list: " + rule);
				destinationList.add(rule);
				System.out.println("list now has: " + destinationList);
				
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				System.out.println("removing item from list: " + rule);
				destinationList.remove(rule);
				System.out.println("List now has: " + destinationList);
			}
			
		}
	}

	/**
	 * Listens to actions on the buttons on the main window
	 * 
	 */
	class MainButtonsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == runButton) {
				System.out
						.println("\nChecking by corese engine is launched to check the conformity of the loaded construction project to the selected queries.\n If no selection is made by user - default settings \n");
				//runQuery();
				for (String cq: queriesToCorese) {
					System.out.println("query going to corese " + cq);
				}
				System.out.println("toto1");
				
/*				// DEBUG OK
				QueryReader qr = new QueryReader(queriesToCorese);
				for (String qc : qr.getQueryFullUri()){
					System.out.println("query taken from Query Reader " + qc);
					System.out.println("totoQR");
				}*/
				System.out.println("toto2");
				QueryReader qr = new QueryReader(queriesToCorese);
				System.out.println("toto3");
				for (String qc : qr.getQueryContents()){
					System.out.println("query CONTENT going to corese " + qc);
					System.out.println("toto");
				}
				//runQuery(queriesToCorese);
				runQuery(qr.getQueryContents());
			} else if (e.getSource() == reportButton) {
				// TO DO generate report in XHTML
				System.out
						.println("\nConformity report will be generated ... \nNot implemented yet\n");
				
				try {
					String repFile = coreseHome + "conformityReport.html";
					XSLTransf repGen = new XSLTransf(resCoreseFileName, xsltReportGenerator, repFile);
					System.out.println("result file in corese is ; " + resCoreseFileName);
					printFileContent(repGen.resultFile);
					
					String repFileSp = coreseHome + "conformityReportSP.html";
					XSLTransf repGenSp = new XSLTransf(resSparqlFileName, xsltReportGenerator, repFileSp);
					System.out.println("result file in sparql is ; " + resSparqlFileName);
					printFileContent(repGenSp.resultFile);
					
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (e.getSource() == quitButton) {
				// add dialog/confirmation "really wanna exit?"
				System.exit(0);
			}
		}
	}

	/*
	 * Implementation methods
	 * 
	 */

	/**
	 * Initialize the application
	 * 
	 */
	private void initApplication() {

		QueryFromAnnotationBuilder qfb = new QueryFromAnnotationBuilder(annotationReglePathLocal);
		this.queries = qfb.getQueriesFull();
		this.queriesNames = qfb.getQueriesShort();

/*		queryListDialog = new QueryDialog(frame, this.queriesNames);
		queriesToCorese = queryListDialog.getSelectedQueries();*/

		// creating engine without precising the datapath, only engine_data
		// change to getIfcExtracted when the xsltExtractUtile is tested and 
		// its applying gives the correct ifcExtractedFile
		coreseEngine = new CoreseEngine(getIfcLoadedFile());
				
	}

	/**
	 * Run the query. Gets called by the Run button
	 * 
	 */
	private void runQuery() {
		// query results results
		IResults res;

		// TODO call ReportGenerator 
		
		if (queries.isEmpty()) {
			System.out
					.println("\nERROR: Cannot run engine. No queries available.");
		} else {
			// System.out.println (queries);
			
			res = coreseEngine.run(queriesToCorese);
			
			// ReportGenerator rg = new
			// ReportGenerator(ifcExtractedFile,queries,res);

			if (res != null) {
				System.out.println(res);
				//writing the res to the file in CORESE format  
				resCorese = res.toCoreseResult();
				System.out.println("The result in Corese format is as follows : ");
				System.out.println(resCorese);
				File fResCorese = new File("corRes.xml");	
				// writing content to the file
				try {
					FileWriter fwC = new FileWriter(fResCorese);	
					PrintWriter pwC = new PrintWriter(fwC);
					pwC.println(resCorese);
					fwC.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resCoreseFileName = fResCorese.getAbsolutePath();
				
				//writing the res to the file in SPARQL format  
				resSparql = res.toSPARQLResult();
				System.out.println("The result in SPARQL format is as follows : ");
				System.out.println(resSparql);
				File fResSparql = new File("sparqlRes.xml");	
				// writing content to the file
				try {
					FileWriter fwS = new FileWriter(fResSparql);	
					PrintWriter pwS = new PrintWriter(fwS);
					pwS.println(resSparql);
					fwS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				resSparqlFileName = fResSparql.getAbsolutePath();
			}
		}

	}
	
	private void runQuery(ArrayList<String> listCoreseQueries) {
		// query results results
		IResults res;

		// TODO call ReportGenerator 
		
		if (listCoreseQueries.isEmpty()) {
			System.out
					.println("\nERROR: Cannot run engine. No queries available.");
		} else {
			// System.out.println (queries);
			
/*	TO DO		
 * QueryReader qr = new QueryReader(listCoreseQueries);
			
			res = coreseEngine.run(qr.listQueryContents);*/
			
			res = coreseEngine.run(listCoreseQueries);
			
			// ReportGenerator rg = new
			// ReportGenerator(ifcExtractedFile,queries,res);

			if (res != null) {
				System.out.println(res);
				//writing the res to the file in CORESE format  
				resCorese = res.toCoreseResult();
				System.out.println("The result in Corese format is as follows : ");
				System.out.println(resCorese);
				File fResCorese = new File("corRes.xml");	
				// writing content to the file
				try {
					FileWriter fwC = new FileWriter(fResCorese);	
					PrintWriter pwC = new PrintWriter(fwC);
					pwC.println(resCorese);
					fwC.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//writing the res to the file in SPARQL format  
				resSparql = res.toSPARQLResult();
				System.out.println("The result in SPARQL format is as follows : ");
				System.out.println(resSparql);
				File fResSparql = new File("sparqlRes.xml");	
				// writing content to the file
				try {
					FileWriter fwS = new FileWriter(fResSparql);	
					PrintWriter pwS = new PrintWriter(fwS);
					pwS.println(resSparql);
					fwS.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}

	}

	private void redirectOutput() {

		// Now create a new TextAreaOutputStream to write to our JTextArea
		// control and wrap a
		// PrintStream around it to support the println/printf methods.
		PrintStream out = new PrintStream(new TextAreaOutputStream(output));

		// redirect standard output stream to the TextAreaOutputStream
		System.setOut(out);

		// redirect standard error stream to the TextAreaOutputStream
		System.setErr(out);

	}

	/*
	 * ****************************************************************************
	 * The code below is mostly automatically generated - implements the GUI
	 * creation.
	 * ****************************************************************************
	 */

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CoreAceUI inst = new CoreAceUI();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public CoreAceUI() {
		super();
		initGUI();

		initApplication();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);

			this
					.setTitle("C3R : Conformity Checking in Construction by Reasoning");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				{
					output = new JTextArea();
					jScrollPane1.setViewportView(output);
					output
							.setText("Welcome to C3R 1.0! \n\n"
									+ "C3R is a reasoning engine for checking of the conformity of your construction project to the construction rules. \n"
									+ "File menu  ... not implemented yet - to see if really needed\n"
									+ "To load your construction project go to menu ConstructionProject \n"
									+ "To choose rules to check please go to menu RulesChooser \n"
									+ "Query menu is a temporary one - if you want to choose the queries manually \n"
									+ "Help menu presents the general info about the project\n"
									+ "Please start ... \n\n\n");
					output.setBackground(new java.awt.Color(0, 0, 123));
					output.setForeground(new java.awt.Color(255, 255, 0));
					output.setFont(new java.awt.Font("Arial", 1, 12));
					output.setEditable(false);
					// custom addition to redirect the output
					// to the text area instead of std out
					redirectOutput();
				}
			}
			{
				jPanel2 = new JPanel();
				FlowLayout jPanel2Layout = new FlowLayout();
				jPanel2Layout.setAlignment(FlowLayout.RIGHT);
				jPanel2.setLayout(jPanel2Layout);
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				jPanel2.setPreferredSize(new java.awt.Dimension(390, 38));
				{
					runButton = new JButton();
					jPanel2.add(runButton);
					runButton.setText("Run Checking");
					runButton.addActionListener(new MainButtonsListener());
				}
				{
					reportButton = new JButton();
					jPanel2.add(reportButton);
					reportButton.setText("Generate Conformity Report");
					reportButton.addActionListener(new MainButtonsListener());
				}
				{
					quitButton = new JButton();
					jPanel2.add(quitButton);
					quitButton.setText("Quit from C3R");
					quitButton.addActionListener(new MainButtonsListener());
				}
			}
			this.setSize(800, 600);
			{
				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				{

					jMenuBar1.add(getJMenuConstructionProject());
					// jMenu3 = new JMenu();
					// jMenu3.setText("ConstructionRules");

					jMenuBar1.add(getJMenuConstructionRules());

					/*ButtonGroup group = new ButtonGroup();
					// default path radio button
					group.add(getNewRadioButton());
					group.add(getModifiedRadioButton());*/
				}

				{
					jMenuHelp = new JMenu();
					jMenuBar1.add(jMenuHelp);
					jMenuHelp.setText("Help");
					{
						helpMenuItem = new JMenuItem();
						jMenuHelp.add(getHelpMenuItem());
						jMenuHelp.add(getAboutMenuItem());

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JButton getAboutOKButton() {
		if (aboutOKButton == null) {
			aboutOKButton = new JButton();
			aboutOKButton.setText("OK");
			aboutOKButton.addActionListener(new aboutDialogOkListener());
		}
		return aboutOKButton;
	}

	private JButton getHelpOKButton() {
		if (helpOKButton == null) {
			helpOKButton = new JButton();
			helpOKButton.setText("OK");
			helpOKButton.addActionListener(new helpDialogOkListener());
		}
		return helpOKButton;
	}
		
	private JDialog getAboutDialogContainer() {
		if (aboutDialogContainer == null) {
			aboutDialogContainer = new JDialog(this);
			GroupLayout aboutDialogContainerLayout = new GroupLayout(
					(JComponent) aboutDialogContainer.getContentPane());
			aboutDialogContainer.getContentPane().setLayout(
					aboutDialogContainerLayout);
			aboutDialogContainer.setPreferredSize(new java.awt.Dimension(335,
					123));
			aboutDialogContainer.setTitle("About");
			aboutDialogContainer.setSize(335, 123);
			aboutDialogContainerLayout
					.setHorizontalGroup(aboutDialogContainerLayout
							.createParallelGroup().add(GroupLayout.LEADING,
									getAboutLabel(),
									GroupLayout.PREFERRED_SIZE, 327,
									GroupLayout.PREFERRED_SIZE).add(
									GroupLayout.LEADING,
									aboutDialogContainerLayout
											.createSequentialGroup().add(0,
													254, Short.MAX_VALUE).add(
													getAboutOKButton(),
													GroupLayout.PREFERRED_SIZE,
													61,
													GroupLayout.PREFERRED_SIZE)
											.addContainerGap(12, 12)));
			aboutDialogContainerLayout
					.setVerticalGroup(aboutDialogContainerLayout
							.createSequentialGroup().add(getAboutLabel(),
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED).add(
									getAboutOKButton(),
									GroupLayout.PREFERRED_SIZE, 26,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
		}
		return aboutDialogContainer;
	}

	private JDialog getHelpDialogContainer() {
		if (helpDialogContainer == null) {
			helpDialogContainer = new JDialog(this);
			GroupLayout helpDialogContainerLayout = new GroupLayout(
					(JComponent) helpDialogContainer.getContentPane());
			helpDialogContainer.getContentPane().setLayout(
					helpDialogContainerLayout);
			helpDialogContainer.setPreferredSize(new java.awt.Dimension(515,
					123));
			helpDialogContainer.setTitle("Help me understand how it works");
			helpDialogContainer.setSize(515, 123);
			helpDialogContainerLayout
					.setHorizontalGroup(helpDialogContainerLayout
							.createParallelGroup().add(GroupLayout.LEADING,
									getHelpLabel(), GroupLayout.PREFERRED_SIZE,
									500, GroupLayout.PREFERRED_SIZE).add(
									GroupLayout.LEADING,
									helpDialogContainerLayout
											.createSequentialGroup().add(0,
													254, Short.MAX_VALUE).add(
													getHelpOKButton(),
													GroupLayout.PREFERRED_SIZE,
													61,
													GroupLayout.PREFERRED_SIZE)
											.addContainerGap(12, 12)));
			helpDialogContainerLayout
					.setVerticalGroup(helpDialogContainerLayout
							.createSequentialGroup().add(getHelpLabel(),
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED).add(
									getHelpOKButton(),
									GroupLayout.PREFERRED_SIZE, 26,
									GroupLayout.PREFERRED_SIZE)
							.addContainerGap());
		}
		return helpDialogContainer;
	}

	private JLabel getAboutLabel() {
		if (aboutLabel == null) {
			aboutLabel = new JLabel();
			aboutLabel.setText("     written by Anastasiya Yurchyshyna");
			aboutLabel.setHorizontalAlignment(SwingConstants.LEFT);
			aboutLabel.setBackground(new java.awt.Color(0, 0, 255));
			aboutLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory
					.createTitledBorder(""), "Author", TitledBorder.LEADING,
					TitledBorder.TOP));
		}
		return aboutLabel;
	}

	private JLabel getHelpLabel() {
		if (helpLabel == null) {
			helpLabel = new JLabel();
			helpLabel
					.setText("     there will be some info about the theoretical background of the project ");
			helpLabel.setHorizontalAlignment(SwingConstants.LEFT);
			helpLabel.setBackground(new java.awt.Color(0, 0, 255));
			helpLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory
					.createTitledBorder(""), "Theoretical background of C3R",
					TitledBorder.LEADING, TitledBorder.TOP));
		}
		return helpLabel;
	}

	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("About Project");
			aboutMenuItem.addActionListener(new AboutMenuItemListener());

		}
		return aboutMenuItem;
	}

	private JMenuItem getHelpMenuItem() {
		if (helpInfoMenuItem == null) {
			helpInfoMenuItem = new JMenuItem();
			helpInfoMenuItem.setText("Theoretical Help");
			helpInfoMenuItem.addActionListener(new HelpMenuItemListener());
		}
		return helpInfoMenuItem;
	}

	private JMenuItem getDefaultDataPathRadioButtonMenuItem() {
		if (defaultDataPathRadioButtonMenuItem == null) {
			defaultDataPathRadioButtonMenuItem = new JRadioButtonMenuItem();
			defaultDataPathRadioButtonMenuItem.setText("Default .ifcxml file");
			defaultDataPathRadioButtonMenuItem.setSelected(true);
			defaultDataPathRadioButtonMenuItem
					.addActionListener(new ConstructionProjectMenuItemListener());
		}
		return defaultDataPathRadioButtonMenuItem;
	}

	private JMenuItem getSetIFCbyURIMenuItem() {
		if (setIfcbyURIMenuItem == null) {
			setIfcbyURIMenuItem = new JMenuItem();
			setIfcbyURIMenuItem.setText("Set construction project by URI");
			setIfcbyURIMenuItem
					.addActionListener(new ConstructionProjectMenuItemListener());
		}
		return setIfcbyURIMenuItem;
	}

	private JMenuItem getSetIFClocalMenuItem() {
		if (setIfcLocalMenuItem == null) {
			setIfcLocalMenuItem = new JMenuItem();
			setIfcLocalMenuItem
					.setText("Set construction project by local address");
			setIfcLocalMenuItem
					.addActionListener(new ConstructionProjectMenuItemListener());
		}
		return setIfcLocalMenuItem;
	}

/*	private JMenuItem validateIfcMenuItem() {
		if (validateLoadMenuItem == null) {
			validateLoadMenuItem = new JMenuItem();
			validateLoadMenuItem
					.setText("Parse loaded construction project .ifcxml");
			validateLoadMenuItem
					.addActionListener(new ConstructionProjectMenuItemListener());
		}
		return validateLoadMenuItem;
	}*/

	private JMenuItem getApplyTransformMenuItem() {
		if (applyTransformXSLTMenuItem == null) {
			applyTransformXSLTMenuItem = new JMenuItem();
			applyTransformXSLTMenuItem
					.setText("Apply XSLT transformation to extract 'utile' project");
			applyTransformXSLTMenuItem
					.addActionListener(new ConstructionProjectMenuItemListener());
		}
		return applyTransformXSLTMenuItem;
	}

	private JFileChooser getFileChooser() {
		if (dataPathFileChooser == null) {
			dataPathFileChooser = new JFileChooser();

			// Uncomment one of the following lines to try a different
			// file selection mode. The first allows just directories
			// to be selected (and, at least in the Java look and feel,
			// shown). The second allows both files and directories
			// to be selected. If you leave these lines commented out,
			// then the default mode (FILES_ONLY) will be used.
			//
			dataPathFileChooser
					.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		}
		return dataPathFileChooser;
	}

	private JSeparator getJSeparator1() {
		if (jSeparator1 == null) {
			jSeparator1 = new JSeparator();
		}
		return jSeparator1;
	}

	private JMenu getJMenuConstructionProject() {
		if (jMenuConstructionProject == null) {
			jMenuConstructionProject = new JMenu();
			jMenuConstructionProject.setText("ConstructionProject");
			jMenuConstructionProject.add(getDefaultDataPathRadioButtonMenuItem());
			jMenuConstructionProject.add(getSetIFCbyURIMenuItem());
			jMenuConstructionProject.add(getSetIFClocalMenuItem());
			//jMenuConstructionProject.add(validateIfcMenuItem());
			{
				jSeparator2 = new JSeparator();
				jMenuConstructionProject.add(jSeparator2);
			}
			jMenuConstructionProject.add(getApplyTransformMenuItem());
			{
				jSeparator3 = new JSeparator();
				jMenuConstructionProject.add(jSeparator3);

			}
			{
				showCurrentIfcMenuItem = new JMenuItem();
				jMenuConstructionProject.add(showCurrentIfcMenuItem);
				showCurrentIfcMenuItem.setText("Show current Ifc file");
				showCurrentIfcMenuItem
						.addActionListener(new ConstructionProjectMenuItemListener());
			}
		}
		return jMenuConstructionProject;
	}

	private JMenu getJMenuConstructionRules() {

		if (jMenuConstructionRules == null) {
			jMenuConstructionRules = new JMenu();
			jMenuConstructionRules.setText("ConstructionRules");

			ButtonGroup group = new ButtonGroup();
			// default path radio button
			group.add(getNewRadioButton());
			group.add(getModifiedRadioButton());

			jMenuConstructionRules.add(getNewRadioButton());
			jMenuConstructionRules.add(getModifiedRadioButton());
			jMenuConstructionRules.add(getJSeparator4());
			jMenuConstructionRules.add(getTypeSubMenuThem());
			jMenuConstructionRules.add(getTypeSubMenuDest());
			jMenuConstructionRules.add(getJSeparator1());
			jMenuConstructionRules.add(getBuildRulesMenuItem());
		}
		return jMenuConstructionRules;
	}

	private JMenuItem getNewRadioButton() {
		if (newRadioButton == null) {
			newRadioButton = new JMenuItem();
			newRadioButton.setText("nouveau");
			//newRadioButton.setSelected(true);
			newRadioButton
					.addActionListener(new ConstructionRulesMenuListener());
		}
		return newRadioButton;
	}

	private JMenuItem getModifiedRadioButton() {
		if (modifiedRadioButton == null) {
			modifiedRadioButton = new JMenuItem();
			modifiedRadioButton.setText("modifie");
			modifiedRadioButton
					.addActionListener(new ConstructionRulesMenuListener());
		}
		return modifiedRadioButton;
	}

	private JMenu getTypeSubMenuThem() {
		if (typeSubMenuThem == null) {
			typeSubMenuThem = new JMenu();
			typeSubMenuThem.setText("Choose Rule Thematics");
			typeSubMenuThem.add(getAccessibiliteCheckBox());
			typeSubMenuThem.add(getAcoustiqueCheckBox());
			typeSubMenuThem.add(getAerationCheckBox());
		}
		return typeSubMenuThem;
	}

	private JMenu getTypeSubMenuDest() {
		if (typeSubMenuDest == null) {
			typeSubMenuDest = new JMenu();
			typeSubMenuDest.setText("Choose Rule Destination");
			typeSubMenuDest.add(getERPCheckBox());
			typeSubMenuDest.add(getBHCNCheckBox());
			typeSubMenuDest.add(getCHPCheckBox());
		}
		return typeSubMenuDest;
	}
	
	private JSeparator getJSeparator2() {
		if (jSeparator2 == null) {
			jSeparator2 = new JSeparator();
		}
		return jSeparator2;
	}

	private JSeparator getJSeparator3() {
		if (jSeparator3 == null) {
			jSeparator3 = new JSeparator();
		}
		return jSeparator3;
	}
	
	private JSeparator getJSeparator4() {
		if (jSeparator4 == null) {
			jSeparator4 = new JSeparator();
		}
		return jSeparator4;
	}

	private JMenuItem getBuildRulesMenuItem() {
		if (buildRulesMenuItem == null) {
			buildRulesMenuItem = new JMenuItem();
			buildRulesMenuItem.setText("Build List of Selected Rules");
			buildRulesMenuItem
					.addActionListener(new ConstructionRulesMenuListener());
		}
		return buildRulesMenuItem;
	}

	private JCheckBoxMenuItem getAccessibiliteCheckBox() {
		if (accessibiliteCheckBox == null) {
			accessibiliteCheckBox = new JCheckBoxMenuItem();
			accessibiliteCheckBox.setText("accessibilite");
			accessibiliteCheckBox
					.addItemListener(new ThematicsRulesSubMenuListener());
		}
		return accessibiliteCheckBox;
	}

	private JCheckBoxMenuItem getAcoustiqueCheckBox() {
		if (acoustiqueCheckBox == null) {
			acoustiqueCheckBox = new JCheckBoxMenuItem();
			acoustiqueCheckBox.setText("acoustique");
			acoustiqueCheckBox
					.addItemListener(new ThematicsRulesSubMenuListener());
		}
		return acoustiqueCheckBox;
	}

	private JCheckBoxMenuItem getAerationCheckBox() {
		if (aerationCheckBox == null) {
			aerationCheckBox = new JCheckBoxMenuItem();
			aerationCheckBox.setText("aeration");
			aerationCheckBox
					.addItemListener(new ThematicsRulesSubMenuListener());
		}
		return aerationCheckBox;
	}

	private JCheckBoxMenuItem getERPCheckBox() {
		if (erpCheckBox == null) {
			erpCheckBox = new JCheckBoxMenuItem();
			erpCheckBox.setText("ERP");
			erpCheckBox.addItemListener(new DestinationRulesSubMenuListener());
		}
		return erpCheckBox;
	}

	private JCheckBoxMenuItem getBHCNCheckBox() {
		if (bhcnCheckBox == null) {
			bhcnCheckBox = new JCheckBoxMenuItem();
			bhcnCheckBox.setText("BHCN");
			bhcnCheckBox.addItemListener(new DestinationRulesSubMenuListener());
		}
		return bhcnCheckBox;
	}

	private JCheckBoxMenuItem getCHPCheckBox() {
		if (chpCheckBox == null) {
			chpCheckBox = new JCheckBoxMenuItem();
			chpCheckBox.setText("CHP");
			chpCheckBox.addItemListener(new DestinationRulesSubMenuListener());
		}
		return chpCheckBox;
	}

	
	
	/**
	 * Dialog action listener
	 * 
	 */
	class aboutDialogOkListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			getAboutDialogContainer().dispose();
		}
	}

	class helpDialogOkListener implements ActionListener {
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			getHelpDialogContainer().dispose();
		}
	}

	/**
	 * About menu action listener
	 * 
	 */
	class AboutMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getAboutDialogContainer().pack();
			getAboutDialogContainer().setLocationRelativeTo(null);
			getAboutDialogContainer().setVisible(true);
		}
	}

	class HelpMenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			getHelpDialogContainer().pack();
			getHelpDialogContainer().setLocationRelativeTo(null);
			getHelpDialogContainer().setVisible(true);
		}
	}
	
//	 getter of the name of the loaded ifc
	public String getIfcLoadedFile() {
		return ifcLoadedFile;
	}

	// getter of the name of the xslt-modified ifc
	public String getIfcExtractedFile() {
		return ifcExtractedFile;
	}

	public void setIfcLoadedFile() {
		try {
			FileChooseApp fca = new FileChooseApp("");
			// boolean isLoaded = fca.openFile();
			if (fca.openFile()) {
				File ifcLoaded = fca.getLoadedFile();
				ifcLoadedFile = ifcLoaded.getAbsolutePath();
				System.out.println(ifcLoadedFile);
				// ifcLoadedFile = fca.getLoadedFileName();
			} else {
				System.out.println("how to open it??");
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public File setIfcLoadedURLFile(String fileUrl) {
		
/*		File fileObjectRes = new File(ifcLoadedFile);
		URL urlOfQuery;
		try {
			urlOfQuery = new URL(fileUrl);
			String fileName = urlOfQuery.getFile();
			File fileObject = new File(fileName);
			fileObjectRes = fileObject;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println ("IMPOSSIBLE to read from URL ; taking current ifcLoadedFile : " + ifcLoadedFile);
			e.printStackTrace();
		}
		
		return fileObjectRes;*/
	
	
		URLUtils fetcher = new URLUtils();
		File fetchedFile = fetcher.readFetchFile(fileUrl);
		//String loadedFile = fetchedFile.getAbsoluteFile().getName();
		//System.out.println("File fetched: " + fetchedFile.getName());
		//System.out.println("Located under: " + fetchedFile.getAbsoluteFile());
		return fetchedFile;
	}

	// get File corresponding to this fileName
	public File getFileFromName(String fileName) {
		File file = new File(fileName);
		return file;
	}

	// print File
	public void printFileContent(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));

			String inputLine = null;
			while ((inputLine = br.readLine()) != null) {
				System.out.println(inputLine);
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File " + fileName + " was not found");
			System.out.println("Please check if it can be accessed : open for reading, stable connexion, etc.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: a reading error occured");
			e.printStackTrace();
		}
	}

	// print File
	public void printFileContent(File file) {
		String fileName = file.getName();
		printFileContent(fileName);
	}
	
	public ArrayList<String> getFilesFromDir(String pathToDir){
		ArrayList<String> dirFiles = new ArrayList<String>();
		File f = new File(pathToDir); 

		if (f.exists()) {
			String[] directoryListing = f.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					dirFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("\n\nNumber of files found: " + dirFiles.size());
		System.out.println("\n\n");
		return dirFiles;
	}
	
	public ArrayList<String> getFilesFullFromDir(String pathToDir){
		ArrayList<String> dirFiles = new ArrayList<String>();
		File f = new File(pathToDir); 

		if (f.exists()) {
			String[] directoryListing = f.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					dirFiles.add(pathToDir + currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("\n\nNumber of files found: " + dirFiles.size());
		System.out.println("\n\n");
		return dirFiles;
	}
	
	public ArrayList<String> getFilesFromUri(String uriName){
		ArrayList<String> uriFiles = new ArrayList<String>();
		
		URLUtils urlu = new URLUtils();
		return uriFiles;
		

	}
	
	public ArrayList<String> getQueryList(String annotationReglePathLocal, String conditionToSelectAnnotations) {
		ArrayList<String> listAnnotationNames = getFilesFromDir(annotationReglePathLocal);
		ArrayList<String> listSelectedQueries = null;
		for (String annCurr : listAnnotationNames) {
			String fullCurrName = annotationReglePathLocal + annCurr;
			//System.out.println(fullCurrName);
			
			// here is pb
			TestDOMXPath tpFull = new TestDOMXPath(fullCurrName,conditionToSelectAnnotations);
			String curQuery = tpFull.getQuery();
			System.out.println(curQuery);
			if (!(curQuery.equals(null))) {
				listSelectedQueries.add(curQuery);
			}
			}	
		return listSelectedQueries;
	}

}
