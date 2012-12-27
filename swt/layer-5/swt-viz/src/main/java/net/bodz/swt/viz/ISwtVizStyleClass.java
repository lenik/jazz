package net.bodz.swt.viz;

import net.bodz.bas.gui.style.IGUIStyleClass;

public interface ISwtVizStyleClass
        extends IGUIStyleClass {

    @Override
    ISwtVizStyleClass getParent();

    // @Override
    // SwtColor getColor();

    // @Override
    // SwtColor getBackgroundColor();

    String getMenuItem();

    void setMenuItem(String menuItem);

    String getToolItem();

    void setToolItem(String toolItem);

    String getViewId();

    void setViewId(String viewId);

}
