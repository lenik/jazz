package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.fmt.json.JsonVariant;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.list.IStack;
import net.bodz.bas.t.variant.MutableVariant;

public abstract class FieldCriterion
        extends Criterion {

    public String fieldName;
    public boolean not;

    public FieldCriterion() {
    }

    public FieldCriterion(String fieldName, boolean not) {
        this.fieldName = fieldName;
        this.not = not;
    }

    @Override
    public boolean isFieldCriterion() {
        return true;
    }

    protected abstract String getDiscriminator();

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

    public void jsonOutDualMode(IJsonOut out, boolean fieldMap)
            throws IOException, FormatException {
        if (!fieldMap) {
            out.object();
            out.key(fieldName);
        }

        out.object();
        String key = getDiscriminator();
        if (not)
            key = "~" + key;
        out.key(key);
        writeValue(out);
        out.endObject();

        if (!fieldMap) {
            out.endObject();
        }
    }

    protected abstract void writeValue(IJsonOut out)
            throws IOException, FormatException;

    /**
     * Don't call this method directly.
     */
    @Deprecated
    @Override
    public final void jsonIn(JsonVariant in, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        jsonIn(in, false, typeInferrer, fieldNameStack);
    }

    public void jsonIn(JsonVariant in, boolean fieldMap, ITypeInferrer typeInferrer, IStack<String> fieldNameStack)
            throws ParseException {
        if (!fieldMap) {
            JsonObject o = in.getObject();
            String fieldName = o.keys().next();
            Object child = o.get(fieldName);
            in = JsonVariant.of(child);
            fieldNameStack.push(fieldName);
        }

        JsonObject o = in.getObject();
        String discriminator = o.keys().next();
        JsonVariant value = in.get(discriminator);

        this.not = discriminator.startsWith("~");

        try {
            Class<?> fieldType = typeInferrer.getFieldType(fieldNameStack);
            readValue(value, fieldType);
        } finally {
            if (!fieldMap)
                fieldNameStack.pop();
        }
    }

    protected abstract void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException;

    protected <T> T convert(Class<T> type, Object value) {
        if (value == null)
            return null;
        MutableVariant var = new MutableVariant(value);
        T dest = var.convert(type);
        return dest;
    }

}
