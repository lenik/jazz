package net.bodz.bas.unitperf.matrix;

public abstract class AbstractVector
        implements Vector {

    private static final long serialVersionUID = 1L;

    protected final int[] data;

    public AbstractVector(int... data) {
        if (data == null)
            throw new NullPointerException("data");
        this.data = data;
    }

    protected abstract Vector dup(int... data);

    @Override
    protected Vector clone() {
        return dup(data);
    }

    public int get(int index) {
        return data[index];
    }

    public void set(int index, int value) {
        data[index] = value;
    }

    @Override
    public Vector diff() {
        return null;
    }

}
