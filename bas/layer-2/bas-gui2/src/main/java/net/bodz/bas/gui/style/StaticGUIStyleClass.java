package net.bodz.bas.gui.style;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.gui.css3.StaticCss3StyleClass;

public class StaticGUIStyleClass
        extends StaticCss3StyleClass
        implements IGUIElementStyleClass {

    Boolean enabled;
    Boolean readOnly;
    Integer tabOrder;
    IFontType fontType;
    IStrokeType strokeType;
    IFillType fillType;
    Map<ImageUsage, IImageData> imageMap = new HashMap<>();

    @Override
    public IGUIElementStyleClass getParent() {
        return (IGUIElementStyleClass) super.getParent();
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

    @Override
    public IImageData getImage(ImageUsage usage) {
        return imageMap.get(usage);
    }

    @Override
    public void setImage(ImageUsage usage, IImageData imageData) {
        imageMap.put(usage, imageData);
    }

    @Override
    public String getViewId() {
        return null;
    }

}
