package net.bodz.bas.io.preparation;

import java.io.IOException;

public interface IStreamWritePreparation {

    boolean getAppendMode();

    void setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    void setAutoFlush(boolean autoFlush);

    void writeBytes(byte[] bytes, int off, int len)
            throws IOException;

    void writeBytes(byte[] bytes)
            throws IOException;

    void writeString(String string)
            throws IOException;

}
