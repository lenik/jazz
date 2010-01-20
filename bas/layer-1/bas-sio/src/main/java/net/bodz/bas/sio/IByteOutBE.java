package net.bodz.bas.sio;

import java.io.IOException;

public interface IByteOutBE
        extends IByteOutNative {

    void writeBE(short s)
            throws IOException;

    void writeBE(int n)
            throws IOException;

    void writeBE(long l)
            throws IOException;

    void writeUtf16BE(char c)
            throws IOException;

    void writeUtf16BE(char[] str)
            throws IOException;

    void writeIeee754BE(float f)
            throws IOException;

    void writeIeee754BE(double d)
            throws IOException;

}
