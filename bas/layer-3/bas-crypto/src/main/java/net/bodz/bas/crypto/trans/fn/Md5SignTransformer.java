package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.AbstractTransformer;

public class Md5SignTransformer
        extends AbstractTransformer<ICodeBin, Md5OfTextBin> {

    String key;

    public Md5SignTransformer(String key) {
        this.key = key;
    }

    @Override
    public Md5OfTextBin transform(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new Md5OfTextBin(key + str + key);
    }

}
