package net.bodz.bas.jvm.exit;

import net.bodz.bas.api.types.Executable;
import net.bodz.bas.commons.controlflow.ControlExit;
import net.bodz.bas.proxy.java.security.ProxySecurityManager;

/**
 * @test {@link CatchExitTest}
 */
public class CatchExit
        extends ProxySecurityManager {

    public CatchExit() {
        this(System.getSecurityManager());
    }

    public CatchExit(SecurityManager proxy) {
        super(proxy);
    }

    @Override
    public void checkExit(int status) {
        // super.checkExit(status);
        throw new SecurityControl(SecurityControl.EXIT, status);
    }

    public <E extends Throwable> void catchExit(Executable<E> executable)
            throws E, ControlExit {
        SecurityManager security0 = System.getSecurityManager();
        try {
            System.setSecurityManager(this);
            executable.execute();
        } catch (SecurityControl control) {
            if (control.getCode() == SecurityControl.EXIT)
                exitHandler((Integer) control.getValue());
            else
                throw control;
        } finally {
            System.setSecurityManager(security0);
        }
    }

    protected void exitHandler(int status) {
        throw new ControlExit(status);
    }

}
