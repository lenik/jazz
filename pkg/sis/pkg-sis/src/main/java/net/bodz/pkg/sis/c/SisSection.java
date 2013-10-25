package net.bodz.pkg.sis.c;

import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisComponent;

public class SisSection
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    public SisSection(ISisComponent parent, String namePrefix) {
        super(parent, namePrefix);
    }

    @Override
    public int getWorks() {
        return 0;
    }

}
