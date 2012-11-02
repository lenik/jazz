package net.bodz.bas.geom_f.base;

import net.bodz.bas.geom_f.api.IExtendable2d;
import net.bodz.bas.geom_f.api.IShape2d;
import net.bodz.bas.geom_f.api.IShapeAmount2d;

public interface IRectangle2d
        extends IShape2d, IShapeAmount2d, IExtendable2d {

    // -o IShape2d

    @Override
    IRectangle2d shot();

    @Override
    Rectangle2d snap();

    @Override
    Rectangle2d snapshot();

    // -o IPointSet2d

    float getX0();

    void setX0(float x0);

    float getY0();

    void setY0(float y0);

    float getX1();

    void setX1(float x1);

    float getY1();

    void setY1(float y1);

    float getX2();

    void setX2(float x2);

    float getY2();

    void setY2(float y2);

    float getX3();

    void setX3(float x3);

    float getY3();

    void setY3(float y3);

    Point2d getPoint0();

    void setPoint0(Point2d point);

    Point2d getPoint1();

    void setPoint1(Point2d point);

    Point2d getPoint2();

    void setPoint2(Point2d point);

    Point2d getPoint3();

    void setPoint3(Point2d point);

    float getWidth();

    void setWidth(float width);

    float getHeight();

    void setHeight(float height);

    boolean isPositive();

    void positize();

    // boolean isIntersectedExtended(Line2d line);

    boolean isIntersected(Rectangle2d rect);

    void normalizeByMinMax();

    void normalizeByArea();

    int OUT_LEFT = 1;
    int OUT_TOP = 2;
    int OUT_RIGHT = 4;
    int OUT_BOTTOM = 8;

    /**
     * @see #OUT_TOP
     * @see #OUT_BOTTOM
     * @see #OUT_LEFT
     * @see #OUT_RIGHT
     */
    int getOutcode(float x, float y);

    /**
     * @see #OUT_TOP
     * @see #OUT_BOTTOM
     * @see #OUT_LEFT
     * @see #OUT_RIGHT
     */
    int getOutcode(Point2d point);

}
