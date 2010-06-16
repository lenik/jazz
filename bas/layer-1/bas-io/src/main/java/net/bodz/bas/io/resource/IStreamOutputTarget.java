package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;

import net.bodz.bas.io.resource.preparation.IFormatDumpPreparation;
import net.bodz.bas.io.resource.preparation.IStreamWritePreparation;
import net.bodz.bas.sio.IByteOutNative;
import net.bodz.bas.sio.IPrintCharOut;

public interface IStreamOutputTarget
        extends ISimpleStreamOutputTarget {

    @Override
    IStreamOutputTarget clone();

    @Override
    IStreamOutputTarget setAppendMode(boolean appendMode);

    @Override
    IStreamOutputTarget setCharset(Charset charset);

    /**
     * @return non-<code>null</code> value.
     */
    IPrintCharOut newLineCharOut()
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IByteOutNative newByteOutNative()
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

    IStreamWritePreparation forWrite()
            throws IOException;

    IFormatDumpPreparation forDump()
            throws IOException;

}
