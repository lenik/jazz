package net.bodz.bas.sec;

import java.lang.reflect.InvocationTargetException;

import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.lang.RunnableThrows;
import net.bodz.bas.lang.Runnables;

public class CatchExit extends _SecurityManager {

    public CatchExit(SecurityManager proxy) {
        super(proxy);
    }

    @Override
    public void checkExit(int status) {
        // super.checkExit(status);
        throw new SecurityControl(SecurityControl.EXIT, status);
    }

    @SuppressWarnings("unchecked")
    public <E extends Throwable> void doCatch(RunnableThrows<E> runnable)
            throws E, ControlExit {
        SecurityManager security0 = System.getSecurityManager();
        SecurityControl control = null;
        try {
            System.setSecurityManager(this);
            ((RunnableThrows<Throwable>) runnable).run();
        } catch (SecurityControl c) {
            control = c;
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof SecurityControl)
                control = (SecurityControl) e.getCause();
            else
                throw (E) e;
        } catch (Throwable t) {
            throw (E) t;
        } finally {
            System.setSecurityManager(security0);
        }
        if (control != null)
            if (control.getCode() == SecurityControl.EXIT)
                exitHandler((Integer) control.getValue());
            else
                throw control;
    }

    protected void exitHandler(int status) {
        throw new ControlExit(status);
    }

    public static <E extends Throwable> void run(RunnableThrows<E> runnable)
            throws ControlExit, E {
        CatchExit s = new CatchExit(System.getSecurityManager());
        s.doCatch(runnable);
    }

    public static void run(Runnable runnable) throws ControlExit {
        run(Runnables.cast(runnable));
    }

}
