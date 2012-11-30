package net.bodz.bas.gui.style;

import java.io.Serializable;

import net.bodz.bas.gui.style.color.*;

public interface IColor
        extends IAlphaChannel, Serializable {

    IColor_RGB24 toRGB24();

    IColor_RGBA32 toRGBA32();

    IColor_RGB48 toRGB48();

    IColor_RGBA64 toRGBA64();

    IColor_RGB3f toRGB48f();

    IColor_CMYK32 toCMYK32();

    IColor_CMYK64 toCMYK64();

}
