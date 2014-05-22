package net.bodz.bas.shell.runner;

import net.bodz.bas.fn.IExecutableVarArgsX;

public class ExecutableVarArgsExecutor
        extends AbstractProgramRunner {

    @Override
    public int getPriority() {
        return 800;
    }

    @Override
    public Class<?> getProgramType() {
        return IExecutableVarArgsX.class;
    }

    @Override
    public boolean isStaticRunner() {
        return false;
    }

    @Override
    public Object load(Object instance) {
        return instance;
    }

    @Override
    public void run(Object context, String... args)
            throws Exception {
        @SuppressWarnings("unchecked")
        IExecutableVarArgsX<Object, Exception> executable = (IExecutableVarArgsX<Object, Exception>) context;

        executable.execute((Object[]) args);
    }

}
