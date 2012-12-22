package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.IComponent;

public class DefaultSection
        extends Section {

    public DefaultSection(String name, IComponent... children) {
        super(name, children);
        setSelected(true);
    }

}
