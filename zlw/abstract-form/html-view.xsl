<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns="http://www.w3.org/1999/xhtml" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:af="http://www.bodz.net/xml/zlw/abstract-form">
	<xsl:output version="1.0" method="xml" encoding="UTF-8" doctype-public="-//W3C//DTD HTML 4.0 Transitional//EN" indent="yes"/>
	<!--The relative path to abstract-form/-->
	<xsl:param name="af-base">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'af-base'"/>
			<xsl:with-param name="default" select="'.'"/>
		</xsl:call-template>
	</xsl:param>
	<!--The namespace of zlw-af in the xsd-instances-->
	<xsl:param name="af-uri" select="'http://www.bodz.net/xml/zlw/abstract-form'"/>
	<!--Output encoding (not well impl.)-->
	<xsl:param name="encoding">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'encoding'"/>
			<xsl:with-param name="default" select="'utf-8'"/>
		</xsl:call-template>
	</xsl:param>
	<!--Page title-->
	<xsl:param name="title">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'title'"/>
			<xsl:with-param name="default" select="'Abstract Form (Unnamed)'"/>
		</xsl:call-template>
	</xsl:param>
	<!--Display type names for debugging purpose-->
	<xsl:param name="show-type_text">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'show-type'"/>
		</xsl:call-template>
	</xsl:param>
	<xsl:param name="show-type" select="($show-type_text='true' or $show-type_text='1')"/>
	<!--Max display size of input-box if @max-length is too large or is not specified. -->
	<xsl:param name="max-size">
		<xsl:call-template name="t-param-text">
			<xsl:with-param name="name" select="'max-size'"/>
			<xsl:with-param name="default" select="'80'"/>
		</xsl:call-template>
	</xsl:param>
	<!---->
	<!--HTML-->
	<xsl:template name="t-copyright">
		<xsl:call-template name="t-param">
			<xsl:with-param name="name" select="'copyright'"/>
			<xsl:with-param name="default">
				<!--<HR/>-->
				<cite>Powered by ZLW::Abstract-Form</cite>
				<br/>
				<cite>$Id: html-view.xsl,v 1.6.2.15 2006-04-22 12:58:14 dansei Exp $</cite>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="t-error-xml">
		<xsl:param name="text" select="'General Error'"/>
		<div class="error-xml">
			<xsl:value-of select="$text"/>
		</div>
	</xsl:template>
	<xsl:template name="t-name">
		<span class="data-name">
			<xsl:value-of select="@name"/>
			<xsl:if test="$show-type and (@hold='true' or @hold='1')">(HOLD)</xsl:if>
		</span>
	</xsl:template>
	<xsl:template name="t-all-text">
		<xsl:value-of select="text()"/>
		<xsl:for-each select="*">
			<xsl:call-template name="t-all-text"/>
		</xsl:for-each>
	</xsl:template>
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
	<xsl:template name="t-href-concat">
		<xsl:param name="lhs"/>
		<xsl:param name="rhs"/>
		<xsl:choose>
			<xsl:when test="$rhs=''">
				<xsl:value-of select="$lhs"/>
			</xsl:when>
			<xsl:when test="substring($lhs, string-length($lhs))='?'">
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
				<xsl:for-each select="ancestor::af:section[1]/*[(@hold='true' or @hold='1')]">
					<xsl:if test="position()>1">&amp;</xsl:if>
					<xsl:value-of select="@name"/>=<xsl:call-template name="t-serialize"/>
				</xsl:for-each>
			</xsl:with-param>
		</xsl:call-template>
	</xsl:template>
	<xsl:template name="t-serialize">
		<xsl:value-of select="text()"/>
	</xsl:template>
	<xsl:template name="t-replace-all">
		<xsl:param name="text" select="text()"/>
		<xsl:param name="pattern"/>
		<xsl:param name="replacement"/>
		<xsl:choose>
			<xsl:when test="not($pattern)">
				<xsl:value-of select="$text"/>
			</xsl:when>
			<xsl:when test="contains($text, $pattern)">
				<xsl:value-of select="concat(substring-before($text, $pattern), $replacement)"/>
				<xsl:call-template name="t-replace-all">
					<xsl:with-param name="text" select="substring-after($text, $pattern)"/>
					<xsl:with-param name="pattern" select="$pattern"/>
					<xsl:with-param name="replacement" select="$replacement"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$text"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-node-index">
		<xsl:param name="select"/>
		<xsl:param name="node"/>
		<xsl:for-each select="$select">
			<xsl:if test="generate-id(.)=generate-id($node)">
				<xsl:value-of select="position()"/>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-import-attributes">
		<xsl:param name="select" select="@*"/>
		<xsl:param name="retvar"/>
		<xsl:for-each select="$select">
			<xsl:variable name="identifier" select="translate(local-name(), '-.', '__')"/>
			<xsl:variable name="value">
				<xsl:call-template name="t-replace-all">
					<xsl:with-param name="text">
						<xsl:call-template name="t-replace-all">
							<xsl:with-param name="text">
								<xsl:call-template name="t-replace-all">
									<xsl:with-param name="text" select="."/>
									<xsl:with-param name="pattern" select="'\\'"/>
									<xsl:with-param name="replacement" select="'\\\\'"/>
								</xsl:call-template>
							</xsl:with-param>
							<xsl:with-param name="pattern" select="'\n'"/>
							<xsl:with-param name="replacement" select="'\\n'"/>
						</xsl:call-template>
					</xsl:with-param>
					<xsl:with-param name="pattern" select="'&quot;'"/>
					<xsl:with-param name="replacement" select="'\\&quot;'"/>
				</xsl:call-template>
			</xsl:variable>
			<xsl:value-of select="concat($retvar, '.', $identifier, ' = &quot;', $value, '&quot;', '; &#10;')"/>
		</xsl:for-each>
	</xsl:template>
	<!--TOP LEVELS-->
	<xsl:template name="t-abstract-form" match="/af:abstract-form" priority="-1">
		<!---->
		<!--Encoding-->
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
							<xsl:attribute name="content"><xsl:value-of select="."/></xsl:attribute>
						</xsl:element>
					</xsl:for-each>
				</xsl:if>
				<!---->
				<!--Meta Content-Type-->
				<xsl:if test="not($meta/af:entry[@key='Content-Type'])">
					<xsl:element name="meta">
						<xsl:attribute name="http-equiv">Content-Type</xsl:attribute>
						<xsl:attribute name="content"><xsl:value-of select="concat('text/html; charset=', $encoding)"/></xsl:attribute>
					</xsl:element>
				</xsl:if>
				<!---->
				<!--CSS Stylesheet of this Html-View-->
				<xsl:element name="link">
					<xsl:attribute name="rel">stylesheet</xsl:attribute>
					<xsl:attribute name="type">text/css</xsl:attribute>
					<xsl:attribute name="href"><xsl:call-template name="t-param-text"><xsl:with-param name="name" select="'css'"/><xsl:with-param name="default" select="concat($af-base, '/html-view.css')"/></xsl:call-template></xsl:attribute>
				</xsl:element>
				<!---->
				<!--Constraints-->
				<xsl:element name="script">
					<xsl:attribute name="language">javascript</xsl:attribute>
					<xsl:attribute name="src"><xsl:value-of select="concat($af-base, '/html-view.js')"/></xsl:attribute>
					<!--BUGFIX: IE SCRIPT require end-tag. -->
					<xsl:value-of select="' '"/>
				</xsl:element>
				<xsl:call-template name="t-html-header"/>
			</head>
			<xsl:element name="body">
				<xsl:attribute name="onunload"><!--BUGFIX: Don't keep control states while turn back to this page. --><!--That is, to completely unload, and re-load next time this page appeared, whatever 'Refresh' or 'Back'. --></xsl:attribute>
				<xsl:call-template name="t-html-body"/>
			</xsl:element>
		</html>
	</xsl:template>
	<xsl:template name="t-doc" match="af:doc" priority="-1">
		<xsl:choose>
			<xsl:when test="@doctype='spec'"/>
			<xsl:otherwise>
				<xsl:copy-of select="node()"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-sections" priority="-1">
		<xsl:for-each select="af:section">
			<xsl:choose>
				<xsl:when test="(@hidden='true' or @hidden='1')">
					<!--Hidden sections are not displayed.-->
				</xsl:when>
				<xsl:when test="(@hidden='false' or @hidden='0')">
					<!--Force visible (explicitly specified)-->
					<xsl:call-template name="t-section"/>
				</xsl:when>
				<xsl:when test="substring(@name, 1, 1) != '.'">
					<!--Default mode, sections start with dot(.) are ignored-->
					<xsl:call-template name="t-section"/>
				</xsl:when>
				<xsl:otherwise>
					<!--.(dot-prefix) default hidden sections are not displayed.-->
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-section" match="af:section" priority="-1">
		<!--Anchor #@section-name-->
		<xsl:variable name="name">
			<xsl:choose>
				<xsl:when test="@name">
					<xsl:value-of select="@name"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('section_', position())"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:element name="a">
			<xsl:attribute name="name"><xsl:value-of select="$name"/></xsl:attribute>
		</xsl:element>
		<xsl:if test="@name">
			<h2>
				<xsl:value-of select="@name"/>
			</h2>
		</xsl:if>
		<table>
			<xsl:for-each select="*">
				<xsl:choose>
					<xsl:when test="namespace-uri()=$af-uri">
						<xsl:variable name="tag" select="local-name()"/>
						<!--by tag names-->
						<xsl:choose>
							<xsl:when test="(@hidden='true' or @hidden='1')">
								<!--Ignore-->
							</xsl:when>
							<xsl:when test="$tag='scalar'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='list'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='map'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='table'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='user'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='error'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
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
							<xsl:when test="$tag='form'">
								<tr>
									<td colspan="2">
										<xsl:if test="$show-type">
											<span class="data-type">Form </span>
											<xsl:call-template name="t-name"/>
										</xsl:if>
										<!--if $show-type isn't specified, the name will appeared in the legend of form-->
									</td>
								</tr>
								<tr>
									<td class="indent"/>
									<td>
										<xsl:call-template name="t-form"/>
									</td>
								</tr>
							</xsl:when>
							<xsl:when test="$tag='doc'">
								<tr>
									<td colspan="2">
										<xsl:call-template name="t-doc"/>
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
										<xsl:call-template name="t-error-xml">
											<xsl:with-param name="text" select="concat('unexpected section element of meta-type: ', $tag)"/>
										</xsl:call-template>
									</td>
								</tr>
							</xsl:otherwise>
						</xsl:choose>
						<tr>
							<td colspan="2" style="data-separator"/>
						</tr>
					</xsl:when>
					<xsl:otherwise>
						<!--Ignore elements of other namespace-->
					</xsl:otherwise>
				</xsl:choose>
			</xsl:for-each>
		</table>
		<hr/>
	</xsl:template>
	<!--DATA ELEMENTS-->
	<xsl:template name="t-typed-text">
		<xsl:param name="type" select="@type"/>
		<xsl:param name="text" select="text()"/>
		<xsl:choose>
			<xsl:when test="$type='link'">
				<xsl:element name="a">
					<xsl:attribute name="href"><xsl:value-of select="$text"/></xsl:attribute>
					<xsl:value-of select="$text"/>
				</xsl:element>
			</xsl:when>
			<xsl:when test="$type='image'">
				<xsl:element name="img">
					<xsl:attribute name="src"><xsl:value-of select="$text"/></xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:when test="$type='sound'">
				<xsl:element name="sound">
					<xsl:attribute name="src"><xsl:value-of select="$text"/></xsl:attribute>
				</xsl:element>
			</xsl:when>
			<xsl:when test="$type='test'">
				<xsl:element name="span">
					<xsl:attribute name="class">vt-test</xsl:attribute>
					<xsl:value-of select="$text"/>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$text"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-scalar" match="af:scalar" priority="-1">
		<span>
			<xsl:call-template name="t-typed-text"/>
		</span>
		<xsl:for-each select="af:method">
			<xsl:value-of select="' '"/>
			<xsl:call-template name="t-method-a"/>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-list" match="af:list" priority="-1">
		<table>
			<xsl:for-each select="af:item">
				<xsl:variable name="index" select="position()"/>
				<tr>
					<td>
						<xsl:value-of select="$index"/>. </td>
					<td>
						<span class="list">
							<xsl:call-template name="t-typed-text">
								<xsl:with-param name="type" select="../@type"/>
							</xsl:call-template>
						</span>
					</td>
					<td>
						<xsl:for-each select="../af:method">
							<xsl:value-of select="' '"/>
							<xsl:call-template name="t-method-a">
								<xsl:with-param name="params" select="concat('&amp;.index=', $index)"/>
							</xsl:call-template>
						</xsl:for-each>
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
	<xsl:template name="t-map" match="af:map" priority="-1">
		<table>
			<thead>
				<tr>
					<th/>
					<th>Key</th>
					<th>Value</th>
					<xsl:for-each select="af:method">
						<th>
							<!--Place Holder-->
							<xsl:value-of select="@name"/>
						</th>
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
								<xsl:call-template name="t-typed-text">
									<xsl:with-param name="type" select="../@type"/>
								</xsl:call-template>
							</span>
						</td>
						<xsl:for-each select="../af:method">
							<td>
								<xsl:value-of select="' '"/>
								<xsl:call-template name="t-method-a">
									<xsl:with-param name="params" select="concat('&amp;.key=', $key)"/>
								</xsl:call-template>
							</td>
						</xsl:for-each>
					</tr>
				</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
	<xsl:template name="t-table" match="af:table" priority="-1">
		<table>
			<thead>
				<tr>
					<!--Sort...-->
					<xsl:for-each select="af:column">
						<th>
							<xsl:value-of select="@name"/>
							<xsl:if test="(@primary-key='true' or @primary-key='1')">(K)</xsl:if>
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
						<xsl:for-each select="../af:column[(@primary-key='true' or @primary-key='1')]">&amp;<xsl:value-of select="@name"/>=<xsl:value-of select="$row/*[local-name()=current()/@name]/text()"/>
						</xsl:for-each>
					</xsl:variable>
					<tr>
						<xsl:for-each select="*">
							<xsl:variable name="column" select="../../af:column[@name=local-name(current())]"/>
							<td>
								<span class="table">
									<xsl:call-template name="t-typed-text">
										<xsl:with-param name="type" select="$column/@type"/>
									</xsl:call-template>
								</span>
							</td>
						</xsl:for-each>
						<xsl:for-each select="../af:method">
							<td>
								<xsl:value-of select="' '"/>
								<xsl:call-template name="t-method-a">
									<xsl:with-param name="params" select="$pk"/>
								</xsl:call-template>
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
				<xsl:value-of select="' '"/>
				<xsl:call-template name="t-method-a">
					<xsl:with-param name="params" select="concat('&amp;.path=', $path)"/>
				</xsl:call-template>
			</xsl:for-each>
		</div>
		<xsl:if test="*[namespace-uri()!=$af-uri]">
			<table>
				<xsl:for-each select="*[namespace-uri() != $af-uri]">
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
	<xsl:template name="t-user" match="af:user" priority="-1">
		<div class="user">
			<xsl:call-template name="t-dump">
				<xsl:with-param name="path" select="@name"/>
				<xsl:with-param name="owner" select="."/>
			</xsl:call-template>
		</div>
	</xsl:template>
	<xsl:template name="t-error" match="af:error" priority="-1">
		<xsl:param name="inside" select="false()"/>
		<xsl:choose>
			<xsl:when test="$inside">
				<xsl:if test="@time">
					<tr>
						<td colspan="2" class="errorfield-name">Error Time</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@time"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="@provider">
					<tr>
						<td colspan="2" class="errorfield-name">Error Provider</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@provider"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="@type">
					<tr>
						<td colspan="2" class="errorfield-name">Error Type</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@type"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="@name">
					<tr>
						<td colspan="2" class="errorfield-name">Error Name</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@name"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="@text">
					<tr>
						<td colspan="2" class="errorfield-name">Error Overview</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@text"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="@detail">
					<tr>
						<td colspan="2" class="errorfield-name">Error Detail</td>
					</tr>
					<tr>
						<td class="indent"/>
						<td class="errorfield-value">
							<xsl:value-of select="@detail"/>
						</td>
					</tr>
				</xsl:if>
				<xsl:if test="af:error-source">
					<xsl:for-each select="af:error-source">
						<tr>
							<td colspan="2" class="errorfield-name">Error Source</td>
						</tr>
						<tr>
							<td class="indent"/>
							<td class="errorfield-value">
								<xsl:value-of select="@name"/>
								<xsl:if test="@status">
									<br/>
									<code>
										<xsl:value-of select="@status"/>
									</code>
								</xsl:if>
							</td>
						</tr>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="af:error">
					<xsl:for-each select="af:error">
						<hr/>
						<xsl:call-template name="t-error">
							<xsl:with-param name="inside" select="true()"/>
						</xsl:call-template>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="af:doc">
					<tr>
						<td colspan="2">
							<xsl:for-each select="af:doc">
								<hr/>
								<xsl:call-template name="t-doc"/>
							</xsl:for-each>
						</td>
					</tr>
				</xsl:if>
				<tr>
					<td colspan="2" align="center">
						<hr/>
						<span class="method">
							<a href="javascript: location.reload(); ">Reload</a>
						</span> | <span class="method">
							<a href="javascript: history.back()">Go Back</a>
						</span>
						<xsl:for-each select="af:method"> | <span class="method">
								<xsl:call-template name="t-method-a"/>
							</span>
						</xsl:for-each>
					</td>
				</tr>
			</xsl:when>
			<xsl:otherwise>
				<table class="error">
					<xsl:call-template name="t-error">
						<xsl:with-param name="inside" select="true()"/>
					</xsl:call-template>
				</table>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!--FORM ELEMENTS-->
	<xsl:template name="t-form" match="af:form" priority="-1">
		<xsl:variable name="form-index">
			<xsl:call-template name="t-node-index">
				<xsl:with-param name="select" select="//af:form"/>
				<xsl:with-param name="node" select="."/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="form-id" select="concat('form_', $form-index)"/>
		<fieldset>
			<xsl:if test="@name">
				<legend>
					<xsl:value-of select="@name"/>
				</legend>
			</xsl:if>
			<xsl:element name="form">
				<xsl:attribute name="id"><xsl:value-of select="$form-id"/></xsl:attribute>
				<xsl:attribute name="action"><xsl:call-template name="t-hint-inherit"/></xsl:attribute>
				<xsl:attribute name="onsubmit">javascript: return zlw_af_form_submit(event); </xsl:attribute>
				<xsl:if test="@form-method">
					<xsl:attribute name="method"><xsl:value-of select="@form-method"/></xsl:attribute>
				</xsl:if>
				<!--INPUT .form-->
				<xsl:element name="input">
					<xsl:attribute name="type">hidden</xsl:attribute>
					<xsl:attribute name="name">.form</xsl:attribute>
					<xsl:attribute name="value"><xsl:value-of select="$form-id"/></xsl:attribute>
				</xsl:element>
				<!--INPUT .method-->
				<xsl:element name="input">
					<xsl:attribute name="type">hidden</xsl:attribute>
					<xsl:attribute name="name">.method</xsl:attribute>
					<xsl:attribute name="value"/>
				</xsl:element>
				<table border="0">
					<xsl:for-each select="*">
						<xsl:choose>
							<xsl:when test="namespace-uri()=$af-uri">
								<xsl:variable name="tag" select="local-name()"/>
								<xsl:choose>
									<xsl:when test="$tag='input'">
										<tr>
											<td>
												<xsl:call-template name="t-name"/>
											</td>
											<td>
												<xsl:call-template name="t-input"/>
											</td>
										</tr>
									</xsl:when>
									<xsl:when test="$tag='selector'">
										<tr>
											<td>
												<xsl:call-template name="t-name"/>
											</td>
											<td>
												<xsl:call-template name="t-selector"/>
											</td>
										</tr>
									</xsl:when>
									<xsl:when test="$tag='method'">
										<!--Merged at bottom. -->
									</xsl:when>
									<xsl:when test="$tag='doc'">
										<xsl:call-template name="t-doc"/>
									</xsl:when>
									<xsl:otherwise>
										<tr>
											<td colspan="2">
												<xsl:call-template name="t-error-xml">
													<xsl:with-param name="text" select="concat('unexpected form input of meta-type: ', $tag)"/>
												</xsl:call-template>
											</td>
										</tr>
									</xsl:otherwise>
								</xsl:choose>
							</xsl:when>
							<xsl:otherwise>
								<!--Ignore elements of other namespace-->
							</xsl:otherwise>
						</xsl:choose>
					</xsl:for-each>
					<tr>
						<td colspan="2">
							<xsl:if test="../af:input | ../af:selector">
								<input type="reset" value="Reset"/>
							</xsl:if>
							<xsl:for-each select="af:method">
								<xsl:value-of select="' '"/>
								<xsl:call-template name="t-method">
									<xsl:with-param name="form-id" select="$form-id"/>
								</xsl:call-template>
							</xsl:for-each>
						</td>
					</tr>
				</table>
			</xsl:element>
		</fieldset>
		<!--For Type-Checking and Constraint-Checking: -->
		<xsl:if test="*/@type or */af:constraint">
			<xsl:element name="script">
				<xsl:attribute name="language">javascript</xsl:attribute>
				<!--1, Prepare variables-->
				<xsl:value-of select="concat('var form = document.getElementById(&quot;', $form-id, '&quot;)', '; &#10;')"/>
				<xsl:value-of select="concat('var c', '; &#10;')"/>
				<!--2, Add information for checking-->
				<xsl:for-each select="*">
					<xsl:if test="namespace-uri()=$af-uri">
						<xsl:variable name="input-name">
							<xsl:choose>
								<xsl:when test="@name">
									<xsl:value-of select="@name"/>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="concat('input_', position())"/>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:variable>
						<!--2.1, Bind af-type-->
						<xsl:if test="@type">
							<xsl:value-of select="concat('form[&quot;', $input-name, '&quot;].af_type = &quot;', @type, '&quot;', '; &#10;')"/>
						</xsl:if>
						<!--2.2, Build constraints tree-->
						<xsl:if test="af:constraint/*">
							<xsl:call-template name="t-constraints">
								<xsl:with-param name="select" select="af:constraint/*"/>
								<xsl:with-param name="retvar" select="'c'"/>
							</xsl:call-template>
							<xsl:value-of select="concat('form[&quot;', $input-name, '&quot;].af_constraints = c', '; &#10;')"/>
						</xsl:if>
					</xsl:if>
				</xsl:for-each>
			</xsl:element>
		</xsl:if>
	</xsl:template>
	<xsl:template name="t-constraints">
		<xsl:param name="select" select="*"/>
		<xsl:param name="retvar"/>
		<xsl:value-of select="concat($retvar, ' = new Array()', '; &#10;')"/>
		<xsl:for-each select="$select">
			<xsl:call-template name="t-constraint">
				<xsl:with-param name="retvar" select="concat($retvar, '[', position() - 1, ']')"/>
			</xsl:call-template>
			<xsl:if test="local-name(..) = 'constraint'">
				<xsl:call-template name="t-import-attributes">
					<xsl:with-param name="select" select="../@*"/>
					<xsl:with-param name="retvar" select="concat($retvar, '[', position() - 1, ']')"/>
				</xsl:call-template>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="t-constraint">
		<xsl:param name="retvar"/>
		<xsl:variable name="model" select="local-name()"/>
		<xsl:value-of select="concat(
