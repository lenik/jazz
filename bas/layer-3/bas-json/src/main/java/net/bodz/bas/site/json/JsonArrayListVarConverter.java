package net.bodz.bas.site.json;

import org.json.JSONArray;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.variant.conv.AbstractVarConverter;

/**
 * TODO not implemented well, yet.
 */
@ExcludedFromIndex
public class JsonArrayListVarConverter
        extends AbstractVarConverter<JsonArrayList> {

    public JsonArrayListVarConverter() {
        super(JsonArrayList.class);
    }

    @Override
    public JsonArrayList fromNumber(Number in)
            throws TypeConvertException {
        JSONArray array = new JSONArray();
        array.put(in);
        return new JsonArrayList(array);
    }

    @Override
    public JsonArrayList fromString(String in)
            throws TypeConvertException {
        JSONArray array = new JSONArray();
        array.put(in);
        return new JsonArrayList(array);
    }

    @Override
    public Number toNumber(JsonArrayList value) {
        if (value.size() == 0)
            return null;
        Object first = value.get(0);
        if (first == null)
            return null;
        if (first instanceof Number)
            return (Number) first;
        String str = first.toString();
        return Double.parseDouble(str);
    }

    @Override
    public boolean toBoolean(JsonArrayList value) {
        return false;
    }

}
