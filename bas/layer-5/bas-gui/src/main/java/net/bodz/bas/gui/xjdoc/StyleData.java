package net.bodz.bas.gui.xjdoc;

import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IStroke;
import net.bodz.bas.i18n.util.Measure;

public class StyleData {

    Visibility visibility = Visibility.visible;

    Measure width;
    Measure height;

    Integer borderWidth;
    IColor borderColor;
    IStroke borderStroke;

    Boolean readOnly;
    Integer tabOrder;

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    public IColor getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(IColor borderColor) {
        this.borderColor = borderColor;
    }

    public IStroke getBorderStroke() {
        return borderStroke;
    }

    public void setBorderStroke(IStroke borderStroke) {
        this.borderStroke = borderStroke;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Integer getTabOrder() {
        return tabOrder;
    }

    public void setTabOrder(Integer tabOrder) {
        this.tabOrder = tabOrder;
    }

}
