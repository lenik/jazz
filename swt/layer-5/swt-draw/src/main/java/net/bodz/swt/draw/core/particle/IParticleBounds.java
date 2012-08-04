package net.bodz.swt.draw.core.particle;

import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;

import org.eclipse.swt.graphics.Rectangle;

public interface IParticleBounds {

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
