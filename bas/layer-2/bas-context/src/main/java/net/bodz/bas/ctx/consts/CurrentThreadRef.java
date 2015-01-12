package net.bodz.bas.ctx.consts;

public class CurrentThreadRef
        extends SystemConstant<Thread> {

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
