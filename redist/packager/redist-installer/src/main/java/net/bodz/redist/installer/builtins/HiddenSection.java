package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.IComponent;

public class HiddenSection
        extends Section {

    public HiddenSection(String name, IComponent... children) {
        super(name, children);
        setSelected(true);
    }

}
