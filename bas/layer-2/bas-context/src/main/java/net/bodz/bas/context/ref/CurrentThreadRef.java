package net.bodz.bas.context.ref;

import net.bodz.bas.t.ref.ReadOnlyRef;

public class CurrentThreadRef
        extends ReadOnlyRef<Thread> {

    @Override
    public Class<? extends Thread> getValueType() {
        return Thread.class;
    }

    @Override
    public Thread get() {
        return Thread.currentThread();
    }

    public static final CurrentThreadRef INSTANCE = new CurrentThreadRef();

}
