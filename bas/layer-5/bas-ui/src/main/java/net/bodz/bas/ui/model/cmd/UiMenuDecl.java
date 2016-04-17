package net.bodz.bas.ui.model.cmd;

import net.bodz.bas.meta.codegen.IndexedType;

/**
 * Can be
 * <ul>
 * <li>menu-bar
 * <li>sub-menu
 * <li>context-menu
 * </ul>
 */
@IndexedType
public abstract class UiMenuDecl
        extends UiLocationDecl {

    @Override
    public LocationType getLocationType() {
        return LocationType.MENU;
    }

}
