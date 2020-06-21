package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.ICodeBin;

public class TruncatedNumberBin
        implements ICodeBin {

    int length;
    long number;
    int radix;

    public TruncatedNumberBin(int length, long number) {
        this(length, number, 10);
    }

    public TruncatedNumberBin(int length, long number, int radix) {
        this.length = length;
        this.number = number;
        this.radix = radix;
    }

    @Override
    public String getStringForm() {
        String numStr = Long.toString(number, radix);
        int numLen = numStr.length();
        if (numLen > length)
            // only get the right-hand part of the number.
            return numStr.substring(numLen - length);
        StringBuilder sb = new StringBuilder();
        for (int i = numLen; i < length; i++)
            sb.append('0');
        sb.append(numStr);
        return sb.toString();
    }

    @Override
    public String toString() {
        return getStringForm();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (number ^ (number >>> 32));
        result = prime * result + length;
        result = prime * result + radix;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TruncatedNumberBin o = (TruncatedNumberBin) obj;
        if (number != o.number)
            return false;
        if (length != o.length)
            return false;
        if (radix != o.radix)
            return false;
        return true;
    }

}
