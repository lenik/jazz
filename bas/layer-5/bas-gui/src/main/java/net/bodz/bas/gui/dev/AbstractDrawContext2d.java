package net.bodz.bas.gui.dev;

import javax.vecmath.Vector2f;

import net.bodz.bas.c.javax.vecmath.ColumnVectors;
import net.bodz.bas.geom.spec0_f.tr.MatrixTransformer2d;
import net.bodz.bas.geom.spec1_f.Point2d;
import net.bodz.bas.gui.spec0.IColor;
import net.bodz.bas.gui.spec0.IFillPattern;
import net.bodz.bas.gui.spec0.IFont;
import net.bodz.bas.gui.spec0.IStroke;

public abstract class AbstractDrawContext2d
        implements IDrawContext2d {

    MatrixTransformer2d transformer;

    IColor color;
    IColor fillColor;
    IStroke stroke;
    IFillPattern pattern;
    IFillPattern fillPattern;
    IFont font;

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
    public IStroke getStroke() {
        return stroke;
    }

    @Override
    public void setStroke(IStroke stroke) {
        this.stroke = stroke;
    }

    @Override
    public IFillPattern getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(IFillPattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public IFillPattern getFillPattern() {
        return fillPattern;
    }

    @Override
    public void setFillPattern(IFillPattern fillPattern) {
        this.fillPattern = fillPattern;
    }

    @Override
    public IFont getFont() {
        return font;
    }

    @Override
    public void setFont(IFont font) {
        this.font = font;
    }

    // abstract implementation

    // -o ITransformedView2d

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
