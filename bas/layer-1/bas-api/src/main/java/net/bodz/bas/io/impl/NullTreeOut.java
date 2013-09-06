package net.bodz.bas.io.impl;

import net.bodz.bas.io.ITextIndention;
import net.bodz.bas.io.ITreeOut;

public class NullTreeOut
        extends NullPrintOut
        implements ITreeOut {

    @Override
    public ITextIndention getTextIndention() {
        return ITextIndention.NULL;
    }

    @Override
    public int enter() {
        return 0;
    }

    @Override
    public int leave() {
        return 0;
    }

}
