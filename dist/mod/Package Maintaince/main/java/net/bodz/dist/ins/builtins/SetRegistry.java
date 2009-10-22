package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins._Component;

public class SetRegistry extends _Component {

    public SetRegistry(boolean visible, boolean defaultSelection) {
        super(visible, defaultSelection);
    }

    @Override
    protected CJob install(ISession session) {
        return null;
    }

}
