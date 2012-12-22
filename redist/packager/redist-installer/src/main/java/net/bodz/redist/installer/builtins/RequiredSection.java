package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.IComponent;

public class RequiredSection
        extends Section {

    public RequiredSection(String name, IComponent... children) {
        super(name, children);
        setReadOnly(true);
        setSelected(true);
    }

}
