<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XMLSpy v2005 U (http://www.xmlspy.com) by Snima Denik (Bytes of Danci.Z) -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>Test of commons-string.xsl</title>
            </head>
            <body>
                <h1>Test Suite</h1>
                <hr/>
                <xsl:call-template name="test-suite"/>
                <hr/>
                <cite>(C)Copyright www.bodz.net, 2004</cite>
            </body>
        </html>
    </xsl:template>
    <xsl:template name="test-suite">
        <xsl:call-template name="test-token-replace-delim"/>
        <xsl:call-template name="test-token-replace-sibling"/>
        <xsl:call-template name="test-token-reverse"/>
        <xsl:call-template name="test-token-trailer"/>
        <xsl:call-template name="test-token-leaders"/>
        <xsl:call-template name="test-substring-reverse"/>
    </xsl:template>
    <xsl:template name="test-substring-reverse">
        <h2>substring-reverse</h2>
        <ul>
            <li>
                <strong>null</strong>: 
            <xsl:call-template name="substring-reverse">
                    <xsl:with-param name="str" select="null"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'hello, world'</strong>: 
            <xsl:call-template name="substring-reverse">
                    <xsl:with-param name="str" select="'hello, world'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'   every thing is possible    '</strong>: 
            <xsl:call-template name="substring-reverse">
                    <xsl:with-param name="str" select="'   every thing is possible    '"/>
                </xsl:call-template>
            </li>
        </ul>
    </xsl:template>
    <xsl:template name="test-token-leaders">
        <h2>token-leaders</h2>
        <ul>
            <li>
                <strong>null</strong>: 
            <xsl:call-template name="token-leaders">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="null"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'singleword'</strong>: 
            <xsl:call-template name="token-leaders">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'singleword'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'only-twice'</strong>: 
            <xsl:call-template name="token-leaders">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'only-twice'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'what-if-four-segments'</strong>: 
            <xsl:call-template name="token-leaders">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'what-if-four-segments'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'let.s-see-some---strange--ones-'</strong>: 
            <xsl:call-template name="token-leaders">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'let.s-see-some---strange--ones-'"/>
                </xsl:call-template>
            </li>
        </ul>
    </xsl:template>
    <xsl:template name="test-token-trailer">
        <h2>token-trailer</h2>
        <ul>
            <li>
                <strong>null</strong>: <xsl:call-template name="token-trailer">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="null"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'singleword'</strong>: <xsl:call-template name="token-trailer">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'singleword'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'only-twice'</strong>: <xsl:call-template name="token-trailer">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'only-twice'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-trailer">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'what-if-four-segments'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-trailer">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'let.s-see-some---strange--ones-'"/>
                </xsl:call-template>
            </li>
        </ul>
    </xsl:template>
    <xsl:template name="test-token-reverse">
        <h2>token-reverse</h2>
        <ul>
            <li>
                <strong>null</strong>: <xsl:call-template name="token-reverse">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="null"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'singleword'</strong>: <xsl:call-template name="token-reverse">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'singleword'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'only-twice'</strong>: <xsl:call-template name="token-reverse">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'only-twice'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-reverse">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'what-if-four-segments'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-reverse">
                    <xsl:with-param name="delim" select="'-'"/>
                    <xsl:with-param name="str" select="'let.s-see-some---strange--ones-'"/>
                </xsl:call-template>
            </li>
        </ul>
    </xsl:template>
    <xsl:template name="test-token-replace-sibling">
        <h2>token-replace-sibling</h2>
        <ul>
            <li>
                <strong>over = null</strong>
                <ul>
                    <li>
                        <strong>null</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="null"/>
                            <xsl:with-param name="base" select="null"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'singleword'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="null"/>
                            <xsl:with-param name="base" select="'singleword'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'only-twice'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="null"/>
                            <xsl:with-param name="base" select="'only-twice'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="null"/>
                            <xsl:with-param name="base" select="'what-if-four-segments'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="null"/>
                            <xsl:with-param name="base" select="'let.s-see-some---strange--ones-'"/>
                        </xsl:call-template>
                    </li>
                </ul>
            </li>
            <li>
                <strong>over = 'singleword'</strong>
                <ul>
                    <li>
                        <strong>'singleword'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'singleword'"/>
                            <xsl:with-param name="base" select="'singleword'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'only-twice'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'singleword'"/>
                            <xsl:with-param name="base" select="'only-twice'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'singleword'"/>
                            <xsl:with-param name="base" select="'what-if-four-segments'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'singleword'"/>
                            <xsl:with-param name="base" select="'let.s-see-some---strange--ones-'"/>
                        </xsl:call-template>
                    </li>
                </ul>
            </li>
            <li>
                <strong>over = 'only-twice'</strong>
                <ul>
                    <li>
                        <strong>'only-twice'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'only-twice'"/>
                            <xsl:with-param name="base" select="'only-twice'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'only-twice'"/>
                            <xsl:with-param name="base" select="'what-if-four-segments'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'only-twice'"/>
                            <xsl:with-param name="base" select="'let.s-see-some---strange--ones-'"/>
                        </xsl:call-template>
                    </li>
                </ul>
            </li>
            <li>
                <strong>over = 'what-if-four-segments'</strong>
                <ul>
                    <li>
                        <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'what-if-four-segments'"/>
                            <xsl:with-param name="base" select="'what-if-four-segments'"/>
                        </xsl:call-template>
                    </li>
                    <li>
                        <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'what-if-four-segments'"/>
                            <xsl:with-param name="base" select="'let.s-see-some---strange--ones-'"/>
                        </xsl:call-template>
                    </li>
                </ul>
            </li>
            <li>
                <strong>over = '-lots---strange--ones-'</strong>
                <ul>
                    <li>
                        <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="delim" select="'-'"/>
                            <xsl:with-param name="over" select="'-lots---strange--ones-'"/>
                            <xsl:with-param name="base" select="'let.s-see-some---strange--ones-'"/>
                        </xsl:call-template>
                    </li>
                </ul>
            </li>
        </ul>
    </xsl:template>
    <xsl:template name="test-token-replace-delim">
        <h2>token-replace-delim</h2>
        <xsl:variable name="new" select="'::'"/>
        <ul>
            <li>
                <strong>null</strong>: <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="old" select="'-'"/>
                    <xsl:with-param name="new" select="$new"/>
                    <xsl:with-param name="str" select="null"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'singleword'</strong>: <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="old" select="'-'"/>
                    <xsl:with-param name="new" select="$new"/>
                    <xsl:with-param name="str" select="'singleword'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'only-twice'</strong>: <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="old" select="'-'"/>
                    <xsl:with-param name="new" select="$new"/>
                    <xsl:with-param name="str" select="'only-twice'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'what-if-four-segments'</strong>: <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="old" select="'-'"/>
                    <xsl:with-param name="new" select="$new"/>
                    <xsl:with-param name="str" select="'what-if-four-segments'"/>
                </xsl:call-template>
            </li>
            <li>
                <strong>'let.s-see-some---strange--ones-'</strong>: <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="old" select="'-'"/>
                    <xsl:with-param name="new" select="$new"/>
                    <xsl:with-param name="str" select="'let.s-see-some---strange--ones-'"/>
                </xsl:call-template>
            </li>
        </ul>
    </xsl:template>
    <!--=================================-->
    <xsl:template name="substring-reverse">
        <xsl:param name="str"/>
        <xsl:if test="string($str)">
            <xsl:call-template name="substring-reverse">
                <xsl:with-param name="str" select="substring($str, 2)"/>
            </xsl:call-template>
        </xsl:if>
        <xsl:value-of select="substring($str, 1, 1)"/>
    </xsl:template>
    <xsl:template name="token-leaders">
        <xsl:param name="str"/>
        <xsl:param name="delim" select="'.'"/>
        <xsl:if test="contains($str, $delim)">
            <xsl:value-of select="substring-before($str, $delim)"/>
            <xsl:if test="contains(substring-after($str, $delim), $delim)">
                <xsl:value-of select="$delim"/>
            </xsl:if>
            <xsl:call-template name="token-leaders">
                <xsl:with-param name="str" select="substring-after($str, $delim)"/>
                <xsl:with-param name="delim" select="$delim"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
    <xsl:template name="token-trailer">
        <xsl:param name="str"/>
        <xsl:param name="delim" select="'.'"/>
        <xsl:if test="contains($str, $delim)">
            <xsl:choose>
                <xsl:when test="contains(substring-after($str, $delim), $delim)">
                    <xsl:call-template name="token-trailer">
                        <xsl:with-param name="str" select="substring-after($str, $delim)"/>
                        <xsl:with-param name="delim" select="$delim"/>
                    </xsl:call-template>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="substring-after($str, $delim)"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
    </xsl:template>
    <xsl:template name="token-reverse">
        <xsl:param name="str"/>
        <xsl:param name="delim" select="'.'"/>
        <xsl:choose>
            <xsl:when test="contains($str, $delim)">
                <xsl:call-template name="token-reverse">
                    <xsl:with-param name="str" select="substring-after($str, $delim)"/>
                    <xsl:with-param name="delim" select="$delim"/>
                </xsl:call-template>
                <xsl:value-of select="concat($delim, substring-before($str, $delim))"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$str"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="token-replace-sibling">
        <xsl:param name="base"/>
        <xsl:param name="over"/>
        <xsl:param name="delim" select="'.'"/>
        <xsl:choose>
            <xsl:when test="not(string($over))">
                <xsl:value-of select="$base"/>
            </xsl:when>
            <xsl:when test="not(string($base))">
                <xsl:message terminate="yes">Base-tokens is underflow, while overlap-tokens remains <xsl:value-of select="$over"/>
                </xsl:message>
            </xsl:when>
            <xsl:otherwise>
                <xsl:choose>
                    <xsl:when test="contains($over, $delim)">
                        <xsl:call-template name="token-replace-sibling">
                            <xsl:with-param name="base">
                                <xsl:call-template name="token-leaders">
                                    <xsl:with-param name="str" select="$base"/>
                                    <xsl:with-param name="delim" select="$delim"/>
                                </xsl:call-template>
                            </xsl:with-param>
                            <xsl:with-param name="over">
                                <xsl:call-template name="token-leaders">
                                    <xsl:with-param name="str" select="$over"/>
                                    <xsl:with-param name="delim" select="$delim"/>
                                </xsl:call-template>
                            </xsl:with-param>
                            <xsl:with-param name="delim" select="$delim"/>
                        </xsl:call-template>
                        <xsl:value-of select="$delim"/>
                    </xsl:when>
                    <xsl:when test="contains($base, $delim)">
                        <xsl:call-template name="token-leaders">
                            <xsl:with-param name="str" select="$base"/>
                            <xsl:with-param name="delim" select="$delim"/>
                        </xsl:call-template>
                        <xsl:value-of select="$delim"/>
                    </xsl:when>
                </xsl:choose>
                <!--Token replace, don't consider any delimitors. -->
                <xsl:choose>
                    <xsl:when test="contains($over, $delim)">
                        <xsl:call-template name="token-trailer">
                            <xsl:with-param name="str" select="$over"/>
                            <xsl:with-param name="delim" select="$delim"/>
                        </xsl:call-template>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="$over"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    <xsl:template name="token-replace-delim">
        <xsl:param name="str"/>
        <xsl:param name="old"/>
        <xsl:param name="new"/>
        <xsl:choose>
            <xsl:when test="contains($str, $old)">
                <xsl:value-of select="concat(substring-before($str, $old), $new)"/>
                <xsl:call-template name="token-replace-delim">
                    <xsl:with-param name="str" select="substring-after($str, $old)"/>
                    <xsl:with-param name="old" select="$old"/>
                    <xsl:with-param name="new" select="$new"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$str"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
</xsl:stylesheet>
