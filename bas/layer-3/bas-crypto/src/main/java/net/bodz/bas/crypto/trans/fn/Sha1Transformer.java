package net.bodz.bas.crypto.trans.fn;

import java.util.function.Function;

import net.bodz.bas.err.TransformException;

public class Sha1Transformer
        implements
            Function<ICodeBin, Sha1OfTextBin> {

    public Sha1Transformer() {
    }

    @Override
    public Sha1OfTextBin apply(ICodeBin input)
            throws TransformException {
        String str = input.getStringForm();
        return new Sha1OfTextBin(str);
    }

}
