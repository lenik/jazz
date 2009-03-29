package net.bodz.swt.controls.gs;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import net.bodz.bas.types.ints.IntIterable;
import net.bodz.bas.types.ints.IntIterator;
import net.bodz.bas.types.ints.PrefetchedIntIterator;

import org.eclipse.swt.graphics.Rectangle;

public abstract class _GeomSpace implements GeomSpace {

    static final Rectangle emptyRect = new Rectangle(0, 0, 0, 0);

    /**
     * @return <code>(0, 0, 0, 0)</code> if no object defined.
     */
    @Override
    public Rectangle getBounds() {
        int n = size();
        if (n == 0)
            return emptyRect;
        Rectangle b0 = getBound(0); // min-max
        if (n == 1)
            return b0;
        assert b0.width >= 0 && b0.height >= 0;
        int xmin = b0.x;
        int xmax = b0.x + b0.width;
        int ymin = b0.y;
        int ymax = b0.y + b0.height;
        for (int i = 1; i < n; i++) {
            Rectangle b = getBound(i);
            assert b.width >= 0 && b.height >= 0;
            int cx0 = b.x;
            int cx1 = b.x + b.width;
            int cy0 = b.y;
            int cy1 = b.y + b.height;
            if (cx0 < xmin)
                xmin = cx0;
            if (cx1 > xmax)
                xmax = cx1;
            if (cy0 < ymin)
                ymin = cy0;
            if (cy1 > ymax)
                ymax = cy1;
        }
        return new Rectangle(xmin, xmax, ymin, ymax);
    }

    @Override
    public int find(int x, int y) {
        int n = size();
        for (int i = 0; i < n; i++) {
            Rectangle b = getBound(i);
            if (b.contains(x, y))
                return i;
        }
        return -1;
    }

    @Override
    public int[] findAll(int x, int y) {
        List<Integer> list = null;
        int n = size();
        for (int i = 0; i < n; i++) {
            Rectangle b = getBound(i);
            if (b.contains(x, y)) {
                if (list == null)
                    list = new ArrayList<Integer>();
                list.add(i);
            }
        }
        if (list == null)
            return null;
        int count = list.size();
        int[] indexes = new int[count];
        for (int i = 0; i < count; i++)
            indexes[i] = list.get(i);
        return indexes;
    }

    public IntIterable findAll() {
        return new IntIterable() {
            @Override
            public IntIterator iterator() {
                return iteratorAll();
            }
        };
    }

    public IntIterator iteratorAll() {
        final int size = size();
        class IterAll implements IntIterator {
            private int i;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public int next() {
                if (i < size)
                    return i++;
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
        return new IterAll();
    }

    @Override
    public IntIterable find(final Rectangle rect) {
        if (rect == null)
            return findAll();

        final int size = size();

        class ClipIter extends PrefetchedIntIterator {
            ClipIter() {
                super(-1);
            }

            private int i;

            @Override
            protected int fetch() {
                while (i < size) {
                    Rectangle b = getBound(i);
                    if (rect.intersects(b))
                        return i++;
                    i++;
                }
                return -1;
            }
        }
        return new IntIterable() {
            @Override
            public IntIterator iterator() {
                return new ClipIter();
            }
        };
    }

    @Override
    public IntIterator iterator(Rectangle rect) {
        if (rect == null)
            return iteratorAll();
        return find(rect).iterator();
    }

}
