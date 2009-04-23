package net.bodz.bas.files;

import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.nls.TypesNLS;
import net.bodz.bas.types.util.PrefetchedIterator;

public abstract class MultipartsFile<T> extends _MapFile<T> {

    private Object   textKey;
    protected String partTerm = "."; //$NON-NLS-1$

    public MultipartsFile(Object in, Object charset) {
        super(in, charset);
    }

    public MultipartsFile(Object in) {
        super(in);
    }

    public Object getTextKey() {
        if (textKey == null)
            try {
                textKey = keyParser.parse("."); //$NON-NLS-1$
            } catch (ParseException e) {
                throw new Error(TypesNLS.getString("MultipartsFile.errorBodyKey"), e); //$NON-NLS-1$
            }
        return textKey;
    }

    protected Object parseText(String text) throws ParseException {
        return parseValue(text);
    }

    private Object _parseText(StringBuffer textBuf) throws ParseException {
        String s = textBuf == null ? null : textBuf.toString();
        return parseValue(s);
    }

    protected boolean isPreHeaderComment(String line) {
        return line.startsWith("#"); //$NON-NLS-1$
    }

    protected boolean isHeaderComment(String line) {
        return line.startsWith("--"); //$NON-NLS-1$
    }

    protected boolean isTextValue(String value) {
        return "<<<".equals(value); //$NON-NLS-1$
    }

    protected boolean isIndentChar(char c) {
        if (c == '\n' || c == '\r')
            return false;
        return Character.isWhitespace(c);
    }

    @Override
    public Iterator<T> iterator() {
        return new PrefetchedIterator<T>() {
            private Iterator<String> lines;
            {
                lines = Files.readByLine2(charset, in).iterator();
            }

            @Override
            protected T fetch() {
                try {
                    return _fetch();
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                } catch (ControlContinue c) {
                    return fetch();
                } catch (ControlBreak b) {
                    return end();
                }
            }

            protected T _fetch() throws ParseException {
                boolean preheader = true;
                boolean header = true;
                Object tKey = getTextKey();
                int indent = 0;
                StringBuffer text = null;
                MapRecordBuilder<T> builder = (MapRecordBuilder<T>) MultipartsFile.this.builder;
                builder.reset();
                L: while (lines.hasNext()) {
                    String line = lines.next();
                    H: while (header) {
                        if (isPreHeaderComment(line) || line.trim().isEmpty()) {
                            if (preheader)
                                continue L;
                            header = false;
                            continue L;
                        }
                        preheader = false;
                        if (isHeaderComment(line))
                            continue L;

                        while (isIndentChar(line.charAt(indent)))
                            indent++;
                        if (indent != 0) {
                            header = false;
                            break H;
                        }

                        String name = line;
                        String value = null;
                        int pos = line.indexOf(':');
                        if (pos != -1) {
                            value = line.substring(pos + 1).trim(); // trimLeft&
                            // Right
                            name = line.substring(0, pos).trim(); // trimRight
                        } else {
                            name = name.trim();
                        }
                        Object key = parseKey(name);
                        if (isTextValue(value)) { // '<<<'
                            header = false;
                            tKey = key;
                        } else {
                            Object val = parseValue(value);
                            builder.add(key, val);
                        }
                        continue L;
                    } // headers

                    if (line.trim().equals(partTerm)) {
                        builder.add(tKey, _parseText(text));
                        text = null;
                        if (tKey == getTextKey()) // end part if text-key
                            break;
                        else {
                            tKey = getTextKey(); // continue next head entry
                            header = true;
                            continue;
                        }
                    }

                    if (text == null)
                        text = new StringBuffer();
                    // remove necessary indent
                    int removeIndent = indent;
                    while (removeIndent > 0 && isIndentChar(line.charAt(0))) {
                        line = line.substring(1);
                        removeIndent--;
                    }
                    text.append(line);
                } // lines

                if (text != null)
                    builder.add(tKey, _parseText(text));
                return builder.accept();
            }
        };
    }

}
