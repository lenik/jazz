package net.bodz.bas.sio;

import net.bodz.bas.sio.util.ITextIndention;
import net.bodz.bas.sio.util.TextIndention;

public class NullTreeOut
        extends NullPrintCharOut
        implements ITreeOut {

    private ITextIndention dummy = new TextIndention();

    @Override
    public ITextIndention getTextIndention() {
        return dummy;
    }

    @Override
    public int enter() {
        return dummy.increaseIndentLevel();
    }

    @Override
    public int leave() {
        return dummy.decreaseIndentLevel();
    }

}
