package net.bodz.bas.jvm.exit;

import net.bodz.bas.err.control.ControlExit;
import net.bodz.bas.fn.IExecutableX;
import net.bodz.bas.proxy.java.security.SecurityManagerProxy;

public class CatchExit
        extends SecurityManagerProxy {

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

    /**
     * Capture exit() calls and convert to {@link ControlExit} exception.
     * 
     * @throws ControlExit
     *             If exit() is called.
     */
    public <X extends Exception> void catchExit(IExecutableX<X> executable)
            throws X {
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

    /**
     * @throws ControlExit
     */
    protected void exitHandler(int status) {
        throw new ControlExit(status);
    }

}
