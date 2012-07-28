package net.bodz.geom.shape;

import java.util.Set;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import net.bodz.geom.base.IPick2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.base.ILine2f;
import net.bodz.geom.shape.base.IPoint2f;
import net.bodz.geom.shape.base.IPolygon2f;
import net.bodz.geom.shape.base.IRectangle2f;
import net.bodz.geom.shape.base.ITriangle2f;
import net.bodz.geom.shape.base.StaticPoint2f;

public interface IShape2f
        extends IPointSet2f, IPick2f, Cloneable {

    /**
     * Special Points
     */
    int SP_CENTER = 0;
    int SP_USER = 100;

    IPoint2f spointRef(int id);

    StaticPoint2f spoint(int id);

    float spointX(int id);

    float spointY(int id);

    void translate(float dx, float dy);

    void translate(Vector2f dv);

    void scale(float k);

    void scale(float kx, float ky);

    void scale(Vector2f kv);

    void scaleAt(float k, float baseX, float baseY);

    void scaleAt(float kx, float ky, float baseX, float baseY);

    void scaleAt(Vector2f kv, float baseX, float baseY);

    void scaleAt(float k, Point2f basePoint);

    void scaleAt(float kx, float ky, Point2f basePoint);

    void scaleAt(Vector2f kv, Point2f basePoint);

    void rotate(float angle);

    void rotateAt(float angle, float baseX, float baseY);

    void rotateAt(float angle, Point2f basePoint);

    IShape2f crop(Point2f baseHalfPlane, Vector2f normal);

    IShape2f crop(ILine2f directed);

    IShape2f crop(IRectangle2f rectangle);

    IShape2f crop(float x0, float y0, float x1, float y1);

    IShape2f crop(ITriangle2f triangle);

    IShape2f crop(float x0, float y0, float x1, float y1, float x2, float y2);

    IShape2f crop(IPolygon2f polygon);

    Set<Class<?>> getConvertToClasses();

    IShape2f convertTo(Class<?> shapeClass);

    IRectangle2f boundingBox();

    IShape2f snapshot();

    IShape2f clone();

    void draw(DrawTarget2f target)
            throws DrawException;

}
