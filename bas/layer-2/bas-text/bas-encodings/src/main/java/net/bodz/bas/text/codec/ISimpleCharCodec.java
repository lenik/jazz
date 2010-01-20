package net.bodz.bas.text.codec;

import java.io.IOException;

import net.bodz.bas.exceptions.DecodeException;
import net.bodz.bas.exceptions.EncodeException;
import net.bodz.bas.sio.IByteIn;
import net.bodz.bas.sio.IByteOut;
import net.bodz.bas.sio.ICharIn;
import net.bodz.bas.sio.ICharOut;

public interface ISimpleCharCodec {

    /**
     * @throws NullPointerException
     *             If <code>in</code> or <code>out</code> is <code>null</code>.
     */
    void encode(ICharIn in, IByteOut out)
            throws IOException, EncodeException;

    /**
     * @throws NullPointerException
     *             If <code>in</code> or <code>out</code> is <code>null</code>.
     */
    void decode(IByteIn in, ICharOut out)
            throws IOException, DecodeException;

}
