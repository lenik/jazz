package net.bodz.bas.rtx;

import java.util.Iterator;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t.iterator.Iterators;

public class NoOptions
        extends AbstractOptions {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<IOption> iterator() {
        return Iterators.empty();
    }

    @Override
    public IOption getOption(String id) {
        return null;
    }

    @Override
    protected AbstractOptions addOption(IOption option) {
        throw new ReadOnlyException();
    }

    static final NoOptions instance = new NoOptions();

    public static NoOptions getInstance() {
        return instance;
    }

}
