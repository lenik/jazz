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

    void writeBool(boolean v)
            throws IOException;

    void writeChar(int flags, char ch)
            throws IOException;

    void writeWords(short[] buf)
            throws IOException;

    void writeWords(short[] buf, int off, int len)
            throws IOException;

    void writeDwords(int[] buf)
            throws IOException;

    void writeDwords(int[] buf, int off, int len)
            throws IOException;

    void writeQwords(long[] buf)
            throws IOException;

    void writeQwords(long[] buf, int off, int len)
            throws IOException;

    void writeFloats(float[] buf)
            throws IOException;

    void writeFloats(float[] buf, int off, int len)
            throws IOException;

    void writeDoubles(double[] buf)
            throws IOException;

    void writeDoubles(double[] buf, int off, int len)
            throws IOException;

    void writeBools(boolean[] buf)
            throws IOException;

    void writeBools(boolean[] buf, int off, int len)
            throws IOException;

    void writeChars(int flags, char[] buf)
            throws IOException;

    void writeChars(int flags, char[] buf, int off, int len)
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
