<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:g="http://www.bodz.net/xml/current/gdml" xmlns:xsf="http://www.bodz.net/xml/current/xsf" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2004/07/xpath-functions" xmlns:xdt="http://www.w3.org/2004/07/xpath-datatypes">
    <xsl:output version="1.0" encoding="UTF-8" indent="no" omit-xml-declaration="no" media-type="text/html" />
    <xsl:template match="/">
        <html>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <head>
                <title />
            </head>
            <body>
                <h2>Language Definition of <xsl:for-each select="g:language">
                        <xsl:for-each select="@name">
                            <xsl:value-of select="." />
                        </xsl:for-each>
                    </xsl:for-each>
                </h2>
                <xsl:for-each select="g:language">
                    <hr color="black" size="1" />
                    <xsl:for-each select="@info">
                        <p align="right">
                            <span style="font-style:italic; ">
                                <xsl:value-of select="." />
                            </span>
                        </p>
                    </xsl:for-each>
                </xsl:for-each>
                <xsl:for-each select="g:language">
                    <table border="1">
                        <thead>
                            <tr>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Symbol</span>
                                </td>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Extends</span>
                                </td>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Association</span>
                                </td>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Priority</span>
                                </td>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Type</span>
                                </td>
                                <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                    <span style="font-size:larger; font-weight:bold; ">Id</span>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <xsl:for-each select="g:symbol">
                                <tr>
                                    <td style="border-bottom-style:solid; border-bottom-width:medium; ">
                                        <xsl:for-each select="@name">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                    <td style="border-bottom-style:solid; ">
                                        <xsl:for-each select="@extends">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                    <td style="border-bottom-style:solid; ">
                                        <xsl:for-each select="@assoc">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                    <td style="border-bottom-style:solid; ">
                                        <xsl:for-each select="@priority">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                    <td style="border-bottom-style:solid; ">
                                        <xsl:for-each select="@type">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                    <td style="border-bottom-style:solid; ">
                                        <xsl:for-each select="@id">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="border-bottom-style:solid; border-bottom-width:medium; " />
                                    <td style="border-bottom-style:solid; " colspan="5">
                                        <xsl:for-each select="@info">
                                            <xsl:value-of select="." />
                                        </xsl:for-each>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </tbody>
                    </table>
                </xsl:for-each>
                <hr color="black" size="1" />
                <xsl:for-each select="g:language">
                    <xsl:for-each select="g:symbol">
                        <h3>Symbol <xsl:for-each select="@name">
                                <xsl:value-of select="." />
                            </xsl:for-each>
                        </h3>
                        <ol style="margin-bottom:0; margin-top:0; " start="1" type="i">
                            <xsl:for-each select="g:syntax">
                                <li>
                                    <xsl:for-each select="@name">
                                        <xsl:value-of select="." />
                                    </xsl:for-each>
                                </li>
                            </xsl:for-each>
                        </ol>
                        <hr color="black" size="1" />
                    </xsl:for-each>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="xsf:embed">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="xsf:group">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="xsf:import">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="xsf:include">
        <xsl:apply-templates />
    </xsl:template>
</xsl:stylesheet>
