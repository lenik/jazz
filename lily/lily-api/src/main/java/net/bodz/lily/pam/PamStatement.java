package net.bodz.lily.pam;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.variant.MutableVariant;

public class PamStatement
        implements
            IJsonForm {

    final IPamModule module;

    Map<String, Object> values = new HashMap<>();

    public PamStatement(IPamModule module) {
        if (module == null)
            throw new NullPointerException("module");
        this.module = module;
    }

    public final void verify()
            throws PamValidateException {
        module.verify(this);
    }

    public void clear() {
        values.clear();
    }

    public Object get(String fieldName) {
        PamField field = module.getPamField(fieldName);
        if (field == null)
            throw new NoSuchKeyException(fieldName);
        return values.get(fieldName);
    }

    public void set(String fieldName, Object value) {
        PamField field = module.getPamField(fieldName);
        if (field == null)
            throw new NoSuchKeyException(fieldName);
        values.put(fieldName, value);
    }

    public String getFormatted(String fieldName) {
        PamField field = module.getPamField(fieldName);
        if (field == null)
            throw new NoSuchKeyException(fieldName);
        Object value = values.get(fieldName);
        return field.format(value);
    }

    public Object setParsed(String fieldName, String text)
            throws ParseException {
        PamField field = module.getPamField(fieldName);
        if (field == null)
            throw new NoSuchKeyException(fieldName);
        Object value = field.parse(text);
        values.put(fieldName, value);
        return value;
    }

    static final Set<Class<?>> jsonCapableTypes = new HashSet<>();
    static {
        jsonCapableTypes.addAll(Arrays.asList(//
                Integer.class, Long.class, Float.class, Double.class, //
                Boolean.class, //
                Character.class, String.class));
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        clear();
        Map<String, PamField> fields = module.getPamFields();
        for (String fieldName : fields.keySet()) {
            PamField field = fields.get(fieldName);
            Object jsonValue = o.get(fieldName);
            if (jsonValue == null) {
                if (! field.isOptional())
                    throw new ParseException(String.format(//
                            "required field %s isn't set.", fieldName));
                continue;
            }
            Object value;
            if (jsonValue instanceof String) {
                value = field.parse((String) jsonValue);
            } else {
                // if (jsonCapableTypes.contains(field.getFieldType())) {
                MutableVariant var = MutableVariant.wrap(jsonValue);
                value = var.convert(field.getFieldType());
            }
            set(fieldName, value);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        Map<String, PamField> fields = module.getPamFields();
        for (String fieldName : fields.keySet()) {
            PamField field = fields.get(fieldName);
            Object value = get(fieldName);
            if (value == null && field.isOptional())
                continue;
            if (! jsonCapableTypes.contains(field.getFieldType())) {
                value = field.format(value);
            }
            out.entry(fieldName, value);
        }
    }

    @Override
    public String toString() {
        Map<String, PamField> fields = module.getPamFields();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (String fieldName : fields.keySet()) {
            PamField field = fields.get(fieldName);
            Object value = get(fieldName);
            if (value == null && field.isOptional())
                continue;
            String text = field.format(value);
            if (i++ != 0)
                sb.append("\n");
            sb.append(fieldName);
            sb.append(": ");
            sb.append(text);
        }
        return sb.toString();
    }

}
