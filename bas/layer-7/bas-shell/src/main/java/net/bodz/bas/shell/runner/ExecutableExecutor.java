package net.bodz.bas.shell.runner;

import net.bodz.bas.fn.IExecutableX;

public class ExecutableExecutor
        extends AbstractProgramRunner {

    @Override
    public int getPriority() {
        return 820;
    }

    @Override
    public Class<?> getProgramType() {
        return IExecutableX.class;
    }

    @Override
    public boolean isStaticRunner() {
        return false;
    }

    @Override
    public void run(Object context, String... args)
            throws Exception {
        @SuppressWarnings("unchecked")
        IExecutableX<Exception> executable = (IExecutableX<Exception>) context;

        executable.execute();
    }

}
