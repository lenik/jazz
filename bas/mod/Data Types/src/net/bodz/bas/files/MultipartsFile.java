package net.bodz.bas.files;

import java.io.File;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.ParseException;
import net.bodz.bas.types.TypeParser;
import net.bodz.bas.types.TypeParsers;
import net.bodz.bas.types.util.PrefetchedIterator;

public abstract class MultipartsFile<T> extends _FileType implements
        FileSource<T> {

    private final Object file;
    private String       encoding;
    private TypeParser   keyParser;
    private TypeParser   valueParser;
    public final Object  textKey;
    private final String partTerm = ".";

    /**
     * @param file
     *            can be File, URL
     */
    public MultipartsFile(Object file, String encoding) {
        this.file = file;
        this.encoding = encoding;
        try {
            this.keyParser = TypeParsers.guess(getKeyClass(), "KeyClass");
            this.valueParser = TypeParsers.guess(getValueClass(), "ValueClass");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.textKey = getTextKey();
    }

    public MultipartsFile(Object file) {
        this(file, null);
    }

    public MultipartsFile(String path) {
        this(new File(path));
    }

    protected Class<?> getKeyClass() {
        return String.class;
    }

    protected Class<?> getValueClass() {
        return String.class;
    }

    protected Object parseKey(String key) throws ParseException {
        return keyParser.parse(key);
    }

    protected Object parseValue(String value) throws ParseException {
        return valueParser.parse(value);
    }

    protected Object getTextKey() {
        try {
            return keyParser.parse(".");
        } catch (ParseException e) {
            throw new Error("error body key", e);
        }
    }

    protected Object parseText(String text) throws ParseException {
        return parseValue(text);
    }

    private Object _parseText(StringBuffer textBuf) throws ParseException {
        String s = textBuf == null ? null : textBuf.toString();
        return parseValue(s);
    }

    protected boolean isPreHeaderComment(String line) {
        return line.startsWith("#");
    }

    protected boolean isHeaderComment(String line) {
        return line.startsWith("--");
    }

    protected boolean isTextValue(String value) {
        return "<<<".equals(value);
    }

    protected abstract void beginPart();

    protected abstract Object endPart();

    protected abstract boolean isPartEmpty();

    protected abstract void addPartEntry(Object key, Object value);

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
                lines = Files.readByLine2(encoding, file).iterator();
            }

            @Override
            protected Object fetch() {
                try {
                    return _fetch();
                } catch (ParseException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }

            protected Object _fetch() throws ParseException {
                boolean preheader = true;
                boolean header = true;
                Object tKey = textKey;
                int indent = 0;
                StringBuffer text = null;
                beginPart();
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
                            addPartEntry(key, val);
                        }
                        continue L;
                    } // headers

                    if (line.trim().equals(partTerm)) {
                        addPartEntry(tKey, _parseText(text));
                        text = null;
                        if (tKey == textKey)
                            break;
                        else {
                            tKey = textKey;
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
                    addPartEntry(tKey, _parseText(text));
                if (isPartEmpty())
                    return END;
                return endPart();
            }
        };
    }
}
