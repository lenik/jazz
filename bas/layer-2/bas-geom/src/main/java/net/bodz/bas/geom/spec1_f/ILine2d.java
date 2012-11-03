package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.IShapeAmount2d;

public interface ILine2d
        extends IPrimitive2d, IShapeAmount2d {

    // -o IShape2d

    @Override
    ILine2d shot();

    @Override
    Line2d snapshot();

    @Override
    Line2d snap();

    // -o ShapeAmount

    /**
     * The length of the line.
     * 
     * This is also the length of the {@link IShapeAmount2d#getLength() shape outline}.
     */
    @Override
    float getLength();

    // -o IPointSet2d

    Point2d getPoint0();

    void setPoint0(Point2d p0);

    Point2d getPoint1();

    void setPoint1(Point2d p1);

    IPointRef2d getPointRef0();

    IPointRef2d getPointRef1();

    float getX0();

    void setX0(float x0);

    float getY0();

    void setY0(float y0);

    float getX1();

    void setX1(float x1);

    float getY1();

    void setY1(float y1);

    float getWidth();

    float getHeight();

    /**
     * Get X-offset of the intersection with X-axis.
     * 
     * @return {@link Float#NaN} if this line is parallel to X-axis.
     */
    float getXaxisA();

    /**
     * Get Y-offset of the intersection with Y-axis.
     * 
     * @return {@link Float#NaN} if this line is parallel to Y-axis.
     */
    float getYaxisB();

    /**
     * The component k in y = kx + b
     */
    float getSlope();

    float getArg();

    float indexOfX(float x);

    float indexOfY(float y);

    Point2d getIntersectionExtended(Line2d line);

    Point2d getIntersection(Line2d line);

    boolean isIntersectedExtended(Line2d line);

    boolean isIntersected(Line2d line);

    Vector2f toVector();

    Vector2f getNormal();

    /**
     * Get the distance to the specific point. This line is treaded as infinite.
     */
    float distanceExtended(Point2d point);

    /**
     * Get the distance to the specific point. This line is treaded as infinite.
     */
    float distanceExtended(float x, float y);

}
