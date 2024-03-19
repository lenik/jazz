package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.util.Map;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.t.variant.IVarMapForm;
import net.bodz.bas.t.variant.IVariantMap;

/**
 * TODO:
 *
 * Refactor JsonFormOptions -> JsonOutProcess(closure, currentDepth, opts(maxDepth))
 *
 * Also, *-Run to *-Process.
 */
public class JsonFormOptions
        implements
            IJsonForm,
            IVarMapForm {

    public static final String K_TYPE_INFO = "typeInfo";
    public static final String K_COMPACT = "compact";
    public static final String K_INDENT = "indent";
    public static final String K_MAX_DEPTH = "maxDepth";
    public static final String K_INCLUDE_NULL = "includeNull";
    public static final String K_INCLUDE_FALSE = "includeFalse";

    boolean typeInfo;
    boolean compact;
    int indent;

    int maxDepth = -1;

    boolean includeNull;
    boolean includeFalse = true;

    public JsonFormOptions() {
    }

    public JsonFormOptions indent(int indent) {
        this.indent = indent;
        return this;
    }

    public JsonFormOptions maxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
        return this;
    }

    public JsonFormOptions includeNull() {
        includeNull = true;
        return this;
    }

    public JsonFormOptions excludeFalse() {
        includeFalse = true;
        return this;
    }

    @Override
    public void readObject(IVariantMap<String> map) {
        typeInfo = map.getBoolean(K_TYPE_INFO, typeInfo);
        compact = map.getBoolean(K_COMPACT, compact);
        indent = map.getInt(K_INDENT, indent);

        includeNull = map.getBoolean(K_INCLUDE_NULL, includeNull);
        includeFalse = map.getBoolean(K_INCLUDE_FALSE, includeFalse);
        maxDepth = map.getInt(K_MAX_DEPTH, maxDepth);
    }

    @Override
    public void writeObject(Map<String, Object> map) {
    }

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        typeInfo = o.getBoolean(K_TYPE_INFO, typeInfo);
        compact = o.getBoolean(K_COMPACT, compact);
        indent = o.getInt(K_INDENT, indent);

        includeNull = o.getBoolean(K_INCLUDE_NULL, includeNull); // false
        includeFalse = o.getBoolean(K_INCLUDE_FALSE, includeFalse); // true
        maxDepth = o.getInt(K_MAX_DEPTH, maxDepth); // -1
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        out.entry(K_TYPE_INFO, typeInfo);
        out.entry(K_COMPACT, compact);
        out.entry(K_INDENT, indent);

        out.entry(K_INCLUDE_NULL, includeNull);
        out.entry(K_INCLUDE_FALSE, includeFalse);

        out.entry(K_MAX_DEPTH, maxDepth);
    }

    public static final JsonFormOptions DEFAULT = new JsonFormOptions();
    public static final JsonFormOptions PRETTY = new JsonFormOptions().indent(4);
    public static final JsonFormOptions COMPACT = new JsonFormOptions();
    public static final JsonFormOptions SQL = new JsonFormOptions();
    public static final JsonFormOptions WEB = new JsonFormOptions();
    public static final JsonFormOptions CANONICAL = new JsonFormOptions();

    public static final JsonFormOptions XXX = DEFAULT;

}
