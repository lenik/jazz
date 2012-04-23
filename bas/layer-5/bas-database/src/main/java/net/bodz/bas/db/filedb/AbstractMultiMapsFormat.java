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
 * Multi-Map File Format:
 * 
 * <pre>
 *  # pre-header comments
 *  -- header comment
 *  name: value
 *  name: <<< text-value
 *       more...
 *       more...
 * .
 *  (\n)
 *  default-text
 * .
 * 
 *  # (second map) pre-header comments
 *  -- (second map) header comment
 * ...
 * </pre>
 * 
 * Preferred file extension: *.mmf
 */
public abstract class AbstractMultiMapsFormat<K, V>
        extends AbstractSeqMaps<K, V> {

    public static final int TEXT_AFTER_HEADER = 0;
    public static final int FREE_FORM = 1;

    private int flags;
    private K textKey;

    public AbstractMultiMapsFormat(IStreamInputSource source) {
        this(source, 0);
    }

    public AbstractMultiMapsFormat(IStreamInputSource source, int flags) {
        super(source);
        this.flags = flags;
    }

    public AbstractMultiMapsFormat(File file, String encoding) {
        this(file, encoding, 0);
    }

    public AbstractMultiMapsFormat(File file, String encoding, int flags) {
        this(new LocalFileResource(file, encoding), flags);
    }

    public AbstractMultiMapsFormat(URL url, String encoding) {
        this(url, encoding, 0);
    }

    public AbstractMultiMapsFormat(URL url, String encoding, int flags) {
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

    protected boolean isPreHeaderComment(String line) {
        return line.startsWith("#");
    }

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

    protected boolean isEndOfMap(String line) {
        return ".".equals(line);
    }

    class Iter
            extends AbstractMitorx<Map<K, V>, IOException> {

        private static final int PREHEADER = 0;
        private static final int HEADER = 1;
        private static final int VALUE_TEXT = 2;
        private static final int TEXT = 3;

        private LineReader lineReader;
        private Map<K, V> map;

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

            map = newMap();

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
                        if (isHeaderComment(line))
                            continue;
                        if (isEndOfMap(line))
                            return map;

                        if (line.trim().isEmpty()) {
                            if (freeForm)
                                continue;
                            // TEXT_AFTER_HEADER
                            key = textKey;
                            state = TEXT;
                            continue;
                        }

                        while (isIndentChar(line.charAt(indent)))
                            indent++;
                        if (indent == 0) {
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
                                state = VALUE_TEXT;
                            } else {
                                V val = parseValue(value);
                                map.put(key, val);
                            }
                            continue;
                        } else {
                            key = textKey;
                            state = TEXT;
                        }
                    case VALUE_TEXT:
                    case TEXT:
                        if (isEndOfText(line)) {
                            map.put(key, _parseText(text));
                            text = null;
                            if (state == TEXT) {
                                // assert key == textKey; // end part if text-key
                                state = PREHEADER;
                                return map;
                            } else {
                                state = HEADER;
                                continue; // next line
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
                } // for lines
                if (text != null)
                    map.put(key, _parseText(text));
            } catch (ParseException e) {
                throw new IOException(e);
            }
            return end();
        } // _next()
    }

    @Override
    public Mitorx<? extends Map<K, V>, ? extends IOException> iterator(boolean allowOverlap) {
        return new Iter();
    }

}
