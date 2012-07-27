package net.bodz.art.installer.builtins;

import net.bodz.art.installer.AbstractComponent;
import net.bodz.art.installer.ISession;

public class SimpleComponent
        extends AbstractComponent {

    public SimpleComponent() {
        super(false, true);
    }

    private class CPack
            extends CJob {

        public CPack(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            try {
                _pack(session);
            } catch (Exception e) {
                throwException(e);
            }
        }

    }

    private class CInstall
            extends CJob {

        public CInstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            try {
                _install(session);
            } catch (Exception e) {
                throwException(e);
            }
        }

    }

    private class CUninstall
            extends CJob {

        public CUninstall(ISession session) {
            super(session);
        }

        @Override
        protected void _run() {
            try {
                _uninstall(session);
            } catch (Exception e) {
                throwException(e);
            }
        }

    }

    @Override
    protected final CJob pack(ISession session) {
        return new CPack(session);
    }

    @Override
    protected final CJob install(ISession session) {
        return new CInstall(session);
    }

    @Override
    protected final CJob uninstall(ISession session) {
        return new CUninstall(session);
    }

    protected void _pack(ISession session)
            throws Exception {
    }

    protected void _install(ISession session)
            throws Exception {
    }

    protected void _uninstall(ISession session)
            throws Exception {
    }

}
