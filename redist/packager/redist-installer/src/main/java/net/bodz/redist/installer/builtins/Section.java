package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.AbstractComponent;
import net.bodz.redist.installer.IComponent;
import net.bodz.redist.installer.ISession;

public class Section
        extends AbstractComponent {

    public Section(String name, IComponent... children) {
        setName(name);
        setVisible(true);
        for (IComponent child : children)
            add(child);
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
