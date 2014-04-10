package net.bodz.bas.gui.style;

import net.bodz.bas.gui.css3.ICss3StyleDeclaration;

public interface IGUIElementStyleDeclaration
        extends ICss3StyleDeclaration, IInputBehaviorDeclaration {

    @Override
    IGUIElementStyleDeclaration getParent();

    /**
     * @see #getFontFamily()
     * @see #getFontSize()
     * @see #getFontSizeType()
     * @see #getFontStyle()
     * @see #getFontWeight()
     * @see #getFontType()
     * @see #getFontVariant()
     */
    IFontType getFontType();

    void setFontType(IFontType fontType);

    IStrokeType getStrokeType();

    void setStrokeType(IStrokeType strokeType);

    IFillType getFillType();

    void setFillType(IFillType fillType);

    IImageData getImage(ImageUsage usage);

    void setImage(ImageUsage usage, IImageData imageData);

    /**
     * Example:
     * 
     * @group advanced/optim
     */
    String getGroup();

}
