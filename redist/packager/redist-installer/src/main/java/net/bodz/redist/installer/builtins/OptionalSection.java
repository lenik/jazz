package net.bodz.redist.installer.builtins;

import net.bodz.redist.installer.IComponent;

public class OptionalSection
        extends Section {

    public OptionalSection(String name, IComponent... children) {
        super(name, children);
    }

}
