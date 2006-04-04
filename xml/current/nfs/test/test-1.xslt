<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2005 U (http://www.xmlspy.com) by Snima Denik (Bytes of Danci.Z) -->
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2004/07/xpath-functions" xmlns:xdt="http://www.w3.org/2004/07/xpath-datatypes">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
    <xsl:template match="/">
        <xsl:variable name="test">
            <xsl:value-of select="'hello'"/>
            <xsl:value-of select="'world'"/>
        </xsl:variable>
        <result>
            <variable>
                <xsl:value-of select="$test"/>
            </variable>
            <not-variable>
                <xsl:value-of select="'hello'"/>
                <xsl:value-of select="'world'"/>
            </not-variable>
        </result>
    </xsl:template>
</xsl:stylesheet>
