package net.bodz.bas.crypto.trans.fn;

import org.apache.commons.codec.digest.DigestUtils;


public class PartialMd5OfTextBin
        extends Md5OfTextBin {

    int length;
    int radix;
    char padLeft = '0';

    public PartialMd5OfTextBin(String text, int length, int radix) {
        super(text);
        this.length = length;
        this.radix = radix;
    }

    public long getMd5Long() {
        byte[] md5 = DigestUtils.md5(text);
        long y = 0;
        for (int i = 0; i < 8; i++) {
            y <<= 8;
            y += md5[i] & 0xFF;
        }
        return y;
    }

    @Override
    public String toString() {
        String md5int = Long.toString(getMd5Long(), radix);
        int numLen = md5int.length();
        if (numLen > length)
            // only get the right-hand part of the number.
            return md5int.substring(numLen - length);
        StringBuilder sb = new StringBuilder();
        for (int i = numLen; i < length; i++)
            sb.append(padLeft);
        sb.append(md5int);
        return sb.toString();
    }

}
