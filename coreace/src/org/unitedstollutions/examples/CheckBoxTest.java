/**
 * 
 */
package org.unitedstollutions.examples;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


/**
 * @author chp01020
 *
 */

/*
 * CheckBoxDemo.java requires 16 image files in the images/geek
 * directory: 
 * geek-----.gif, geek-c---.gif, geek--g--.gif, geek---h-.gif, geek----t.gif,
 * geek-cg--.gif, ..., geek-cght.gif.
 */
public class CheckBoxTest extends JPanel
                          implements ItemListener {
    JCheckBox chinButton;
    JCheckBox glassesButton;
    JCheckBox hairButton;
    JCheckBox teethButton;
    
    private ArrayList<String> data;
    private ArrayList<JCheckBox> checkBoxes;

    /*
     * Four accessory choices provide for 16 different
     * combinations. The image for each combination is
     * contained in a separate image file whose name indicates
     * the accessories. The filenames are "geek-XXXX.gif"
     * where XXXX can be one of the following 16 choices.
     * The "choices" StringBuffer contains the string that
     * indicates the current selection and is used to generate
     * the file name of the image to display.

       ----             //zero accessories

       c---             //one accessory
       -g--
       --h-
       ---t

       cg--             //two accessories
       c-h-
       c--t
       -gh-
       -g-t
       --ht

       -ght             //three accessories
       c-ht
       cg-t
       cgh-

       cght             //all accessories
     */

    StringBuffer choices;
    JLabel pictureLabel;

    public CheckBoxTest() {
        super(new BorderLayout());
        	
		data = new ArrayList<String>();

		data.add("query1.txt");
		data.add("query2.txt");
		data.add("query3.txt");
		data.add("query4.txt");
		data.add("query5.txt");
		data.add("query6.txt");
		data.add("query7.txt");

        
        //Create the check boxes.
        checkBoxes = new ArrayList<JCheckBox>();
        for(String query : data) {
        	checkBoxes.add(new JCheckBox(query,true));
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
        
        add(checkPanel, BorderLayout.LINE_START);
//        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the check boxes. */
    public void itemStateChanged(ItemEvent e) {
 
    	int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        JCheckBox chkBx = (JCheckBox)e.getItemSelectable();
        
 //       System.out.println("Clicked was " + source.toString());
        
//        if(checkBoxes.contains(source)) {
//        	System.out.println("The data array contains the source");
//        	System.out.println(checkBoxes.get(checkBoxes.indexOf(source)).toString());
//        	System.out.println("the g-thang has " + chkBx.getText() + " as text");
//        	
//        	for(String queryFileName : data) {
//        		if (source == queryFileName) {
//        			System.out.println("found match at position " + data.indexOf(queryFileName));
//        		} else {
//        			System.out.println("no match found!");
//        		}
//        	}
//        	
//        } else {
//        	System.out.println("does not contain");
//        }
        
        
        
        if (source == chinButton) {
            index = 0;
            c = 'c';
        } else if (source == glassesButton) {
            index = 1;
            c = 'g';
        } else if (source == hairButton) {
            index = 2;
            c = 'h';
        } else if (source == teethButton) {
            index = 3;
            c = 't';
        }

        //Now that we know which button was pushed, find out
        //whether it was selected or deselected.
        if (e.getStateChange() == ItemEvent.DESELECTED) {
//            c = '-';
        	System.out.println("query " + chkBx.getText() + " was deselected ... remove");
        } else if(e.getStateChange() == ItemEvent.SELECTED) {
        	System.out.println("query " + chkBx.getText() + " was selected ... add");
        }

        //Apply the change to the string.
//        choices.setCharAt(index, c);

//        updatePicture();
    }

    protected void updatePicture() {
        //Get the icon corresponding to the image.
        ImageIcon icon = createImageIcon(
                                    "images/geek/geek-"
                                    + choices.toString()
                                    + ".gif");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(choices.toString());
        if (icon == null) {
            pictureLabel.setText("Missing Image");
        } else {
            pictureLabel.setText(null);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CheckBoxTest.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CheckBoxTest();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
