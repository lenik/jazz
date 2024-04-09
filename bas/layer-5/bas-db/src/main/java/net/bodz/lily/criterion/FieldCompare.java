package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.t.variant.conv.StringVarConverter;

public class FieldCompare<T>
        extends FieldCriterion {

    Class<T> valueType;

    public CompareMode mode = CompareMode.EQUALS;
    public T value;

    public FieldCompare(Class<T> valueType) {
        this.valueType = valueType;
    }

    public FieldCompare(String fieldName, boolean yes, CompareMode mode, Class<T> valueType, T value) {
        super(fieldName, yes);
        if (mode == null)
            throw new NullPointerException("mode");
        if (valueType == null)
            throw new NullPointerException("valueType");
        this.mode = mode;
        this.valueType = valueType;
        this.value = value;
    }

    @Override
    public FieldCriterion clone() {
        return new FieldCompare<T>(fieldName, yes, mode, valueType, value);
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
    public void parseObject(String s, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        int sp = s.indexOf(' ');

        CompareMode mode = null;
        if (sp != -1) {
            String modeStr = s.substring(0, sp);
            mode = CompareMode.ofCamelName(modeStr);
            s = s.substring(sp + 1);
        }

        try {
            value = StringVarConverter.INSTANCE.to(s, valueType);
        } catch (TypeConvertException e) {
            throw new ParseException("error parse value from \"" + s + "\".", e);
        }

        if (mode != null)
            this.mode = mode;
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        out.print(getDiscriminator());
        out.print(" ");
        out.print(value);
    }

}
