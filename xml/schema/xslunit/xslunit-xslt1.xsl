<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xu="http://xml.bodz.net/xslunit">
    <xsl:output method="xml" encoding="utf-8" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>XSLUnit Test Report</title>
                <style type="text/css">.done { color: green }
.failed { color: red }</style>
            </head>
            <body>
                <xsl:apply-templates select="*"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="/xu:TestCases | //xu:Group">
        <table>
            <caption>Test Group</caption>
            <thead>
                <tr>
                    <th>Test Case</th>
                    <th>Expected</th>
                    <th>Result</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates select="xu:TestCase"/>
            </tbody>
        </table>
        <xsl:if test="xu:Group">
            <hr/>
            <div style="indent: 10px; ">
                <xsl:apply-templates select="xu:Group"/>
            </div>
        </xsl:if>
    </xsl:template>
    <xsl:template match="//xu:TestCase">
        <xsl:variable name="expected" select="xu:Expected/text()"/>
        <xsl:variable name="result" select="xu:Result/text()"/>
        <tr>
            <td>
                <xsl:value-of select="@name"/>
            </td>
            <td>
                <xsl:value-of select="xu:Expected"/>
            </td>
            <xsl:element name="td">
                <xsl:choose>
                    <xsl:when test="$expected = $result">
                        <xsl:attribute name="class">failed</xsl:attribute>FAIL</xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="class">done</xsl:attribute>OK</xsl:otherwise>
                </xsl:choose>
                <xsl:value-of select="xu:Result"/>
            </xsl:element>
            <td>
                <xsl:value-of select="xu:Description"/>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
