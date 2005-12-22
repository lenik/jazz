<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:cx="http://www.bodz.net/xml/zlw/common-xslt/1.0">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template name="cx:for">
		<xsl:param name="name"/>
		<xsl:param name="from"/>
		<xsl:param name="to"/>
		<xsl:param name="step" select="1"/>
		<xsl:param name="body"/>
		<xsl:variable name="cont">
			<xsl:choose>
				<xsl:when test="$step &gt; 0">
					<xsl:value-of select="$from &lt;= $to"/>
				</xsl:when>
				<xsl:when test="$step &lt; 0">
					<xsl:value-of select="$from &gt;= $to"/>
				</xsl:when>
				<xsl:otherwise>
					<!--ASSERT($step != 0)-->
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:if test="$cont">
			<xsl:copy-of select="$body"/>
			<xsl:if test="$from &lt;= $to">
				<xsl:call-template name="cx:for">
					<xsl:with-param name="name" select="$name"/>
					<xsl:with-param name="from" select="$from + $step"/>
					<xsl:with-param name="to" select="$to"/>
					<xsl:with-param name="step" select="$step"/>
					<xsl:with-param name="body" select="$body"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:if>
	</xsl:template>
	<xsl:template name="cx:repeat">
		<xsl:param name="name"/>
		<xsl:param name="count"/>
		<xsl:param name="body"/>
		<xsl:if test="$count > 0">
			<xsl:copy-of select="$body"/>
			<xsl:call-template name="cx:repeat">
				<xsl:with-param name="name" select="$name"/>
				<xsl:with-param name="count" select="$count - 1"/>
				<xsl:with-param name="body" select="$body"/>
			</xsl:call-template>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
