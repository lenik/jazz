package net.bodz.bas.ui.style;

import java.io.Serializable;

import net.bodz.bas.ui.style.color.IAlphaChannel;
import net.bodz.bas.ui.style.color.IColor_CMYK32;
import net.bodz.bas.ui.style.color.IColor_CMYK64;
import net.bodz.bas.ui.style.color.IColor_HSL24;
import net.bodz.bas.ui.style.color.IColor_HSL3f;
import net.bodz.bas.ui.style.color.IColor_HSLA32;
import net.bodz.bas.ui.style.color.IColor_HSLA4f;
import net.bodz.bas.ui.style.color.IColor_RGB24;
import net.bodz.bas.ui.style.color.IColor_RGB3f;
import net.bodz.bas.ui.style.color.IColor_RGB48;
import net.bodz.bas.ui.style.color.IColor_RGBA32;
import net.bodz.bas.ui.style.color.IColor_RGBA4f;
import net.bodz.bas.ui.style.color.IColor_RGBA64;

public interface IColor
        extends IAlphaChannel, Serializable {

    IColor_RGB24 toRGB24();

    IColor_RGBA32 toRGBA32();

    IColor_RGB48 toRGB48();

    IColor_RGBA64 toRGBA64();

    IColor_RGB3f toRGB3f();

    IColor_RGBA4f toRGBA4f();

    IColor_HSL24 toHSL24();

    IColor_HSLA32 toHSLA32();

    IColor_HSL3f toHSL3f();

    IColor_HSLA4f toHSLA4f();

    IColor_CMYK32 toCMYK32();

    IColor_CMYK64 toCMYK64();

}
