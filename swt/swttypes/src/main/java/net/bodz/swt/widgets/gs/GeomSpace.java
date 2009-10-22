package net.bodz.swt.widgets.gs;

import net.bodz.bas.types.ints.IntIterable;
import net.bodz.bas.types.ints.IntIterator;

import org.eclipse.swt.graphics.Rectangle;

public interface GeomSpace {

    int size();

    /**
     * @return copy of rectangle which can be reused.
     */
    Rectangle getBound(int index);

    /**
     * if no element in the geom space, an empty rect at [0,0] is assumed.
     * 
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

    /**
     * Find geoms fully included.
     * 
     * @param rect
     *            <code>null</code> to iterator all geoms.
     */
    IntIterable find(Rectangle rect);

    /**
     * Find geoms fully included.
     * 
     * @param rect
     *            <code>null</code> to iterator all geoms.
     */
    IntIterator iterator(Rectangle rect);

}
