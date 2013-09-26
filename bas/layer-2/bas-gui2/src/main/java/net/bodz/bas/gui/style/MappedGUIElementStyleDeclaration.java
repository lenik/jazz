package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.MappedCss3StyleDeclaration;

public abstract class MappedGUIElementStyleDeclaration
        extends MappedCss3StyleDeclaration
        implements IGUIElementStyleDeclaration {

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

    /** ⇱ Implementaton Of {@link IInputBehaviorDeclaration}. */
    /* _____________________________ */static section.iface __INPUT_BEHAVIOR__;

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
    public Integer getMaxLength() {
        return getIntegerProperty("max-length", getParent().getMaxLength(), false);
    }

    @Override
    public void setMaxLength(Integer maxLength) {
        setProperty("max-length", maxLength);
    }

    @Override
    public Character getEchoChar() {
        return getCharProperty("echo-char", getParent().getEchoChar(), false);
    }

    @Override
    public void setEchoChar(Character echoChar) {
        setProperty("echo-char", echoChar);
    }

}
