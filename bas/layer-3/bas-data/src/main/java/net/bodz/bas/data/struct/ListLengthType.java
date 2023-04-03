package net.bodz.bas.data.struct;

import java.io.IOException;
import java.util.Objects;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.io.IDataIn;
import net.bodz.bas.io.IDataOut;
import net.bodz.bas.io.StringLengthType;

public class ListLengthType {

    public static final ListLengthType providedByteCount = counted("providedByteCount", 0, false);
    public static final ListLengthType providedItemCount = counted("providedItemCount", 0, true);

    /** The list is prefixed with a 8-bit byte size. */
    public static final ListLengthType byteCountPrefix8 = counted("byteCountPrefix8", 1, false);
    /** The list is prefixed with a 16-bit byte size. */
    public static final ListLengthType byteCountPrefix16 = counted("byteCountPrefix16", 2, false);
    /** The list is prefixed with a 32-bit byte size. */
    public static final ListLengthType byteCountPrefix32 = counted("byteCountPrefix32", 4, false);

    /** The list is prefixed with a 8-bit byte count. */
    public static final ListLengthType itemCountPrefix8 = counted("itemCountPrefix8", 1, true);
    /** The list is prefixed with a 16-bit byte count. */
    public static final ListLengthType itemCountPrefix16 = counted("itemCountPrefix16", 2, true);
    /** The list is prefixed with a 32-bit byte count. */
    public static final ListLengthType itemCountPrefix32 = counted("itemCountPrefix32", 4, true);

    /** The list is terminated by a zero byte. */
    public static final ListLengthType terminatedByNull = terminatedBy("terminatedByNull", (byte) 0);

    public final String name;
    public final int countFieldBytes;
    public final boolean hasCountField;
    public final boolean countByItem;
    public final boolean countByByte;
    public final boolean hasTerminator;
    public final byte terminateByte;

    private ListLengthType(String name, int countFieldBytes, boolean isItemCount) {
        this.name = name;
        this.countFieldBytes = countFieldBytes;
        this.hasCountField = (countFieldBytes == 0);
        this.countByItem = isItemCount;
        this.countByByte = !isItemCount;
        this.hasTerminator = false;
        this.terminateByte = 0;
    }

    private ListLengthType(String name, byte terminateByte) {
        this.name = name;
        this.countFieldBytes = 0;
        this.countByItem = false;
        this.countByByte = false;
        this.hasCountField = false;
        this.hasTerminator = true;
        this.terminateByte = terminateByte;
    }

    public static ListLengthType counted(String name, int countFieldBytes, boolean isItemCount) {
        return new ListLengthType(name, countFieldBytes, isItemCount);
    }

    public static ListLengthType terminatedBy(String name, byte terminateByte) {
        return new ListLengthType(name, terminateByte);
    }

    public static ListLengthType terminatedBy(byte terminateByte) {
        switch (terminateByte) {
        case 0:
            return ListLengthType.terminatedByNull;
        }
        return terminatedBy_autoNamed(terminateByte);
    }

    static ListLengthType terminatedBy_autoNamed(byte terminateByte) {
        int ord = terminateByte & 0xFF;
        String name = "terminatedBy" + ord;
        return new ListLengthType(name, terminateByte);
    }

    public StringLengthType charListToStringType() {
        if (hasTerminator) {
            char terminateChar = (char) (terminateByte & 0xFF);
            return StringLengthType.terminatedBy(terminateChar);
        } else {
            if (countByItem)
                switch (countFieldBytes) {
                case 0:
                    return StringLengthType.providedCharCount;
                case 1:
                    return StringLengthType.charCountPrefix8;
                case 2:
                    return StringLengthType.charCountPrefix16;
                case 4:
                    return StringLengthType.charCountPrefix32;
                default:
                    throw new IllegalUsageException("invalid countFieldBytes: " + countFieldBytes);
                }
            else
                switch (countFieldBytes) {
                case 0:
                    return StringLengthType.providedByteCount;
                case 1:
                    return StringLengthType.byteCountPrefix8;
                case 2:
                    return StringLengthType.byteCountPrefix16;
                case 4:
                    return StringLengthType.byteCountPrefix32;
                default:
                    throw new IllegalUsageException("invalid countFieldBytes: " + countFieldBytes);
                }
        }
    }

    public int readCountField(IDataIn in)
            throws IOException {
        switch (countFieldBytes) {
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
                terminateByte);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListLengthType other = (ListLengthType) obj;
        return countByByte == other.countByByte //
                && countFieldBytes == other.countFieldBytes //
                && hasTerminator == other.hasTerminator //
                && terminateByte == other.terminateByte;
    }

}
