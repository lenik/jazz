package net.bodz.bas.io.res.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.OpenOption;

import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.t.object.IContextUtility;

public interface IStreamWriting
        extends
            IContextUtility {

    @Override
    IStreamWriting clone();

    OpenOption[] getOpenOptions();

    IStreamWriting setOpenOptions(OpenOption... openOptions);

    boolean isAutoFlush();

    IStreamWriting setAutoFlush(boolean autoFlush);

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

    void write(InputStream in)
            throws IOException;

    void writeChars(IStreamInputSource in, int maxLength)
            throws IOException;

    void writeChars(IStreamInputSource in)
            throws IOException;

    void writeChars(Reader in)
            throws IOException;

    void writeString(String string)
            throws IOException;

    void writeString(Iterable<String> strings)
            throws IOException;

}
