package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;

public class Sha1Transformer
        implements ITransformer<ICodeBin, Sha1OfTextBin> {

    public Sha1Transformer() {
    }

    @Override
    public Sha1OfTextBin transform(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new Sha1OfTextBin(str);
    }

}
