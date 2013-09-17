package net.bodz.pkg.installer.builtins;

import net.bodz.pkg.installer.IComponent;

public class OptionalSection
        extends Section {

    public OptionalSection(String name, IComponent... children) {
        super(name, children);
    }

}
