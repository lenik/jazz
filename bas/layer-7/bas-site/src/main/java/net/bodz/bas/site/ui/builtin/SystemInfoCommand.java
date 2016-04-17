package net.bodz.bas.site.ui.builtin;

import net.bodz.bas.http.ui.cmd.UiServletCommand;

/**
 * @label System Info
 * @cmd.onclick location.href = WEB_APP + "system/info";
 */
public class SystemInfoCommand
        extends UiServletCommand {

    public SystemInfoCommand() {
        addLocation(BasSiteMenu.class);
    }

}
