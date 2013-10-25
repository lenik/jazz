package net.bodz.pkg.sis.c;

import net.bodz.pkg.sis.ISisComponent;

public class OptionalSection
        extends SisSection {

    private static final long serialVersionUID = 1L;

    public OptionalSection(ISisComponent parent, String namePrefix) {
        super(parent, namePrefix);
        setSelected(false);
    }

}
