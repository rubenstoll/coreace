/**
 * 
 */
package org.unitedstollutions.test;

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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


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
 * @author chp01020
 *
 */
public class QueryLister extends JDialog implements ActionListener, ItemListener {

	private static QueryLister dialog;
	private static ArrayList<String> queries;
	private ArrayList<String> possibleValues;
	
	/**
	 * Set up and show the dialog. The first Component argument determines which
	 * frame the dialog depends on; it should be a component in the dialog's
	 * controlling frame. The second Component argument should be null if you
	 * want the dialog to come up with its left corner in the center of the
	 * screen; otherwise, it should be the component on top of which the dialog
	 * should appear.
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
	
	private void addQuery(Object query) {
		
		for(String currQuery : possibleValues ) {
			if (query == currQuery) {
				queries.add(currQuery);
			}
		}
		
	}

	private void removeQuery(Object query) {

		for(String currQuery : queries ) {
			if (query == currQuery) {
				queries.remove(currQuery);
			}
		}

	}
	
	private QueryLister(Frame frame, Component locationComp, String labelText,
			String title, ArrayList<String> data) {
		super(frame, title, true);

		this.possibleValues = data;
		
		// Create and initialize the buttons.
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		//
		final JButton setButton = new JButton("OK");
		setButton.setActionCommand("OK");
		setButton.addActionListener(this);
		getRootPane().setDefaultButton(setButton);        

        //Create the check boxes.
        ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
        for(String query : data) {
        	checkBoxes.add(new JCheckBox(query));
        }
        
        
		//Put the check boxes in a column in a panel
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        for(JCheckBox checkBox : checkBoxes) {
        	checkPanel.add(checkBox);
        }

		
		JScrollPane listScroller = new JScrollPane(checkPanel);
		listScroller.setPreferredSize(new Dimension(250, 80));
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
	
	// Handle clicks on the Set and Cancel buttons.
	public void actionPerformed(ActionEvent e) {
		if ("OK".equals(e.getActionCommand())) {
//			QueryLister.value = (String) (list.getSelectedValue());
			QueryLister.dialog.setVisible(false);
		}
		// this is the cancel part .. makes the dialog disappear
		QueryLister.dialog.setVisible(false);
	}
	
    /** Listens to the check boxes. */
	public void itemStateChanged(ItemEvent e) {
		
        Object source = e.getItemSelectable();

        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.SELECTED) {
        	addQuery(source);
        } else {
        	removeQuery(source);
        }

	}

	
}
