package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.AbstractTransformer;

public class Sha1Transformer
        extends AbstractTransformer<ICodeBin, Sha1OfTextBin> {

    public Sha1Transformer() {
    }

    @Override
    public Sha1OfTextBin transform(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new Sha1OfTextBin(str);
    }

}
