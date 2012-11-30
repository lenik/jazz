package net.bodz.bas.gui.css3.property;

public enum QuotesMode {

    /**
     * The 'open-quote' and 'close-quote' values of the 'content' property produce no quotation
     * marks.
     */
    none,

    /**
     * <pre>
     * [string string]+
     * </pre>
     * 
     * Values for the 'open-quote' and 'close-quote' values of the 'content' property are taken from
     * this list of pairs of quotation marks (opening and closing). The first (leftmost) pair
     * represents the outermost level of quotation, the second pair the first level of embedding,
     * etc. The user agent must apply the appropriate pair of quotation marks according to the level
     * of embedding.
     */
    string_pairs,

}
