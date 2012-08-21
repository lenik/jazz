package net.bodz.bas.sio;

import net.bodz.bas.sio.util.ITextIndention;

public class DecoratedTreeOut
        extends DecoratedPrintOut
        implements ITreeOut {

    private static final long serialVersionUID = 1L;

    public DecoratedTreeOut(ITreeOut _orig) {
        super(_orig);
    }

    @Override
    public ITreeOut getWrapped() {
        return (ITreeOut) _orig;
    }

    @Override
    public ITextIndention getTextIndention() {
        return getWrapped().getTextIndention();
    }

    @Override
    public int enter() {
        return getWrapped().enter();
    }

    @Override
    public int leave() {
        return getWrapped().leave();
    }

}
