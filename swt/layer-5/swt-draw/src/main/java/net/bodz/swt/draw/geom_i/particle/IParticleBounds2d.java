package net.bodz.swt.draw.geom_i.particle;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.t._int.IntIterable;

public interface IParticleBounds2d {

    /**
     * Get the particle count.
     */
    int getParticleCount();

    /**
     * Get the bounding box for the specific particle.
     * 
     * @param index
     *            Particle index (0-based).
     * @return The bounding rectangle box.
     */
    Rectangle getBoundingBox(int index);

    /**
     * Get the bounding box for all particles.
     * 
     * @return The bounding rectangle. Returns empty rectangle if there is no particle.
     */
    Rectangle getBoundingBox();

    /**
     * Find particle at the given position.
     * 
     * @param position
     *            The position to find.
     * @return The particle index. If there is multiple particles overlapped at the specified point,
     *         the top-most one should be returned. If there is no particle at the specified point,
     *         <code>-1</code> is returned.
     */
    int getParticleIndexAt(Point position);

    int getParticleIndexAt(int x, int y);

    /**
     * Find all particles at the given position.
     * 
     * @param position
     *            The position to find.
     * @return Array of particle indexes.
     */
    int[] getParticleIndexesAt(Point position);

    int[] getParticleIndexesAt(int x, int y);

    /**
     * Find particles fully included in the bounding box.
     * 
     * @param boundingBox
     *            Non-<code>null</code> bounding box.
     */
    IntIterable getParticleIndexes(Rectangle boundingBox);

}
