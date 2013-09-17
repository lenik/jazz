package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.AbstractComponent;
import net.bodz.pkg.installer.IComponent;
import net.bodz.pkg.installer.ISession;

public class Section
        extends AbstractComponent {

    public Section(String name, IComponent... children) {
        setId(name);
        setVisible(true);
        for (IComponent child : children)
            add(child);
    }

    @Override
    public CJob install(ISession session) {
        return null;
    }

}
