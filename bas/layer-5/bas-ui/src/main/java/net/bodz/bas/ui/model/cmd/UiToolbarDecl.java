package net.bodz.bas.ui.model.cmd;

import net.bodz.bas.meta.codegen.IndexedType;

/**
 * Can be
 * <ul>
 * <li>main toolbar.
 * <li>named toolbars.
 * <li>toolbars for specific view.
 * </ul>
 */
@IndexedType
public abstract class UiToolbarDecl
        extends UiLocationDecl {

    @Override
    public LocationType getLocationType() {
        return LocationType.TOOLBAR;
    }

}