$retvar, ' = new Object()', '; &#10;', 
$retvar, '.model = &quot;', $model, '&quot;', '; &#10;')"/>
		<xsl:call-template name="t-import-attributes">
			<xsl:with-param name="retvar" select="$retvar"/>
		</xsl:call-template>
		<xsl:choose>
			<xsl:when test="$model='range'"/>
			<xsl:when test="$model='pattern'"/>
			<xsl:when test="$model='and' or $model='or' or $model='xor'">
				<xsl:call-template name="t-constraints">
					<xsl:with-param name="retvar" select="concat($retvar, '.item')"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$model='not'">
				<xsl:call-template name="t-constraint">
					<xsl:with-param name="retvar" select="concat($retvar, '.regular')"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="t-error-xml">
					<xsl:with-param name="text" select="concat('unexpected contraint model: ', $model)"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-input" match="af:input" priority="-1">
		<xsl:variable name="input-name">
			<xsl:choose>
				<xsl:when test="@name">
					<xsl:value-of select="@name"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('input_', position())"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="@multiline='true'">
				<xsl:element name="textarea">
					<xsl:attribute name="class">input-m <xsl:if test="(@read-only='true' or @read-only='1')">read-only</xsl:if></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="$input-name"/></xsl:attribute>
					<xsl:if test="(@read-only='true' or @read-only='1')">
						<xsl:attribute name="readonly">readonly</xsl:attribute>
					</xsl:if>
					<xsl:if test="@max-length">
						<!--TEXTAREA doesn't support max-length-->
					</xsl:if>
					<xsl:if test="@init">
						<xsl:value-of select="@init"/>
					</xsl:if>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:element name="input">
					<xsl:attribute name="type">text</xsl:attribute>
					<xsl:attribute name="class">input <xsl:if test="(@read-only='true' or @read-only='1')">read-only</xsl:if></xsl:attribute>
					<xsl:attribute name="name"><xsl:value-of select="$input-name"/></xsl:attribute>
					<xsl:if test="(@read-only='true' or @read-only='1')">
						<xsl:attribute name="readonly">readonly</xsl:attribute>
					</xsl:if>
					<xsl:if test="@max-length">
						<xsl:attribute name="maxlength"><xsl:value-of select="@max-length"/></xsl:attribute>
						<xsl:attribute name="size"><xsl:choose><xsl:when test="number(@max-length) > number($max-size)"><xsl:value-of select="$max-size"/></xsl:when><xsl:otherwise><xsl:value-of select="@max-length"/></xsl:otherwise></xsl:choose></xsl:attribute>
					</xsl:if>
					<xsl:if test="@init">
						<xsl:attribute name="value"><xsl:value-of select="@init"/></xsl:attribute>
					</xsl:if>
				</xsl:element>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-selector" match="af:selector" priority="-1">
		<xsl:choose>
			<xsl:when test="af:list">
				<xsl:call-template name="t-selector-2">
					<xsl:with-param name="type" select="'list'"/>
					<xsl:with-param name="select" select="af:list"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="af:map">
				<xsl:call-template name="t-selector-2">
					<xsl:with-param name="type" select="'map'"/>
					<xsl:with-param name="select" select="af:map"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="af:list-ref">
				<xsl:variable name="name" select="af:list-ref/text()"/>
				<xsl:variable name="select" select="ancestor::af:section/af:list[@name=$name]"/>
				<xsl:choose>
					<xsl:when test="$select">
						<xsl:call-template name="t-selector-2">
							<xsl:with-param name="type" select="'list'"/>
							<xsl:with-param name="select" select="$select"/>
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="t-error-xml">
							<xsl:with-param name="text" select="concat('list(', $name, ') is not existed')"/>
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="af:map-ref">
				<xsl:variable name="name" select="af:map-ref/text()"/>
				<xsl:variable name="select" select="ancestor::af:section/af:map[@name=$name]"/>
				<xsl:choose>
					<xsl:when test="$select">
						<xsl:call-template name="t-selector-2">
							<xsl:with-param name="type" select="'map'"/>
							<xsl:with-param name="select" select="$select"/>
						</xsl:call-template>
					</xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="t-error-xml">
							<xsl:with-param name="text" select="concat('map(', $name, ') is not existed')"/>
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="t-error-xml">
					<xsl:with-param name="text" select="'the collection to be selected cannot be resolved. '"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-selector-2">
		<xsl:param name="type"/>
		<xsl:param name="select"/>
		<xsl:param name="content" select="$select/*[local-name()='item' or local-name()='entry' or local-name()='row']"/>
		<xsl:variable name="input-name">
			<xsl:choose>
				<xsl:when test="@name">
					<xsl:value-of select="@name"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="concat('input_', position())"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="read-only" select="(@read-only='true' or @read-only='1')"/>
		<xsl:variable name="init" select="@init|af:init"/>
		<xsl:choose>
			<xsl:when test="contains('||string|number|', concat('|', $select/@type, '|'))">
				<xsl:element name="select">
					<xsl:attribute name="name"><xsl:value-of select="$input-name"/></xsl:attribute>
					<xsl:choose>
						<xsl:when test="(@multiple='true' or @multiple='1')">
							<xsl:attribute name="multiple">multiple</xsl:attribute>
						</xsl:when>
						<xsl:otherwise>
							<xsl:attribute name="size">1</xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:for-each select="$content">
						<xsl:variable name="key">
							<xsl:choose>
								<xsl:when test="$type='list'">
									<xsl:value-of select="position()"/>
								</xsl:when>
								<xsl:when test="$type='map'">
									<xsl:value-of select="@key"/>
								</xsl:when>
							</xsl:choose>
						</xsl:variable>
						<!--Skip non-init items in read-only mode. -->
						<xsl:if test="not($read-only and $init[.!=$key])">
							<xsl:element name="option">
								<xsl:attribute name="value"><xsl:value-of select="$key"/></xsl:attribute>
								<xsl:if test="$init[.=$key]">
									<xsl:attribute name="selected">selected</xsl:attribute>
								</xsl:if>
								<xsl:choose>
									<xsl:when test="$type='list'">
										<xsl:call-template name="t-all-text"/>
									</xsl:when>
									<xsl:when test="$type='map'">
										<xsl:value-of select="@key"/>: <xsl:call-template name="t-all-text"/>
									</xsl:when>
								</xsl:choose>
							</xsl:element>
						</xsl:if>
					</xsl:for-each>
				</xsl:element>
			</xsl:when>
			<xsl:otherwise>
				<xsl:variable name="input-type">
					<xsl:choose>
						<xsl:when test="(@multiple='true' or @multiple='1')">checkbox</xsl:when>
						<xsl:otherwise>radio</xsl:otherwise>
					</xsl:choose>
				</xsl:variable>
				<ol>
					<xsl:for-each select="$content">
						<xsl:variable name="key">
							<xsl:choose>
								<xsl:when test="$type='list'">
									<xsl:value-of select="position()"/>
								</xsl:when>
								<xsl:when test="$type='map'">
									<xsl:value-of select="@key"/>
								</xsl:when>
							</xsl:choose>
						</xsl:variable>
						<li>
							<xsl:element name="input">
								<xsl:attribute name="name"><xsl:value-of select="$input-name"/></xsl:attribute>
								<xsl:attribute name="type"><xsl:value-of select="$input-type"/></xsl:attribute>
								<xsl:attribute name="value"><xsl:value-of select="$key"/></xsl:attribute>
								<xsl:if test="$read-only">
									<xsl:attribute name="readonly">readonly</xsl:attribute>
									<xsl:attribute name="class">read-only</xsl:attribute>
								</xsl:if>
								<xsl:if test="$init[.=$key]">
									<xsl:attribute name="checked">checked</xsl:attribute>
								</xsl:if>
							</xsl:element>
							<xsl:call-template name="t-typed-text">
								<xsl:with-param name="type" select="$select/@type"/>
							</xsl:call-template>
						</li>
					</xsl:for-each>
				</ol>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-method" match="af:method[local-name(..)='form']" priority="-1">
		<xsl:param name="form-id"/>
		<xsl:param name="name" select="concat('.method_', position())"/>
		<xsl:element name="input">
			<xsl:attribute name="type">submit</xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="$name"/></xsl:attribute>
			<xsl:attribute name="value"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:attribute name="onclick">javascript: var form = document.getElementById('<xsl:value-of select="$form-id"/>'); <xsl:if test="@hint or af:method-parameter"><!--Out-going??-->form.action = '<xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs"><xsl:for-each select="af:method-parameter"><xsl:value-of select="concat('&amp;', @name, '=', text())"/></xsl:for-each></xsl:with-param></xsl:call-template>'; </xsl:if>form['.method'].value = form['<xsl:value-of select="$name"/>'].value; form.submit(); </xsl:attribute>
		</xsl:element>
	</xsl:template>
	<xsl:template name="t-method-a" match="af:method[local-name(..)!='form']" priority="-1">
		<xsl:param name="params" select="''"/>
		<span class="method">
			<xsl:element name="a">
				<xsl:attribute name="href"><xsl:call-template name="t-href-concat"><xsl:with-param name="lhs"><xsl:call-template name="t-hint-with-hold"/></xsl:with-param><xsl:with-param name="rhs"><xsl:value-of select="concat('.method=', @name, $params)"/><xsl:for-each select="af:method-parameter"><xsl:value-of select="concat('&amp;', @name, '=', text())"/></xsl:for-each></xsl:with-param></xsl:call-template></xsl:attribute>
				<xsl:value-of select="@name"/>
			</xsl:element>
		</span>
	</xsl:template>
	<!--Extension-Points-->
	<xsl:template name="t-html-header" priority="-1"/>
	<xsl:template name="t-html-body" priority="-1">
		<h1>
			<xsl:value-of select="$title"/>
		</h1>
		<hr/>
		<xsl:call-template name="t-sections"/>
		<xsl:call-template name="t-copyright"/>
	</xsl:template>
</xsl:stylesheet>
