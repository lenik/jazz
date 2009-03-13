package net.bodz.swt.controls.gs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.bodz.bas.types.util.PrefetchedIterator;

import org.eclipse.swt.graphics.Rectangle;

public abstract class _GeomSpace implements GeomSpace {

    /**
     * @return <code>null</code> if no object defined.
     */
    @Override
    public Rectangle getBounds() {
        int n = size();
        if (n == 0)
            return null;
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

    @Override
    public Iterator<Integer> iterator(int x0, int y0, int x1, int y1) {
        final Rectangle rect = new Rectangle(x0, y0, x1 - x0, y1 - y0);
        return new PrefetchedIterator<Integer>() {

            int i;

            @Override
            protected Object fetch() {
                while (i < size()) {
                    Rectangle b = getBound(i);
                    if (rect.intersects(b))
                        return i++;
                    i++;
                }
                return END;
            }

        };
    }

}
