package net.bodz.bas.gui.css3.property;

import java.io.Serializable;

public class TextDecorationMode
        implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Each line of text is underlined. */
    boolean underline;

    /** Each line of text has a line above it. */
    boolean overline;

    /** Each line of text has a line through the middle. */
    boolean line_through;

    /**
     * Text blinks (alternates between visible and invisible). Conforming user agents may simply not
     * blink the text. Note that not blinking the text is one technique to satisfy checkpoint 3.3 of
     * WAI-UAAG.
     */
    boolean blink;

}
