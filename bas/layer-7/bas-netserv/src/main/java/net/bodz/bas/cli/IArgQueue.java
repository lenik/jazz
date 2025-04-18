package net.bodz.bas.cli;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.repr.path.IBasicTokenQueue;

public interface IArgQueue
        extends IArguments,
                IBasicTokenQueue {

    @NotNull
    @Override
    default String[] toArray() {
        return IBasicTokenQueue.super.toArray();
    }

}
