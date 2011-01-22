package net.bodz.geom.shape;

import java.util.Set;

import net.bodz.geom.base.Pick2f;
import net.bodz.geom.drawtarget.DrawException;
import net.bodz.geom.drawtarget.DrawTarget2f;
import net.bodz.geom.shape.base.Line2f;
import net.bodz.geom.shape.base.Point2f;
import net.bodz.geom.shape.base.Polygon2f;
import net.bodz.geom.shape.base.Rectangle2f;
import net.bodz.geom.shape.base.Triangle2f;
import net.bodz.math.mat.Vector2f;

public interface Shape2f extends PointSet2f, Pick2f, Cloneable {

    /**
     * Special Points
     */
    int SP_CENTER = 0;
    int SP_USER   = 100;

    Point2f spointRef(int id);

    Point2f.Static spoint(int id);

    float spointX(int id);

    float spointY(int id);

    void translate(float dx, float dy);

    void translate(Vector2f dv);

    void scale(float k);

    void scale(float kx, float ky);

    void scale(Vector2f kv);

    void rotate(float angle);

    void scale(float k, float baseX, float baseY);

    void scale(float kx, float ky, float baseX, float baseY);

    void scale(Vector2f kv, float baseX, float baseY);

    void rotate(float angle, float baseX, float baseY);

    void scale(float k, Point2f basePoint);

    void scale(float kx, float ky, Point2f basePoint);

    void scale(Vector2f kv, Point2f basePoint);

    void rotate(float angle, Point2f basePoint);

    Shape2f crop(Point2f baseHalfPlane, Vector2f normal);

    Shape2f crop(Line2f directed);

    Shape2f crop(Rectangle2f rectangle);

    Shape2f crop(float x0, float y0, float x1, float y1);

    Shape2f crop(Triangle2f triangle);

    Shape2f crop(float x0, float y0, float x1, float y1, float x2, float y2);

    Shape2f crop(Polygon2f polygon);

    Set<Class<?>> getConvertToClasses();

    Shape2f convertTo(Class<?> shapeClass);

    Rectangle2f boundingBox();

    Shape2f snapshot();

    Shape2f clone();

    void draw(DrawTarget2f target) throws DrawException;
}
