package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;

public class FieldCompare<T>
        extends FieldCriterion {

    Class<T> valueType;

    public CompareMode mode = CompareMode.EQUALS;
    public T value;

    public FieldCompare() {
    }

    public FieldCompare(String fieldName, boolean not, CompareMode mode, T value) {
        super(fieldName, not);
        if (mode == null)
            throw new NullPointerException("mode");
        if (value == null)
            throw new NullPointerException("value");
        this.mode = mode;
        this.value = value;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldCompare(this);
    }

    @Override
    protected String getDiscriminator() {
        return mode.camelName();
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
        out.value(value);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {

        this.valueType = (Class<T>) fieldType;

        Object val = in.getScalarFor(fieldName);
        this.value = convert(valueType, val);
    }

}
