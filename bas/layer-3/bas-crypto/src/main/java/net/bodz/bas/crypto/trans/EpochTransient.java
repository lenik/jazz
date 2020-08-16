package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.TextBin;

public class EpochTransient
        extends AbstractFlyingTransient {

    public EpochTransient(long window) {
        super(window);
    }

    @Override
    public TextBin getCode(long index) {
        String text = Long.toString(index);
        return new TextBin(text);
    }

}
