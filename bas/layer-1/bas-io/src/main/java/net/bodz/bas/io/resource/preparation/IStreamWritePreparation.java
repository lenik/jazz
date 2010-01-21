package net.bodz.bas.io.resource.preparation;

import java.io.IOException;

public interface IStreamWritePreparation
        extends Cloneable {

    IStreamWritePreparation clone();

    boolean getAppendMode();

    IStreamWritePreparation setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    IStreamWritePreparation setAutoFlush(boolean autoFlush);

    void writeString(String string)
            throws IOException;

    void writeBytes(byte[] bytes, int off, int len)
            throws IOException;

    void writeBytes(byte[] bytes)
            throws IOException;

}
