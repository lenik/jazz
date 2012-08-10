package net.bodz.swt.draw.geom_i;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import net.bodz.bas.util.ints.IntIterable;
import net.bodz.bas.util.ints.IntIterator;
import net.bodz.bas.util.ints.PrefetchedIntIterator;

public abstract class AbstractParticleBounds2i
        implements IParticleBounds2i {

    static final Rectangle emptyRect = new Rectangle(0, 0, 0, 0);

    /**
     * @return <code>(0, 0, 0, 0)</code> if no object defined.
     */
    @Override
    public Rectangle getBoundingBox() {
        int n = getParticleCount();
        if (n == 0)
            return emptyRect;

        Rectangle entire = getBoundingBox(0); // min-max
        if (n == 1)
            return entire;

        for (int index = 1; index < n; index++) {
            Rectangle bbox = getBoundingBox(index);
            entire.add(bbox);
        }
        return entire;
    }

    @Override
    public final int getParticleIndexAt(int x, int y) {
        return getParticleIndexAt(new Point(x, y));
    }

    @Override
    public int getParticleIndexAt(Point point) {
        int n = getParticleCount();
        for (int index = 0; index < n; index++) {
            Rectangle bbox = getBoundingBox(index);
            if (bbox.contains(point))
                return index;
        }
        return -1;
    }

    @Override
    public final int[] getParticleIndexesAt(int x, int y) {
        return getParticleIndexesAt(new Point(x, y));
    }

    @Override
    public int[] getParticleIndexesAt(Point point) {
        int n = getParticleCount();
        if (n == 0)
            return null;

        List<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            Rectangle b = getBoundingBox(i);
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
    public IntIterable getParticleIndexes(final Rectangle boundingBox) {
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
        private final Rectangle boundingBox;

        ClippedParticleIterable(Rectangle boundingBox) {
            super(-1);
            this.boundingBox = boundingBox;
        }

        private int i;

        @Override
        protected int fetch() {
            while (i < size) {
                Rectangle _bbox = getBoundingBox(i);
                if (boundingBox.intersects(_bbox))
                    return i++;
                i++;
            }
            return -1;
        }
    }

}
