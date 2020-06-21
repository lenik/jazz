package net.bodz.bas.crypto.trans;

import net.bodz.bas.crypto.trans.fn.TextBin;

public class TimeHashTransient
        extends AbstractFlyingTransient {

    public TimeHashTransient(long window) {
        super(window);
    }

    @Override
    public TextBin getCode(long index) {
        String text = Long.toString(index);
        return new TextBin(text);
    }

}
