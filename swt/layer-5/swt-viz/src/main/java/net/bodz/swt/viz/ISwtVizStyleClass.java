package net.bodz.swt.viz;

import net.bodz.bas.gui.spec0.IGUIStyleClass;

public interface ISwtVizStyleClass
        extends IGUIStyleClass {

    @Override
    ISwtVizStyleClass getParent();

    String getMenuItem();

    void setMenuItem(String menuItem);

    String getToolItem();

    void setToolItem(String toolItem);

    String getViewId();

    void setViewId(String viewId);

}
