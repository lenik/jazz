package net.bodz.bas.gui.style;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.gui.css3.StaticCss3StyleDeclaration;

public class StaticGUIStyleDeclaration
        extends StaticCss3StyleDeclaration
        implements IGUIElementStyleDeclaration {

    IFontType fontType;
    IStrokeType strokeType;
    IFillType fillType;
    Map<ImageUsage, IImageData> imageMap = new HashMap<>();

    Boolean enabled;
    Boolean readOnly;
    Integer tabOrder;
    Integer maxLength;

    @Override
    public IGUIElementStyleDeclaration getParent() {
        return (IGUIElementStyleDeclaration) super.getParent();
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

    /** ⇱ Implementaton Of {@link IInputBehaviorDeclaration}. */
    ;

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
    public Integer getMaxLength() {
        return maxLength;
    }

    @Override
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

}
