package net.bodz.bas.io.adapter;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import net.bodz.bas.io.IByteOut;

public class ByteOutPrintStream
        extends PrintStream {

    public ByteOutPrintStream(IByteOut out) {
        super(new ByteOutOutputStream(out));
    }

    public ByteOutPrintStream(IByteOut out, boolean autoFlush) {
        super(new ByteOutOutputStream(out), autoFlush);
    }

    public ByteOutPrintStream(IByteOut out, boolean autoFlush, String encoding)
            throws UnsupportedEncodingException {
        super(new ByteOutOutputStream(out), autoFlush, encoding);
    }

}
