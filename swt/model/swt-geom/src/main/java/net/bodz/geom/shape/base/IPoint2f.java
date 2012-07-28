package net.bodz.geom.shape.base;

import javax.vecmath.Point2f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.IShape2f;

public interface IPoint2f
        extends IShape2f {

    float x();

    float y();

    void x(float x);

    void y(float y);

    void add(float dx, float dy);

    void add(Vector2f dv);

    void sub(float dx, float dy);

    void sub(Vector2f dv);

    IPoint2f addCopy(float dx, float dy);

    IPoint2f addCopy(Vector2f dv);

    IPoint2f subCopy(float dx, float dy);

    IPoint2f subCopy(Vector2f dv);

    Vector2f vectorFrom(float startX, float startY);

    Vector2f vectorFrom(IPoint2f start);

    Vector2f vectorTo(float endX, float endY);

    Vector2f vectorTo(IPoint2f end);

    ILine2f lineFromRef(IPoint2f start);

    ILine2f lineToRef(IPoint2f end);

    ILine2f.Static lineFrom(IPoint2f start);

    ILine2f.Static lineTo(IPoint2f end);

    ILine2f.Static lineTo(Vector2f dv);

    ILine2f.Static halfPlane(Vector2f normal);

    float distanceSquared(IPoint2f point);

    float distanceSquared(float x, float y);

    boolean inside(IPoint2f base, Vector2f normal);

    // -o Pick

    // -o IShape2f
    @Override
    void translate(float dx, float dy);

    @Override
    void scale(float kx, float ky);

    @Override
    void rotate(float angle);

    @Override
    void scaleAt(float kx, float ky, float baseX, float baseY);

    @Override
    void rotateAt(float angle, float baseX, float baseY);

    @Override
    IPoint2f crop(Point2f base, Vector2f normal);

    @Override
    StaticPoint2f snapshot();

    @Override
    IPoint2f clone();

}
