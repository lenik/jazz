package net.bodz.bas.gui.draw_f.dc;

import javax.vecmath.Vector2f;

import net.bodz.bas.c.javax.vecmath.ColumnVectors;
import net.bodz.bas.geom.spec0_f.tr.MatrixTransformer2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.ui.style.IColor;
import net.bodz.bas.ui.style.IFillType;
import net.bodz.bas.ui.style.IFontType;
import net.bodz.bas.ui.style.IStrokeType;

public abstract class AbstractDrawContext2d
        implements IDrawContext2d {

    MatrixTransformer2d transformer;

    IColor color;
    IColor fillColor;
    IStrokeType stroke;
    IFillType pattern;
    IFillType fillPattern;
    IFontType font;

    public MatrixTransformer2d getTransformer() {
        return transformer;
    }

    public void setTransformer(MatrixTransformer2d transformer) {
        this.transformer = transformer;
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
    public IColor getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(IColor fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public IStrokeType getStroke() {
        return stroke;
    }

    @Override
    public void setStroke(IStrokeType stroke) {
        this.stroke = stroke;
    }

    @Override
    public IFillType getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(IFillType pattern) {
        this.pattern = pattern;
    }

    @Override
    public IFillType getFillPattern() {
        return fillPattern;
    }

    @Override
    public void setFillPattern(IFillType fillPattern) {
        this.fillPattern = fillPattern;
    }

    @Override
    public IFontType getFont() {
        return font;
    }

    @Override
    public void setFont(IFontType font) {
        this.font = font;
    }

    // abstract implementation

    /** ⇱ Implementaton Of {@link net.bodz.bas.geom.spec0_f.tr.ITransformedView2d}. */
    /* _____________________________ */static section.iface __TRANSFORM__;

    @Override
    public void translate(float x, float y) {
        transformer.mul(ColumnVectors.translate3f(x, y));
    }

    @Override
    public void translate(javax.vecmath.Vector2f dv) {
        transformer.mul(ColumnVectors.translate3f(dv.x, dv.y));
    }

    @Override
    public final void scale(float k) {
        scale(k, k);
    }

    @Override
    public void scale(float kx, float ky) {
        transformer.mul(ColumnVectors.scale3f(kx, ky));
    }

    @Override
    public final void scaleAt(float k, Point2d basePoint) {
        scaleAt(k, basePoint);
    }

    @Override
    public void scaleAt(float kx, float ky, Point2d basePoint) {
        translate(-basePoint.x, -basePoint.y);
        scale(kx, ky);
        translate(basePoint.x, basePoint.y);
    }

    @Override
    public void rotate(float angle) {
        transformer.mul(ColumnVectors.rotate3f(angle));
    }

    @Override
    public void rotateAt(float angle, Point2d basePoint) {
        translate(-basePoint.x, -basePoint.y);
        rotate(angle);
        translate(basePoint.x, basePoint.y);
    }

    @Override
    public void invert() {
        transformer.invert();
    }

    @Override
    public void transform(Vector2f vector) {
        transformer.transform(vector);
    }

    @Override
    public void transform(Point2d point) {
        transformer.transform(point);
    }

    @Override
    public void inverse(Vector2f vector) {
        transformer.inverse(vector);
    }

    @Override
    public void inverse(Point2d point) {
        transformer.inverse(point);
    }

}
