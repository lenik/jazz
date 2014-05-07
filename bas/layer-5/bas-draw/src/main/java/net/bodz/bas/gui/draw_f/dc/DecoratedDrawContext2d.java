package net.bodz.bas.gui.draw_f.dc;

import net.bodz.bas.ui.style.IColor;
import net.bodz.bas.ui.style.IFillType;
import net.bodz.bas.ui.style.IFontType;
import net.bodz.bas.ui.style.IStrokeType;

public abstract class DecoratedDrawContext2d
        extends DecoratedBaseDrawContext2d
        implements IDrawContext2d {

    private static final long serialVersionUID = 1L;

    public DecoratedDrawContext2d(IDrawContext2d _orig) {
        super(_orig);
    }

    @Override
    public IDrawContext2d getWrapped() {
        return (IDrawContext2d) _orig;
    }

    @Override
    public IColor getColor() {
        return getWrapped().getColor();
    }

    @Override
    public void setColor(IColor color) {
        getWrapped().setColor(color);
    }

    @Override
    public IColor getFillColor() {
        return getWrapped().getFillColor();
    }

    @Override
    public void setFillColor(IColor fillColor) {
        getWrapped().setFillColor(fillColor);
    }

    @Override
    public IStrokeType getStroke() {
        return getWrapped().getStroke();
    }

    @Override
    public void setStroke(IStrokeType stroke) {
        getWrapped().setStroke(stroke);
    }

    @Override
    public IFillType getPattern() {
        return getWrapped().getPattern();
    }

    @Override
    public void setPattern(IFillType pattern) {
        getWrapped().setPattern(pattern);
    }

    @Override
    public IFillType getFillPattern() {
        return getWrapped().getFillPattern();
    }

    @Override
    public void setFillPattern(IFillType fillPattern) {
        getWrapped().setFillPattern(fillPattern);
    }

    @Override
    public IFontType getFont() {
        return getWrapped().getFont();
    }

    @Override
    public void setFont(IFontType font) {
        getWrapped().setFont(font);
    }

}
