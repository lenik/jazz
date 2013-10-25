package net.bodz.pkg.sis.c;

import net.bodz.pkg.sis.ISisComponent;

public class RequiredSection
        extends SisSection {

    private static final long serialVersionUID = 1L;

    public RequiredSection(ISisComponent parent, String namePrefix) {
        super(parent, namePrefix);
        setLocked(true);
    }

}
