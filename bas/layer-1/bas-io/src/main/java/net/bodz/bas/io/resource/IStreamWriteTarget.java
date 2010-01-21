package net.bodz.bas.io.resource;

import java.io.DataOutput;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;

import net.bodz.bas.sio.IByteOutNative;
import net.bodz.bas.sio.ILineCharOut;

public interface IStreamWriteTarget
        extends ISimpleStreamWriteTarget {

    /**
     * @return non-<code>null</code> value.
     */
    ILineCharOut newLineCharOut()
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

}
