<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet

version="1.0"

xmlns:xsl="http://www.w3.org/1999/XSL/Transform"

xmlns:fo="http://www.w3.org/1999/XSL/Format"

xmlns:g="http://www.bodz.net/xml/draft/gdml"

xmlns:xalan="http://xml.apache.org/xalan"

xmlns:xslapi="xalan://freejava.xml.xsl.SimpleXsltApi"

xmlns:redirect="http://xml.apache.org/xalan/redirect"

extension-element-prefixes="xslapi redirect"

>

	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>

	

	<xsl:template match="/">

		<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" elementFormDefault="qualified" attributeFormDefault="unqualified">

		</xs:schema>

	</xsl:template>

	

</xsl:stylesheet>
