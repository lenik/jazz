package net.bodz.bas.ctx.sys;

public class CurrentThreadRef
        extends SystemConstantRef<Thread> {

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
