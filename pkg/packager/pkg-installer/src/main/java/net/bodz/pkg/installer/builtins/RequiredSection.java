package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.IComponent;

public class RequiredSection
        extends Section {

    public RequiredSection(String name, IComponent... children) {
        super(name, children);
        setReadOnly(true);
        setSelected(true);
    }

}
