package net.bodz.bas.geom_f.c1;

import net.bodz.bas.geom_f.api.IPointRefSet2d;
import net.bodz.bas.geom_f.api.ITransformable2d;
import net.bodz.bas.geom_f.base.IPointRef2d;
import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.tr.AbstractTransformable2d;

public class PointRefSet2dTools {

    public static ITransformable2d transforming(IPointRefSet2d pointSet) {
        return new Transformable2dImpl(pointSet);
    }

    static class Transformable2dImpl
            extends AbstractTransformable2d {

        IPointRefSet2d pointSet;

        public Transformable2dImpl(IPointRefSet2d pointSet) {
            this.pointSet = pointSet;
        }

        @Override
        public void translate(float dx, float dy) {
            int n = pointSet.getPointCount();
            for (int i = 0; i < n; i++) {
                IPointRef2d point = pointSet.getPointRef(i);
                point.translate(dx, dy);
            }
        }

        @Override
        public void scale(float kx, float ky) {
            int n = pointSet.getPointCount();
            for (int i = 0; i < n; i++) {
                IPointRef2d point = pointSet.getPointRef(i);
                point.scale(kx, ky);
            }
        }

        @Override
        public void scaleAt(float kx, float ky, Point2d basePoint) {
            int n = pointSet.getPointCount();
            for (int i = 0; i < n; i++) {
                IPointRef2d point = pointSet.getPointRef(i);
                point.scaleAt(kx, ky, basePoint);
            }
        }

        @Override
        public void rotate(float angle) {
            int n = pointSet.getPointCount();
            for (int i = 0; i < n; i++) {
                IPointRef2d point = pointSet.getPointRef(i);
                point.rotate(angle);
            }
        }

        @Override
        public void rotateAt(float angle, Point2d basePoint) {
            int n = pointSet.getPointCount();
            for (int i = 0; i < n; i++) {
                IPointRef2d point = pointSet.getPointRef(i);
                point.rotateAt(angle, basePoint);
            }
        }

    }

}
