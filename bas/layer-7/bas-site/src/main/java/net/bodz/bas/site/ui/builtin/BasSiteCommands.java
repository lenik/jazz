package net.bodz.bas.site.ui.builtin;

import net.bodz.bas.ui.model.action.MutableActionProvider;

public class BasSiteCommands
        extends MutableActionProvider {

    {
        addAction(new SystemInfoAction());
    }

}
