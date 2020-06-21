package net.bodz.bas.crypto.trans.fn;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5OfDataBin
        implements ICodeBin {

    byte[] data;

    public Md5OfDataBin(byte[] data) {
        this.data = data;
    }

    @Override
    public String getStringForm() {
        String md5 = DigestUtils.md5Hex(data);
        return md5;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        Md5OfDataBin o = (Md5OfDataBin) obj;
        if (!Arrays.equals(data, o.data))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return getStringForm();
    }

}
