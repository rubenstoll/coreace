package org.unitedstollutions.coreace;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class QueryDialog extends JDialog implements ActionListener {

	private JPanel jPanel1; 
	private JScrollPane jScrollPane1;
	//private JCheckBox jCheckBox1;
	private JPanel jPanel2; 
	private JButton cancelButton;
	private JButton okButton;

	private ArrayList<String> queries;
	private ArrayList<String> data;
	private String defaultQueriesPath = "http://rainbow.essi.fr/~anastasiya/data/queries/";
	
	
	class checkBoxListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			
			// get value of check box
			// upcasts to checkbox so we can get the label on the check box
			// instead of the component's name
			String query = defaultQueriesPath + ((JCheckBox) e.getItemSelectable()).getText();

			System.out.println("The string on the check box is " + query);
			
			// Now that we know which button was pushed, find out
			// whether it was selected or deselected.
			if (e.getStateChange() == ItemEvent.SELECTED) {
				addQuery(query);
			} else if (e.getStateChange() == ItemEvent.DESELECTED) {
				removeQuery(query);
			}

		}		
	}
	
	public void actionPerformed(ActionEvent e) {
		// Handle clicks on the Set and Cancel buttons.
		if ("OK".equals(e.getActionCommand())) {
			// QueryLister.dialog.setVisible(false);
			clearAndHide();
		}
		// this is the cancel part .. makes the dialog disappear
		// QueryLister.dialog.setVisible(false);
		this.queries = null;
		clearAndHide();
	}

	public QueryDialog(Frame aFrame, ArrayList<String> data) {
		super(aFrame, false);

		this.data = data;

		initGUI();

		this.queries = new ArrayList<String>();

	}

	/** This method clears the dialog and hides it. */
	public void clearAndHide() {
		setVisible(false);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getSelectedQueries() {
		return queries;
	}

	/**
	 * Add a query to the list queries
	 * 
	 * @param query
	 */
	private void addQuery(String query) {

		queries.add(query);

	}

	
	public void refreshContents() {
		jPanel1.removeAll();
		for (JCheckBox checkBox : getCheckBoxes()) {
			jPanel1.add(checkBox);
		}
		jPanel1.revalidate();
	}
	
	/**
	 * Remove a query from the list of queries
	 * 
	 * @param query
	 * 
	 */
	private void removeQuery(String query) {

		queries.remove(query);
	}

	private ArrayList<JCheckBox> getCheckBoxes() {
		// Create the check boxes.
		ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
		for (String query : data) {
			checkBoxes.add(new JCheckBox(query, false));
		}

		// Register a listener for the check boxes.
		for (JCheckBox checkBox : checkBoxes) {
			checkBox.addItemListener(new checkBoxListener());
		}

		return checkBoxes;
	}

	private void initGUI() {
		try {
			{
			}
			{
				this.setSize(288, 237);
			}
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			this.setTitle("Query Chooser");
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(90, 70));
				{
					jPanel1 = new JPanel();
					BoxLayout jPanel1Layout = new BoxLayout(jPanel1,
							javax.swing.BoxLayout.Y_AXIS);
					jPanel1.setLayout(jPanel1Layout);
					jScrollPane1.setViewportView(jPanel1);
					jPanel1.setBorder(BorderFactory.createTitledBorder(null,
							"Available Queries", TitledBorder.LEADING,
							TitledBorder.TOP));
					{
						// custom - creates a dynamic check box list
						for (JCheckBox checkBox : getCheckBoxes()) {
							jPanel1.add(checkBox);
						}
						
						// jCheckBox1 = new JCheckBox();
						// jPanel1.add(jCheckBox1);
						// jCheckBox1.setText("jCheckBox1");
					}
				}
			}
			{
				jPanel2 = new JPanel();
				getContentPane().add(jPanel2, BorderLayout.SOUTH);
				{
					cancelButton = new JButton();
					jPanel2.add(cancelButton);
					cancelButton.setText("cancel");
					cancelButton.setPreferredSize(new java.awt.Dimension(77, 23));
					cancelButton.addActionListener(this);
				}
				{
					okButton = new JButton();
					jPanel2.add(okButton);
					okButton.setText("ok");
					okButton.setPreferredSize(new java.awt.Dimension(77, 23));
					okButton.addActionListener(this);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setQueryList(ArrayList<String> queryList){
		queries = queryList;
	}
	
	//constructor : 
	//create QueryDialog object with list of queries queryList
	
	public QueryDialog (ArrayList<String> queryList){
		queries = queryList;
		this.refreshContents();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

}
