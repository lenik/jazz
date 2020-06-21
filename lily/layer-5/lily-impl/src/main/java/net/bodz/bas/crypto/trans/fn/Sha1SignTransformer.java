package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.AbstractTransformer;

public class Sha1SignTransformer
        extends AbstractTransformer<ICodeBin, Sha1OfTextBin> {

    String key;

    public Sha1SignTransformer(String key) {
        if (key == null)
            throw new NullPointerException("key");
        this.key = key;
    }

    @Override
    public Sha1OfTextBin transform(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new Sha1OfTextBin(key + str + key);
    }

}
