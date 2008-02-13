/**
 * 
 */
package org.unitedstollutions.examples;
/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * 
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java 
 * language and environment is gratefully acknowledged.
 * 
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

//demo xml file
/*
<?xml version="1.0"?>
<people>
<person>
  <name>Ian Darwin</name>
  <email>http://www.darwinsys.com/</email>
  <country>Canada</country>
</person>
<person>
  <name>Another Darwin</name>
  <email type="intranet">afd@node1</email>
  <country>Canada</country>
</person>
</people>

*/

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/** Make up and write an XML document, using JDOM
 * @author Ian Darwin, http://www.darwinsys.com/
 * @version $Id: DocWriteJDOM.java,v 1.4 2004/02/09 03:34:10 ian Exp $
 */
public class DocWriteJDOM {

  public static void main(String[] av) throws Exception {
    DocWriteJDOM dw = new DocWriteJDOM();
    Document doc = dw.makeDoc();
    // Create an output formatter, and have it write the doc.
    new XMLOutputter().output(doc, System.out);
  }

  /** Generate the XML document */
  protected Document makeDoc() throws Exception {
      Document doc = new Document(new Element("Poem"));
      doc.getRootElement().
        addContent(new Element("Stanza").
          addContent(new Element("Line").
              setText("Once, upon a midnight dreary")).
          addContent(new Element("Line").
              setText("While I pondered, weak and weary")));

      return doc;
  }
}
