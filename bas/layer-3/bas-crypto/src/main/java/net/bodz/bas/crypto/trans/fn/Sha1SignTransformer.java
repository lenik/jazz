package net.bodz.bas.crypto.trans.fn;

import java.util.function.Function;

public class Sha1SignTransformer
        implements
            Function<ICodeBin, Sha1OfTextBin> {

    String key;

    public Sha1SignTransformer(String key) {
        if (key == null)
            throw new NullPointerException("key");
        this.key = key;
    }

    @Override
    public Sha1OfTextBin apply(ICodeBin input) {
        String guard = input.getStringForm();
        return new Sha1OfTextBin(guard + key + guard);
    }

}
