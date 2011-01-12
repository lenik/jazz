package net.bodz.bas.text.codec;

import java.io.IOException;

import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;
import net.bodz.bas.util.exception.DecodeException;
import net.bodz.bas.util.exception.EncodeException;

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
