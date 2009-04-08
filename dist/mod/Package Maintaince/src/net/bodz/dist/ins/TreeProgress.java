package net.bodz.dist.ins;

public class TreeProgress {

    private final TreeProgress parent;
    private final float        bias;
    private final float        range;
    private int                size;
    private int                index;

    public TreeProgress(int size) {
        this(null, size);
    }

    public TreeProgress(TreeProgress parent, int size) {
        if (size < 1)
            throw new IllegalArgumentException("size must be positive");
        this.parent = parent;
        if (parent == null) {
            this.bias = 0;
            this.range = 1.0f;
        } else {
            this.bias = parent.get();
            this.range = parent.getUnitRange();
        }
        this.size = size;
    }

    public TreeProgress getParent() {
        return parent;
    }

    public float get() {
        float progress = bias + range * index / size;
        return progress;
    }

    public float getUnitRange() {
        return range / size;
    }

    public void incr() {
        index++;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setIndex(float p) {
        index = (int) (p * size);
    }

}
