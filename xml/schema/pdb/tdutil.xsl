<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:fn="http://www.w3.org/2004/07/xpath-functions" xmlns:xdt="http://www.w3.org/2004/07/xpath-datatypes">
	<xsl:param name="sql.stmtend" select="';'"/>
	<xsl:param name="sql.nameq.left" select="'['"/>
	<xsl:param name="sql.cstyle" select="'end'"/>
	<xsl:param name="sql.nameq.right" select="']'"/>
	<xsl:param name="sql.nulldef" select="'null'"/>
	<xsl:variable name="cset.lc" select="'abcdefghijklmnopqrstuvwxyz'"/>
	<xsl:variable name="cset.uc" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
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
	<xsl:template name="t-nameq">
		<xsl:param name="name" select="text()"/>
		<xsl:param name="left" select="$sql.nameq.left"/>
		<xsl:param name="right" select="$sql.nameq.right"/>
		<xsl:variable name="cname" select="concat(' ', translate($name, 
  'abcdefghijklmnopqrstuvwxyz', 
  'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 
' ')"/>
		<xsl:variable name="keyword" select="contains('
 ADD ALL ALTER AND ANY AS ASC AVG BEGIN 
 BETWEEN BREAK BROWSE BULK BY CASE CHECK 
 CHECKPOINT CLOSE CLUSTERED COALESCE 
 COMMIT COMMITTED COMPUTE CONFIRM 
 CONSTRAINT CONTINUE CONTROLROW CONVERT 
 COUNT CREATE CURRENT CURRENT_DATE 
 CURRENT_TIME CURRENT_TIMESTAMP 
 CURRENT_USER CURSOR DATABASE DBCC 
 DEALLOCATE DECLARE DEFAULT DELETE DESC 
 DISK DISTINCT DOUBLE DROP DUMMY DUMP 
 ELSE END ERRLVL ERROREXIT EXCEPT EXEC 
 EXECUTE EXISTS EXIT FETCH FILLFACTOR 
 FLOPPY FOR FOREIGN FROM GOTO GRANT 
 GROUP HAVING HOLDLOCK IDENTITY 
 IDENTITY_INSERT IDENTITYCOL IF IN INDEX 
 INSENSITIVE INSERT INTERSECT INTO IS 
 ISOLATION KEY KILL LEVEL LIKE LINENO 
 LOAD MAX MIN MIRROREXIT NOCHECK 
 NONCLUSTERED NOT NULL NULLIF OF OFF 
 OFFSETS ON ONCE  ONLY OPEN OPTION OR 
 ORDER OVER PERM PERMANENT PIPE PLAN 
 PRECISION PREPARE PRIMARY PRINT PROC 
 PROCEDURE PROCESSEXIT PUBLIC RAISERROR 
 READ RECONFIGURE REFERENCES REPEATABLE 
 REPLICATION RETURN REVOKE ROLLBACK 
 ROWCOUNT RULE SAVE SCROLL SELECT 
 SERIALIZABLE SESSION_USER SET SETUSER 
 SHUTDOWN SOME STATISTICS SUM 
 SYSTEM_USER TABLE TAPE TEMP TEMPORARY 
 TEXTSIZE THEN TO TRAN TRANSACTION 
 TRIGGER TRUNCATE TSEQUAL UNCOMMITTED 
 UNION UNIQUE UPDATE UPDATETEXT USE USER 
 VALUES VARYING VIEW WAITFOR WHEN WHERE 
 WHILE WITH WRITETEXT 

 FILE OUTER ', 
