package net.bodz.bas.vfs.impl.filter;

public interface ISimpleCodec {

    int NaN = -1;

    /**
     * Encode a single byte.
     * 
     * @param byt
     *            The byte to be encoded.
     * @return The encoded byte. Return {@value #NaN} if the byte couldn't be encoded.
     */
    int encode(int byt);

    /**
     * Decode a single byte.
     * 
     * @param byt
     *            The byte to be decoded.
     * @return The decoded byte. Return {@value #NaN} if the byte couldn't be decoded.
     */
    int decode(int byt);

    int encode(byte[] bytes, int off, int len);

    int decode(byte[] bytes, int off, int len);

}
