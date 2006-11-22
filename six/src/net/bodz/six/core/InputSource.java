package net.bodz.six.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.CharsetDecoder;

public class InputSource extends Reader {

    public static final int EOF                = -1;

    InputStream             binIn;
    boolean                 ended              = false;

    boolean                 guessEncoding      = true;
    String                  encoding;
    CharsetDecoder          decoder;

    LogTarget               decodingExceptions = DummyLogTarget.DUMMY;
    LogTarget               inputChains        = DummyLogTarget.DUMMY;

    @Override
    public int read() throws IOException {
        
        return super.read();
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (ended)
            return -1;
        if (len == 0)
            return 0;
        int len0 = len;
        while (len > 0) {
            int c = read();
            if (c == EOF)
                break;
            // TODO - if (c >= 0x10000) ...
            cbuf[off++] = (char) c;
            len--;
        }
        int cc = len0 - len;
        if (cc > 0)
            return cc;
        ended = true;
        return -1; // EOF
    }

    @Override
    public void close() throws IOException {
        if (binIn != null)
            binIn.close();
    }

    // TODO - buffered reader.
    int   bufferSize;
    int[] buffer;

    void discardBuffer() {
        buffer = null;
    }

}