$cname)"/>
		<xsl:choose>
			<xsl:when test="$keyword">
				<xsl:value-of select="concat($left, $name, $right)"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$name"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-nameq-list">
		<xsl:param name="list" select="text()"/>
		<xsl:param name="sep" select="','"/>
		<xsl:choose>
			<xsl:when test="contains($list, $sep)">
				<xsl:call-template name="t-nameq">
					<xsl:with-param name="name">
						<xsl:value-of select="normalize-space(substring-before($list, $sep))"/>
					</xsl:with-param>
				</xsl:call-template>
				<xsl:value-of select="$sep"/>
				<xsl:variable name="rest" select="normalize-space(substring-after($list, $sep))"/>
				<xsl:if test="$rest">
					<xsl:call-template name="t-nameq-list">
						<xsl:with-param name="list" select="$rest"/>
						<xsl:with-param name="sep" select="$sep"/>
					</xsl:call-template>
				</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="t-nameq">
					<xsl:with-param name="name">
						<xsl:value-of select="normalize-space($list)"/>
					</xsl:with-param>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-guessdt">
		<xsl:param name="type" select="text()"/>
		<xsl:choose>
			<xsl:when test="$type = 'bit'">b</xsl:when>
			<xsl:when test="contains($type, 'bool')">b</xsl:when>
			<xsl:when test="contains($type, 'char')">s</xsl:when>
			<xsl:when test="contains($type, 'text')">s</xsl:when>
			<xsl:when test="contains($type, 'clob')">s</xsl:when>
			<xsl:when test="contains($type, 'date')">d</xsl:when>
			<xsl:when test="contains($type, 'number')">n</xsl:when>
			<xsl:when test="$type = 'byte'">n</xsl:when>
			<xsl:when test="$type = 'short'">n</xsl:when>
			<xsl:when test="$type = 'long'">n</xsl:when>
			<xsl:when test="contains($type, 'int')">n</xsl:when>
			<xsl:when test="contains($type, 'float')">n</xsl:when>
			<xsl:when test="contains($type, 'double')">n</xsl:when>
			<xsl:when test="contains($type, 'float')">n</xsl:when>
			<xsl:when test="contains($type, 'decimal')">n</xsl:when>
			<xsl:when test="contains($type, 'numeric')">n</xsl:when>
			<xsl:when test="contains($type, 'currency')">n</xsl:when>
			<xsl:when test="contains($type, 'money')">n</xsl:when>
			<xsl:when test="contains($type, 'binary')">z</xsl:when>
			<xsl:when test="contains($type, 'image')">z</xsl:when>
			<xsl:otherwise>?</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-nullprop">
		<xsl:param name="props"/>
		<xsl:variable name="lc">
			<xsl:call-template name="t-lc">
				<xsl:with-param name="text" select="$props"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="norm" select="normalize-space($lc)"/>
		<xsl:choose>
			<xsl:when test="contains($norm, 'not null')">not null</xsl:when>
			<xsl:when test="contains($norm, 'primary key')">not null</xsl:when>
			<xsl:when test="contains($norm, 'null')">null</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$sql.nulldef"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-javatype">
		<xsl:param name="type" select="text()"/>
		<xsl:param name="nullable" select="false()"/>
		<xsl:variable name="dt">
			<xsl:call-template name="t-guessdt">
				<xsl:with-param name="type" select="$type"/>
			</xsl:call-template>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="$dt = 's'">String</xsl:when>
			<xsl:when test="$dt = 'n'">
				<xsl:choose>
					<xsl:when test="contains($type, 'double') and $nullable">Double</xsl:when>
					<xsl:when test="contains($type, 'double')">double</xsl:when>
					<xsl:when test="contains($type, 'float') and $nullable">Float</xsl:when>
					<xsl:when test="contains($type, 'float')">float</xsl:when>
					<xsl:when test="(contains($type, 'large')
or contains($type, 'big')
or contains($type, 'long'))
and $nullable">Long</xsl:when>
					<xsl:when test="(contains($type, 'large')
or contains($type, 'big')
or contains($type, 'long'))">long</xsl:when>
					<xsl:when test="contains($type, 'short') and $nullable">Short</xsl:when>
					<xsl:when test="contains($type, 'short')">short</xsl:when>
					<xsl:when test="(contains($type, 'tiny')
or $type = 'byte')
and $nullable">Integer</xsl:when>
					<xsl:when test="(contains($type, 'tiny')
