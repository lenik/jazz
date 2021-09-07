package net.bodz.bas.crypto.trans.fn;

import java.util.function.Function;

public class PartialMd5Transformer
        implements
            Function<ICodeBin, PartialMd5OfTextBin> {

    int length;
    int radix;

    public PartialMd5Transformer(int length, int radix) {
        this.length = length;
        this.radix = radix;
    }

    @Override
    public PartialMd5OfTextBin apply(ICodeBin input) {
        String str = input.getStringForm();
        return new PartialMd5OfTextBin(str, length, radix);
    }

}
