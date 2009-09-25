<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xe="http://www.bodz.net/xml/current/nfs/xslt-evaluate">
    <xsl:output/>
    <xsl:template match="xe:xslt-evaluate">
        <html>
            <head>
                <title>XSLT Evaluate Result - <xsl:value-of select="@name"/>
                </title>
            </head>
            <body>
                <h1>XSLT Evaluate Result - <xsl:value-of select="@name"/>
                </h1>
                <hr/>
                <xsl:apply-templates/>
                <hr/>
                <cite>XSLT Evaluate</cite>
                <cite>(C)Copyright www.bodz.net, 2004</cite>
            </body>
        </html>
    </xsl:template>
    <xsl:template name="xe:evaluate">
        <xsl:call-template name="any-template"/>
    </xsl:template>
    <xsl:template name="any-template">
        <xsl:for-each select="@*">
            <xsl:apply-templates/>
        </xsl:for-each>
        <xsl:for-each select="*">
            <xsl:apply-templates/>
            <xsl:call-template name="any-template"/>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
