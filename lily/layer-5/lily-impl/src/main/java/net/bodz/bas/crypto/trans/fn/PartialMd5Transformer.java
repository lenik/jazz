package net.bodz.bas.crypto.trans.fn;

import net.bodz.bas.err.TransformException;
import net.bodz.bas.fn.ITransformer;

public class PartialMd5Transformer
        implements ITransformer<ICodeBin, PartialMd5OfTextBin> {

    int length;
    int radix;

    public PartialMd5Transformer(int length, int radix) {
        this.length = length;
        this.radix = radix;
    }

    @Override
    public PartialMd5OfTextBin transform(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new PartialMd5OfTextBin(str, length, radix);
    }

}
