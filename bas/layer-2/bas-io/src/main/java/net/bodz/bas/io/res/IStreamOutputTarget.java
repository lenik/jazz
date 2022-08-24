package net.bodz.bas.io.res;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.OpenOption;

import net.bodz.bas.c.java.io.IDataOutput;
import net.bodz.bas.c.java.io.IObjectOutput;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.res.tools.IStreamWriting;
import net.bodz.bas.io.res.tools.StreamWriting;
import net.bodz.bas.sugar.IToChain;

public interface IStreamOutputTarget
        extends
            ISimpleStreamOutputTarget,
            IToChain {

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
    IDataOut newDataOutLE(OpenOption... options)
            throws IOException;

    /**
     * @return non-<code>null</code> value.
     */
    IDataOut newDataOutBE(OpenOption... options)
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

    /**
     * @return non-<code>null</code> value.
     */
    BufferedWriter newBufferedWriter(OpenOption... options)
            throws IOException;

    default IStreamWriting write() {
        return new StreamWriting(this);
    }

}
