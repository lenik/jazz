package net.bodz.bas.data.codec;

import java.io.IOException;

import net.bodz.bas.err.DecodeException;
import net.bodz.bas.err.EncodeException;
import net.bodz.bas.io.IByteIn;
import net.bodz.bas.io.IByteOut;
import net.bodz.bas.io.ICharIn;
import net.bodz.bas.io.ICharOut;

public interface ISimpleByteCodec {

    /**
     * @throws NullPointerException
     *             If <code>in</code> or <code>out</code> is <code>null</code>.
     */
    void encode(IByteIn in, ICharOut out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If <code>in</code> or <code>out</code> is <code>null</code>.
     */
    void decode(ICharIn in, IByteOut out)
            throws IOException, DecodeException;

}
