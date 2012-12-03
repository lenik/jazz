package net.bodz.swt.viz;

import net.bodz.bas.gui.spec0.StrictGUIStyleClass;

public class StrictSwtVizStyleClass
        extends StrictGUIStyleClass
        implements ISwtVizStyleClass {

    String menuItem;
    String toolItem;
    String viewId;

    @Override
    public ISwtVizStyleClass getParent() {
        return (ISwtVizStyleClass) super.getParent();
    }

    @Override
    public String getMenuItem() {
        return menuItem;
    }

    @Override
    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public String getToolItem() {
        return toolItem;
    }

    @Override
    public void setToolItem(String toolItem) {
        this.toolItem = toolItem;
    }

    @Override
    public String getViewId() {
        return viewId;
    }

    @Override
    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

}
