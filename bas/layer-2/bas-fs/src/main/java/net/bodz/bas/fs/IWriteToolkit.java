package net.bodz.bas.fs;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.zip.ZipOutputStream;

public interface IWriteToolkit {

    boolean getAppendMode();

    void setAppendMode(boolean appendMode);

    boolean isAutoFlush();

    void setAutoFlush(boolean autoFlush);

    OutputStream newOutputStream()
            throws IOException;

    PrintStream newPrintStream()
            throws IOException;

    ZipOutputStream newZipOutputStream()
            throws IOException;

    Writer newWriter()
            throws IOException;

    void writeBytes(byte[] bytes, int off, int len)
            throws IOException;

    void writeBytes(byte[] bytes)
            throws IOException;

    void writeString(String string)
            throws IOException;

}
