/**
 * 
 */
package org.unitedstollutions.coreace.utils;

import java.util.ArrayList;

/**
 * @author ruben
 *
 */
public class QueryFile {

	private String fileName;
	private String ifcValue;
	private String acValue;
	private ArrayList<String> ifcList;
	
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the ifcValue
	 */
	public String getIfcValue() {
		return ifcValue;
	}
	/**
	 * @param ifcValue the ifcValue to set
	 */
	public void setIfcValue(String ifcValue) {
		this.ifcValue = ifcValue;
	}
	/**
	 * @return the acValue
	 */
	public String getAcValue() {
		return acValue;
	}
	/**
	 * @param acValue the acValue to set
	 */
	public void setAcValue(String acValue) {
		this.acValue = acValue;
	}
	/**
	 * @return the ifcList
	 */
	public ArrayList<String> getIfcList() {
		return ifcList;
	}
	/**
	 * @param ifcList the ifcList to set
	 */
	public void setIfcList(ArrayList<String> ifcList) {
		this.ifcList = ifcList;
	}
	
	
	
}
