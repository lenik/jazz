<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2005 U (http://www.xmlspy.com) by Snima Denik (Bytes of Danci.Z) -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<TestRoot>
			<xsl-properties>
				<xsl:call-template name="xsl-properties"/>
			</xsl-properties>
		</TestRoot>
	</xsl:template>
	<xsl:template name="xsl-properties">
		<xsl-version>
			<xsl:value-of select="system-property('xsl:version')"/>
		</xsl-version>
		<xsl-vendor>
			<xsl:value-of select="system-property('xsl:vendor')"/>
		</xsl-vendor>
		<xsl-vendor-url>
			<xsl:value-of select="system-property('xsl:vendor-url')"/>
		</xsl-vendor-url>
	</xsl:template>
</xsl:stylesheet>
