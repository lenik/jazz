package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.Css3StyleClass;
import net.bodz.bas.gui.css3.ICss3StyleClass;

public class GUIStyleClass
        extends Css3StyleClass
        implements IGUIStyleClass {

    public GUIStyleClass(IGUIStyleClass parent) {
        super(parent);
    }

    @Override
    public IGUIStyleClass getParent() {
        return (IGUIStyleClass) super.getParent();
    }

    @Override
    public void setParent(ICss3StyleClass parent) {
        if (parent == null)
            parent = new StrictGUIStyleClass();
        else {
            if (!(parent instanceof IGUIStyleClass))
                throw new IllegalArgumentException("Incompatible parent: " + parent);
        }
        super.setParent(parent);
    }

    @Override
    public Boolean getEnabled() {
        return getBooleanProperty("enabled", getParent().getEnabled(), true);
    }

    @Override
    public void setEnabled(Boolean enabled) {
        setProperty("enabled", enabled);
    }

    @Override
    public Boolean getReadOnly() {
        return getBooleanProperty("read-only", getParent().getReadOnly(), false);
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
        setProperty("read-only", readOnly);
    }

    @Override
    public Integer getTabOrder() {
        return getIntegerProperty("tab-order", getParent().getTabOrder(), false);
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
        setProperty("tab-order", tabOrder);
    }

    @Override
    public IFontType getFontType() {
        return null;
    }

    @Override
    public void setFontType(IFontType fontType) {
    }

    @Override
    public IStrokeType getStrokeType() {
        return null;
    }

    @Override
    public void setStrokeType(IStrokeType strokeType) {
    }

    @Override
    public IFillType getFillType() {
        return null;
    }

    @Override
    public void setFillType(IFillType fillType) {
    }

}
