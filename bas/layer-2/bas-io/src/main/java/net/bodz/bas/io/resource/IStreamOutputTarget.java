package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

import net.bodz.bas.sio.IByteOutEx;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.util.IToolable;

/**
 * 提供更多的打开方式。
 */
public interface IStreamOutputTarget
        extends ISimpleStreamOutputTarget, IToolable {

    @Override
    IStreamOutputTarget clone();

    @Override
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
    @Override
    ICharOut newCharOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IPrintOut newPrintOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IByteOutEx newByteOutNative()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    OutputStream newOutputStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    DataOutput newDataOutput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    ObjectOutput newObjectOutput()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    PrintStream newPrintStream()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    Writer newWriter()
            throws IOException;

}
