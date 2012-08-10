package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.ConfigPage;
import net.bodz.redist.installer.ISession;

public abstract class ConfigComponent
        extends AbstractComponent {

    public ConfigComponent() {
        super(false, true);
    }

    @Override
    public boolean hasConfig() {
        return true;
    }

    @Override
    public abstract ConfigPage createConfig(ISession session);

    @Override
    public double getProgressScaleToParent() {
        return 0.0;
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
