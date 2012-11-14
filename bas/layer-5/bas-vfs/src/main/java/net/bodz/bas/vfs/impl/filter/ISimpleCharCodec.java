package net.bodz.bas.vfs.impl.filter;

public interface ISimpleCharCodec {

    /**
     * Encode a single char.
     * 
     * @param ch
     *            The char to be encoded.
     * @return The encoded char. Return {@value #NaN} if the char couldn't be encoded.
     */
    int encode(char ch);

    /**
     * Decode a single char.
     * 
     * @param ch
     *            The char to be decoded.
     * @return The decoded char. Return {@value #NaN} if the char couldn't be decoded.
     */

    int decode(char ch);

    int encode(char[] chars, int off, int len);

    int decode(char[] chars, int off, int len);

}
