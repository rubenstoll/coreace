/**
 * 
 */
package org.unitedstollutions.coreace;

import java.util.ArrayList;


/**
 * @author yurchyshyna
 * 
 */
public class AnnotationRegle {

	private String origineRegle = "";
	private String actualiteRegle = "";
	private String thematiqueRegle = "";
	private String exigenceRegle = "";
	private String sujetRegle = "";
	private String domaineApplication = "";
	private String sousDomaineApplication = "";
	private String destinationRegle = "";
	private String textRegle = "";
	private String contenuRegle = "";
	private String extraitTypeTexteReglementaire = "";
	private String extraitTypeLEG = "";
	private String extraitEmanant = "";
	private String extraitSigle = "";
	private String extraitNumero = "";
	private String extraitDate = "";
	private String extraitTitre = "";
	private String extraitRelatifA = "";
	private String extraitInfoExtractionUIS = "";
	private String extraitInfoExtractionArticle = "";
	private String extraitInfoExtractionParagraphe = "";
	private String extraitInfoExtractionNumeroOrdreDansParagraphe = "";
	private String aboutIFCData = "";
	
	
	// default constructor
	public AnnotationRegle(){
		
	}
	
	// constructor : create object if given AnnoFile name
	public AnnotationRegle(String nameAnnoFile){
		

	}

	/*
	 * SETTERS
	 */

	public void setOrigineRegle(String origineRegle) {
		this.origineRegle = origineRegle;
	}

	public void setActualiteRegle(String actualiteREgle) {
		this.actualiteRegle = actualiteREgle;
	}

	public void setThematiqueRegle(String thematiqueRegle) {
		this.thematiqueRegle = thematiqueRegle;
	}

	public void setExigenceRegle(String exigenceRegle) {
		this.exigenceRegle = exigenceRegle;
	}

	public void setSujetRegle(String sujetRegle) {
		this.sujetRegle = sujetRegle;
	}

	public void setDomaineApplication(String domaineApplication) {
		this.domaineApplication = domaineApplication;
	}
	
	public void setSousDomaineApplication(String sousDomaineApplication) {
		this.sousDomaineApplication = sousDomaineApplication;
	}
	
	public void setDestinationRegle(String destinationRegle) {
		this.destinationRegle = destinationRegle;
	}
	
	public void setTextRegle(String textRegle) {
		this.textRegle = textRegle;
	}
	
	public void setContenuRegle(String content) {
		this.contenuRegle = content;
	}
	
	public void setExtraitTypeTexteReglementaire(String extraitTypeTexteReglementaire) {
		this.extraitTypeTexteReglementaire = extraitTypeTexteReglementaire;
	}
	
	public void setExtraitTypeLEG(String extraitTypeLEG) {
		this.extraitTypeLEG = extraitTypeLEG;
	}
	
	public void setExtraitEmanant(String extraitEmanant) {
		this.extraitEmanant = extraitEmanant;
	}
	
	public void setExtraitSigle(String extraitSigle) {
		this.extraitSigle = extraitSigle;
	}
	
	public void setExtraitNumero(String extraitNumero) {
		this.extraitNumero = extraitNumero;
	}
	
	public void setExtraitDate(String extraitDate) {
		this.extraitDate = extraitDate;
	}
	
	public void setExtraitTitre(String extraitTitre) {
		this.extraitTitre = extraitTitre;
	}
	
	public void setExtraitRelatifA(String extraitRelatifA) {
		this.extraitRelatifA = extraitRelatifA;
	}
	
	public void setExtraitInfoExtractionUIS(String extraitInfoExtractionUIS) {
		this.extraitInfoExtractionUIS = extraitInfoExtractionUIS;
	}
	
	public void setExtraitInfoExtractionArticle(String extraitInfoExtractionArticle) {
		this.extraitInfoExtractionArticle = extraitInfoExtractionArticle;
	}
	
	public void setExtraitInfoExtractionParagraphe(String extraitInfoExtractionParagraphe) {
		this.extraitInfoExtractionParagraphe = extraitInfoExtractionParagraphe;
	}
	
	public void setExtraitInfoExtractionNumeroOrdreDansParagraphe(String extraitInfoExtractionNumeroOrdreDansParagraphe) {
		this.extraitInfoExtractionNumeroOrdreDansParagraphe = extraitInfoExtractionNumeroOrdreDansParagraphe;
	}
	
	public void setAboutIFCData(String aboutIFCData) {
		this.aboutIFCData = aboutIFCData;
	}

	
	/*
	 * GETTERS
	 */
	public String getOrigineRegle() {
		return origineRegle;
	}

	public String getActualiteRegle() {
		return actualiteRegle;
	}

	public String getThematiqueRegle() {
		return thematiqueRegle;
	}

	
	public String getExigenceRegle() {
		return exigenceRegle;
	}
	
	
	public String getSujetRegle() {
		return sujetRegle;
	}
	
	public String getDomaineApplication() {
		return domaineApplication;
	}
	
	public String getSousDomaineApplication() {
		return sousDomaineApplication;
	}
	
	public String getDestinationRegle() {
		return destinationRegle;
	}
	
	public String getTextRegle() {
		return textRegle;
	}
	
	public String getContenuRegle() {
		return contenuRegle;
	}
	
	public String getExtraitTypeTexteReglementaire() {
		return extraitTypeTexteReglementaire;
	}
	
	public String getExtraitTypeLEG() {
		return extraitTypeLEG;
	}
	
	public String getExtraitEmanant() {
		return extraitEmanant;
	}
	
	public String getExtraitSigle() {
		return extraitSigle;
	}
	
	public String getExtraitNumero() {
		return extraitNumero;
	}
	
	public String getExtraitDate() {
		return extraitDate;
	}

	
	public String getExtraitTitre() {
		return extraitTitre;
	}	
	
	public String getExtraitRelatifA() {
		return extraitRelatifA;
	}	
	
	public String getExtraitInfoExtractionUIS() {
		return extraitInfoExtractionUIS;
	}	
	
	public String getExtraitInfoExtractionArticle() {
		return extraitInfoExtractionArticle;
	}
	
	public String getExtraitInfoExtractionParagraphe() {
		return extraitInfoExtractionParagraphe;
	}
	
	public String getExtraitInfoExtractionNumeroOrdreDansParagraphe() {
		return extraitInfoExtractionNumeroOrdreDansParagraphe;
	}
	
	public String getAboutIFCData() {
		return aboutIFCData;
	}
	
	// give the values of all necessary tags
	public ArrayList<String> getAllTags() {
		ArrayList<String> listOfAllTags = null;
		listOfAllTags.add(getActualiteRegle());
		listOfAllTags.add(getThematiqueRegle());
		listOfAllTags.add(getDomaineApplication());
		listOfAllTags.add(getDestinationRegle());
		listOfAllTags.add(getContenuRegle());
		
		return listOfAllTags;
	}

	public boolean hasValue (String tag, String value){
		boolean res = false;
		if (tag == "thematiqueRegle"){
			if (this.thematiqueRegle == value){
				res = true;
			}
			// TODO for main tags
		}
		return res;
	}
	

}
