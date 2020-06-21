package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;

public class Sha1SignTransformer
        implements ITransformer<ICodeBin, Sha1OfTextBin> {

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
