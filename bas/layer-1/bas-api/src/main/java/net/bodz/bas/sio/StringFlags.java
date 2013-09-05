package net.bodz.bas.sio;

public class StringFlags {

    public static final int _16BIT = 1 << 0;
    public static final int _32BIT = 1 << 1;

    /**
     * The string is prefixed with a character count.
     */
    public static final int LENGTH_PREFIX = 1 << 2;

    /**
     * The string is prefixed with a byte count.
     */
    public static final int SIZE_PREFIX = 1 << 3;

    /**
     * Use 32-bit length or size to support very long string.
     */
    public static final int LONG = 1 << 4;

    /**
     * The string is terminated by a carriage-return ('\r').
     */
    public static final int CR_TERM = 1 << 5;

    /**
     * The string is terminated by a line-feed ('\n').
     */
    public static final int LF_TERM = 1 << 6;

    /**
     * The string is terminated by a zero ('\0').
     */
    public static final int NULL_TERM = 1 << 7;

    /**
     * When multiple terminate characters are used, the sequence is important.
     */
    public static final int XXX_TERM_MASK = CR_TERM | LF_TERM | NULL_TERM;

}
