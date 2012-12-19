package net.bodz.bas.io.resource.tools;

import java.io.IOException;

import net.bodz.bas.io.resource.IStreamInputSource;

public interface IStreamWriting
        extends Cloneable {

    IStreamWriting clone();

    boolean isAppendMode();

    IStreamWriting setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    IStreamWriting setAutoFlush(boolean autoFlush);

    int getBlockSize();

    IStreamWriting setBlockSize(int blockSize);

    void write(byte[] bytes, int off, int len)
            throws IOException;

    void write(byte[] bytes)
            throws IOException;

    void writeChars(char[] chars, int off, int len)
            throws IOException;

    void writeChars(char[] chars)
            throws IOException;

    void write(IStreamInputSource in, int maxLength)
            throws IOException;

    void write(IStreamInputSource in)
            throws IOException;

    void writeChars(IStreamInputSource in, int maxLength)
            throws IOException;

    void writeChars(IStreamInputSource in)
            throws IOException;

    void writeString(String string)
            throws IOException;

}
