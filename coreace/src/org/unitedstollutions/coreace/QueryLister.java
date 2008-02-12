/**
 * 
 */
package org.unitedstollutions.coreace;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent; 
import java.util.ArrayList;

import javax.swing.*;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

/**
 * @author yurchyshyna
 *
 */
public class QueryLister extends JDialog implements ActionListener, ItemListener {

	private static QueryLister dialog;
	private static ArrayList<String> queries;
	
	/**
	 * Set up and show the dialog. The first Component argument determines which
	 * frame the dialog depends on; it should be a component in the dialog's
	 * controlling frame. The second Component argument should be null if you
	 * want the dialog to come up with its left corner in the center of the
	 * screen; otherwise, it should be the component on top of which the dialog
	 * should appear.
	 */
	
	/**
	 * Calls constructor to build the dialog box
	 * 
	 */
	public static ArrayList<String> showDialog(Component frameComp,
			Component locationComp, String labelText, String title,
			ArrayList<String> possibleValues) {
		
		Frame frame = JOptionPane.getFrameForComponent(frameComp);

		dialog = new QueryLister(frame, locationComp, labelText, title,
				possibleValues);
		dialog.setVisible(true);
		return queries;
	}
	
	
	/**
	 * Add a query to the list queries
	 * 
	 * @param query
	 */
	private void addQuery(String query) {
		
		QueryLister.queries.add(query);
		
	}

	/**
	 * Remove a query from the list of queries
	 * @param query
	 * 
	 */
	private void removeQuery(String query) {

		QueryLister.queries.remove(query);
	}
	
	
	/**
	 * Constructor and dialog builder
	 * 
	 * @param frame
	 * @param locationComp
	 * @param labelText
	 * @param title
	 * @param data
	 * 
	 */
	private QueryLister(Frame frame, Component locationComp, String labelText,
			String title, ArrayList<String> data) {
		super(frame, title, true);

		this.queries = data;
		
		// Create and initialize the buttons.
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		//
		final JButton setButton = new JButton("OK");
		setButton.setActionCommand("OK");
		setButton.addActionListener(this);
		getRootPane().setDefaultButton(setButton);      
		
		// Create choice for thematique
		//final JChoice c = new JChoice ();
		//c.add("accessibilite");
		//c.add("acoustique");
		//c.addActionListener(this);

        //Create the check boxes.
        ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
        for(String query : data) {
        	checkBoxes.add(new JCheckBox(query,false));
        }
        
        //Register a listener for the check boxes.
        for(JCheckBox checkBox : checkBoxes) {
            checkBox.addItemListener(this);
        }

        
		//Put the check boxes in a column in a panel
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        for(JCheckBox checkBox : checkBoxes) {
        	checkPanel.add(checkBox);
        }

		
		JScrollPane listScroller = new JScrollPane(checkPanel);
		listScroller.setPreferredSize(new Dimension(250, 180));
		listScroller.setAlignmentX(LEFT_ALIGNMENT);

		// Create a container so that we can add a title around
		// the scroll pane. Can't add a title directly to the
		// scroll pane because its background would be white.
		// Lay out the label and scroll pane from top to bottom.
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel(labelText);
		label.setLabelFor(checkPanel);
		listPane.add(label);
		listPane.add(Box.createRigidArea(new Dimension(0, 5)));
		listPane.add(listScroller);
		listPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Lay out the buttons from left to right.
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(setButton);
		setButton.setText("OK");

		// Put everything together, using the content pane's BorderLayout.
		Container contentPane = getContentPane();
		contentPane.add(listPane, BorderLayout.CENTER);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

		// Initialize values.
//		setValue(initialValue);
		pack();
		setLocationRelativeTo(locationComp);

	
	}
	
	/**
	 * Listen to the buttons
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		// Handle clicks on the Set and Cancel buttons.
		if ("OK".equals(e.getActionCommand())) {
//			QueryLister.value = (String) (list.getSelectedValue());
			QueryLister.dialog.setVisible(false);
		}
		// this is the cancel part .. makes the dialog disappear
		QueryLister.dialog.setVisible(false);
	}
	
	/** 
	 * Listens to changes performed on the check boxes
	 * 
	 */
	public void itemStateChanged(ItemEvent e) {
		
        String query = ((JCheckBox)e.getItemSelectable()).getText();
        
        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	addQuery(query);
        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
        	removeQuery(query); 
        }

	}

	
}
