<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2005 U (http://www.xmlspy.com) by Snima Denik (Bytes of Danci.Z) -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsf="http://www.bodz.net/xml/current/xsf">
	<xsl:import href="base.xslt"/>
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<!--传递 xsf:group 的所有子元素 （由 select 属性给出）至上一层。-->
	<xsl:template match="xsf:group">
		<xsl:for-each select="@* | * | comment() | processing-instruction() | text()">
			<xsl:apply-templates select="."/>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="xsf:include">
		<xsl:choose>
			<xsl:when test="@type = 'xml' or not(@type)">
				<xsl:for-each select="document(@href)/*">
					<xsl:apply-templates select="."/>
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>
				<xsl:message terminate="yes">
					<xsl:value-of select="concat('include-type ', @type, ' is not supported')"/>
				</xsl:message>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
</xsl:stylesheet>
