package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.NullCss3StyleDeclaration;

public class NullGUIElementStyleDeclaration
        extends NullCss3StyleDeclaration
        implements IGUIElementStyleDeclaration {

    @Override
    public IGUIElementStyleDeclaration getParent() {
        return null;
    }

    @Override
    public Boolean getEnabled() {
        return null;
    }

    @Override
    public void setEnabled(Boolean enabled) {
    }

    @Override
    public Boolean getReadOnly() {
        return null;
    }

    @Override
    public void setReadOnly(Boolean readOnly) {
    }

    @Override
    public Integer getTabOrder() {
        return null;
    }

    @Override
    public void setTabOrder(Integer tabOrder) {
    }

    @Override
    public Integer getMaxLength() {
        return null;
    }

    @Override
    public void setMaxLength(Integer maxLength) {
    }

    @Override
    public Character getEchoChar() {
        return null;
    }

    @Override
    public void setEchoChar(Character echoChar) {
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

    @Override
    public IImageData getImage(ImageUsage usage) {
        return null;
    }

    @Override
    public void setImage(ImageUsage usage, IImageData imageData) {
    }

    @Override
    public String getGroup() {
        return null;
    }

}
