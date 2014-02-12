package net.bodz.bas.text.att;

/**
 * Attributed Text
 *
 * Recommended file extension: .att, .atxt, .attrtxt
 */
public interface IAttributedText {

    String getAttribute(String key);

    void setAttribute(String key, String value);

    String getText();

    void setText(String text);

    class fn
            extends AttributedTextParser {
    }

}
