package net.bodz.bas.unitperf.matrix;

public interface Vector {
//        extends ArrayMatrix {

    int get(int index);

    void set(int index, int value);

    Vector transpose();

    Vector diff();

    // Vector intg();

    Vector add(Vector vector);

    Vector subtract(Vector vector);

    Vector multiply(Vector vector);

    int sum();

    int average();

    Vector negative();

    int dotProduct(Vector vector);

    ArrayMatrix multiply(ArrayMatrix matrix);

}
