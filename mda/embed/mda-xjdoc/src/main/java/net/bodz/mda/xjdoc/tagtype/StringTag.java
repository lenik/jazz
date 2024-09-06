package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.model.AbstractDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public class StringTag
        extends AbstractDocTag<String> {

    public StringTag() {
    }

    public StringTag(String string) {
        this.data = string;
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        // TODO normalize-space?
        data = javadoc;
    }

    @Override
    public void writeJavadoc(String tagName, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        String string = getString();
        // TODO Line-wrap...
        writer.writeTag(tagName, string);
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String text, IOptions options)
            throws ParseException {
        if (! (text.startsWith("\"") && text.endsWith("\"")))
            throw new ParseException("unquoted: " + text);

        String quotedString = text;
        String unescaped = StringEscape.parseQuotedJavaString(quotedString);
        this.data = unescaped;
    }

    @Override
    public void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException, FormatException {
        String str = getString();
        String escaped = StringEscape.escapeJava(str);
        String quoted = StringQuote.qq(escaped);
        out.attribute(attributeName, quoted);
    }

}
