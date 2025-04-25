package net.bodz.bas.text.parser;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.net.util.ReadBuffer;
import net.bodz.bas.t.buffer.CharArrayBuffer;

public abstract class BufferedLineParser<T>
        implements IBufferedParser<T>,
                   ILineParser<T>,
                   IParseContext {

    static final Logger logger = LoggerFactory.getLogger(BufferedLineParser.class);

    public final ReadBuffer buffer = new ReadBuffer(4096);
    CharArrayBuffer decoded = buffer.decoded;

    int selectionStart;
    int selectionEnd;

    public BufferedLineParser() {
        buffer.setDecodeMode(true);
    }

    @Override
    public String getSelection() {
        char[] backedArray = decoded.getBackedArray();
        return new String(backedArray, selectionStart, selectionEnd - selectionStart);
    }

    @Override
    public void parse(IParsedValueCallback<T> callback, IParseErrorCallback errorCallback) {
        buffer.decode();

        final char[] buf = decoded.getBackedArray();
        final int off = decoded.getBackedArrayOffset();
        final int len = decoded.length();
        int look = off;
        int pos = off;
        int stop = off;

        try {
            for (int n = 0; n < len; n++) {
                char ch = buf[look++];
                switch (ch) {
                    case '\r':
                        continue;
                    case '\n':
                        selectionStart = off;
                        selectionEnd = pos;
                        try {
                            T value = parseLine(buf, off, pos - off);
                            callback.parsedValue(this, value);
                        } catch (ParseException e) {
                            if (errorCallback != null && errorCallback.parseError(this, e)) {
                                // drop away the error text
                            } else {
                                // stop parse
                                return;
                            }
                        }
                        stop = look;
                        break;
                }
                pos = look;
            }
        } finally {
            if (stop != off) {
                decoded.delete(0, stop - off);
            }
        }
    }

}
