package net.bodz.swt.viz;

import net.bodz.bas.gui.style.MappedGUIStyleClass;

public abstract class MappedSwtVizStyleClass
        extends MappedGUIStyleClass
        implements ISwtVizStyleClass /* , IDisposable */{

    // @Override
    // public SwtColor getColor() {
    // IColor color = super.getColor();
    // SwtColor swtColor = (SwtColor) color;
    // return swtColor;
    // }

    // @Override
    // public SwtColor getBackgroundColor() {
    // IColor color = super.getBackgroundColor();
    // SwtColor swtColor = (SwtColor) color;
    // return swtColor;
    // }

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
