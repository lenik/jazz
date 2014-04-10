package net.bodz.bas.gui.css3;

import java.io.Serializable;
import java.util.StringTokenizer;

import net.bodz.bas.c.string.StringPart;
import net.bodz.bas.gui.css3.property.BorderStyleMode;
import net.bodz.bas.gui.style.IColor;
import net.bodz.bas.i18n.unit.std.Length;

public class Border
        implements IBorderAttributes, Serializable {

    private static final long serialVersionUID = 1L;

    BorderStyleMode style = BorderStyleMode.solid;
    ICss3Length width; // = LengthMeasure.PIXEL(1);
    IColor color;
    boolean useCurrentColor = true;

    @Override
    public BorderStyleMode getStyle() {
        return style;
    }

    @Override
    public void setStyle(BorderStyleMode style) {
        if (style == null)
            throw new NullPointerException("style");
        this.style = style;
    }

    @Override
    public ICss3Length getWidth() {
        return width;
    }

    @Override
    public void setWidth(ICss3Length width) {
        this.width = width;
    }

    @Override
    public IColor getColor() {
        return color;
    }

    @Override
    public void setColor(IColor color) {
        this.color = color;
    }

    @Override
    public boolean isUseCurrentColor() {
        return useCurrentColor;
    }

    @Override
    public void setUseCurrentColor(boolean useCurrentColor) {
        this.useCurrentColor = useCurrentColor;
    }

    @Override
    public String toString() {
        return style + " " + width + " " + color;
    }

    public static Border parse(String str) {
        if (str.endsWith("%"))
            str = StringPart.chop(str).trim();

        Border border = new Border();
        StringTokenizer tokens = new StringTokenizer(str, " ");
        while (tokens.hasMoreTokens()) {
            String tok = tokens.nextToken();

            BorderStyleMode style = BorderStyleMode.valueOf(tok);

            IColor color;
            Length width = Length.parseOrNaN(tok);
        }
        return border;
    }

}
