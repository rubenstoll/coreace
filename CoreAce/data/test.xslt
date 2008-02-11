<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" 
    xmlns:ifc="http://rainbow.essi.fr/~anastasiya/data/test_ifc.xml/"
    xmlns:xlink="http://www.w3.org/1999/xlink"
    xmlns:ex="urn:iso.org:standard:10303:part(28):version(2):xmlschema:common" 
    >

    <xsl:variable name="resourceURL">
        <xsl:text>http://rainbow.essi.fr/~anastasiya/data/test_ifc.xml</xsl:text>
    </xsl:variable>
    
    <xsl:variable name="nsPrefix">ifc</xsl:variable>
          
       <!-- define the doc as rdf -->
    <xsl:template match="/">
        <xsl:comment>start of the rdf file</xsl:comment>
        <rdf:RDF>      
            <rdf:Description
                rdf:about="{$resourceURL}">
               <xsl:apply-templates/>
            </rdf:Description>
        </rdf:RDF>
            
    </xsl:template>
    
    <!-- preparing properties to be minuscule -->
    <xsl:template match="*[not(starts-with(name(.),'Ifc'))]" name="toMinuscule">
            <xsl:element name="{$nsPrefix}:{translate(name(),'ABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞ', 'abcdefghijklmnopqrstuvwxyzàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþ')}">
                <xsl:apply-templates/>
            </xsl:element>        
    </xsl:template>
    
    
    <!-- Copy remaining elements, putting them in a namespace. -->
    <xsl:template match="*[not(starts-with(name(.),'ex'))]">
        <xsl:apply-templates select="toMinuscule"/>
        <xsl:element name="{$nsPrefix}:{name()}">
            <xsl:apply-templates select="@*|node()"/>
        </xsl:element>

    </xsl:template>
     
    
</xsl:stylesheet>
