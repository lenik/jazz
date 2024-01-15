package net.bodz.lily.criterion;

public class FieldCompare<T>
        extends FieldCriterion {

    CompareMode mode;
    T value;

    public FieldCompare(String fieldName, boolean not, CompareMode mode, T value) {
        super(fieldName, not);
        if (mode == null)
            throw new NullPointerException("mode");
        if (value == null)
            throw new NullPointerException("value");
        this.mode = mode;
        this.value = value;
    }

}
