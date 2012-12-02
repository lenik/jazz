package net.bodz.swt.viz;

import net.bodz.bas.gui.spec0.IControlStyleClass;

public interface ISwtVizStyleClass
        extends IControlStyleClass {

    @Override
    ISwtVizStyleClass getParent();

    String getMenuItem();

    void setMenuItem(String menuItem);

    String getToolItem();

    void setToolItem(String toolItem);

    String getViewId();

    void setViewId(String viewId);

}
