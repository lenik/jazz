package net.bodz.bas.text.textmap;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.err.BadFormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.pojo.Pair;
import net.bodz.bas.typer.std.IParser;

public class TextMapParserProc<K, V> {

    private IParser<K> keyParser;
    private IParser<V> valueParser;

    private TextMapFormat format;
    private int keyStartLen;
    private int keyEndLen;
    private int keyPatternLen;

    private int lineIndex;

    /**
     * Each key must be on a single line.
     */
    private String key;
    private StringBuilder buffer;

    public TextMapParserProc(IParser<K> keyParser, IParser<V> valueParser) {
        if (keyParser == null)
            throw new NullPointerException("keyParser");
        if (valueParser == null)
            throw new NullPointerException("valueParser");
        this.keyParser = keyParser;
        this.valueParser = valueParser;

        buffer = new StringBuilder();
    }

    public TextMapFormat getFormat() {
        return format;
    }

    public void setFormat(TextMapFormat format) {
        this.format = format;
    }

    public synchronized Entry<K, V> process(String line_)
            throws IOException, ParseException {
        Entry<String, String> entry = receive(line_);
        if (entry == null)
            return null;

        String keyStr = entry.getKey();
        K key = keyParser.parse(keyStr);

        String valueStr = entry.getValue();
        V value = valueParser.parse(valueStr);

        return Pair.of(key, value);
    }

    /**
     * @param line_
     *            <code>null</code> for EOF.
     */
    protected synchronized Entry<String, String> receive(String line_)
            throws IOException {
        if (line_ == null) {
            if (key != null)
                return Pair.of(key, buffer.toString());
            else
                return null;
        }

        String line = StringPart.chomp(line_);
        if (lineIndex == 0 && format == null)
            autoInitFormat(line);

        int len = line.length();
        if (len >= keyPatternLen) {
            if (line.substring(0, keyStartLen).equals(format.keyStartPattern)
                    && line.substring(len - keyEndLen).equals(format.keyEndPattern)) {
                Pair<String, String> pair = null;
                if (key != null)
                    pair = Pair.of(key, buffer.toString());
                key = line.substring(keyStartLen, len - keyEndLen);
                buffer.setLength(0);
                return pair;
            }
        }

        if (format.indented) {
            int spaces = len;
            for (int i = 0; i < len; i++)
                if (!Character.isWhitespace(line_.charAt(i))) {
                    spaces = i;
                    break;
                }
            line_ = line_.substring(spaces);
        }

        buffer.append(line_);
        return null;
    }

    void autoInitFormat(String line)
            throws BadFormatException {
        format = new TextMapFormat();

        if (line.isEmpty())
            throw new BadFormatException("First line can't be empty.");
        int len = line.length();

        int startLen = 0;
        for (int i = 0; i < len; i++) {
            char ch = line.charAt(i);
            if (Character.isWhitespace(ch))
                throw new BadFormatException("First line has leading space.");
            if (Character.isLetterOrDigit(ch)) {
                startLen = i;
                break;
            }
        }

        int endLen = 0;
        for (int i = len - 1; i >= startLen; i--) {
            char ch = line.charAt(i);
            if (Character.isWhitespace(ch))
                throw new BadFormatException("First line has trailing space.");
            if (Character.isLetterOrDigit(ch)) {
                endLen = len - i - 1;
                break;
            }
        }

        if (startLen == 0 && endLen == 0)
            throw new BadFormatException("First line doesn't include start/end pattern.");

        format.keyStartPattern = line.substring(0, startLen);
        format.keyEndPattern = line.substring(len - endLen);
        keyStartLen = startLen;
        keyEndLen = endLen;
        keyPatternLen = startLen + endLen;
    }

}
