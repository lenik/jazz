package net.bodz.lily.site.ui.action;

import net.bodz.bas.meta.decl.Priority;
import net.bodz.bas.site.ui.action.BasSiteMenu;
import net.bodz.bas.ui.model.action.Location;
import net.bodz.bas.ui.model.action.UiMenuDecl;
import net.bodz.bas.ui.model.action.builtin.MainMenubar;

/**
 * @image
 * @label ☘
 * @see BasSiteMenu
 */
@Location(MainMenubar.class)
@Priority(-80)
public class LilySiteMenu
        extends UiMenuDecl {

}
