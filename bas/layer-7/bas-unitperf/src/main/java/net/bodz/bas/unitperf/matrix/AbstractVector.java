package net.bodz.bas.unitperf.matrix;

/**
 * TODO
 */
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

    @Override
    public Vector add(Vector vector) {
        return null;
    }

    @Override
    public int average() {
        return 0;
    }

    @Override
    public int dotProduct(Vector vector) {
        return 0;
    }

    @Override
    public ArrayMatrix multiply(ArrayMatrix matrix) {
        return null;
    }

    @Override
    public Vector multiply(Vector vector) {
        return null;
    }

    @Override
    public Vector negative() {
        return null;
    }

    @Override
    public Vector subtract(Vector vector) {
        return null;
    }

    @Override
    public int sum() {
        return 0;
    }

    @Override
    public Vector transpose() {
        return null;
    }

}
