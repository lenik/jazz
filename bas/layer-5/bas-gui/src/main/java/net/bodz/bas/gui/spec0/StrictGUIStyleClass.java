package net.bodz.bas.gui.spec0;

import net.bodz.bas.gui.css3.StrictCss3StyleClass;

public class StrictGUIStyleClass
        extends StrictCss3StyleClass
        implements IGUIStyleClass {

    Boolean enabled;
    Boolean readOnly;
    Integer tabOrder;
    IFontType fontType;
    IStrokeType strokeType;
    IFillType fillType;

    @Override
    public IGUIStyleClass getParent() {
        return (IGUIStyleClass) super.getParent();
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Boolean getReadOnly() {
        return readOnly;
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public Integer getTabOrder() {
        return tabOrder;
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
        this.tabOrder = tabOrder;
    }

    @Override
    public IFontType getFontType() {
        return fontType;
    }

    @Override
    public void setFontType(IFontType fontType) {
        this.fontType = fontType;
    }

    @Override
    public IStrokeType getStrokeType() {
        return strokeType;
    }

    @Override
    public void setStrokeType(IStrokeType strokeType) {
        this.strokeType = strokeType;
    }

    @Override
    public IFillType getFillType() {
        return fillType;
    }

    @Override
    public void setFillType(IFillType fillType) {
        this.fillType = fillType;
    }

}
