package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.ISession;

public class SetRegistry
        extends AbstractComponent {

    public SetRegistry(boolean visible, boolean defaultSelection) {
        super(visible, defaultSelection);
    }

    @Override
    protected CJob install(ISession session) {
        return null;
    }

}
