package net.bodz.bas.io.resource.preparation;

import java.io.IOException;

import net.bodz.bas.io.resource.IStreamInputSource;

public interface IStreamWritePreparation
        extends Cloneable {

    IStreamWritePreparation clone();

    boolean isAppendMode();

    IStreamWritePreparation setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    IStreamWritePreparation setAutoFlush(boolean autoFlush);

    int getBlockSize();

    IStreamWritePreparation setBlockSize(int blockSize);

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
