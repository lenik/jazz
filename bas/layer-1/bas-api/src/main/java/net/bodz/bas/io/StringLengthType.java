package net.bodz.bas.io;

import java.io.IOException;

public enum StringLengthType {

    providedCharCount(0, true),
    providedByteCount(0, false),

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
    public final boolean countByChar;
    public final boolean countByByte;
    public final boolean fixedSize;
    public final boolean hasTerminator;
    public final char terminateChar;

    private StringLengthType(int headerSize, boolean countByChar) {
        this.headerSize = headerSize;
        this.countByChar = countByChar;
        this.countByByte = !countByChar;
        this.fixedSize = headerSize == 0;
        this.hasTerminator = false;
        this.terminateChar = 0;
    }

    private StringLengthType(char terminateChar) {
        this.headerSize = 0;
        this.countByChar = false;
        this.countByByte = false;
        this.fixedSize = false;
        this.hasTerminator = true;
        this.terminateChar = terminateChar;
    }

    public StringBuilder newStringBuilder(int length) {
        if (hasTerminator)
            return new StringBuilder();
        if (countByChar)
            return new StringBuilder(length);
        else
            return new StringBuilder(length * 3);
    }

    public int readCountField(IDataIn in)
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

    public void writeCountField(IDataOut out, int count)
            throws IOException {
        switch (headerSize) {
        case 1:
            out.writeByte(count);
            break;
        case 2:
            out.writeWord(count);
            break;
        case 4:
            out.writeDword(count);
            break;
        }
    }

}
