<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="C:\.radiko\miaj\mirror\site\zlw\common-xslt\1.0\test\t-control.xslt"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cx1="http://www.bodz.net/xml/zlw/common-xslt/1.0">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:include href="../control.xslt"/>
	<xsl:template match="/">
		<test-root>
			<t-for>
				<xsl:call-template name="t-for"/>
			</t-for>
			<t-repeat>
				<xsl:call-template name="t-repeat"/>
			</t-repeat>
		</test-root>
	</xsl:template>
	<xsl:template name="t-for">
		<xsl:call-template name="cx1:for">
			<xsl:with-param name="name" select="'RESERVED'"/>
			<xsl:with-param name="from" select="-10"/>
			<xsl:with-param name="to" select="12.33"/>
			<xsl:with-param name="step" select="0.5"/>
			<xsl:with-param name="body">
				<for-item>The name-expansion was not supported, yet. </for-item>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="t-repeat">
		<xsl:call-template name="cx1:repeat">
			<xsl:with-param name="name" select="'RESERVED'"/>
			<xsl:with-param name="count" select="3"/>
			<xsl:with-param name="body">
				<repeat-item>The name-expansion was not supported, yet.</repeat-item>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
</xsl:stylesheet>
