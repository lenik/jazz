<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xu="http://xml.bodz.net/schema/xslunit">
    <xsl:output method="html" encoding="utf-8"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>XSLUnit Test Report1</title>
                <style type="text/css">TABLE { border: solid 1px gray; }

.done { color: green }
.failed { color: red }</style>
            </head>
            <body>
                <xsl:apply-templates select="*"/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="xu:Group | xu:TestCases">
        <table>
            <caption>Test Group: <xsl:value-of select="@title"/>
            </caption>
            <thead>
                <tr>
                    <th>Test Case</th>
                    <th>Expected</th>
                    <th>Actual</th>
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
    <xsl:template match="xu:TestCase">
        <xsl:variable name="expected" select="xu:Expected/text()"/>
        <xsl:variable name="Actual" select="xu:Actual/text()"/>
        <tr>
            <td>
                <xsl:value-of select="@name"/>
            </td>
            <td>
                <xsl:value-of select="xu:Expected"/>
            </td>
            <xsl:element name="td">
                <xsl:choose>
                    <xsl:when test="$expected = $Actual">
                        <xsl:attribute name="class">done</xsl:attribute>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:attribute name="class">failed</xsl:attribute>
                    </xsl:otherwise>
                </xsl:choose>
                <xsl:value-of select="xu:Actual"/>
            </xsl:element>
            <td>
                <xsl:value-of select="xu:Description"/>
            </td>
        </tr>
    </xsl:template>
</xsl:stylesheet>
