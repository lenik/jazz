package net.bodz.art.installer.builtins;

import net.bodz.art.installer.AbstractComponent;
import net.bodz.art.installer.ISession;

public class SetRegistry extends AbstractComponent {

    public SetRegistry(boolean visible, boolean defaultSelection) {
        super(visible, defaultSelection);
    }

    @Override
    protected CJob install(ISession session) {
        return null;
    }

}
