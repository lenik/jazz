package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sugar.IToolable;

/**
 * 提供更多的打开方式。
 */
public interface IStreamOutputTarget
        extends ISimpleStreamOutputTarget, IToolable {

    @Override
    void setCharset(Charset charset);

    /**
     * @throws IllegalCharsetNameException
     *             If <code>charsetName</code> isn't existed.
     */
    void setCharset(String charsetName);

    /**
     * @return non-<code>null</code> value.
     */
    IPrintOut newPrintOut(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IByteOutEx newByteOutNative(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    OutputStream newOutputStream(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IDataOutput newDataOutput(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IObjectOutput newObjectOutput(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    PrintStream newPrintStream(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Writer newWriter(OpenOption... options)
            throws IOException;

}
