package net.bodz.swt.viz;

import net.bodz.bas.gui.style.RootControlStyleClass;

public class RootSwtVizStyleClass
        extends RootControlStyleClass
        implements ISwtVizStyleClass {

    private static final long serialVersionUID = 1L;

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
