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

    void writeBytes(byte[] bytes, int off, int len)
            throws IOException;

    void writeBytes(byte[] bytes)
            throws IOException;

    void writeChars(char[] chars, int off, int len)
            throws IOException;

    void writeChars(char[] chars)
            throws IOException;

    void writeBytes(IStreamInputSource in, int maxLength)
            throws IOException;

    void writeBytes(IStreamInputSource in)
            throws IOException;

    void writeChars(IStreamInputSource in, int maxLength)
            throws IOException;

    void writeChars(IStreamInputSource in)
            throws IOException;

    void write(String string)
            throws IOException;

}
