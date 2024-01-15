package net.bodz.lily.criterion;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonArray;

public class FieldIn<T>
        extends FieldCriterion {

    Class<T> valueType;
    public Collection<T> values;

    public FieldIn() {
        values = Collections.emptyList();
    }

    @SafeVarargs
    public FieldIn(String fieldName, boolean not, T... values) {
        super(fieldName, not);
        if (values == null)
            throw new NullPointerException("values");
        this.values = Arrays.asList(values);
    }

    public FieldIn(String fieldName, boolean not, Collection<T> values) {
        super(fieldName, not);
        if (values == null)
            throw new NullPointerException("values");
        this.values = values;
    }

    @Override
    public void accept(ICriterionVisitor visitor) {
        visitor.fieldIn(this);
    }

    @Override
    protected String getDiscriminator() {
        return Criterions.K_IN;
    }

    @Override
    protected void writeValue(IJsonOut out)
            throws IOException, FormatException {
        out.array();
        for (Object item : values) {
            out.value(item);
        }
        out.endArray();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException {
        values.clear();

        this.valueType = (Class<T>) fieldType;

        JsonArray jArray = in.getArrayFor(fieldName);
        for (Object jItemAny : jArray) {
            JsonVariant jItem = JsonVariant.of(jItemAny);
            Object value = jItem.getScalarFor(fieldName);
            T dest = convert(valueType, value);
            values.add(dest);
        }
    }

}
