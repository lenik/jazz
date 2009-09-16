package net.bodz.xfo.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Map;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.xfo.types.Attribute;
import net.bodz.xfo.types.SourceType;

/**
 * Common inlined attributes annotation:
 * 
 * <pre>
 * attribute-declaration:: 
 *   '@' attribute-id[':'] attribute-text
 * </pre>
 * 
 * For multiple occurances of attributes with same id, they are joined with `<code>, </code>' by
 * default.
 */
public abstract class _SourceType implements SourceType {

    private final Charset charset;

    public _SourceType(Charset charset) {
        this.charset = charset;
    }

    @Override
    public Reader open(File file) throws IOException {
        Charset cs = charset;
        if (cs == null)
            cs = Charset.defaultCharset();
        FileInputStream in = new FileInputStream(file);
        return new InputStreamReader(in, cs);
    }

    @Override
    public Map<String, String> parseAttributes(String sourceName, Reader reader)
            throws IOException, ParseException {
        return null;
    }

    protected void parseAttributeDeclaration(String declaration, Attribute attribute)
            throws ParseException {
        assert declaration.startsWith("@");
        String id;
        String text;
        int p = declaration.indexOf(':');
        if (p == -1) {
            declaration = declaration.substring(1).trim();
            int len = declaration.length();
            p = declaration.indexOf(' ');
            if (p == -1) {
                for (int i = 1; i < len; i++) {
                    if (Character.isWhitespace(declaration.charAt(i))) {
                        p = i;
                        break;
                    }
                }
                if (p == -1)
                    p = len;
            }
            id = declaration.substring(0, p);
            if (p >= len)
                text = "";
            else
                text = declaration.substring(p + 1);
        } else {
            id = declaration.substring(1, p);
            text = declaration.substring(p + 1);
        }
        attribute.id = id.trim();
        attribute.text = text.trim();
    }

}
