<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:fo="http://www.w3.org/1999/XSL/Format"
xmlns:xalan="http://xml.apache.org/xalan"
xmlns:xslapi="xalan://freejava.xml.xsl.SimpleXsltApi"
xmlns:redirect="http://xml.apache.org/xalan/redirect"
extension-element-prefixes="xslapi redirect"
>
	<xsl:output method="text" version="1.0" encoding="UTF-8"/>
	
	<xsl:template match="/grammer" xml:space="preserve">
	
<xsl:copy-of select="append[@location='head']"/>
<xsl:copy-of select="append[@location='declare']"/>

<xsl:call-template name="t_union_type"/>
<xsl:call-template name="t_tokens"/>

%%

<xsl:call-template name="t_main"/>
		
%%

<xsl:copy-of select="append[@location='define']"/>
<xsl:copy-of select="append[@location='tail']"/>

	</xsl:template>
	
	<xsl:template name="t_union_type">
	</xsl:template>
	
	<xsl:template name="t_tokens">
	</xsl:template>
	
	<xsl:template name="t_main">
	</xsl:template>
	
</xsl:stylesheet>
