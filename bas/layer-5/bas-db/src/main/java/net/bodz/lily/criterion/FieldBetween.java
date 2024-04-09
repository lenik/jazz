package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.json.JsonArray;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.t.variant.conv.StringVarConverter;

public class FieldBetween<T>
        extends FieldCriterion {

    Class<T> valueType;
    public T min;
    public T max;

    public FieldBetween() {
    }

    public FieldBetween(String fieldName, boolean yes, T min, T max) {
        super(fieldName, yes);
        if (min == null)
            throw new NullPointerException("min");
        if (max == null)
            throw new NullPointerException("max");
        this.min = min;
        this.max = max;
    }

    @Override
    public FieldCriterion clone() {
        return new FieldBetween<T>(fieldName, yes, min, max);
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldBetween(this);
    }

    @Override
    protected String getDiscriminator() {
        return Criterions.K_BETWEEN;
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
        out.array();
        out.value(min);
        out.value(max);
        out.endArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
        JsonArray a = in.getArrayFor(fieldName);
        if (a.length() != 2)
            throw new ParseException("expect array of size 2.");

        this.valueType = (Class<T>) fieldType;

        min = convert(valueType, a.get(0));
        max = convert(valueType, a.get(1));
    }

    @Override
    public void parseObject(String s, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        s = s.trim();
        if (! (s.startsWith("(") && s.endsWith(")")))
            throw new ParseException("expect '(' and ')' at start and end");

        s = s.substring(1, s.length() - 1);
        int comma = s.indexOf(',');
        if (comma == -1)
            throw new ParseException("missing ',' in (...).");

        String minStr = s.substring(0, comma);
        String maxStr = s.substring(comma + 1);

        T min;
        try {
            min = StringVarConverter.INSTANCE.to(minStr, valueType);
        } catch (TypeConvertException e) {
            throw new ParseException("error parse min value from \"" + minStr + "\".", e);
        }

        T max;
        try {
            max = StringVarConverter.INSTANCE.to(maxStr, valueType);
        } catch (TypeConvertException e) {
            throw new ParseException("error parse min value from \"" + minStr + "\".", e);
        }

        this.min = min;
        this.max = max;
    }

    @Override
    public void printObject(ITreeOut out)
            throws IOException {
        out.print("(");
        out.print(min);
        out.print(", ");
        out.print(max);
        out.print(")");
    }

}
