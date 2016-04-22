package net.bodz.bas.ui.model.action;

/**
 * Can be
 * <ul>
 * <li>menu-bar
 * <li>sub-menu
 * <li>context-menu
 * </ul>
 */
public abstract class UiMenuDecl
        extends UiLocationDecl {

    @Override
    public LocationType getLocationType() {
        return LocationType.MENU;
    }

    public boolean isMenubar() {
        return false;
    }

    public boolean isPreferredVertical() {
        return false;
    }

}
