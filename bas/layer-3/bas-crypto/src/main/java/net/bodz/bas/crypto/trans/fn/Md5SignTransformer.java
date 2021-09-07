package net.bodz.bas.crypto.trans.fn;

import java.util.function.Function;

public class Md5SignTransformer
        implements
            Function<ICodeBin, Md5OfTextBin> {

    String key;

    public Md5SignTransformer(String key) {
        this.key = key;
    }

    @Override
    public Md5OfTextBin apply(ICodeBin input) {
        String str = input.getStringForm();
        return new Md5OfTextBin(key + str + key);
    }

}
