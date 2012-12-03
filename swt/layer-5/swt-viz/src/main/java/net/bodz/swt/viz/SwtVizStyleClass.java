package net.bodz.swt.viz;

import net.bodz.bas.gui.css3.ICss3StyleClass;
import net.bodz.bas.gui.spec0.GUIStyleClass;
import net.bodz.bas.gui.spec0.IGUIStyleClass;

public class SwtVizStyleClass
        extends GUIStyleClass
        implements ISwtVizStyleClass {

    public SwtVizStyleClass(IGUIStyleClass parent) {
        super(parent);
    }

    @Override
    public ISwtVizStyleClass getParent() {
        return (ISwtVizStyleClass) super.getParent();
    }

    @Override
    public void setParent(ICss3StyleClass parent) {
        if (parent == null)
            parent = new StrictSwtVizStyleClass();
        else {
            if (!(parent instanceof ISwtVizStyleClass))
                throw new IllegalArgumentException("Incompatible parent: " + parent);
        }
        super.setParent(parent);
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
