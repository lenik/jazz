package net.bodz.lily.criterion;

import java.io.BufferedReader;
import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;

public class FieldCompare<T>
        extends FieldCriterion {

    Class<T> valueType;

    public CompareMode mode = CompareMode.EQUALS;
    public T value;

    public FieldCompare() {
    }

    public FieldCompare(String fieldName, boolean yes, CompareMode mode, T value) {
        super(fieldName, yes);
        if (mode == null)
            throw new NullPointerException("mode");
        this.mode = mode;
        this.value = value;
    }

    @Override
    public FieldCriterion clone() {
        return new FieldCompare<T>(fieldName, yes, mode, value);
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldCompare(this);
    }

    @Override
    protected boolean isFieldCompare() {
        return true;
    }

    @Override
    protected boolean isFieldEquals() {
        return (mode == CompareMode.EQUALS) || (mode == CompareMode.NOT_EQUALS);
    }

    @Override
    protected boolean isEqualsOrNot() {
        if (mode == CompareMode.EQUALS)
            return yes;
        if (mode == CompareMode.NOT_EQUALS)
            return ! yes;
        throw new IllegalUsageException();
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
    public void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {

        this.valueType = (Class<T>) fieldType;

        Object val = in.getScalarFor(fieldName);
        this.value = convert(valueType, val);
    }

    @Override
    public void parseObject(BufferedReader in)
            throws ParseException, IOException {
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        out.print(getDiscriminator());
        out.print(" ");
        out.print(value);
    }

}
