package net.bodz.lily.criterion;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.IllegalUsageException;
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
    public boolean yes = true;

    public FieldCriterion() {
    }

    public FieldCriterion(String fieldName, boolean yes) {
        this.fieldName = fieldName;
        this.yes = yes;
    }

    @Override
    public abstract FieldCriterion clone();

    public FieldCriterion negate() {
        FieldCriterion o = clone();
        o.yes = ! o.yes;
        return o;
    }

    @Override
    public ICriterion reduce() {
        return this;
    }

    @Override
    public boolean isFieldCriterion() {
        return true;
    }

    protected boolean isFieldCompare() {
        return false;
    }

    protected boolean isFieldEquals() {
        return false;
    }

    protected boolean isEqualsOrNot() {
        throw new IllegalUsageException();
    }

    protected abstract String getDiscriminator();

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        jsonOutSmart(out, false);
    }

    public void jsonOutSmart(IJsonOut out, boolean useFieldMap)
            throws IOException, FormatException {
        if (! useFieldMap) {
            out.object();
            out.key(fieldName);
        }

        if (isFieldEquals()) {
            writeValue(out);
        } else {
            out.object();
            String key = getDiscriminator();
            if (! yes && ! isFieldEquals())
                key = "~" + key;
            out.key(key);
            writeValue(out);
            out.endObject();
        }

        if (! useFieldMap) {
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
        if (! fieldMap) {
            JsonObject o = in.getObject();
            String fieldName = o.keys().next();
            Object child = o.get(fieldName);
            in = JsonVariant.of(child);
            fieldNameStack.push(fieldName);
        }

        JsonObject o = in.getObject();
        String discriminator = o.keys().next();
        JsonVariant value = in.get(discriminator);

        this.yes = ! discriminator.startsWith("~");

        try {
            Class<?> fieldType = typeInferrer.getFieldType(fieldNameStack);
            readValue(value, fieldType);
        } finally {
            if (! fieldMap)
                fieldNameStack.pop();
        }
    }

    public abstract void readValue(JsonVariant in, Class<?> fieldType)
            throws ParseException;

    protected <T> T convert(Class<T> type, Object value) {
        if (value == null)
            return null;
        MutableVariant var = new MutableVariant(value);
        T dest = var.convert(type);
        return dest;
    }

}
