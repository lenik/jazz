package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.resource.IStreamInputSource;
import net.bodz.bas.io.resource.builtin.LocalFileResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.util.iter.AbstractMitorx;
import net.bodz.bas.util.iter.Mitorx;

/**
 * @see PartRecords
 */
public abstract class _PartRecords<K, V>
        extends MapResRecords<K, V> {

    public static final int TEXT_AFTER_HEADER = 0;
    public static final int FREE_FORM = 1;

    private int flags;
    private K textKey;

    public _PartRecords(IStreamInputSource source) {
        this(source, 0);
    }

    public _PartRecords(IStreamInputSource source, int flags) {
        super(source);
        this.flags = flags;
    }

    public _PartRecords(File file, String encoding) {
        this(file, encoding, 0);
    }

    public _PartRecords(File file, String encoding, int flags) {
        this(new LocalFileResource(file, encoding), flags);
    }

    public _PartRecords(URL url, String encoding) {
        this(url, encoding, 0);
    }

    public _PartRecords(URL url, String encoding, int flags) {
        this(new URLResource(url, encoding), flags);
    }

    public K getTextKey() {
        if (textKey == null)
            try {
                textKey = parseKey(".");
            } catch (ParseException e) {
                throw new Error("error body key", e);
            }
        return textKey;
    }

    protected Object parseText(String text)
            throws ParseException {
        return parseValue(text);
    }

    private V _parseText(StringBuilder textBuf)
            throws ParseException {
        String s = textBuf == null ? null : textBuf.toString();
        return parseValue(s);
    }

    /**
     * Overrides {@link #isStartOfPart(String)}
     */
    protected boolean isPreHeaderComment(String line) {
        return line.startsWith("#");
    }

    /**
     * Overrided by:
     * <ul>
     * <li>{@link #isPreHeaderComment(String)}
     * <li>{@link #isStartOfPart(String)}
     * </ul>
     */
    protected boolean isHeaderComment(String line) {
        return line.startsWith("--");
    }

    protected boolean isTextValue(String value) {
        return "<<<".equals(value);
    }

    protected boolean isIndentChar(char c) {
        if (c == '\n' || c == '\r')
            return false;
        return Character.isWhitespace(c);
    }

    protected boolean isEndOfText(String line) {
        return ".".equals(line);
    }

    /**
     * Overrided by {@link #isPreHeaderComment(String)}
     * <p>
     * Overrides {@link #isHeaderComment(String)}
     */
    protected Map<K, V> isStartOfPart(String line) {
        if (".".equals(line))
            return newMap();
        return null;
    }

    class Iter
            extends AbstractMitorx<Map<K, V>, IOException> {

        private static final int PREHEADER = 0;
        private static final int HEADER = 1;
        private static final int HEADER_TEXT = 2;
        private static final int TEXT = 3;

        private LineReader lineReader;
        private Map<K, V> map;
        private Map<K, V> nextMap;

        @Override
        public Map<K, V> _next()
                throws IOException {
            if (lineReader == null)
                lineReader = source.newLineReader();
            int state = PREHEADER;
            boolean freeForm = (flags & FREE_FORM) != 0;
            K textKey = getTextKey();
            K key = null;
            int indent = 0;
            StringBuilder text = null;

            if (nextMap == null)
                nextMap = newMap();
            map = nextMap;
            nextMap = null;

            try {
                String line;
                while ((line = lineReader.readLine()) != null) {
                    line = StringPart.trimRight(line);
                    switch (state) {
                    case PREHEADER:
                        if (isPreHeaderComment(line) || line.trim().isEmpty())
                            continue;
                        state = HEADER;
                    case HEADER:
                        nextMap = isStartOfPart(line);
                        if (nextMap != null)
                            return map;
                        if (isHeaderComment(line))
                            continue;
                        if (line.trim().isEmpty()) {
                            if (freeForm)
                                continue;
                            // TEXT_AFTER_HEADER
                            key = textKey;
                            state = TEXT;
                        } else {
                            while (isIndentChar(line.charAt(indent)))
                                indent++;
                            if (indent != 0) {
                                state = TEXT;
                            } else {
                                String name = line;
                                String value = null;
                                int pos = line.indexOf(':', 1); // allow : as key name.
                                if (pos != -1) {
                                    value = line.substring(pos + 1).trim(); // trimLeft&Right
                                    name = line.substring(0, pos).trim(); // trimRight
                                } else {
                                    name = name.trim();
                                }
                                key = parseKey(name);
                                if (isTextValue(value)) { // '<<<'
                                    state = HEADER_TEXT;
                                    textKey = key;
                                } else {
                                    V val = parseValue(value);
                                    map.put(key, val);
                                }
                            }
                        }
                        continue;
                    case HEADER_TEXT:
                    case TEXT:
                        if (isEndOfText(line)) {
                            map.put(key, _parseText(text));
                            text = null;
                            if (state == TEXT)
                                // assert key == textKey; // end part if text-key
                                break;
                            else {
                                state = HEADER;
                                continue;
                            }
                        }

                        if (text == null)
                            text = new StringBuilder();
                        // remove necessary indent
                        int removeIndent = indent;
                        while (removeIndent > 0 && isIndentChar(line.charAt(0))) {
                            line = line.substring(1);
                            removeIndent--;
                        }
                        text.append(line);
                        text.append('\n');
                    } // switch section
                } // lines
                if (text != null)
                    map.put(key, _parseText(text));
            } catch (ParseException e) {
                throw new IOException(e);
            }
            return end();
        }

    }

    @Override
    public Mitorx<? extends Map<K, V>, ? extends IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
