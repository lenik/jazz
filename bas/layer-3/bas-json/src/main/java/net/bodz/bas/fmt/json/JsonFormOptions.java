package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

public class JsonFormOptions
        implements
            IJsonForm,
            IVarMapForm {

    boolean typeInfo;
    public boolean compact;

    JsonFormOptions() {
    }

    public JsonFormOptions(IVariantMap<String> map)
            throws ParseException {
        readObject(map);
    }

    @Override
    public void readObject(IVariantMap<String> map)
            throws ParseException {
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
    }

    public static final JsonFormOptions DEFAULT = new JsonFormOptions();
    public static final JsonFormOptions PRETTY = new JsonFormOptions();
    public static final JsonFormOptions COMPACT = new JsonFormOptions();
    public static final JsonFormOptions SQL = new JsonFormOptions();
    public static final JsonFormOptions WEB = new JsonFormOptions();
    public static final JsonFormOptions CANONICAL = new JsonFormOptions();

    public static final JsonFormOptions XXX = DEFAULT;

}
