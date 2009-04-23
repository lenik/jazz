package net.bodz.geom.shape.base.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.bodz.geom.shape.EditablePointSet2f;
import net.bodz.geom.shape.PointSet2f;
import net.bodz.geom.shape.base.Point2f;

public class H_PointSet2f {

    private static class _PointIterator {
        PointSet2f ps;
        int        index          = 0;

        boolean    currentDeleted = false;

        public _PointIterator(PointSet2f ps) {
            assert ps != null;
            this.ps = ps;
        }

        @SuppressWarnings("unused")
        public boolean hasNext() {
            return index < ps.pointCount();
        }

        public void remove() {
            if (currentDeleted)
                throw new IllegalStateException("already been deleted.");

            if (ps instanceof EditablePointSet2f) {
                int currentIndex = index - 1;
                if (currentIndex < 0)
                    throw new IllegalStateException("next hasn't been called.");
                if (currentIndex >= ps.pointCount())
                    throw new NoSuchElementException();

                EditablePointSet2f eps = (EditablePointSet2f) ps;
                eps.removePoint(currentIndex);
                index--;
                currentDeleted = true;
                return;
            }

            throw new UnsupportedOperationException();
        }
    }

    public static class PointIterator extends _PointIterator implements Iterator<Point2f.Static> {

        public PointIterator(PointSet2f ps) {
            super(ps);
        }

        @Override
        public boolean hasNext() {
            return index < ps.pointCount();
        }

        public Point2f.Static next() {
            if (index >= ps.pointCount())
                throw new NoSuchElementException();
            currentDeleted = false;
            return ps.point(index++);
        }

    }

    public static class PointRefIterator extends _PointIterator implements Iterator<Point2f> {

        public PointRefIterator(PointSet2f ps) {
            super(ps);
        }

        @Override
        public boolean hasNext() {
            return index < ps.pointCount();
        }

        public Point2f next() {
            if (index >= ps.pointCount())
                throw new NoSuchElementException();
            currentDeleted = false;
            return ps.pointRef(index++);
        }

    }

    /*
     * public static final Point2f[] convertToArray(Object data) { if (data ==
     * null) return null; if (data instanceof Point2f) return new Point2f[] {
     * (Point2f) data, }; if (data instanceof Point2f[]) return (Point2f[])
     * data;
     * 
     * Class clazz = data.getClass(); ArrayList<Point2f> list = new
     * ArrayList<Point2f>(); if (clazz.isArray()) { int length =
     * Array.getLength(data); for (int i = 0; i < length; i++) { Object v =
     * Array.get(data, i); if (v instanceof Point2f) list.add((Point2f) v); else
     * if (v instanceof Number && i + 1 < length) { Object v2 = Array.get(data,
     * i + 1); assert v2 instanceof Number; list.add(new Point2f.XY(((Number)
     * v).floatValue(), ((Number) v2).floatValue())); } else throw new
     * IllegalArgumentException( "Invalid point in point-array-def"); } } else {
     * throw new IllegalArgumentException(
     * "Invalid points data representation"); }
     * 
     * Point2f[] pts = new Point2f[list.size()]; list.toArray(pts);
     * 
     * return pts; }
     */
}
