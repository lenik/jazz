package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;

public class Md5SignTransformer
        implements ITransformer<ICodeBin, Md5OfTextBin> {

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
