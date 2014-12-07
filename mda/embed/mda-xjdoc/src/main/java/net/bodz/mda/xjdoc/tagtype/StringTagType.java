package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.c.string.StringEscape;
import net.bodz.bas.c.string.StringQuote;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.rtx.IOptions;

public class StringTagType
        extends AbstractTagType {

    @Override
    public Class<?> getValueType() {
        return String.class;
    }

    @Override
    public String parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options)
            throws ParseException {
        // TODO normalize-space?
        return string;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, IOptions options)
            throws IOException {
        String string = (String) value;
        // TODO Line-wrap...
        writer.writeTag(rootTagName, string);
    }

    @Override
    public String parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException {
        if (string.startsWith("\"") && string.endsWith("\"")) {
            String quotedString = string;
            String unescaped = StringEscape.unescapeJava(quotedString);
            return unescaped;
        } else
            return string;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
        if (value == null) {
            // warning...
            return;
        }

        String str = value.toString();
        String escaped = StringEscape.escapeJava(str);
        String quoted = StringQuote.qq(escaped);
        out.attribute(prefix, quoted);
    }

    static final StringTagType INSTANCE = new StringTagType();

    public static StringTagType getInstance() {
        return INSTANCE;
    }

}
