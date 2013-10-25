package net.bodz.pkg.sis.c;

import net.bodz.pkg.sis.ISisComponent;

public class HiddenSection
        extends SisSection {

    private static final long serialVersionUID = 1L;

    public HiddenSection(ISisComponent parent, String namePrefix) {
        super(parent, namePrefix);
        setVisible(false);
    }

}
