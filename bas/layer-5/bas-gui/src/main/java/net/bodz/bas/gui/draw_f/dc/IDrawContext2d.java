package net.bodz.bas.gui.draw_f.dc;

import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.gui.style.IFillType;
import net.bodz.bas.gui.style.IFontType;
import net.bodz.bas.gui.style.IStrokeType;

public interface IDrawContext2d
        extends IBaseDrawContext2d {

    // graphics attributes

    IColor getColor();

    void setColor(IColor color);

    IColor getFillColor();

    void setFillColor(IColor fillColor);

    IStrokeType getStroke();

    void setStroke(IStrokeType stroke);

    IFillType getPattern();

    void setPattern(IFillType pattern);

    IFillType getFillPattern();

    void setFillPattern(IFillType fillPattern);

    IFontType getFont();

    void setFont(IFontType font);

}
