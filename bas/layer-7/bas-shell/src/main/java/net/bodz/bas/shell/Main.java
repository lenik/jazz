package net.bodz.bas.shell;

import java.util.ServiceLoader;

import net.bodz.bas.c.system.System2;
import net.bodz.bas.err.control.ControlExit;
import net.bodz.bas.fn.IExecutableVarArgsX;
import net.bodz.bas.fn.IExecutableX;
import net.bodz.bas.jvm.exit.CatchExit;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.shell.runner.IProgramRunner;

@Deprecated
public class Main
        implements
            IExecutableVarArgsX<String, Exception> {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void execute(String... classAndArgs)
            throws Exception {
        String className = classAndArgs[0];

        String[] args = new String[classAndArgs.length - 1];
        System.arraycopy(classAndArgs, 1, args, 0, args.length);

        Class<?> programClass = Class.forName(className);
        Object instance = null;

        for (IProgramRunner runner : ServiceLoader.load(IProgramRunner.class)) {
            Class<?> supportedType = runner.getProgramType();
            if (!supportedType.isAssignableFrom(programClass))
                continue;

            Object loadedContext = null;

            if (runner.isStaticRunner())
                loadedContext = runner.load(programClass);
            else {
                if (instance == null)
                    instance = programClass.getConstructor().newInstance();
                loadedContext = runner.load(instance);
            }

            if (loadedContext == null)
                continue;

            runner.run(loadedContext, args);
            return;
        }

        logger.error("Not a program: " + programClass);
        System2.exit(1);
    }

    public static void main(final String... args)
            throws Exception {
        final Main main = new Main();

        try {
            CatchExit catcher = new CatchExit();
            catcher.catchExit(new IExecutableX<Exception>() {
                @Override
                public void execute()
                        throws Exception {
                    main.execute(args);
                }
            });
        } catch (ControlExit e) {
            System2.setExitStatus(e.getStatus());
        }
    }

}
