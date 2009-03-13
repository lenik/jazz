package net.bodz.swt.controls.gs;

import java.util.Iterator;

import org.eclipse.swt.graphics.Rectangle;

public interface GeomSpace {

    int size();

    /**
     * @return copy of rectangle which can be reused.
     */
    Rectangle getBound(int index);

    /**
     * @return copy of rectangle which can be reused.
     */
    Rectangle getBounds();

    /**
     * @return -1 if not found any object
     */
    int find(int x, int y);

    /**
     * @return empty array if none found.
     */
    int[] findAll(int x, int y);

    Iterator<Integer> iterator(int x0, int y0, int x1, int y1);

}
