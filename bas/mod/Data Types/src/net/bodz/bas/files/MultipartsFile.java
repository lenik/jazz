package net.bodz.bas.files;

import java.io.File;
import java.util.Iterator;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
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
    private Object       textKey;

    /**
     * @param file
     *            can be File, URL
     */
    public MultipartsFile(Object file, String encoding) {
        this.file = file;
        this.encoding = encoding;
        try {
            this.keyParser = TypeParsers.guess(getKeyClass());
        } catch (CreateException e) {
            throw new IllegalUsageError("Invalid key class: " + getKeyClass(),
                    e);
        }
        try {
            this.valueParser = TypeParsers.guess(getValueClass());
        } catch (CreateException e) {
            throw new IllegalUsageError("Invalid value class: "
                    + getValueClass(), e);
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
                StringBuffer text = null;
                beginPart();
                while (lines.hasNext()) {
                    String line = lines.next();
                    if (header) {
                        if (line.isEmpty() || isPreHeaderComment(line)) {
                            if (preheader)
                                continue;
                            header = false;
                            continue;
                        }
                        preheader = false;
                        if (isHeaderComment(line))
                            continue;

                        String name = line;
                        String value = null;
                        int pos = line.indexOf(':');
                        if (pos != -1) {
                            value = line.substring(pos + 1).trim();
                            name = line.substring(0, pos).trim();
                        } else {
                            name = name.trim();
                        }
                        Object key = parseKey(name);
                        if (isTextValue(value)) {
                            header = false;
                            tKey = key;
                        } else {
                            Object val = parseValue(value);
                            addPartEntry(key, val);
                        }
                        continue;
                    }
                    if (line.equals(".")) {
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
                    else
                        text.append('\n');
                    text.append(line);
                }
                if (text != null)
                    addPartEntry(tKey, _parseText(text));
                if (isPartEmpty())
                    return END;
                return endPart();
            }
        };
    }

}
