package net.bodz.swt.viz;

import net.bodz.bas.gui.spec0.ControlStyleClass;
import net.bodz.bas.gui.spec0.IControlStyleClass;

public class SwtVizStyleClass
        extends ControlStyleClass
        implements ISwtVizStyleClass {

    public SwtVizStyleClass(IControlStyleClass parent) {
        super(parent);
    }

    @Override
    public ISwtVizStyleClass getParent() {
        return (ISwtVizStyleClass) super.getParent();
    }

    @Override
    public String getMenuItem() {
        return getProperty("menu-item");
    }

    @Override
    public void setMenuItem(String menuItem) {
        setProperty("menu-item", menuItem);
    }

    @Override
    public String getToolItem() {
        return getProperty("tool-item");
    }

    @Override
    public void setToolItem(String toolItem) {
        setProperty("tool-item", toolItem);
    }

    @Override
    public String getViewId() {
        return getProperty("view-id");
    }

    @Override
    public void setViewId(String viewId) {
        setProperty("view-id", viewId);
    }

}
