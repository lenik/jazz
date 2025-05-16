package net.bodz.bas.rtx;

import java.util.Collections;
import java.util.Iterator;

import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.meta.decl.NotNull;

class NullOptions
        implements IMutableOptions {

    @Override
    public int size() {
        return 0;
    }

    @NotNull
    @Override
    public Iterator<IOption> iterator() {
        return Collections.emptyIterator();
    }

    @Override
    public IOption getOption(@NotNull String id) {
        return null;
    }

    @Override
    public IMutableOptions addOption(IOption option) {
        throw new ReadOnlyException();
    }

    @Override
    public IOption removeOption(@NotNull String id) {
        return null;
    }

    @Override
    public String toString() {
        return OptionsFn.dump(this);
    }

}
