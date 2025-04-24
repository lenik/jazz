package net.bodz.bas.io;

import net.bodz.bas.meta.decl.NotNull;

public class DecoratedTreeOut
        extends DecoratedPrintOut
        implements ITreeOut {

    private static final long serialVersionUID = 1L;

    public DecoratedTreeOut(ITreeOut _orig) {
        super(_orig);
    }

    @NotNull
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
