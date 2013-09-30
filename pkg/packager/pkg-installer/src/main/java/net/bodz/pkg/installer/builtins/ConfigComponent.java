package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.AbstractComponent;
import net.bodz.pkg.installer.ISession;
import net.bodz.pkg.installer.pageflow.ConfigPage;

public abstract class ConfigComponent
        extends AbstractComponent {

    public ConfigComponent() {
        setSelected(true);
    }

    @Override
    public boolean hasConfig() {
        return true;
    }

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
