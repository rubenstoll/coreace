/**
 * 
 */
package org.unitedstollutions.coreace;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


/**
 * TextInputDemo.java uses these additional files:
 *   SpringUtilities.java
 *   ...
 */

public class TextInputDialog extends JPanel
                                          implements ActionListener,
                                                     FocusListener {
    JTextField ifcField;
    JSpinner stateSpinner;
    boolean addressSet = false;
    boolean ifcSet = false;
    Font regularFont, italicFont;
    JLabel ifcDisplay;
    final static int GAP = 10;
    String defaultIfc = "http://rainbow.essi.fr/~anastasiya/data/ifc02.ifcxml";

    
    public TextInputDialog() {
       
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize() {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                                     pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf,
                                         BoxLayout.PAGE_AXIS));
        leftHalf.add(createEntryFields());
        leftHalf.add(createButtons());

        add(leftHalf);
        add(createAddressDisplay());

    }

    protected JComponent createButtons() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));

        JButton button = new JButton("Set IFC URL");
        button.addActionListener(this);
        panel.add(button);

        button = new JButton("Clear IFC URL");
        button.addActionListener(this);
        button.setActionCommand("clear");
        panel.add(button);

        //Match the SpringLayout's gap, subtracting 5 to make
        //up for the default gap FlowLayout provides.
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0,
                                                GAP-5, GAP-5));
        return panel;
    }

    /**
     * Called when the user clicks the button or presses
     * Enter in a text field.
     */
    public void actionPerformed(ActionEvent e) {
        if ("clear".equals(e.getActionCommand())) {
            ifcSet = false;
            ifcField.setText("http://");
        } else {
            ifcSet = true;
        }
        updateDisplays();
    }

    protected void updateDisplays() {
    	ifcDisplay.setText(getIfc());
        if (ifcSet) {
        	ifcDisplay.setFont(regularFont);
        } else {
        	ifcDisplay.setFont(italicFont);
        }
    }

    protected JComponent createAddressDisplay() {
        JPanel panel = new JPanel(new BorderLayout());
        ifcDisplay = new JLabel();
        ifcDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = ifcDisplay.getFont().deriveFont(Font.PLAIN,
                                                            16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        //Lay out the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(
                                GAP/2, //top
                                0,     //left
                                GAP/2, //bottom
                                0));   //right
        panel.add(new JSeparator(JSeparator.VERTICAL),
                  BorderLayout.LINE_START);
        panel.add(ifcDisplay,
                  BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }
    
    
    public String getIfc() {
        if (!ifcSet) return "No URI set.";

        String ifc = ifcField.getText();
/*        String state = (String)stateSpinner.getValue();
        String zip = zipField.getText();*/
        String empty = "";

        if ((ifc == null) || empty.equals(ifc)) {
        	System.out.println("no ifc address specified; taking the default one");
        	ifc = defaultIfc;
        } 
/*        TO DO checking of the URL correct format 
        else if
        	(!(ifc.startsWith("http://"))) {
        		System.out.println ("wrong URL; taking the default one");
        		ifc = defaultIfc;   		
        	
        }*/
        
/*      
        StringBuffer sb = new StringBuffer();
//        sb.append("<html><p align=center>");
        sb.append(ifc);
        sb.append("<br>");
        sb.append(city);
        sb.append(" ");
        sb.append(state); //should format
        sb.append(" ");
        sb.append(zip);
        sb.append("</p></html>");*/

        return ifc;
        
    }

    //A convenience method for creating a MaskFormatter.
    protected MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

    /**
     * Called when one of the fields gets the focus so that
     * we can select the focused field.
     */
    public void focusGained(FocusEvent e) {
        Component c = e.getComponent();
        if (c instanceof JFormattedTextField) {
            selectItLater(c);
        } else if (c instanceof JTextField) {
            ((JTextField)c).selectAll();
        }
    }

    //Workaround for formatted text field focus side effects.
    protected void selectItLater(Component c) {
        if (c instanceof JFormattedTextField) {
            final JFormattedTextField ftf = (JFormattedTextField)c;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ftf.selectAll();
                }
            });
        }
    }

    //Needed for FocusListener interface.
    public void focusLost(FocusEvent e) { } //ignore

    protected JComponent createEntryFields() {
        JPanel panel = new JPanel(new SpringLayout());

        String[] labelStrings = {
            "URL of the IFC: ",
            //more if needed
        };

        JLabel[] labels = new JLabel[labelStrings.length];
        JComponent[] fields = new JComponent[labelStrings.length];
        int fieldNum = 0;

        //Create the text field and set it up.
        
        ifcField  = new JTextField();
        ifcField.setColumns(20);
        fields[fieldNum++] = ifcField;
        
/*      
        zipField = new JFormattedTextField(
                            createFormatter("#####"));
        fields[fieldNum++] = zipField;*/

        //Associate label/field pairs, add everything,
        //and lay it out.
        for (int i = 0; i < labelStrings.length; i++) {
            labels[i] = new JLabel(labelStrings[i],
                                   JLabel.TRAILING);
            labels[i].setLabelFor(fields[i]);
            panel.add(labels[i]);
            panel.add(fields[i]);

            //Add listeners to each field.
            JTextField tf = null;
            if (fields[i] instanceof JSpinner) {
                tf = getTextField((JSpinner)fields[i]);
            } else {
                tf = (JTextField)fields[i];
            }
            tf.addActionListener(this);
            tf.addFocusListener(this);
        }
        SpringUtilities.makeCompactGrid(panel,
                                        labelStrings.length, 2,
                                        GAP, GAP, //init x,y
                                        GAP, GAP/2);//xpad, ypad
        return panel;
    }


    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } else {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

}

