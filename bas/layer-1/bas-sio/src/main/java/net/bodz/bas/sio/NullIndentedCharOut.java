package net.bodz.bas.sio;

import net.bodz.bas.sio.indent.IIndentedOut;
import net.bodz.bas.sio.indent.ITextIndention;
import net.bodz.bas.sio.indent.TextIndention;

public class NullIndentedCharOut
        extends NullPrintCharOut
        implements IIndentedOut {

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
