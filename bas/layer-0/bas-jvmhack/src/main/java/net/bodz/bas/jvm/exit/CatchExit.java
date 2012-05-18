package net.bodz.bas.jvm.exit;

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

    public <X extends Exception> void catchExit(ExitableProgram<X> executable)
            throws X, IllegalExitException {
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

    protected void exitHandler(int status)
            throws IllegalExitException {
        throw new IllegalExitException(status);
    }

}
