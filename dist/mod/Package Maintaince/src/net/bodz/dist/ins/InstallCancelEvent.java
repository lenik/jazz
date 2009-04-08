package net.bodz.dist.ins;

import java.util.EventObject;

public class InstallCancelEvent extends EventObject {

    private static final long serialVersionUID = 7943548040453259894L;

    public ISession            session;

    public InstallCancelEvent(Installer installer, ISession session) {
        super(installer);
        this.session = session;
    }

}
