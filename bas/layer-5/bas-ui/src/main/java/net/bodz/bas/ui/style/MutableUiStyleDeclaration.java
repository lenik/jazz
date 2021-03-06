package net.bodz.bas.ui.style;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.ui.css3.MutableCss3StyleDeclaration;

public class MutableUiStyleDeclaration
        extends MutableCss3StyleDeclaration
        implements IUiElementStyleDeclaration {

    private static final long serialVersionUID = 1L;

    IFontType fontType;
    IStrokeType strokeType;
    IFillType fillType;
    Map<ImageUsage, IImageData> imageMap = new HashMap<ImageUsage, IImageData>();

    Boolean enabled;
    Boolean readOnly;
    Integer tabOrder;
    Integer maxLength;
    Character echoChar;

    @Override
    public IUiElementStyleDeclaration getParent() {
        return (IUiElementStyleDeclaration) super.getParent();
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
    public String getGroup() {
        return null;
    }

    /** ⇱ Implementaton Of {@link IInputBehaviorDeclaration}. */
    /* _____________________________ */static section.iface __INPUT_BEHAVIOR__;

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

    @Override
    public Character getEchoChar() {
        return echoChar;
    }

    @Override
    public void setEchoChar(Character echoChar) {
        this.echoChar = echoChar;
    }

}
