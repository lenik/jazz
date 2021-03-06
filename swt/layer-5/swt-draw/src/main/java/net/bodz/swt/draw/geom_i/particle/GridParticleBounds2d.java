package net.bodz.swt.draw.geom_i.particle;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.c.object.IEmptyConsts;
import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.err.ReadOnlyException;
import net.bodz.bas.t._int.IntIterable;
import net.bodz.bas.t._int.IntIterator;
import net.bodz.bas.t._int.PrefetchedIntIterator;

public abstract class GridParticleBounds2d
        extends AbstractParticleBounds2d {

    static final int DEFAULT_CELLWIDTH = 5;
    static final int DEFAULT_CELLHEIGHT = 5;

    int cellw = DEFAULT_CELLWIDTH;
    int cellh = DEFAULT_CELLHEIGHT;

    public final int getSize() {
        return getParticleCount();
    }

    public void setSize(int size) {
        throw new ReadOnlyException("size");
    }

    public abstract Point getOrig(int index);

    @Override
    public Rectangle getBoundingBox(int index) {
        Point orig = getOrig(index);
        int x = orig.x;
        int y = orig.y;
        Rectangle b = new Rectangle(x, y, cellw, cellh);
        return b;
    }

    public int getCellWidth() {
        return cellw;
    }

    public void setCellWidth(int cellw) {
        if (cellw < 0)
            throw new OutOfDomainException("cellWidth", cellw, 0);
        this.cellw = cellw;
    }

    public int getCellHeight() {
        return cellh;
    }

    public void setCellHeight(int cellh) {
        if (cellh < 0)
            throw new OutOfDomainException("cellHeight", cellh, 0);
        this.cellh = cellh;
    }

    public int getCellSize() {
        if (cellw != cellh)
            throw new IllegalStateException("different cellw/cellh");
        return cellw;
    }

    public void setCellSize(int size) {
        setCellWidth(size);
        setCellHeight(size);
    }

    public static abstract class _Array
            extends GridParticleBounds2d {

        int size;
        int rows;
        int columns;
        int hpad;
        int vpad;

        public _Array(int size, int rows, int columns) {
            setSize(size);
            setRows(rows);
            setColumns(columns);
        }

        @Override
        public int getParticleCount() {
            return size;
        }

        @Override
        public void setSize(int size) {
            this.size = size;
        }

        public int getRows() {
            return rows;
        }

        public void setRows(int rows) {
            if (rows <= 0)
                throw new OutOfDomainException("rows", rows, 0);
            this.rows = rows;
        }

        public int getColumns() {
            return columns;
        }

        public void setColumns(int columns) {
            if (columns <= 0)
                throw new OutOfDomainException("columns", columns, 0);
            this.columns = columns;
        }

        public int getHPadding() {
            return hpad;
        }

        public void setHPadding(int padding) {
            if (padding < 0)
                throw new OutOfDomainException("hpad", padding, 0);
            this.hpad = padding;
        }

        public int getVPadding() {
            return vpad;
        }

        public void setVPadding(int padding) {
            if (padding < 0)
                throw new OutOfDomainException("vpad", padding, 0);
            this.vpad = padding;
        }

        public int getPadding() {
            if (hpad != vpad)
                throw new IllegalStateException("different hpad/vpad");
            return hpad;
        }

        public void setPadding(int padding) {
            setHPadding(padding);
            setVPadding(padding);
        }

        @Override
        public Rectangle getBoundingBox() {
            int w = columns * cellw;
            int h = rows * cellh;
            if (columns >= 1)
                w += (columns - 1) * hpad;
            if (rows >= 1)
                h += (rows - 1) * vpad;
            return new Rectangle(0, 0, w, h);
        }

        @Override
        public int[] getParticleIndexesAt(Point point) {
            int index = getParticleIndexAt(point);
            if (index == -1)
                return IEmptyConsts.emptyIntArray;
            return new int[] { index };
        }

        @Override
        public IntIterable getParticleIndexes(final Rectangle boundingBox) {
            return new IntIterable() {
                @Override
                public IntIterator iterator() {
                    return getParticleIndexesIterator(boundingBox);
                }
            };
        }

        IntIterator getParticleIndexesIterator(Rectangle boundingBox) {
            if (boundingBox == null)
                throw new NullPointerException("boundingBox");

            assert boundingBox.width >= 0 && boundingBox.height >= 0;

            int x0 = Math.max(0, boundingBox.x);
            int y0 = Math.max(0, boundingBox.y);
            int dx = cellw + hpad;
            int dy = cellh + vpad;
            final int r0 = y0 / dy;
            final int c0 = x0 / dx;
            if (r0 >= rows || c0 >= columns)
                return IntIterator.empty;

            int r1 = (y0 + boundingBox.height) / dy;
            int c1 = (x0 + boundingBox.width) / dx;
            if (r1 >= rows)
                r1 = rows - 1;
            if (c1 >= columns)
                c1 = columns - 1;
            final int rn = r1 - r0 + 1;
            final int cn = c1 - c0 + 1;
            if (rn < 1 || cn < 1)
                return IntIterator.empty;

            return iteratorCells(r0, c0, rn, cn);
        }

        protected abstract IntIterator iteratorCells(final int r0, final int c0, final int rn, final int cn);

    }

    public static class HFirst
            extends _Array {

        /**
         * @param columns
         *            can't be zero.
         */
        public HFirst(int size, int columns) {
            super(size, (size + columns - 1) / columns, columns);
        }

        @Override
        public Point getOrig(int index) {
            int row = index / columns;
            int col = index % columns;
            int x = col * (cellw + hpad);
            int y = row * (cellh + vpad);
            return new Point(x, y);
        }

        @Override
        public int getParticleIndexAt(Point point) {
            int x = point.x;
            int y = point.y;
            if (x < 0 || y < 0)
                return -1;
            int dx = cellw + hpad;
            int dy = cellh + vpad;
            if (x % dx >= cellw)
                return -1;
            if (y % dy >= cellh)
                return -1;
            int xmax = columns * dx;
            int ymax = rows * dy;
            if (x >= xmax || y >= ymax)
                return -1;
            int row = y / dy;
            int col = x / dx;
            assert row >= 0 : "bad row: " + row;
            assert col >= 0 && col < columns : "bad column: " + col;
            int index = row * columns + col;
            if (index >= size)
                return -1;
            return index;
        }

        @Override
        protected IntIterator iteratorCells(final int r0, final int c0, final int rn, final int cn) {
            assert rn >= 0 && cn >= 0;
            final int r1 = r0 + rn;
            final int c1 = c0 + cn;
            class Iter
                    extends PrefetchedIntIterator {
                public Iter() {
                    super(-1);
                }

                int row = r0;
                int col = c0;
                int index = row * columns + col;

                @Override
                protected int fetch() {
                    if (row >= r1 || index >= size)
                        return END;
                    int ret = index++;
                    if (++col >= c1) {
                        row++;
                        col = c0;
                        index += c0 + (columns - c1);
                    }
                    return ret;
                }
            }
            return new Iter();
        }
    }

}
