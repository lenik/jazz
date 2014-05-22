package net.bodz.bas.shell.runner;

public class RunnableRunner
        extends AbstractProgramRunner {

    @Override
    public int getPriority() {
        return 900;
    }

    @Override
    public Class<?> getProgramType() {
        return Runnable.class;
    }

    @Override
    public boolean isStaticRunner() {
        return false;
    }

    @Override
    public void run(Object context, String... args)
            throws Exception {
        Runnable runnable = (Runnable) context;
        runnable.run();
    }

}
