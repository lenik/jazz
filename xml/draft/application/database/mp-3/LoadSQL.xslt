<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

	<xsl:param name="arg1"/>

	<xsl:output encoding="gb2312" method="text"/>
	
	<xsl:template name="t_root" match="/load">
		--start--
		<xsl:call-template name="scalar1">
			<xsl:with-param name="name">
				<b>Hello!</b>
			</xsl:with-param>
		</xsl:call-template>
		
		arg1: <xsl:copy-of select="$arg1"/>
		
		<xsl:number value="1+1"/>
		eval arg1: [<xsl:value-of select="count(descent::accessor/field)"/>]
		
		<xsl:for-each select="field">
			<xsl:number format="I."/>
			<xsl:value-of select="$arg1"/>
		</xsl:for-each>
		--end--
	</xsl:template>

	<xsl:template name="scalar1">
		<xsl:param name="name"/>
		<xsl:copy-of select="$name"/>
	</xsl:template>
</xsl:stylesheet>
