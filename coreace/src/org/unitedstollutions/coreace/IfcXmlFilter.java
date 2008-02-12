/**
 * 
 */
package org.unitedstollutions.coreace;

/**
 * @author yurchyshyna
 *
 */
import javax.swing.*;
import java.io.*;

public class IfcXmlFilter extends javax.swing.filechooser.FileFilter {

  public boolean accept (File f) {
    return f.getName ().toLowerCase ().endsWith (".ifcxml")
          || f.isDirectory ();
  }
  
  public String getDescription () {
    return "IfcXml files (*.ifcxml)";
  }
} // class IfcXmlFilter