or $type = 'byte')">int</xsl:when>
					<xsl:when test="contains($type, 'int') and $nullable">Integer</xsl:when>
					<xsl:when test="contains($type, 'int')">int</xsl:when>
					<xsl:when test="$nullable">Double</xsl:when>
					<xsl:otherwise>double</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:when test="$dt = 'b' and $nullable">Boolean</xsl:when>
			<xsl:when test="$dt = 'b'">boolean</xsl:when>
			<xsl:when test="$dt = 'd'">java.util.Date</xsl:when>
			<xsl:when test="$dt = 'z'">java.io.InputStream</xsl:when>
			<xsl:otherwise>Object</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-addslashes">
		<xsl:param name="str"/>
		<xsl:variable name="str1">
			<xsl:call-template name="t-replace-all">
				<xsl:with-param name="text" select="$str"/>
				<xsl:with-param name="pattern">\</xsl:with-param>
				<xsl:with-param name="replacement">\\</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="str2">
			<xsl:call-template name="t-replace-all">
				<xsl:with-param name="text" select="$str1"/>
				<xsl:with-param name="pattern">"</xsl:with-param>
				<xsl:with-param name="replacement">\"</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="str3">
			<xsl:call-template name="t-replace-all">
				<xsl:with-param name="text" select="$str2"/>
				<xsl:with-param name="pattern" select="'&#10;'"/>
				<xsl:with-param name="replacement">\n</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:variable name="str4">
			<xsl:call-template name="t-replace-all">
				<xsl:with-param name="text" select="$str3"/>
				<xsl:with-param name="pattern" select="'&#13;'"/>
				<xsl:with-param name="replacement">\r</xsl:with-param>
			</xsl:call-template>
		</xsl:variable>
		<xsl:value-of select="$str4"/>
	</xsl:template>
	<xsl:template name="t-capitalize">
		<xsl:param name="text" select="text()"/>
		<xsl:choose>
			<xsl:when test="contains($text, '_')">
				<xsl:variable name="bef" select="substring-before($text, '_')"/>
				<xsl:variable name="aft" select="substring-after($text, '_')"/>
				<xsl:choose>
					<xsl:when test="$bef = ''">
						<xsl:value-of select="'_'"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="concat(translate(substring($bef, 1, 1), $cset.lc, $cset.uc), substring($bef, 2))"/>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:call-template name="t-capitalize">
					<xsl:with-param name="text" select="$aft"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="concat(translate(substring($text, 1, 1), $cset.lc, $cset.uc), substring($text, 2))"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-capid">
		<xsl:param name="text" select="text()"/>
		<xsl:param name="def" select="concat('_', position())"/>
		<xsl:choose>
			<xsl:when test="$text = ''">
				<xsl:value-of select="$def"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:call-template name="t-capitalize">
					<xsl:with-param name="text" select="$text"/>
				</xsl:call-template>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template name="t-lc">
		<xsl:param name="text" select="text()"/>
		<xsl:value-of select="translate($text, $cset.uc, $cset.lc)"/>
	</xsl:template>
	<xsl:template name="t-uc">
		<xsl:param name="text" select="text()"/>
		<xsl:value-of select="translate($text, $cset.lc, $cset.uc)"/>
	</xsl:template>
	<xsl:template name="t-lcfirst">
		<xsl:param name="text" select="text()"/>
		<xsl:value-of select="concat(translate(substring($text, 1, 1), $cset.uc, $cset.lc), substring($text, 2))"/>
	</xsl:template>
	<xsl:template name="t-ucfirst">
		<xsl:param name="text" select="text()"/>
		<xsl:value-of select="concat(translate(substring($text, 1, 1), $cset.lc, $cset.uc), substring($text, 2))"/>
	</xsl:template>
	<xsl:template name="t-basename">
		<xsl:param name="path"/>
		<xsl:variable name="t">
			<xsl:choose>
				<xsl:when test="contains($path, '/')">
					<xsl:value-of select="substring-after($path, '/')"/>
				</xsl:when>
				<xsl:when test="contains($path, '\')">
					<xsl:value-of select="substring-after($path, '\')"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$path"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:choose>
			<xsl:when test="contains($t, '/') or contains($t, '\')">
				<xsl:call-template name="t-basename">
					<xsl:with-param name="path" select="$t"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$t"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
