package net.bodz.bas.c.string;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.io.ICharOut;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.io.impl.TreeOutImpl;

public abstract class AbstractTextBlock
        implements ITextBlock {

    public void format(ICharOut out) {
        ITreeOut treeOut = TreeOutImpl.from(out);
        format(treeOut);
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        format(buf);
        return buf.toString();
    }

}
