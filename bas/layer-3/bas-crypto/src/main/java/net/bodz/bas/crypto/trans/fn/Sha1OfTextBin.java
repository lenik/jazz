package net.bodz.bas.crypto.trans.fn;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha1OfTextBin
        implements ICodeBin {

    String text;

    public Sha1OfTextBin(String text) {
        this.text = text;
    }

    @Override
    public String getStringForm() {
        // Workaround for: Android < 8
        // String sha1 = DigestUtils.sha1Hex(text);
        String sha1 = new String(Hex.encodeHex(DigestUtils.sha1(text)));
        return sha1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj.getClass() != getClass())
            return false;
        Sha1OfTextBin o = (Sha1OfTextBin) obj;
        if (!text.equals(o.text))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return getStringForm().hashCode();
    }

    @Override
    public String toString() {
        return getStringForm();
    }

}
