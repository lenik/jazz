package net.bodz.bas.data.struct;

import net.bodz.bas.io.StringLengthType;

public class StringBinUtils {

    public static StringLengthType defaultStringLengthType = StringLengthType.terminatedByNull;

    public static StringLengthType toStringLengthType(StringBin a) {
        if (a.length() != -1)
            return StringLengthType.providedCharCount;
        if (a.size() != -1)
            return StringLengthType.providedByteCount;

        switch (a.lengthBits()) {
        case 0:
            break;
        case 8:
            return StringLengthType.charCountPrefix8;
        case 16:
            return StringLengthType.charCountPrefix16;
        case 32:
            return StringLengthType.charCountPrefix32;
        default:
            throw new IllegalArgumentException("invalid lengthBits: " + a.lengthBits());
        }

        switch (a.sizeBits()) {
        case 0:
            break;
        case 8:
            return StringLengthType.byteCountPrefix8;
        case 16:
            return StringLengthType.byteCountPrefix16;
        case 32:
            return StringLengthType.byteCountPrefix32;
        default:
            throw new IllegalArgumentException("invalid sizeBits: " + a.sizeBits());
        }

        int term = a.term();
        if (term != -1)
            return StringLengthType.terminatedBy((char) term);

        return defaultStringLengthType;
    }

    public static int getProvidedCount(StringBin a) {
        if (a == null)
            throw new NullPointerException("a");
        int n = a.length();
        if (n != -1)
            return n;
        n = a.size();
        if (n != -1)
            return n;
        return -1;
    }

}
