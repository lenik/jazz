package net.bodz.bas.shell.runner;

import java.lang.reflect.Method;

import net.bodz.bas.err.control.Control;

public class MainMethodInvoker
        extends AbstractRedirectedLauncher {

    boolean canBeNonPublic = true;

    @Override
    public int getPriority() {
        return -100;
    }

    @Override
    public Class<?> getProgramType() {
        return Object.class;
    }

    @Override
    public boolean isStaticRunner() {
        return true;
    }

    @Override
    public Method load(Class<?> mainClass) {
        try {
            Method mainMethod = mainClass.getMethod("main", String[].class);

            if (canBeNonPublic)
                mainMethod.setAccessible(true);

            return mainMethod;
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Override
    protected void runRedirected(Object context, String... args)
            throws Exception {
        Method mainMethod = (Method) context;
        Control.invoke(mainMethod, null, (Object) args);
    }

}
