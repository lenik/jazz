package net.bodz.dist.ins.builtins;

import net.bodz.dist.ins.ConfigPage;
import net.bodz.dist.ins.ISession;
import net.bodz.dist.ins.SessionJob;
import net.bodz.dist.ins._Component;

import org.eclipse.swt.widgets.Composite;

public abstract class ConfigComponent extends _Component {

    public ConfigComponent() {
        super(false, true);
    }

    @Override
    public boolean hasConfig() {
        return true;
    }

    @Override
    public abstract ConfigPage createConfig(ISession session, Composite parent, int style);

    @Override
    public double getProgressScaleToParent() {
        return 0.0;
    }

    @Override
    public SessionJob install(ISession session) {
        return null;
    }

}
