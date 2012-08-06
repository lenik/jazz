package net.bodz.bas.geom_f.api;

import javax.vecmath.Vector2f;

import net.bodz.bas.geom_f.base.Point2d;

public interface ITransformable2d {

    void translate(float dx, float dy);

    void translate(Vector2f dv);

    void scale(float k);

    void scale(float kx, float ky);

    void scaleAt(float k, Point2d basePoint);

    void scaleAt(float kx, float ky, Point2d basePoint);

    void rotate(float angle);

    void rotateAt(float angle, Point2d basePoint);

}
