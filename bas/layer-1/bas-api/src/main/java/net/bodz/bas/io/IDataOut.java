package net.bodz.bas.io;

import java.io.IOException;

import net.bodz.bas.io.impl.NullDataOut;

/**
 * The byte-order of this interface is undefined.
 */
public interface IDataOut
        extends IByteOut {

    boolean isBigEndian();

    void writeByte(int b)
            throws IOException;

    void writeWord(int w)
            throws IOException;

    void writeDword(int dw)
            throws IOException;

    void writeQword(long qw)
            throws IOException;

    /**
     * @see Float#floatToIntBits(float)
     */
    void writeFloat(float v)
            throws IOException;

    /**
     * @see Double#doubleToLongBits(double)
     */
    void writeDouble(double v)
            throws IOException;

    void writeBoolean(boolean v)
            throws IOException;

    void writeUtf8Char(char ch)
            throws IOException;

    /**
     * Write UTF-encoded string.
     * 
     * @see StringFlags#_16BIT
     * @see StringFlags#_32BIT
     * @see StringFlags#NULL_TERM
     * @see StringFlags#LENGTH_PREFIX
     * @see StringFlags#SIZE_PREFIX
     * @see StringFlags#LONG
     */
    void writeString(int flags, String str)
            throws IOException;

    /**
     * Write string in specified encoding.
     * 
     * @param flags
     *            The flags {@link StringFlags#_16BIT} and {@link StringFlags#_32BIT} are ignored.
     * @see StringFlags#NULL_TERM
     * @see StringFlags#LENGTH_PREFIX
     * @see StringFlags#SIZE_PREFIX
     * @see StringFlags#LONG
     * 
     * @param encoding
     *            The encoding. <code>null</code> for UTF-8/16/32.
     */
    void writeString(int flags, String str, String encoding)
            throws IOException;

    IDataOut NULL_LE = new NullDataOut(false);
    IDataOut NULL_BE = new NullDataOut(true);

}
