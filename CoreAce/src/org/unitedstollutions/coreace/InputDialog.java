/**
 * 
 */
package org.unitedstollutions.coreace;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;
/**
 * @author yurchyshyna
 *
 */
public class InputDialog {
	private String defaultName = "http://rainbow.essi.fr/~anastasiya/data/test_ifc.ifcxml";
	private String name = "";
	
	public InputDialog(){
		
		try {
			name = JOptionPane.showInputDialog(null, "Enter URL of your construction project");
			//CANCEL button
			if (name == null) { 
				System.out.println("if no URL is set, the default value is taken : " + defaultName);
				name = defaultName;				
			}
			//System.out.println(name);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getUrl(){
		return name;
	}
	
}

