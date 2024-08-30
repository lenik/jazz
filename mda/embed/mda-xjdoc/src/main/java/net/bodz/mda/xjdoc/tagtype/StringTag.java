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
    public void parseAttribute(String attributeName, String extension, String string, IOptions options)
            throws ParseException {
        if (string.startsWith("\"") && string.endsWith("\"")) {
            String quotedString = string;
            String unescaped = StringEscape.parseQuotedJavaString(quotedString);
            string = unescaped;
        }

    }

    @Override
    public void writeObject(IFlatfOutput out, String name, IOptions options)
            throws IOException, FormatException {
        String tag = getTagName();
        if (tag == null) {
            // warning...
            return;
        }

        String str = getString();
        String escaped = StringEscape.escapeJava(str);
        String quoted = StringQuote.qq(escaped);
        out.attribute(name, quoted);
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        // TODO normalize-space?
        data = javadoc;
    }

    @Override
    public void writeJavadoc(String annotation, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        String string = getString();
        // TODO Line-wrap...
        writer.writeTag(annotation, string);
    }

}
