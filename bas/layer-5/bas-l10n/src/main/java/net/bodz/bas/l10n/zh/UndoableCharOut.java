package net.bodz.bas.l10n.zh;

import java.io.Flushable;
import java.io.IOException;

import net.bodz.bas.io.ISimpleCharOut;

public class UndoableCharOut
        implements ISimpleCharOut, Flushable {

    private final ISimpleCharOut out;
    private int preWrite = -1;

    public UndoableCharOut(ISimpleCharOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    @Override
    public void write(int ch)
            throws IOException {
        if (preWrite != -1)
            out.write(preWrite);
        preWrite = ch;
    }

    public int lookback() {
        return preWrite;
    }

    public int lookback(int depth) {
        if (depth != 1)
            throw new UnsupportedOperationException("only 1 char to look back is supported");
        return preWrite;
    }

    /**
     * @return <code>false</code> If nothing to undo.
     */
    public boolean undo() {
        if (preWrite == -1)
            return false;
        preWrite = -1;
        return true;
    }

    public void flush()
            throws IOException {
        if (preWrite != -1)
            out.write(preWrite);
    }

}
