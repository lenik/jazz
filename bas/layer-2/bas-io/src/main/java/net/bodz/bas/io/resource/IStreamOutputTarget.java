package net.bodz.bas.io.resource;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sugar.IToolable;

/**
 * 提供更多的打开方式。
 */
public interface IStreamOutputTarget
        extends ISimpleStreamOutputTarget, IToolable {

    @Override
    IStreamOutputTarget clone();

    boolean isAppendMode();

    void setAppendMode(boolean appendMode);

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
    ICharOut newCharOut()
            throws IOException;

    IByteOut newByteOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IPrintOut newPrintOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IPrintOut newPrintOut(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IByteOutEx newByteOutNative()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IByteOutEx newByteOutNative(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    OutputStream newOutputStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    OutputStream newOutputStream(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IDataOutput newDataOutput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IDataOutput newDataOutput(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IObjectOutput newObjectOutput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IObjectOutput newObjectOutput(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    PrintStream newPrintStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    PrintStream newPrintStream(boolean append)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Writer newWriter()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Writer newWriter(boolean append)
            throws IOException;

}
