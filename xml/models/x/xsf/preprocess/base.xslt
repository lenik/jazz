<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2005 U (http://www.xmlspy.com) by Snima Denik (Bytes of Danci.Z) -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsf="http://www.bodz.net/xml/current/xsf">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<!--重载 XSLT 默认行为。

默认时 XSLT 提取默认元素的所有文本结点并连接成一个文本。
重载后默认元素（包括 element, attribute, comment, processing-instruction, text ）以原始形式呈现。-->
	<xsl:template match="*">
		<xsl:copy>
			<xsl:apply-templates select="@* | * | comment() | processing-instruction() | text()"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="@*">
		<xsl:copy/>
	</xsl:template>
	<xsl:template match="text()">
		<xsl:copy/>
	</xsl:template>
	<xsl:template match="processing-instruction()">
		<xsl:copy/>
	</xsl:template>
	<xsl:template match="comment()">
		<xsl:copy/>
	</xsl:template>
</xsl:stylesheet>
