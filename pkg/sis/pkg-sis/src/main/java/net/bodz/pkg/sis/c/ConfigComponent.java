package net.bodz.pkg.sis.c;

import net.bodz.pkg.sis.AbstractSisComponent;
import net.bodz.pkg.sis.ISisComponent;
import net.bodz.pkg.sisi.page.ConfigPage;
import net.bodz.swt.c.pageflow.IPage;

public abstract class ConfigComponent
        extends AbstractSisComponent {

    private static final long serialVersionUID = 1L;

    ConfigPage page;

    public ConfigComponent(ISisComponent parent, String namePrefix) {
        super(parent, namePrefix);
    }

    @Override
    public IPage getConfigPage() {
        if (page == null)
            page = createConfigPage();
        return page;
    }

    public abstract ConfigPage createConfigPage();

}
