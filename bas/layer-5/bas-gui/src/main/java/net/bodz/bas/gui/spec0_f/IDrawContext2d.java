package net.bodz.bas.gui.spec0_f;

import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFillPattern;
import net.bodz.bas.gui.spec0.IFont;
import net.bodz.bas.gui.spec0.IStroke;

public interface IDrawContext2d {

    IShape2dLayer getTargetLayer();

    void setTargetLayer(IShape2dLayer layer);

    // Drawing attributes

    IColor getColor();

    void setColor(IColor color);

    IColor getFillColor();

    void setFillColor(IColor fillColor);

    IStroke getStroke();

    void setStroke(IStroke stroke);

    IFillPattern getPattern();

    void setPattern(IFillPattern pattern);

    IFillPattern getFillPattern();

    void setFillPattern(IFillPattern fillPattern);

    IFont getFont();

    void setFont(IFont font);

}