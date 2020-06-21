package net.bodz.bas.crypto.trans.fn;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5OfTextBin
        implements ICodeBin {

    String text;

    public Md5OfTextBin(String text) {
        this.text = text;
    }

    @Override
    public String getStringForm() {
        String md5 = DigestUtils.md5Hex(text);
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
        Md5OfTextBin o = (Md5OfTextBin) obj;
        if (!text.equals(o.text))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return text.hashCode();
    }

    @Override
    public String toString() {
        return getStringForm();
    }

}
