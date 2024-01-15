package net.bodz.lily.criterion;

public class FieldBetween<T>
        extends FieldCriterion {

    T min;
    T max;

    public FieldBetween(String fieldName, boolean not, T min, T max) {
        super(fieldName, not);
        if (min == null)
            throw new NullPointerException("min");
        if (max == null)
            throw new NullPointerException("max");
        this.min = min;
        this.max = max;
    }

}
