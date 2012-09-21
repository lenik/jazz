package net.bodz.bas.geom_f.particle;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import net.bodz.bas.geom_f.base.Point2d;
import net.bodz.bas.geom_f.base.Rectangle2d;
import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;
import net.bodz.bas.util.ints.PrefetchedIntIterator;

public abstract class AbstractParticleBounds2d
        implements IParticleBounds2d {

    static final Rectangle2d emptyRect = new Rectangle2d(0, 0, 0, 0);

    /**
     * @return <code>(0, 0, 0, 0)</code> if no object defined.
     */
    @Override
    public Rectangle2d getBoundingBox() {
        int n = getParticleCount();
        if (n == 0)
            return emptyRect;

        Rectangle2d entire = getBoundingBox(0); // min-max
        if (n == 1)
            return entire;

        for (int index = 1; index < n; index++) {
            Rectangle2d bbox = getBoundingBox(index);
            entire.include(bbox);
        }
        return entire;
    }

    @Override
    public int getParticleIndexAt(Point2d point) {
        int n = getParticleCount();
        for (int index = 0; index < n; index++) {
            Rectangle2d bbox = getBoundingBox(index);
            if (bbox.contains(point))
                return index;
        }
        return -1;
    }

    @Override
    public int[] getParticleIndexesAt(Point2d point) {
        int n = getParticleCount();
        if (n == 0)
            return null;

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            Rectangle2d b = getBoundingBox(i);
            if (b.contains(point))
                list.add(i);
        }

        int count = list.size();
        int[] indexes = new int[count];
        for (int i = 0; i < count; i++)
            indexes[i] = list.get(i);
        return indexes;
    }

    public IntIterable getParticleIndexes() {
        return new IntIterable() {
            @Override
            public IntIterator iterator() {
                return new AllParticleIterator();
            }
        };
    }

    class AllParticleIterator
            implements IntIterator {

        private final int size = getParticleCount();
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

    @Override
    public IntIterable getParticleIndexes(final Rectangle2d boundingBox) {
        if (boundingBox == null)
            throw new NullPointerException("boundingBox");

        return new IntIterable() {
            @Override
            public IntIterator iterator() {
                return new ClippedParticleIterable(boundingBox);
            }
        };
    }

    class ClippedParticleIterable
            extends PrefetchedIntIterator {

        private final int size = getParticleCount();
        private final Rectangle2d boundingBox;

        ClippedParticleIterable(Rectangle2d boundingBox) {
            super(-1);
            this.boundingBox = boundingBox;
        }

        private int i;

        @Override
        protected int fetch() {
            while (i < size) {
                Rectangle2d _bbox = getBoundingBox(i);
                if (boundingBox.isIntersected(_bbox))
                    return i++;
                i++;
            }
            return -1;
        }

    }

}
