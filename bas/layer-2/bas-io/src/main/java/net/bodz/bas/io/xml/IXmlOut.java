package net.bodz.bas.io.xml;

import java.util.Map;

import net.bodz.bas.io.ITreeOut;

public interface IXmlOut
        extends ITreeOut {

    void processInstruction(String target, String data);

    void comment(String str);

    IXmlTagBuilder tag(String name);

    IXmlTagBuilder tag(String name, String text);

    IXmlTagBuilder tag(String name, Map<String, ?> attributes, String text);

    /**
     * Write the begin part of the tag pair, for example, <code>&lt;tag attr='val' ...&gt;</code>.
     * <p>
     * A tag name stack is used to remember names from previous calls.
     *
     * @param name
     *            The tag name, non-<code>null</code>.
     */
    IXmlTagBuilder start(String name);

    /**
     * Write the begin part of the tag pair, for example, <code>&lt;tag attr='val' ...&gt;</code>.
     * <p>
     * A tag name stack is used to remember names from previous calls.
     *
     * @param name
     *            The tag name, non-<code>null</code>.
     * @param attributes
     *            Attributes for the tag, can be <code>null</code> if none.
     */
    IXmlTagBuilder start(String name, Map<String, ?> attributes);

    /**
     * Write the end part of the tag pair, for example, <code>&lt;/tag&gt;</code>.
     * <p>
     * The last tag name is used.
     *
     * @throws IllegalStateException
     *             If no more tag to end.
     */
    void end();

    void endAll();

    /**
     * Write the left angle and the name of the begin tag, for example, <code>&lt;tag</code>.
     * <p>
     * A tag name stack is used.
     */
    void startTagBegin(String name);

    void attribute(String name, Object value);

    /**
     * Write the right angle of the start tag. ie., <code>&gt;</code>.
     */
    void startTagEnd(boolean empty);

    void text(String str);

    void textln(String str);

    void cdata(String cdata);

    void verbatim(String str);

    IXmlOut NULL = new NullXmlOut();

}
