package net.bodz.bas.geom.spec1_f;

import net.bodz.bas.c.javax.vecmath.Vector2f;
import net.bodz.bas.geom.spec0_f.CurveDirection;
import net.bodz.bas.geom.spec0_f.IPrimitive2d;
import net.bodz.bas.geom.spec0_f.IShapeAmount2d;

public interface ITriangle2d
        extends IPrimitive2d, IShapeAmount2d /* , IExtendable2d */{

    // -o Shape

    @Override
    Triangle2d snapshot();

    @Override
    ITriangle2d shot();

    float getX0();

    void setX0(float x);

    float getY0();

    void setY0(float y);

    float getX1();

    void setX1(float x);

    float getY1();

    void setY1(float y);

    float getX2();

    void setX2(float x);

    float getY2();

    void setY2(float y);

    Point2d getPoint0();

    void setPoint0(Point2d p);

    Point2d getPoint1();

    void setPoint1(Point2d p);

    Point2d getPoint2();

    void setPoint2(Point2d p);

    Vector2f getVector0();

    Vector2f getVector1();

    Vector2f getVector2();

    float getLength0();

    float getLength1();

    float getLength2();

    float getAngle0();

    float getAngle1();

    float getAngle2();

    CurveDirection getDirection();

    Circle2d getIncircle();

    Circle2d getCircumcircle();

}
