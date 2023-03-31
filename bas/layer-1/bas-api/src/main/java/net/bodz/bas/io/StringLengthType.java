package net.bodz.bas.io;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringLengthType {

    public static final StringLengthType providedCharCount = counted("providedCharCount", 0, true, false);
    public static final StringLengthType providedByteCount = counted("providedByteCount", 0, false, false);
    public static final StringLengthType providedRawCharCount = counted("providedRawCharCount", 0, true, true);
    public static final StringLengthType providedRawByteCount = counted("providedRawByteCount", 0, false, true);

    /** The string is prefixed with a 8-bit character count. */
    public static final StringLengthType charCountPrefix8 = counted("charCountPrefix8", 1, true, false);
    /** The string is prefixed with a 16-bit character count. */
    public static final StringLengthType charCountPrefix16 = counted("charCountPrefix16", 2, true, false);
    /** The string is prefixed with a 32-bit character count. */
    public static final StringLengthType charCountPrefix32 = counted("charCountPrefix32", 4, true, false);

    /** The string is prefixed with a 8-bit byte count. */
    public static final StringLengthType byteCountPrefix8 = counted("byteCountPrefix8", 1, false, false);
    /** The string is prefixed with a 16-bit byte count. */
    public static final StringLengthType byteCountPrefix16 = counted("byteCountPrefix16", 2, false, false);
    /** The string is prefixed with a 32-bit byte count. */
    public static final StringLengthType byteCountPrefix32 = counted("byteCountPrefix32", 4, false, false);

    /** The string is prefixed with a 8-bit character count. */
    public static final StringLengthType rawCharCountPrefix8 = counted("charCountPrefix8", 1, true, true);
    /** The string is prefixed with a 16-bit character count. */
    public static final StringLengthType rawCharCountPrefix16 = counted("charCountPrefix16", 2, true, true);
    /** The string is prefixed with a 32-bit character count. */
    public static final StringLengthType rawCharCountPrefix32 = counted("charCountPrefix32", 4, true, true);

    /** The string is prefixed with a 8-bit byte count. */
    public static final StringLengthType rawByteCountPrefix8 = counted("byteCountPrefix8", 1, false, true);
    /** The string is prefixed with a 16-bit byte count. */
    public static final StringLengthType rawByteCountPrefix16 = counted("byteCountPrefix16", 2, false, true);
    /** The string is prefixed with a 32-bit byte count. */
    public static final StringLengthType rawByteCountPrefix32 = counted("byteCountPrefix32", 4, false, true);

    public static final StringLengthType terminatedByNull = terminatedBy("terminatedByNull", '\0');
    public static final StringLengthType terminatedByCr = terminatedBy("terminatedByCr", '\r');
    public static final StringLengthType terminatedByLf = terminatedBy("terminatedByLf", '\n');
    public static final StringLengthType terminatedBySpace = terminatedBy("terminatedBySpace", ' ');
    public static final StringLengthType terminatedByTab = terminatedBy("terminatedByTab", '\t');

    public final String name;
    public final int countFieldBytes;
    public final boolean hasCountField;
    public final boolean countByChar;
    public final boolean countByByte;
    public final boolean raw;
    public final boolean autoCompact;
    public final boolean hasTerminator;
    public final char terminator;

    public StringLengthType(String name, int countFieldBytes, boolean countByChar, boolean raw) {
        this.name = name;
        this.countFieldBytes = countFieldBytes;
        this.hasCountField = countFieldBytes > 0;
        this.countByChar = countByChar;
        this.countByByte = !countByChar;
        this.raw = raw;
        this.autoCompact = !raw;
        this.hasTerminator = false;
        this.terminator = 0;
    }

    public StringLengthType(String name, char terminator) {
        this.name = name;
        this.countFieldBytes = 0; // not used
        this.hasCountField = false; // not used
        this.countByChar = false; // not used
        this.countByByte = false; // not used
        this.raw = false; // not used
        this.autoCompact = !raw; // not used
        this.hasTerminator = true;
        this.terminator = terminator;
    }

    public static StringLengthType counted(String name, int countFieldBytes, boolean countByChar, boolean raw) {
        return new StringLengthType(name, countFieldBytes, countByChar, raw);
    }

    public static StringLengthType terminatedBy(String name, char terminateChar) {
        return new StringLengthType(name, terminateChar);
    }

    public static StringLengthType terminatedBy(char terminateChar) {
        switch (terminateChar) {
        case 0:
            return StringLengthType.terminatedByNull;
        case '\r':
            return StringLengthType.terminatedByCr;
        case '\n':
            return StringLengthType.terminatedByLf;
        case ' ':
            return StringLengthType.terminatedBySpace;
        case '\t':
            return StringLengthType.terminatedByTab;
        }
        return terminatedBy_autoNamed(terminateChar);
    }

    static Pattern wordsPattern = Pattern.compile("\\w+");

    static StringLengthType terminatedBy_autoNamed(char terminateChar) {
        String charName = Character.getName(terminateChar);
        if (charName == null)
            charName = "Unassigned";

        StringBuilder charId = new StringBuilder(charName.length());
        Matcher matcher = wordsPattern.matcher(charName);
        while (matcher.find()) {
            String word = matcher.group();
            String ucfirst = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            charId.append(ucfirst);
        }

        String name = "terminatedBy" + charId;
        return new StringLengthType(name, terminateChar);
    }

    public StringBuilder newStringBuilder(int length) {
        if (hasTerminator)
            return new StringBuilder();
        if (countByChar)
            return new StringBuilder(length);
        else
            return new StringBuilder(length * 3);
    }

    public int readCountField(IDataIn in, int count)
            throws IOException {
        switch (countFieldBytes) {
        case 0:
            return count;
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
        switch (countFieldBytes) {
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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(//
                countByByte, //
                countFieldBytes, //
                hasTerminator, //
                terminator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StringLengthType other = (StringLengthType) obj;
        return countByByte == other.countByByte //
                && countFieldBytes == other.countFieldBytes //
                && hasTerminator == other.hasTerminator //
                && terminator == other.terminator;
    }

}
