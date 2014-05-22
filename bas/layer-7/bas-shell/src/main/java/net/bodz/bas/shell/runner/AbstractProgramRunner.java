package net.bodz.bas.shell.runner;

public abstract class AbstractProgramRunner
        implements IProgramRunner {

    int exitStatus;

    @Override
    public int getPriority() {
        return 0;
    }

    public boolean loadRun(Class<?> programClass, String... args)
            throws Exception {
        Object ctx = load(programClass);
        if (ctx == null)
            return false;
        run(ctx, args);
        return true;
    }

    public boolean loadRun(Object instance, String... args)
            throws Exception {
        Object ctx = load(instance);
        if (ctx == null)
            return false;
        run(ctx, args);
        return true;
    }

    @Override
    public Object load(Class<?> programClass) {
        return null;
    }

    @Override
    public Object load(Object instance) {
        return null;
    }

    public int getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    protected void onExit(int status) {
        System.exit(status);
    }

}
