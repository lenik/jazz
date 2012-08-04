package net.bodz.geom.shape.base;

import javax.vecmath.Point2f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.geom.shape.IShape2f;

public interface IPointRef2d
        extends IShape2f {

    float x();

    float y();

    void x(float x);

    void y(float y);

    void add(float dx, float dy);

    void add(Vector2f dv);

    void sub(float dx, float dy);

    void sub(Vector2f dv);

    IPointRef2d addCopy(float dx, float dy);

    IPointRef2d addCopy(Vector2f dv);

    IPointRef2d subCopy(float dx, float dy);

    IPointRef2d subCopy(Vector2f dv);

    Vector2f vectorFrom(float startX, float startY);

    Vector2f vectorFrom(IPointRef2d start);

    Vector2f vectorTo(float endX, float endY);

    Vector2f vectorTo(IPointRef2d end);

    ILineRef2f lineFromRef(IPointRef2d start);

    ILineRef2f lineToRef(IPointRef2d end);

    ILineRef2f.Static lineFrom(IPointRef2d start);

    ILineRef2f.Static lineTo(IPointRef2d end);

    ILineRef2f.Static lineTo(Vector2f dv);

    ILineRef2f.Static halfPlane(Vector2f normal);

    float distanceSquared(IPointRef2d point);

    float distanceSquared(float x, float y);

    boolean inside(IPointRef2d base, Vector2f normal);

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
    IPointRef2d crop(Point2f base, Vector2f normal);

    @Override
    StaticPoint2f snapshot();

    @Override
    IPointRef2d clone();

}
