package net.bodz.bas.site.ui.builtin;

import net.bodz.bas.ui.model.cmd.CommandList;

public class BasSiteCommands
        extends CommandList {

    {
        addCommand(new SystemInfoCommand());
    }

}
