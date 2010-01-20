package net.bodz.bas.sio;

import java.io.IOException;

public interface IByteOutLE
        extends IByteOutNative {

    void writeLE(short s)
            throws IOException;

    void writeLE(int n)
            throws IOException;

    void writeLE(long l)
            throws IOException;

    void writeUtf16LE(char c)
            throws IOException;

    void writeUtf16LE(char[] str)
            throws IOException;

    void writeIeee754LE(float f)
            throws IOException;

    void writeIeee754LE(double d)
            throws IOException;

}
