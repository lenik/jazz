<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/xhtml" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2004/07/xpath-functions" xmlns:xdt="http://www.w3.org/2004/07/xpath-datatypes" xmlns:nl="http://www.bodz.net/xml/current/nfs/library" xmlns:libview="http://www.bodz.net/xml/current/nfs/library-view">
    <xsl:import href="commons-string.xslt"/>
    <xsl:output method="xml" encoding="utf-8"/>
    <xsl:template match="/nl:library">
        <html>
            <head>
                <title>NFS :: Library - <xsl:value-of select="@name"/>
                </title>
                <meta http-equiv="Content-Type" content="text/xhtml; charset=utf-8"/>
                <xsl:element name="meta">
                    <xsl:attribute name="http-equiv">keywords</xsl:attribute>
                    <xsl:attribute name="content">NFS Library<xsl:for-each select="@*"><xsl:value-of select="concat(., ' ')"/></xsl:for-each></xsl:attribute>
                </xsl:element>
                <meta http-equiv="author" content="{ @author }"/>
                <meta http-equiv="company" content="{ @company }"/>
                <meta http-equiv="email" content="{ @email }"/>
                <meta http-equiv="copyright" content="{ @copyright }"/>
                <meta http-equiv="generator" content="http://www.bodz.net/xml/current/nfs/library-view.xsl"/>
                <style type="text/css">body {
    background-color: #Fff6FA;
    font-family: Arial, Helvetica, sans-serif;
    font-size: 12px;
    line-height: 24px;
    color: #461870;
}
a:link { color: #7D2BC8; }
a:visited { color: #7D2BC8; }
a:hover { color: #B17CE2; }
a:active { color: #B17CE2; }
h1 {
    background-color: #634480;
    color: #FFFFFF;
    padding-top:5px;
    padding-bottom: 5px;
}
h2 {
    background-color: #634480;
    color: #FFFFFF;
    padding-top:3px;
    padding-bottom: 3px;
}
h3 {
    background-color: #634480;
    color: #FFFFFF;
}
h4 {
    color: #634480;
}
h5 {
    color: #634480;
}</style>
                <style type="text/css">hr {
    height:1px;
    border-color: #663399;
    border-style: solid;
    border-width: 1;
}
pre {
    font-size:14px;
    background-color: #F2E9F8;
}</style>
                <style type="text/css">.Header {
    background-color: #634480;
    color: #ffffff;
    font-weight: bold;
}
.Copyright {
    color: #634480;
    background-color: #f6eCf7;
    font-family: Courier New, Courier, mono;
}
.Info { font-size: 14px }
.VersionInfo { }
.TabCell { width: 30px}
.NavBar {
    background-color: #E6DCE7;
    height: 6px;
}
.NavLink {
    color:  #7D2BC8;
    font-family: Arial, Helvetica, sans-serif;
    border-width: 1px;
    border-style: solid;
    border-color: #AF96CF;
    text-decoration: none;
    cursor: hand;
    background-color: #E6DCE7;
}</style>
                <style type="text/css">.FProto { font-size: 14px }
.FSyn { margin-left: 60px }
.FParam { margin-left: 40px }
.FPName { font-weight: bold }
.FPInfo { }
.FTest { margin-left: 60px }
.SAcite { padding-right: 10px }</style>
            </head>
            <body>
                <a name="library" id="library"/>
                <h1>NFS :: Library - <xsl:value-of select="@name"/>
                </h1>
                <p class="Info">
                    <xsl:value-of select="@info"/>
                </p>
                <xsl:call-template name="version-info"/>
                <a name="library-index" id="library-index"/>
                <h2>Library Index</h2>
                <xsl:call-template name="package-index"/>
                <hr/>
                <xsl:for-each select="nl:package">
                    <xsl:call-template name="package"/>
                </xsl:for-each>
                <hr/>
                <xsl:if test="libview:footer">
                    <xsl:copy-of select="libview:footer"/>
                </xsl:if>
                <table class="Header" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>NFS :: Library - <xsl:value-of select="@name"/>
                        </td>
                        <td align="right">
                            <a href="#library" class="NavLink">Top</a>
                            <a href="#library" class="NavLink">Library</a>
                            <a href="#library-index" class="NavLink">Index</a>
                        </td>
                    </tr>
                </table>
                <table class="Copyright" width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>(C) Copyright <xsl:value-of select="@copyright"/>
                        </td>
                        <td align="right">
                            <em>Powered by NFS::Library</em>
                        </td>
                    </tr>
                </table>
            </body>
        </html>
    </xsl:template>
    <xsl:template name="package-index" match="nl:package">
        <xsl:param name="parent"/>
        <xsl:variable name="this">
            <xsl:if test="string($parent)">
                <xsl:value-of select="$parent"/>.</xsl:if>
            <xsl:if test="not(../nl:library)">
                <xsl:value-of select="@name"/>
            </xsl:if>
        </xsl:variable>
        <ul>
            <xsl:for-each select="nl:package">
                <li>
                    <xsl:call-template name="anchor">
                        <xsl:with-param name="link">
                            <xsl:if test="string($this)">
                                <xsl:value-of select="$this"/>.</xsl:if>
                            <xsl:value-of select="@name"/>
                        </xsl:with-param>
                        <xsl:with-param name="prefix" select="$this"/>
                        <xsl:with-param name="name" select="@name"/>
                        <xsl:with-param name="suffix">index</xsl:with-param>
                    </xsl:call-template>
                    <xsl:call-template name="auto-info"/>
                    <xsl:if test="nl:package">
                        <xsl:call-template name="package-index">
                            <xsl:with-param name="parent" select="$this"/>
                        </xsl:call-template>
                    </xsl:if>
                </li>
            </xsl:for-each>
        </ul>
    </xsl:template>
    <xsl:template name="package" match="nl:package">
        <xsl:param name="parent"/>
        <xsl:variable name="this">
            <xsl:if test="string($parent)">
                <xsl:value-of select="$parent"/>.</xsl:if>
            <xsl:value-of select="@name"/>
        </xsl:variable>
        <xsl:call-template name="anchor">
            <xsl:with-param name="prefix" select="$this"/>
            <xsl:with-param name="name">index</xsl:with-param>
        </xsl:call-template>
        <h2>Package <xsl:value-of select="$this"/>
        </h2>
        <p class="Info">
            <xsl:value-of select="@info"/>
        </p>
        <xsl:call-template name="version-info"/>
        <xsl:call-template name="package-index"/>
        <hr/>
        <xsl:if test="nl:function">
            <h4>Functions</h4>
            <ul>
                <xsl:for-each select="nl:function">
                    <li>
                        <xsl:call-template name="anchor">
                            <xsl:with-param name="link" select="@name"/>
                            <xsl:with-param name="prefix" select="$this"/>
                            <xsl:with-param name="name">function-<xsl:value-of select="@name"/>
                            </xsl:with-param>
                        </xsl:call-template>
                        <xsl:call-template name="auto-info"/>
                    </li>
                </xsl:for-each>
            </ul>
        </xsl:if>
        <xsl:if test="nl:class">
            <h4>Classes</h4>
            <ul>
                <xsl:for-each select="nl:class">
                    <li>
                        <xsl:call-template name="anchor">
                            <xsl:with-param name="link" select="@name"/>
                            <xsl:with-param name="prefix" select="$this"/>
                            <xsl:with-param name="name">class-<xsl:value-of select="@name"/>
                            </xsl:with-param>
                        </xsl:call-template>
                        <xsl:call-template name="auto-info"/>
                    </li>
                </xsl:for-each>
            </ul>
        </xsl:if>
        <xsl:if test="nl:instance">
            <h4>Instances</h4>
            <ul>
                <xsl:for-each select="nl:instance">
                    <li>
                        <xsl:call-template name="anchor">
                            <xsl:with-param name="link" select="@name"/>
                            <xsl:with-param name="prefix" select="$this"/>
                            <xsl:with-param name="name">instance-<xsl:value-of select="@name"/>
                            </xsl:with-param>
                        </xsl:call-template>
                        <xsl:call-template name="auto-info"/>
                    </li>
                </xsl:for-each>
            </ul>
        </xsl:if>
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
        <xsl:apply-templates select="nl:function">
            <xsl:with-param name="parent" select="$this"/>
        </xsl:apply-templates>
        <xsl:apply-templates select="nl:class">
            <xsl:with-param name="parent" select="$this"/>
        </xsl:apply-templates>
        <xsl:apply-templates select="nl:instance">
            <xsl:with-param name="parent" select="$this"/>
        </xsl:apply-templates>
        <xsl:for-each select="nl:package">
            <xsl:call-template name="package">
                <xsl:with-param name="parent" select="$this"/>
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    <xsl:template name="function" match="nl:function">
        <xsl:param name="parent"/>
        <xsl:variable name="ismethod" select="local-name(.)!='function'"/>
        <xsl:variable name="this">
            <xsl:if test="string($parent)">
                <xsl:value-of select="$parent"/>.</xsl:if>
            <xsl:if test="$ismethod">method-</xsl:if>
            <xsl:if test="not($ismethod)">function-</xsl:if>
            <xsl:value-of select="@name"/>
        </xsl:variable>
        <xsl:call-template name="anchor">
            <xsl:with-param name="name" select="$this"/>
        </xsl:call-template>
        <h3>
            <xsl:if test="$ismethod">Method </xsl:if>
            <xsl:if test="not($ismethod)">Function </xsl:if>
            <xsl:value-of select="@name"/>
        </h3>
        <xsl:call-template name="version-info"/>
        <hr/>
        <h4>Synopsis</h4>
        <table class="FSyn" border="0">
            <tr>
                <td colspan="4" align="right">
                    <span class="FProto">
                        <xsl:if test="nl:return">
                            <xsl:call-template name="anchor">
                                <xsl:with-param name="link" select="nl:return/@type"/>
                                <xsl:with-param name="name" select="$this"/>
                                <xsl:with-param name="suffix">return</xsl:with-param>
                            </xsl:call-template>
                        </xsl:if>
                        <xsl:if test="not(nl:return)">void</xsl:if>
                        <strong>
                            <xsl:value-of select="concat(' ', @name)"/>
                        </strong>
                    </span>
                </td>
                <td>
                    <span class="FProto">(</span>
                </td>
            </tr>
            <xsl:for-each select="nl:argument">
                <tr>
                    <td class="TabCell"> </td>
                    <td>
                        <div align="right" class="FProto">
                            <xsl:value-of select="@type"/>
                        </div>
                    </td>
                    <td>
                        <span class="FProto"/>
                    </td>
                    <td>
                        <span class="FProto">
                            <strong>
                                <xsl:call-template name="anchor">
                                    <xsl:with-param name="link" select="@name"/>
                                    <xsl:with-param name="name" select="$this"/>
                                    <xsl:with-param name="suffix" select="concat('arg', position())"/>
                                </xsl:call-template>
                            </strong>
                            <xsl:if test="position() != last()">, </xsl:if>
                        </span>
                    </td>
                    <td>
                        <xsl:if test="@info">/*<span class="FProto">
                                <em>
                                    <xsl:value-of select="concat(' ', @info, ' ')"/>
                                </em>
                            </span>*/</xsl:if>
                    </td>
                </tr>
            </xsl:for-each>
            <tr>
                <td>
                    <span class="Ptototype"/>
                </td>
                <td>
                    <span class="FProto"/>
                </td>
                <td>
                    <span class="FProto"/>
                </td>
                <td>
                    <span class="FProto"/>
                </td>
                <td>
                    <span class="FProto">)</span>
                </td>
            </tr>
        </table>
        <xsl:if test="nl:argument">
            <h4>Parameters</h4>
            <table class="FParam">
                <xsl:for-each select="nl:argument">
                    <tr>
                        <td class="FPName" valign="top">
                            <xsl:call-template name="anchor">
                                <xsl:with-param name="name" select="$this"/>
                                <xsl:with-param name="suffix" select="concat('arg', position())"/>
                            </xsl:call-template>
                            <xsl:value-of select="@name"/>
                        </td>
                        <td class="TabCell"/>
                        <td class="FPInfo">
                            <xsl:value-of select="@info"/>
                            <xsl:call-template name="auto-summary"/>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>
        </xsl:if>
        <xsl:if test="nl:return">
            <xsl:call-template name="anchor">
                <xsl:with-param name="name" select="$this"/>
                <xsl:with-param name="suffix">return</xsl:with-param>
            </xsl:call-template>
            <h4>Return Value</h4>
            <blockquote>
                <xsl:value-of select="nl:return/@info"/>
                <xsl:call-template name="auto-summary"/>
            </blockquote>
        </xsl:if>
        <xsl:call-template name="auto-example"/>
        <xsl:call-template name="auto-note"/>
        <xsl:if test="nl:test">
            <h4>Tests</h4>
            <table class="FTest" border="0">
                <xsl:for-each select="nl:test">
                    <tr>
                        <td>
                            <xsl:if test="@name">
                                <em>
                                    <xsl:value-of select="concat(@name, ' test: ')"/>
                                </em>
                            </xsl:if>
                        </td>
                        <td class="TabCell"/>
                        <td>
                            <xsl:for-each select="nl:self-call">
                                <xsl:value-of select="../../@name"/>(<xsl:for-each select="nl:argument">
                                    <xsl:value-of select="concat(@name, ' := ', @value)"/>
                                    <xsl:if test="position() != last()">, </xsl:if>
                                </xsl:for-each>)</xsl:for-each>
                        </td>
                        <td class="TabCell"/>
                        <td>
                            <xsl:value-of select="'Returns '"/>
                            <xsl:choose>
                                <xsl:when test="@assert = 'eq'"> </xsl:when>
                                <xsl:when test="@assert = 'ne'">value other than </xsl:when>
                                <xsl:when test="@assert = 'lt'">value less than </xsl:when>
                                <xsl:when test="@assert = 'le'">value less than or equal to </xsl:when>
                                <xsl:when test="@assert = 'gt'">value greater than </xsl:when>
                                <xsl:when test="@assert = 'ge'">value greater than or equal to </xsl:when>
                                <xsl:otherwise>value <xsl:value-of select="@assert"/>to </xsl:otherwise>
                            </xsl:choose>
                            <xsl:value-of select="nl:return/@value"/>
                        </td>
                        <td class="TabCell"/>
                        <td>
                            <xsl:if test="@info">
                                <xsl:value-of select="@info"/>
                            </xsl:if>
                        </td>
                    </tr>
                </xsl:for-each>
            </table>
        </xsl:if>
        <xsl:call-template name="auto-compatible"/>
        <xsl:call-template name="auto-see-also">
            <xsl:with-param name="this" select="$this"/>
        </xsl:call-template>
        <xsl:if test="$ismethod">
            <xsl:call-template name="navbar-method"/>
        </xsl:if>
        <xsl:if test="not($ismethod)">
            <xsl:call-template name="navbar-function"/>
        </xsl:if>
    </xsl:template>
    <xsl:template name="class" match="nl:class">
        <xsl:param name="parent"/>
        <xsl:variable name="this">
            <xsl:if test="string($parent)">
                <xsl:value-of select="$parent"/>.</xsl:if>class-<xsl:value-of select="@name"/>
        </xsl:variable>
        <xsl:call-template name="anchor">
            <xsl:with-param name="name" select="$this"/>
        </xsl:call-template>
        <h3>Class <xsl:value-of select="$this"/>
        </h3>
        <xsl:call-template name="version-info"/>
        <hr/>
        <h4>Structure</h4>
        <table class="CSyn" border="0">
            <tr>
                <td colspan="5">Class <strong>
                        <xsl:value-of select="@name"/>
                    </strong>
                    <xsl:if test="@base"> extends <strong>
                            <xsl:call-template name="auto-cross-link">
                                <xsl:with-param name="this" select="$this"/>
                                <xsl:with-param name="name" select="@base"/>
                            </xsl:call-template>
                        </strong>
                    </xsl:if>
                </td>
                <td> </td>
            </tr>
            <xsl:for-each select="nl:interface">
                <tr>
                    <td class="TabCell"/>
                    <td>interface</td>
                    <td/>
                    <td colspan="2">
                        <strong>
                            <xsl:value-of select="@name"/>
                        </strong>
                    </td>
                    <td>
                        <xsl:if test="@info">
                            <em>
                                <xsl:value-of select="@info"/>
                            </em>
                        </xsl:if>
                    </td>
                </tr>
            </xsl:for-each>
            <tr>
                <td colspan="5">
                    <div align="center">
                        <em>----- Methods -----</em>
                    </div>
                </td>
                <td> </td>
            </tr>
            <xsl:for-each select="nl:method">
                <tr>
                    <td> </td>
                    <td>
                        <xsl:call-template name="access-feature"/>
                    </td>
                    <td align="right">
                        <xsl:call-template name="modifier-feature"/>
                    </td>
                    <td>
                        <xsl:if test="nl:return">
                            <xsl:value-of select="nl:return/@type"/>
                        </xsl:if>
                        <xsl:if test="not(nl:return)">void</xsl:if>
                    </td>
                    <td>
                        <strong>
                            <a href="#_func_method">_method_</a>
                            <xsl:call-template name="anchor">
                                <xsl:with-param name="link" select="@name"/>
                                <xsl:with-param name="prefix" select="$this"/>
                                <xsl:with-param name="name" select="concat('method-', @name)"/>
                            </xsl:call-template>
                        </strong>(<xsl:for-each select="nl:argument">
                            <xsl:value-of select="concat(@type, ' ', @name)"/>
                            <xsl:if test="position() != last()">, </xsl:if>
                        </xsl:for-each>)</td>
                    <td>
                        <xsl:if test="@info">
                            <em>
                                <xsl:value-of select="@info"/>
                            </em>
                        </xsl:if>
                    </td>
                </tr>
            </xsl:for-each>
            <tr>
                <td colspan="5">
                    <div align="center">
                        <em>----- Properties -----</em>
                    </div>
                </td>
                <td> </td>
            </tr>
            <xsl:for-each select="nl:property">
                <tr>
                    <td> </td>
                    <td>
                        <xsl:call-template name="access-feature"/>
                    </td>
                    <td align="right">
                        <xsl:call-template name="modifier-feature"/>
                    </td>
                    <td>
                        <xsl:value-of select="@type"/>
                    </td>
                    <td>
                        <strong>
                            <xsl:call-template name="anchor">
                                <xsl:with-param name="link" select="@name"/>
                                <xsl:with-param name="prefix" select="$this"/>
                                <xsl:with-param name="name">concat('property-', @name)</xsl:with-param>
                            </xsl:call-template>
                        </strong>
                    </td>
                    <td>
                        <xsl:if test="@info">
                            <em>
                                <xsl:value-of select="@info"/>
                            </em>
                        </xsl:if>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
        <h4>Properties</h4>
        <blockquote>
            <div class="hang">
                <a name="_class_-prop_" id="_class_-prop_"/>
                <strong>_property_</strong>
                <br/>_page_</div>
        </blockquote>
        <xsl:call-template name="auto-example"/>
        <xsl:call-template name="auto-note"/>
        <xsl:if test="nl:test">
            <h4>Tests</h4>
            <blockquote>
                <strong>_name_ test
   </strong>
                <table border="0">
                    <tr>
                        <td class="TabCell"/>
                        <td>_test_self_call_(_args_)</td>
                        <td class="TabCell"/>
                        <td>Result: _return_</td>
                    </tr>
                </table>
            </blockquote>
        </xsl:if>
        <xsl:call-template name="auto-compatible"/>
        <h4>See Also</h4>
        <ul>
            <li>_see_also_</li>
        </ul>
        <xsl:call-template name="navbar-class"/>
        <xsl:for-each select="nl:method">
            <xsl:call-template name="function">
                <xsl:with-param name="parent" select="$this"/>
            </xsl:call-template>
        </xsl:for-each>
        <xsl:apply-templates select="nl:property">
            <xsl:with-param name="parent" select="$this"/>
        </xsl:apply-templates>
    </xsl:template>
    <xsl:template name="instance" match="nl:instance"/>
    <!--========== PARTIAL COMPONENTS ==========-->
    <xsl:template name="version-info">
        <table class="VersionInfo" border="0" cellpadding="0" cellspacing="0">
            <xsl:if test="@version">
                <tr>
                    <th width="164" scope="row">
                        <div align="right">Version</div>
                    </th>
                    <td width="309" class="TabCell"> </td>
                    <td width="309">
                        <xsl:value-of select="@version"/>
                    </td>
                </tr>
            </xsl:if>
            <xsl:if test="@status">
                <tr>
                    <th scope="row">
                        <div align="right">Status</div>
                    </th>
                    <td class="TabCell"> </td>
                    <td width="309">
                        <xsl:value-of select="@status"/>
                    </td>
                </tr>
            </xsl:if>
            <xsl:if test="@author">
                <tr>
                    <th scope="row">
                        <div align="right">Author</div>
                    </th>
                    <td class="TabCell"> </td>
                    <td width="309">
                        <xsl:value-of select="@author"/>
                    </td>
                </tr>
            </xsl:if>
            <xsl:if test="@company">
                <tr>
                    <th scope="row">
                        <div align="right">Company</div>
                    </th>
                    <td class="TabCell"> </td>
                    <td width="309">
                        <xsl:value-of select="@company"/>
                    </td>
                </tr>
            </xsl:if>
            <xsl:if test="@email">
                <tr>
                    <th scope="row">
                        <div align="right">E-mail</div>
                    </th>
                    <td class="TabCell"> </td>
                    <td width="309">
                        <xsl:value-of select="@email"/>
                    </td>
                </tr>
            </xsl:if>
        </table>
    </xsl:template>
    <xsl:template name="navbar-package">
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
    </xsl:template>
    <xsl:template name="navbar-function">
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
    </xsl:template>
    <xsl:template name="navbar-class">
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
    </xsl:template>
    <xsl:template name="navbar-method">
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
    </xsl:template>
    <xsl:template name="navbar-instance">
        <div class="NavBar">
            <a href="#_func_prev" class="NavLink">Previous</a>
            <a href="#library" class="NavLink">Library</a>
            <a href="#_package_" class="NavLink">Package</a>
            <a href="_func_" class="NavLink">Function</a>
            <a href="_func_next" class="NavLink">Next</a>
        </div>
    </xsl:template>
    <!--========== UTILITIES ==========-->
    <xsl:template name="auto-info">
        <xsl:if test="@info">: <xsl:value-of select="@info"/>
        </xsl:if>
    </xsl:template>
    <xsl:template name="access-feature">
        <xsl:if test="@access">
            <xsl:value-of select="@access"/>
        </xsl:if>
    </xsl:template>
    <xsl:template name="modifier-feature">
        <xsl:if test="@static">static </xsl:if>
        <xsl:if test="@constant">const </xsl:if>
        <xsl:if test="@transient">transient </xsl:if>
        <xsl:if test="@storage">
            <xsl:value-of select="concat(@storage, ' ')"/>
        </xsl:if>
        <xsl:if test="@share">
            <xsl:value-of select="concat(@share, ' ')"/>
        </xsl:if>
        <xsl:if test="@manage">managed </xsl:if>
    </xsl:template>
    <xsl:template name="auto-summary">
        <xsl:if test="nl:summary">
            <xsl:copy-of select="nl:summary"/>
        </xsl:if>
    </xsl:template>
    <xsl:template name="auto-note">
        <xsl:if test="nl:note">
            <h4>Notes</h4>
            <blockquote>
                <dl>
                    <xsl:for-each select="nl:note">
                        <dt>
                            <strong>
                                <xsl:value-of select="@name"/>
                            </strong>
                            <dd>
                                <xsl:value-of select="@info"/>
                                <xsl:call-template name="auto-summary"/>
                            </dd>
                        </dt>
                    </xsl:for-each>
                </dl>
            </blockquote>
        </xsl:if>
    </xsl:template>
    <xsl:template name="auto-compatible">
        <xsl:if test="nl:compatible">
            <h4>Compatible</h4>
            <ul>
                <xsl:for-each select="nl:compatible">
                    <li>
                        <xsl:value-of select="@for"/>
                        <xsl:if test="@level">: <xsl:value-of select="@level"/>
                        </xsl:if>
                        <xsl:if test="not(@level)">: Compatible</xsl:if>
                        <xsl:if test="@info">
                            <div>-- <xsl:value-of select="@info"/>
                            </div>
                        </xsl:if>
                    </li>
                </xsl:for-each>
            </ul>
        </xsl:if>
    </xsl:template>
    <xsl:template name="auto-example">
        <xsl:if test="nl:example">
            <h4>Examples</h4>
            <xsl:for-each select="nl:example">
                <blockquote>
                    <xsl:if test="@info">
                        <div class="Info">
                            <xsl:value-of select="@info"/>
                        </div>
                    </xsl:if>
                    <div>
                        <pre>
                            <xsl:value-of select="text()"/>
                        </pre>
                    </div>
                </blockquote>
            </xsl:for-each>
        </xsl:if>
    </xsl:template>
    <xsl:template name="auto-see-also">
        <xsl:param name="this"/>
        <xsl:if test="nl:see-also">
            <h4>See Also</h4>
            <xsl:variable name="type" select="local-name(.)"/>
            <xsl:variable name="prefix">
                <xsl:if test="$type != 'package'">
                    <xsl:value-of select="concat($type, '-')"/>
                </xsl:if>
            </xsl:variable>
            <blockquote>
                <xsl:for-each select="descendant::nl:see-also">
                    <cite class="SAcite">
                        <xsl:choose>
                            <!--Explicit mode-->
                            <xsl:when test="@href">
                                <xsl:element name="a">
                                    <xsl:attribute name="href"><xsl:value-of select="@href"/></xsl:attribute>
                                    <xsl:choose>
                                        <xsl:when test="@name">
                                            <xsl:value-of select="@name"/>
                                        </xsl:when>
                                        <xsl:otherwise>
                                            <xsl:value-of select="@href"/>
                                        </xsl:otherwise>
                                    </xsl:choose>
                                </xsl:element>
                            </xsl:when>
                            <!--(Related) Qualified name mode-->
                            <xsl:otherwise>
                                <xsl:call-template name="cross-link">
                                    <xsl:with-param name="this" select="$this"/>
                                    <xsl:with-param name="name" select="concat($prefix, @name)"/>
                                </xsl:call-template>
                            </xsl:otherwise>
                        </xsl:choose>
                    </cite>
                </xsl:for-each>
            </blockquote>
        </xsl:if>
    </xsl:template>
    <!--========== BASIC UTILITIES ==========-->
    <xsl:template name="anchor">
        <xsl:param name="link"/>
        <xsl:param name="prefix"/>
        <xsl:param name="name"/>
        <xsl:param name="suffix"/>
        <xsl:variable name="full">
            <xsl:if test="string($prefix)">
                <xsl:value-of select="$prefix"/>
                <xsl:if test="string($name) or string($suffix)">.</xsl:if>
            </xsl:if>
            <xsl:if test="string($name)">
                <xsl:value-of select="$name"/>
                <xsl:if test="string($suffix)">.</xsl:if>
            </xsl:if>
            <xsl:if test="string($suffix)">
                <xsl:value-of select="$suffix"/>
            </xsl:if>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="string($link)">
                <xsl:element name="a">
                    <xsl:attribute name="href">#<xsl:value-of select="$full"/></xsl:attribute>
                    <xsl:if test="$link='1'">
                        <xsl:value-of select="$full"/>
                    </xsl:if>
                    <xsl:if test="$link!='1'">
                        <xsl:value-of select="$link"/>
                    </xsl:if>
                </xsl:element>
            </xsl:when>
            <xsl:otherwise>
                <xsl:element name="a">
                    <xsl:attribute name="name"><xsl:value-of select="$full"/></xsl:attribute>
                </xsl:element>
                <!--span style="color: red">(<xsl:value-of select="$full"/>)</span-->
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="cross-link">
        <xsl:param name="this"/>
        <xsl:param name="name"/>
        <xsl:element name="a">
            <xsl:attribute name="href">#<xsl:call-template name="token-replace-sibling"><xsl:with-param name="base" select="$this"/><xsl:with-param name="over" select="$name"/></xsl:call-template></xsl:attribute>
            <xsl:call-template name="build-domain">
                <xsl:with-param name="str" select="$name"/>
            </xsl:call-template>
        </xsl:element>
    </xsl:template>
    <xsl:template name="auto-cross-link">
        <xsl:param name="this"/>
        <xsl:param name="name"/>
        <xsl:choose>
            <!--Outside type-name with dot(.), such as libxyz.typex-->
            <xsl:when test="contains($name, '.')">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="$name"/>
                </xsl:call-template>
            </xsl:when>
            <!--Explicitly meta-type-->
            <xsl:when test="contains($name, '-')">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="$name"/>
                </xsl:call-template>
            </xsl:when>
            <!--(Sibling) Function existed-->
            <xsl:when test="ancestor::nl:function[@name=$name]">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="concat('function-', $name)"/>
                </xsl:call-template>
            </xsl:when>
            <!--(Sibling) Class existed-->
            <xsl:when test="ancestor::nl:class[@name=$name]">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="concat('class-', $name)"/>
                </xsl:call-template>
            </xsl:when>
            <!--(Sibling) Method existed-->
            <xsl:when test="ancestor::nl:method[@name=$name]">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="concat('method-', $name)"/>
                </xsl:call-template>
            </xsl:when>
            <!--(Sibling) Instance existed-->
            <xsl:when test="ancestor::nl:instance[@name=$name]">
                <xsl:call-template name="cross-link">
                    <xsl:with-param name="this" select="$this"/>
                    <xsl:with-param name="name" select="concat('instance-', $name)"/>
                </xsl:call-template>
            </xsl:when>
            <!--Not defined name in this library-->
            <xsl:otherwise>
                <xsl:value-of select="$name"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="build-domain">
        <xsl:param name="str"/>
        <xsl:choose>
            <xsl:when test="contains($str, '.')">
                <xsl:call-template name="build-domain-token">
                    <xsl:with-param name="token" select="substring-before($str, '.')"/>
                </xsl:call-template>::<xsl:call-template name="build-domain">
                    <xsl:with-param name="str" select="substring-after($str, '.')"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:call-template name="build-domain-token">
                    <xsl:with-param name="token" select="$str"/>
                </xsl:call-template>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="build-domain-token">
        <xsl:param name="token"/>
        <xsl:choose>
            <xsl:when test="starts-with($token, 'function-')">
                <xsl:value-of select="substring-after($token, '-')"/>
            </xsl:when>
            <xsl:when test="starts-with($token, 'class-')">
                <xsl:value-of select="substring-after($token, '-')"/>
            </xsl:when>
            <xsl:when test="starts-with($token, 'instance-')">
                <xsl:value-of select="substring-after($token, '-')"/>
            </xsl:when>
            <xsl:otherwise>
                <!--package-->
                <xsl:value-of select="$token"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
