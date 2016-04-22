package net.bodz.bas.ui.model.action;

/**
 * Can be
 * <ul>
 * <li>main toolbar.
 * <li>named toolbars.
 * <li>toolbars for specific view.
 * </ul>
 */
public abstract class UiToolbarDecl
        extends UiLocationDecl {

    @Override
    public LocationType getLocationType() {
        return LocationType.TOOLBAR;
    }

}
