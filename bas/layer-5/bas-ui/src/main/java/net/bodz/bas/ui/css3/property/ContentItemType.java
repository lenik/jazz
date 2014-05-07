package net.bodz.bas.ui.css3.property;

public enum ContentItemType {

    /** Text content (see the section on strings). */
    string,

    /**
     * The value is a URI that designates an external resource (such as an image). If the user agent
     * cannot display the resource it must either leave it out as if it were not specified or
     * display some indication that the resource cannot be displayed.
     */
    uri,

    /**
     * Counters may be specified with two different functions: 'counter()' or 'counters()'. The
     * former has two forms: 'counter(name)' or 'counter(name, style)'. The generated text is the
     * value of the innermost counter of the given name in scope at this pseudo-element; it is
     * formatted in the indicated style ('decimal' by default). The latter function also has two
     * forms: 'counters(name, string)' or 'counters(name, string, style)'. The generated text is the
     * value of all counters with the given name in scope at this pseudo-element, from outermost to
     * innermost separated by the specified string. The counters are rendered in the indicated style
     * ('decimal' by default). See the section on automatic counters and numbering for more
     * information. The name must not be 'none', 'inherit' or 'initial'. Such a name causes the
     * declaration to be ignored.
     */
    counter,

    /** These values are replaced by the appropriate string from the 'quotes' property. */
    open_quote, close_quote,

    /** Introduces no content, but increments (decrements) the level of nesting for quotes. */
    no_open_quote, no_close_quote,

    /**
     * This function returns as a string the value of attribute X for the subject of the selector.
     * The string is not parsed by the CSS processor. If the subject of the selector does not have
     * an attribute X, an empty string is returned. The case-sensitivity of attribute names depends
     * on the document language.
     */
    attr_X,

}
