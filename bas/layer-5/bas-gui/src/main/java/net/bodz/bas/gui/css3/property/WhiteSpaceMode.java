package net.bodz.bas.gui.css3.property;

/**
 * Newlines in the source can be represented by a carriage return (U+000D), a linefeed (U+000A) or
 * both (U+000D U+000A) or by some other mechanism that identifies the beginning and end of document
 * segments, such as the SGML RECORD-START and RECORD-END tokens. The CSS 'white-space' processing
 * model assumes all newlines have been normalized to line feeds. UAs that recognize other newline
 * representations must apply the white space processing rules as if this normalization has taken
 * place. If no newline rules are specified for the document language, each carriage return (U+000D)
 * and CRLF sequence (U+000D U+000A) in the document text is treated as single line feed character.
 * This default normalization rule also applies to generated content.
 * 
 * UAs must recognize line feeds (U+000A) as newline characters. UAs may additionally treat other
 * forced break characters as newline characters per UAX14.
 */
public enum WhiteSpaceMode {

    /**
     * This value directs user agents to collapse sequences of white space, and break lines as
     * necessary to fill line boxes.
     */
    normal,

    /**
     * This value prevents user agents from collapsing sequences of white space. Lines are only
     * broken at preserved newline characters.
     */
    pre,

    /** This value collapses white space as for 'normal', but suppresses line breaks within text. */
    nowrap,

    /**
     * This value prevents user agents from collapsing sequences of white space. Lines are broken at
     * preserved newline characters, and as necessary to fill line boxes.
     */
    pre_wrap,

    /**
     * This value directs user agents to collapse sequences of white space. Lines are broken at
     * preserved newline characters, and as necessary to fill line boxes.
     */
    pre_line,

}
