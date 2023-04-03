package net.bodz.bas.data.struct;

public class ListBinUtils {

    public static ListLengthType defaultListLengthType = ListLengthType.terminatedByNull;

    public static ListLengthType toListLengthType(ListBin a) {
        if (a.nitem() != -1)
            return ListLengthType.providedItemCount;
        if (a.size() != -1)
            return ListLengthType.providedByteCount;

        switch (a.nitemBits()) {
        case 0:
            break;
        case 8:
            return ListLengthType.itemCountPrefix8;
        case 16:
            return ListLengthType.itemCountPrefix16;
        case 32:
            return ListLengthType.itemCountPrefix32;
        default:
            throw new IllegalArgumentException("invalid nitemBits: " + a.nitemBits());
        }

        switch (a.sizeBits()) {
        case 0:
            break;
        case 8:
            return ListLengthType.byteCountPrefix8;
        case 16:
            return ListLengthType.byteCountPrefix16;
        case 32:
            return ListLengthType.byteCountPrefix32;
        default:
            throw new IllegalArgumentException("invalid sizeBits: " + a.sizeBits());
        }

        int term = a.term();
        if (term != -1)
            return ListLengthType.terminatedBy((byte) term);

        return defaultListLengthType;
    }

    public static int getProvidedCount(ListBin a) {
        int n = a.nitem();
        if (n != -1)
            return n;
        n = a.size();
        if (n != -1)
            return n;
        return -1;
    }

}
