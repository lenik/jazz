<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/xhtml" xmlns:af="http://www.bodz.net/xml/zlw/abstract-form" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:variable name="encoding">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'encoding'"/>
			<xsl:with-param name="default" select="'utf-8'"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="af-base">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'af-base'"/>
			<xsl:with-param name="default" select="'.'"/>
		</xsl:call-template>
	</xsl:variable>
	<xsl:template name="t-param">
		<xsl:param name="name"/>
		<xsl:param name="default"/>
		<xsl:variable name="node" select="//af:section[@name='.page']/*[@name=$name]"/>
		<xsl:choose>
			<xsl:when test="$node">
				<xsl:copy-of select="$node/node()"/>
			</xsl:when>
			<xsl:when test="$default">
				<xsl:copy-of select="$default"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-param-text">
		<xsl:param name="name"/>
		<xsl:param name="default"/>
		<xsl:variable name="node" select="//af:section[@name='.page']/*[@name=$name]"/>
		<xsl:choose>
			<xsl:when test="$node">
				<xsl:for-each select="$node">
					<xsl:value-of select="text()"/>
					<xsl:for-each select="*">
						<xsl:call-template name="t-all-text"/>
					</xsl:for-each>
				</xsl:for-each>
			</xsl:when>
			<xsl:when test="$default">
				<xsl:value-of select="$default"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	<!--HTML-->
	<xsl:template match="/af:abstract-form">
		<!---->
		<!--Encoding-->
		<xsl:variable name="encoding">
			<xsl:call-template name="t-param-text">
				<xsl:with-param name="name" select="'encoding'"/>
				<xsl:with-param name="default" select="'utf-8'"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="title">
			<xsl:call-template name="t-param-text">
				<xsl:with-param name="name" select="'title'"/>
				<xsl:with-param name="default" select="'Abstract Form (Unnamed)'"/>
			</xsl:call-template>
		</xsl:variable>
		<html>
			<head>
				<!---->
				<!--Title-->
				<title>
					<xsl:value-of select="$title"/>
				</title>
				<!---->
				<!--Meta *any*-->
				<xsl:variable name="meta" select="//af:section[@name='.page']/af:map[@name='meta']"/>
				<xsl:if test="$meta">
					<xsl:for-each select="$meta/af:entry">
						<xsl:element name="meta">
							<xsl:attribute name="http-equiv"><xsl:value-of select="@key"/></xsl:attribute>
							<xsl:attribute name="content"><xsl:call-template name="t-all-text"/></xsl:attribute>
						</xsl:element>
					</xsl:for-each>
				</xsl:if>
				<!---->
				<!--CSS Stylesheet of this Html-View-->
				<style type="text/css">
					<xsl:call-template name="t-param-text">
						<xsl:with-param name="name" select="'css'"/>
						<xsl:with-param name="default">.indent { }
