package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.IComponent;

public class DefaultSection
        extends Section {

    public DefaultSection(String name, IComponent... children) {
        super(name, children);
        setSelected(true);
    }

}
