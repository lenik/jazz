<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2004/07/xpath-functions" xmlns:xdt="http://www.w3.org/2004/07/xpath-datatypes" xmlns:td="TableDef" xmlns:pdb="http://xml.bodz.net/schema/pdb" xmlns:u="http://xml.bodz.net/schema/pdb/user">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/td:Section">
		<xsl:element namespace="http://xml.bodz.net/schema/pdb" name="pdb">
			<xsl:attribute namespace="http://www.w3.org/2001/XMLSchema-instance" name="schemaLocation">http://xml.bodz.net/schema/pdb http://xml.bodz.net/schema/pdb/pdb.xsd</xsl:attribute>
			<xsl:attribute name="name" select="@name"/>
			<xsl:attribute name="label" select="concat(@title, td:Title)"/>
			<xsl:apply-templates select="td:Table"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="td:Table">
		<xsl:element namespace="http://xml.bodz.net/schema/pdb" name="table">
			<xsl:attribute name="name" select="td:Name"/>
			<xsl:attribute name="label" select="td:Comment"/>
			<xsl:if test="contains(td:Comment, '[典]')">
				<xsl:attribute name="opts" select="'D'"/>
			</xsl:if>
			<xsl:apply-templates select="td:Field | td:Comment"/>
			<xsl:apply-templates select="td:Row"/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="td:Field">
		<xsl:element namespace="http://xml.bodz.net/schema/pdb" name="field">
			<xsl:attribute name="name" select="td:Name"/>
			<xsl:attribute name="label" select="td:Tip"/>
			<xsl:attribute name="type"><xsl:variable name="type"><xsl:call-template name="t-lc"><xsl:with-param name="text"><xsl:choose><xsl:when test="contains(td:Type, '(')"><xsl:value-of select="normalize-space(
substring-before(td:Type, '('))"/></xsl:when><xsl:otherwise><xsl:value-of select="normalize-space(td:Type)"/></xsl:otherwise></xsl:choose></xsl:with-param></xsl:call-template></xsl:variable><xsl:variable name="param" select="normalize-space(
substring-before(
substring-after(td:Type, '('), ')'))"/><xsl:choose><xsl:when test="$type = 'bool' or 
$type = 'boolean'">b</xsl:when><xsl:when test="$type = 'bit'">B</xsl:when><xsl:when test="$type = 'byte'">I1</xsl:when><xsl:when test="$type = 'short'">I2</xsl:when><xsl:when test="$type = 'int'">I4</xsl:when><xsl:when test="$type = 'long'">I8</xsl:when><xsl:when test="$type = 'float'">F4</xsl:when><xsl:when test="$type = 'double'">F8</xsl:when><xsl:when test="$type='datetime'">T</xsl:when><xsl:when test="$type='date'">Td</xsl:when><xsl:when test="$type='time'">Tt</xsl:when><xsl:when test="$type='timestamp'">Ts</xsl:when><xsl:when test="$type = 'currency' or 
$type = 'money'">N[20, 2]</xsl:when><xsl:when test="$type = 'decimal' or
$type = 'numeric'"><xsl:value-of select="concat('N[', $param, ']')"/></xsl:when><xsl:when test="$type = 'char'"><xsl:value-of select="concat('C[', $param, ']')"/></xsl:when><xsl:when test="$type = 'varchar' or 
$type = 'longvarchar'"><xsl:value-of select="concat('C[, ', $param, ']')"/></xsl:when><xsl:when test="$type = 'nchar'"><xsl:value-of select="concat('U[', $param, ']')"/></xsl:when><xsl:when test="$type = 'nvarchar' or 
$type = 'nlongvarchar'"><xsl:value-of select="concat('U[, ', $param, ']')"/></xsl:when><xsl:when test="$type = 'text' or $type='clob'">C[,]</xsl:when><xsl:when test="$type = 'binary'"><xsl:value-of select="concat('B[', $param, ']')"/></xsl:when><xsl:when test="$type = 'varbinary'"><xsl:value-of select="concat('B[, ', $param, ']')"/></xsl:when><xsl:when test="$type = 'blob' or
$type = 'image'">B[,]</xsl:when><xsl:when test="$type = 'object'">O</xsl:when><xsl:otherwise>?</xsl:otherwise></xsl:choose></xsl:attribute>
			<xsl:variable name="opts">
				<xsl:variable name="props">
					<xsl:call-template name="t-lc">
						<xsl:with-param name="text" select="normalize-space(td:Props)"/>
					</xsl:call-template>
				</xsl:variable>
				<xsl:if test="td:Format">
					<xsl:comment select="concat('format is not supported: ', td:Comment)"/>
				</xsl:if>
				<xsl:choose>
					<xsl:when test="contains($props, 'not null')"/>
					<xsl:otherwise>N</xsl:otherwise>
				</xsl:choose>
				<xsl:if test="contains($props, 'default')">Dx(<xsl:value-of>
						<xsl:variable name="default_" select="substring-after($props, 'default ')"/>
						<xsl:choose>
							<xsl:when test="contains($default_, ' ')">
								<xsl:value-of select="substring-before($default_, ' ')"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$default_"/>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:value-of>)</xsl:if>
				<xsl:if test="contains($props, 'unique')">U</xsl:if>
				<xsl:if test="contains($props, 'identity')">Di</xsl:if>
				<xsl:variable name="refstr"/>
				<xsl:if test="contains(td:Description, '->')">
					<xsl:variable name="quant" select="normalize-space(substring-before(td:Description, '->'))"/>
					<xsl:variable name="remote_" select="normalize-space(substring-after(td:Description, '->'))"/>
					<xsl:variable name="remote">
						<xsl:choose>
							<xsl:when test="contains($remote_, ' ')">
								<xsl:value-of select="substring-before($remote_, ' ')"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$remote_"/>
							</xsl:otherwise>
						</xsl:choose>
					</xsl:variable>
					<xsl:value-of select="concat('F(', $quant, ' ⇒ ', $remote, ')')"/>
				</xsl:if>
			</xsl:variable>
			<xsl:if test="string-length($opts) != 0">
				<xsl:attribute name="opts" select="$opts"/>
			</xsl:if>
			<xsl:if test="string-length(td:Description) != 0">
				<xsl:attribute name="doc" select="td:Description"/>
			</xsl:if>
		</xsl:element>
	</xsl:template>
	<xsl:template match="td:Props">
		<xsl:comment>Inline sql to define field or index isn't supported: <xsl:value-of select="text()"/>
		</xsl:comment>
	</xsl:template>
	<xsl:template match="td:Comment"/>
	<xsl:template match="td:Row">
		<xsl:element namespace="http://xml.bodz.net/schema/pdb" name="instance">
			<xsl:for-each select="*">
				<xsl:element namespace="http://xml.bodz.net/schema/pdb" name="{ local-name() }">
					<xsl:value-of select="text()"/>
				</xsl:element>
			</xsl:for-each>
		</xsl:element>
	</xsl:template>
	<xsl:variable name="cset.lc" select="'abcdefghijklmnopqrstuvwxyz'"/>
	<xsl:variable name="cset.uc" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
	<xsl:template name="t-lc">
		<xsl:param name="text" select="text()"/>
		<xsl:value-of select="translate($text, $cset.uc, $cset.lc)"/>
	</xsl:template>
</xsl:stylesheet>
