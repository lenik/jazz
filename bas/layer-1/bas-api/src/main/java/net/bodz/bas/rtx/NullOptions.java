package net.bodz.bas.rtx;

import java.util.Collections;
import java.util.Iterator;

import net.bodz.bas.err.ReadOnlyException;

class NullOptions
        extends AbstractOptions {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<IOption> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public IOption getOption(String id) {
        return null;
    }

    @Override
    protected AbstractOptions addOption(IOption option) {
        throw new ReadOnlyException();
    }

}