.data-type { font-weight: bold }
.data-name { text-decoration: underline }
.data-separator { 
	height: 1px; 
	bottom-border: 1px solid gray 
}
.scalar { font-family: courier, helvetica, sans-serif, arial }
.list { }
.map { }
.table { }
.user { }
.error { border: red 3px solid; color: red }
.error-message { font-style: italic; color: magenta }
.input { }
.selector { }
.event { }</xsl:with-param>
					</xsl:call-template>
				</style>
				<!---->
				<!--Constraints-->
				<xsl:element name="script">
					<xsl:attribute name="language">javascript</xsl:attribute>
					<xsl:attribute name="src"><xsl:value-of select="concat($af-base, '/abstract-form.js')"/></xsl:attribute>
				</xsl:element>
				<!--xsl:if test="$check-constraint">
					<script language="javascript" src="abstract-form/constraints.js"/>
				</xsl:if-->
			</head>
			<body>
				<h1>
					<xsl:value-of select="$title"/>
				</h1>
				<hr/>
				<xsl:call-template name="t-sections"/>
				<xsl:call-template name="t-copyright"/>
			</body>
		</html>
	</xsl:template>
	<xsl:template name="t-copyright">
		<xsl:call-template name="t-param">
			<xsl:with-param name="name" select="'copyright'"/>
			<xsl:with-param name="default">
				<!--<HR/>-->
				<cite>Powered by ZLW::Abstract-Form</cite>
				<br/>
				<cite>$Id: html-view.xsl,v 1.6.2.1 2005-12-09 07:59:32 dansei Exp $</cite>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="t-error-message">
		<xsl:param name="message" select="'General Error'"/>
		<div class="error-message">
			<xsl:value-of select="$message"/>
		</div>
	</xsl:template>
	<xsl:template name="t-name">
		<span class="data-name">
			<xsl:value-of select="@name"/>
			<xsl:if test="@hold='true'">
				<xsl:variable name="data-type">
					<xsl:call-template name="t-param-text">
						<xsl:with-param name="name" select="'data-type'"/>
						<xsl:with-param name="default" select="'hidden'"/>
					</xsl:call-template>
				</xsl:variable>
				<xsl:if test="$data-type='visible'">(HOLD)</xsl:if>
			</xsl:if>
		</span>
	</xsl:template>
	<xsl:template name="t-all-text">
		<xsl:value-of select="text()"/>
		<xsl:for-each select="*">
			<xsl:call-template name="t-all-text"/>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-href-concat">
		<xsl:param name="lhs"/>
		<xsl:param name="rhs"/>
		<xsl:choose>
			<xsl:when test="$rhs = ''">
				<xsl:value-of select="$lhs"/>
			</xsl:when>
			<xsl:when test="substring($lhs, string-length($lhs)) = '?'">
				<xsl:value-of select="concat($lhs, $rhs)"/>
			</xsl:when>
			<xsl:when test="contains($lhs, '?') or contains($lhs, '&amp;')">
				<xsl:value-of select="concat($lhs, '&amp;', $rhs)"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat($lhs, '?', $rhs)"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-hint-inherit">
		<xsl:choose>
			<xsl:when test="@hint">
				<xsl:value-of select="@hint"/>
			</xsl:when>
			<xsl:when test="..">
				<xsl:for-each select="..">
					<xsl:call-template name="t-hint-inherit"/>
				</xsl:for-each>
			</xsl:when>
			<xsl:otherwise>?</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-hint-with-hold">
		<xsl:call-template name="t-href-concat">
			<xsl:with-param name="lhs">
				<xsl:call-template name="t-hint-inherit"/>
			</xsl:with-param>
			<xsl:with-param name="rhs">
				<xsl:for-each select="ancestor::af:section[1]/*[@hold='true']">
					<xsl:if test="position()>1">&amp;</xsl:if>
					<xsl:value-of select="@name"/>=<xsl:call-template name="t-serialize"/>
				</xsl:for-each>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="t-serialize">
		<xsl:value-of select="text()"/>
	</xsl:template>
	<!--STRUCTURE ELEMENTS-->
	<xsl:template name="t-sections">
		<xsl:for-each select="af:section">
			<xsl:choose>
				<xsl:when test="substring(@name, 1, 1) != '.'">
					<xsl:call-template name="t-section"/>
				</xsl:when>
				<xsl:otherwise>
					<!--Hidden sections are not displayed.-->
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-section">
		<!--Anchor #@section-name-->
		<xsl:variable name="data-type">
			<xsl:call-template name="t-param-text">
				<xsl:with-param name="name" select="'data-type'"/>
				<xsl:with-param name="default" select="'hidden'"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:element name="a">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		</xsl:element>
		<h2>
			<xsl:value-of select="@name"/>
		</h2>
		<table>
			<xsl:for-each select="./*">
				<xsl:variable name="tag" select="local-name(.)"/>
				<!--by tag names-->
				<xsl:choose>
					<xsl:when test="$tag = 'scalar'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">Scalar </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-scalar"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'list'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">List </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-list"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'map'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">Map </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-map"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'table'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">Table </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-table"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'user'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">User </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-user"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'error'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">Error</span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-error"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:when test="$tag = 'form'">
						<tr>
							<td colspan="2">
								<xsl:if test="$data-type='visible'">
									<span class="data-type">Form </span>
								</xsl:if>
								<xsl:call-template name="t-name"/>
							</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-form"/>
							</td>
						</tr>
					</xsl:when>
					<xsl:otherwise>
						<tr>
							<td colspan="2">Error</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td>
								<xsl:call-template name="t-error-message">
									<xsl:with-param name="message" select="concat('unexpected section element of meta-type: ', $tag)"/>
								</xsl:call-template>
							</td>
						</tr>
					</xsl:otherwise>
				</xsl:choose>
				<tr>
					<td colspan="2" style="data-separator"/>
				</tr>
			</xsl:for-each>
		</table>
		<hr/>
	</xsl:template>
	<!--DATA ELEMENTS-->
	<xsl:template name="t-scalar">
		<span>
			<xsl:call-template name="t-all-text"/>
		</span>
	</xsl:template>
	<xsl:template name="t-list">
		<table>
			<xsl:for-each select="af:item">
				<xsl:variable name="index" select="position()"/>
				<tr>
					<td>
						<xsl:value-of select="$index"/>. </td>
					<td>
						<span class="list">
							<xsl:call-template name="t-all-text"/>
						</span>
					</td>
					<td>
						<xsl:for-each select="../../af:method">
							<span class="method">
								<xsl:element name="a">
									<xsl:attribute name="href"><xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs" select="concat('.method=', @name, '&amp;.index=', $index)"/></xsl:call-template></xsl:attribute>
									<xsl:value-of select="@name"/>
								</xsl:element>
							</span>
							<xsl:value-of select="' '"/>
						</xsl:for-each>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
	<xsl:template name="t-map">
		<table>
			<thead>
				<tr>
					<th/>
					<th>Key</th>
					<th>Value</th>
					<xsl:for-each select="af:method">
						<th/>
					</xsl:for-each>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="af:entry">
					<xsl:variable name="key" select="@key"/>
					<tr>
						<td>
							<xsl:value-of select="position()"/>. </td>
						<td>
							<xsl:value-of select="@key"/>
						</td>
						<td>
							<span class="map">
								<xsl:call-template name="t-all-text"/>
							</span>
						</td>
						<xsl:for-each select="../af:method">
							<td>
								<span class="method">
									<xsl:element name="a">
										<xsl:attribute name="href"><xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs" select="concat('.method=', @name, '&amp;.key=', $key)"/></xsl:call-template></xsl:attribute>
										<xsl:value-of select="@name"/>
									</xsl:element>
								</span>
								<xsl:value-of select="' '"/>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
	<xsl:template name="t-table">
		<table>
			<thead>
				<tr>
					<!--Sort...-->
					<xsl:for-each select="af:column">
						<th>
							<xsl:value-of select="@name"/>
							<xsl:if test="@primary-key">(K)</xsl:if>
							<xsl:if test="@sort-priority">
								<!--Sorting...?-->
							</xsl:if>
						</th>
					</xsl:for-each>
					<xsl:for-each select="af:method">
						<th>
							<xsl:value-of select="@name"/>
						</th>
					</xsl:for-each>
				</tr>
			</thead>
			<tbody>
				<xsl:for-each select="af:row">
					<xsl:variable name="row" select="."/>
					<xsl:variable name="pk">
						<xsl:for-each select="../af:column">
							<xsl:if test="@primary-key">&amp;<xsl:variable name="name" select="@name"/>
								<xsl:value-of select="@name"/>=<xsl:value-of select="$row/*[local-name()=$name]/text()"/>
							</xsl:if>
						</xsl:for-each>
					</xsl:variable>
					<tr>
						<xsl:for-each select="*">
							<td>
								<span class="table">
									<xsl:value-of select="text()"/>
								</span>
							</td>
						</xsl:for-each>
						<xsl:for-each select="../af:method">
							<td>
								<span class="method">
									<xsl:element name="a">
										<xsl:attribute name="href"><xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs" select="concat('.method=', @name, $pk)"/></xsl:call-template></xsl:attribute>
										<xsl:value-of select="@name"/>
									</xsl:element>
								</span>
								<xsl:value-of select="' '"/>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
	<xsl:template name="t-dump">
		<xsl:param name="path"/>
		<xsl:param name="owner"/>
		<div>
			<xsl:value-of select="text()"/>
			<xsl:for-each select="$owner/af:method">
				<span class="method">
					<xsl:element name="a">
						<xsl:attribute name="href"><xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs"><xsl:value-of select="concat('.method=', @name, '&amp;.path=', $path)"/></xsl:with-param></xsl:call-template></xsl:attribute>
						<xsl:value-of select="@name"/>
					</xsl:element>
				</span>
				<xsl:value-of select="' '"/>
			</xsl:for-each>
		</div>
		<xsl:if test="*[namespace-uri() != 'http://www.bodz.net/xml/zlw/abstract-form']">
			<table>
				<xsl:for-each select="*[namespace-uri() != 'http://www.bodz.net/xml/zlw/abstract-form']">
					<tr valign="baseline">
						<td>
							<xsl:value-of select="position()"/>. </td>
						<td>
							<span>
								<strong>
									<xsl:value-of select="name()"/>
								</strong>
							</span>
						</td>
						<td>
							<xsl:call-template name="t-dump">
								<xsl:with-param name="path" select="concat($path, '.', local-name())"/>
								<xsl:with-param name="owner" select="$owner"/>
							</xsl:call-template>
						</td>
					</tr>
				</xsl:for-each>
			</table>
		</xsl:if>
	</xsl:template>
	<xsl:template name="t-user">
		<div class="user">
			<xsl:call-template name="t-dump">
				<xsl:with-param name="path" select="@name"/>
				<xsl:with-param name="owner" select="."/>
			</xsl:call-template>
		</div>
	</xsl:template>
	<xsl:template name="t-error">
		<table class="error">
			<tr>
				<th>
					<xsl:if test="@code">Error-Code: 
					<xsl:value-of select="@code"/>
						<xsl:value-of select="' '"/>
					</xsl:if>
					<xsl:if test="@source">
						<strong>Error-Source</strong>: 
					<xsl:value-of select="@source"/>
						<xsl:value-of select="' '"/>
					</xsl:if>
					<span class="method">( <a href="javascript: location.reload(); ">Reload</a> | <a href="javascript: history.back()">Go Back</a> )</span>
				</th>
			</tr>
			<tr>
				<td>
					<xsl:call-template name="t-dump">
						<xsl:with-param name="path" select="@name"/>
						<xsl:with-param name="owner" select="."/>
					</xsl:call-template>
				</td>
			</tr>
		</table>
	</xsl:template>
	<!--FORM ELEMENTS-->
	<xsl:template name="t-form">
		<xsl:variable name="form-id" select="concat('form_', position())"/>
		<xsl:variable name="form-name">
			<xsl:choose>
				<xsl:when test="@name">
					<xsl:value-of select="@name"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="position()"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:element name="form">
			<xsl:attribute name="id"><xsl:value-of select="$form-id"/></xsl:attribute>
			<xsl:attribute name="action"><xsl:call-template name="t-hint-inherit"/></xsl:attribute>
			<xsl:attribute name="onsubmit">javascript: return zlw_af_form_onsubmit(); </xsl:attribute>
			<xsl:if test="@form-method">
				<xsl:attribute name="method"><xsl:value-of select="@form-method"/></xsl:attribute>
			</xsl:if>
			<!--INPUT .form-->
			<xsl:element name="input">
				<xsl:attribute name="type">hidden</xsl:attribute>
				<xsl:attribute name="name">.form</xsl:attribute>
				<xsl:attribute name="value"><xsl:value-of select="$form-name"/></xsl:attribute>
			</xsl:element>
			<xsl:for-each select="*">
				<xsl:variable name="tag" select="local-name(.)"/>
				<xsl:choose>
					<xsl:when test="$tag = 'input'">
						<xsl:call-template name="t-input"/>
					</xsl:when>
					<xsl:when test="$tag = 'selector'">
						<xsl:call-template name="t-selector"/>
					</xsl:when>
					<xsl:when test="$tag = 'method'">
						<xsl:call-template name="t-method">
							<xsl:with-param name="form-id" select="$form-id"/>
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="t-error-message">
							<xsl:with-param name="message" select="concat('unexpected form input of meta-type: ', $tag)"/>
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	<xsl:template name="t-input">
		<div>
			<xsl:call-template name="t-name"/>: <xsl:choose>
				<xsl:when test="@multilne">
					<xsl:element name="textarea">
						<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
						<xsl:if test="@read-only">
							<xsl:attribute name="readonly"/>
						</xsl:if>
						<xsl:if test="@init">
							<xsl:value-of select="@init"/>
						</xsl:if>
					</xsl:element>
				</xsl:when>
				<xsl:otherwise>
					<xsl:element name="input">
						<xsl:attribute name="type">text</xsl:attribute>
						<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
						<xsl:if test="@read-only">
							<xsl:attribute name="readonly"/>
						</xsl:if>
						<xsl:if test="@init">
							<xsl:attribute name="value"><xsl:value-of select="@init"/></xsl:attribute>
						</xsl:if>
					</xsl:element>
				</xsl:otherwise>
			</xsl:choose>
		</div>
	</xsl:template>
	<xsl:template name="t-selector">
		<div>
			<xsl:call-template name="t-name"/>: <xsl:choose>
				<xsl:when test="af:list">
					<xsl:call-template name="t-selector-list">
						<xsl:with-param name="list" select="af:list"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="af:map">
					<xsl:call-template name="t-selector-map">
						<xsl:with-param name="map" select="af:map"/>
					</xsl:call-template>
				</xsl:when>
				<xsl:when test="af:list-ref">
					<xsl:variable name="name" select="af:list-ref/text()"/>
					<xsl:variable name="list" select="ancestor::af:section/af:list[@name=$name]"/>
					<xsl:choose>
						<xsl:when test="$list">
							<xsl:call-template name="t-selector-list">
								<xsl:with-param name="list" select="$list"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="t-error-message">
								<xsl:with-param name="message" select="concat('list(', $name, ') is not existed')"/>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:when test="af:map-ref">
					<xsl:variable name="name" select="af:map-ref/text()"/>
					<xsl:variable name="map" select="ancestor::af:section/af:map[@name=$name]"/>
					<xsl:choose>
						<xsl:when test="$map">
							<xsl:call-template name="t-selector-map">
								<xsl:with-param name="map" select="$map"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="t-error-message">
								<xsl:with-param name="message" select="concat('map(', $name, ') is not existed')"/>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="t-error-message">
						<xsl:with-param name="message" select="'the collection to be selected cannot be resolved. '"/>
					</xsl:call-template>
				</xsl:otherwise>
			</xsl:choose>
		</div>
	</xsl:template>
	<xsl:template name="t-selector-list">
		<xsl:param name="list"/>
		<xsl:element name="select">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:choose>
				<xsl:when test="@multiple = 'true'">
					<xsl:attribute name="multiple">multiple</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="size">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:for-each select="$list/af:item">
				<xsl:element name="option">
					<xsl:attribute name="value"><xsl:value-of select="position()"/></xsl:attribute>
					<xsl:call-template name="t-all-text"/>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	<xsl:template name="t-selector-map">
		<xsl:param name="map"/>
		<xsl:element name="select">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:choose>
				<xsl:when test="@multiple = 'true'">
					<xsl:attribute name="multiple">multiple</xsl:attribute>
				</xsl:when>
				<xsl:otherwise>
					<xsl:attribute name="size">1</xsl:attribute>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:for-each select="$map/af:entry">
				<xsl:element name="option">
					<xsl:attribute name="value"><xsl:value-of select="@key"/></xsl:attribute>
					<xsl:value-of select="@key"/>: <xsl:call-template name="t-all-text"/>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	<xsl:template name="t-method">
		<xsl:param name="form-id"/>
		<xsl:element name="input">
			<xsl:attribute name="type">submit</xsl:attribute>
			<xsl:attribute name="name">.method</xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:if test="@hint">
				<!--Out-going??-->
				<xsl:attribute name="onclick">javascript: var form = document.getElementById('<xsl:value-of select="$form-id"/>'); form.action = '<xsl:call-template name="t-hint-with-hold"/>'; form.submit(); </xsl:attribute>
			</xsl:if>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
