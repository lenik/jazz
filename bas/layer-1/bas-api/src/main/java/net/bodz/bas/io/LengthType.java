package net.bodz.bas.io;

import java.io.IOException;

public enum LengthType {

    /** The string is prefixed with a 8-bit character count. */
    charCountPrefix8(1, true),
    /** The string is prefixed with a 16-bit character count. */
    charCountPrefix16(2, true),
    /** The string is prefixed with a 32-bit character count. */
    charCountPrefix32(4, true),

    /** The string is prefixed with a 8-bit byte count. */
    byteCountPrefix8(1, false),
    /** The string is prefixed with a 16-bit byte count. */
    byteCountPrefix16(2, false),
    /** The string is prefixed with a 32-bit byte count. */
    byteCountPrefix32(4, false),

    /** The string is terminated by a carriage-return ('\r'). */
    terminatedByCr('\r'),
    /** The string is terminated by a line-feed ('\n'). */
    terminatedByNl('\n'),
    /** The string is terminated by a zero ('\0'). */
    terminatedByNul('\0'),

    ;

    public final int headerSize;
    public final boolean countByChars;
    public final boolean countByBytes;
    public final boolean hasTerminator;
    public final char terminatorChar;

    private LengthType(int headerSize, boolean countByChar) {
        this.headerSize = headerSize;
        this.countByChars = countByChar;
        this.countByBytes = !countByChar;
        this.hasTerminator = false;
        this.terminatorChar = 0;
    }

    private LengthType(char delim) {
        this.headerSize = 0;
        this.countByChars = false;
        this.countByBytes = false;
        this.hasTerminator = true;
        this.terminatorChar = delim;
    }

    public StringBuilder newStringBuilder(int length) {
        if (hasTerminator)
            return new StringBuilder();
        if (countByChars)
            return new StringBuilder(length);
        else
            return new StringBuilder(length * 3);
    }

    public int readLengthHeader(IDataIn in)
            throws IOException {
        switch (headerSize) {
        case 1:
            return in.readByte();
        case 2:
            return in.readWord();
        case 4:
            return in.readDword();
        }
        return 0;
    }

    public void writeLengthHeader(IDataOut out, int length)
            throws IOException {
        switch (headerSize) {
        case 1:
            out.writeByte(length);
            break;
        case 2:
            out.writeWord(length);
            break;
        case 4:
            out.writeDword(length);
            break;
        }
    }

}
