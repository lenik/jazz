package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.IComponent;

public class HiddenSection
        extends Section {

    public HiddenSection(String name, IComponent... children) {
        super(name, children);
        setSelected(true);
    }

}
