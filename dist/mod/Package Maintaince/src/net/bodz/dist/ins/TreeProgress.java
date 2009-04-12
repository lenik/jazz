package net.bodz.dist.ins;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.lang.err.OutOfDomainException;
import net.bodz.dist.nls.PackNLS;

public class TreeProgress implements Progress {

    private final TreeProgress parent;
    private final int          parentUnits;
    private final float        bias;
    private final float        range;
    private int                size;
    private int                index;
    private boolean            autoMode;

    public TreeProgress(int size) {
        this(null, -1, size);
    }

    public TreeProgress(TreeProgress parent, int size) {
        this(parent, 1, size);
    }

    /**
     * @param parentUnits
     *            number of parent units
     * @param size
     *            0 means INFINITE.
     */
    public TreeProgress(TreeProgress parent, int parentUnits, int size) {
        this.parent = parent;
        this.parentUnits = parentUnits;
        if (parent == null) {
            this.bias = 0;
            this.range = 1.0f;
            this.listeners = new ArrayList<ProgressChangeListener>(1);
        } else {
            this.bias = parent.get();
            this.range = parent.getUnitRange() * parentUnits;
            this.listeners = parent.listeners;
        }
        this.size = size;
    }

    public TreeProgress getParent() {
        return parent;
    }

    public int getParentUnits() {
        return parentUnits;
    }

    public float getUnitRange() {
        if (size == 0)
            return 0;
        return range / size;
    }

    public float get() {
        if (size == 0)
            return bias;
        float progress = bias + range * index / size;
        return progress;
    }

    public void set(float p) {
        int newIndex = (int) (p * size);
        setIndex(newIndex);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (this.size != size) {
            if (size < 0)
                throw new OutOfDomainException("size", size, 0); //$NON-NLS-1$
            this.size = size;
            progressChange();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        if (this.index != index) {
            if (index < 0)
                throw new OutOfDomainException("index", index, 0); //$NON-NLS-1$
            this.index = index;
            if (index >= size && autoMode) {
                size = 1;
                int n = index;
                while (n != 0) {
                    n >>= 1;
                    size <<= 1;
                }
                assert size > index;
            }
            progressChange();
        }
    }

    public void incr() {
        setIndex(index + 1);
    }

    public void incr(int n) {
        setIndex(index + n);
    }

    public boolean isAuto() {
        return autoMode;
    }

    public void setAuto(boolean autoMode) {
        this.autoMode = autoMode;
    }

    private final List<ProgressChangeListener> listeners;

    void progressChange() {
        if (listeners.isEmpty())
            return;
        ProgressChangeEvent e = new ProgressChangeEvent(this);
        for (ProgressChangeListener l : listeners)
            l.progressChange(e);
    }

    @Override
    public void addProgressChangeListener(ProgressChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeProgressChangeListener(ProgressChangeListener listener) {
        listeners.remove(listener);
    }

}
