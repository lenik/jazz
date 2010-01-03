package net.bodz.bas.db.filedb;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.NoSuchElementException;

import net.bodz.bas.commons.exceptions.ParseException;
import net.bodz.bas.commons.iterators.DirectIterator;
import net.bodz.bas.commons.iterators._DirectIterator;
import net.bodz.bas.io.FileResLink;
import net.bodz.bas.io.LineReader;
import net.bodz.bas.io.ResLink;
import net.bodz.bas.io.URLResLink;
import net.bodz.bas.nls.TypesNLS;

/**
 * @see PartRecords
 */
public abstract class _PartRecords<K, V> extends MapResRecords<K, V> {

    public static final int TEXT_AFTER_HEADER = 0;
    public static final int FREE_FORM = 1;

    private int flags;
    private K textKey;

    public _PartRecords(ResLink resLink) {
        this(resLink, null, 0);
    }

    public _PartRecords(ResLink resLink, Charset charset) {
        this(resLink, charset, 0);
    }

    public _PartRecords(ResLink resLink, Charset charset, int flags) {
        super(resLink, charset);
        this.flags = flags;
    }

    public _PartRecords(File file) {
        this(file, null, 0);
    }

    public _PartRecords(File file, String encoding) {
        this(file, encoding, 0);
    }

    public _PartRecords(File file, String encoding, int flags) {
        this(new FileResLink(file), Charset.forName(encoding), flags);
    }

    public _PartRecords(URL url) {
        this(url, null, 0);
    }

    public _PartRecords(URL url, String encoding) {
        this(url, encoding, 0);
    }

    public _PartRecords(URL url, String encoding, int flags) {
        this(new URLResLink(url), Charset.forName(encoding), flags);
    }

    public K getTextKey() {
        if (textKey == null)
            try {
                textKey = parseKey("."); //$NON-NLS-1$
            } catch (ParseException e) {
                throw new Error(TypesNLS.getString("MultipartsFile.errorBodyKey"), e); //$NON-NLS-1$
            }
        return textKey;
    }

    protected Object parseText(String text) throws ParseException {
        return parseValue(text);
    }

    private V _parseText(StringBuffer textBuf) throws ParseException {
        String s = textBuf == null ? null : textBuf.toString();
        return parseValue(s);
    }

    /**
     * Overrides {@link #isStartOfPart(String)}
     */
    protected boolean isPreHeaderComment(String line) {
        return line.startsWith("#"); //$NON-NLS-1$
    }

    /**
     * Overrided by:
     * <ul>
     * <li>{@link #isPreHeaderComment(String)}
     * <li>{@link #isStartOfPart(String)}
     * </ul>
     */
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

    class Iter extends _DirectIterator<Map<K, V>, IOException> {

        private static final int PREHEADER = 0;
        private static final int HEADER = 1;
        private static final int HEADER_TEXT = 2;
        private static final int TEXT = 3;

        private LineReader lineReader;
        private boolean end;
        private Map<K, V> map;
        private Map<K, V> nextMap;

        @Override
        public boolean next() throws IOException {
            if (end)
                return false;
            if (lineReader == null) {
                Reader reader = resLink.openReader(charset);
                lineReader = new LineReader(reader);
            }
            int state = PREHEADER;
            boolean freeForm = (flags & FREE_FORM) != 0;
            K textKey = getTextKey();
            K key = null;
            int indent = 0;
            StringBuffer text = null;

            if (nextMap == null)
                nextMap = newMap();
            map = nextMap;
            nextMap = null;

            try {
                String line;
                while ((line = lineReader.readLine()) != null) {
                    switch (state) {
                    case PREHEADER:
                        if (isPreHeaderComment(line) || line.trim().isEmpty())
                            continue;
                        state = HEADER;
                    case HEADER:
                        nextMap = isStartOfPart(line);
                        if (nextMap != null)
                            return true;
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
                            text = new StringBuffer();
                        // remove necessary indent
                        int removeIndent = indent;
                        while (removeIndent > 0 && isIndentChar(line.charAt(0))) {
                            line = line.substring(1);
                            removeIndent--;
                        }
                        text.append(line);
                    } // switch section
                } // lines
                if (text != null)
                    map.put(key, _parseText(text));
            } catch (ParseException e) {
                throw new IOException(e);
            }
            end = true;
            return true;
        }

        @Override
        public Map<K, V> get() throws NoSuchElementException {
            return map;
        }

    }

    @Override
    public DirectIterator<? extends Map<K, V>, IOException> iterator() {
        return new Iter();
    }

}
