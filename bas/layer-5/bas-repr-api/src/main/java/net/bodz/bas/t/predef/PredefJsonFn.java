package net.bodz.bas.t.predef;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

@FnHelper
public class PredefJsonFn {

    /**
     * @param strict
     *            raise error for unknown name/key.
     */
    public static <predef_t extends Predef<predef_t, K>, K extends Comparable<K>> //
    predef_t jsonGet(JsonObject o, String propertyName, Class<predef_t> type, predef_t defaultValue, boolean strict)
            throws ParseException {
        if (type == null)
            throw new NullPointerException("type");

        PredefMetadata<predef_t, K> metadata = PredefMetadata.forClass(type);
        if (metadata == null)
            throw new IllegalUsageException("Unregistered predef type: " + type);

        Object val = o.get(propertyName);
        if (val == null)
            return defaultValue;

        predef_t ret = null;
        if (val instanceof String) {
            String name = (String) val;
            ret = metadata.ofName(name);
            if (ret == null && strict)
                throw new ParseException(String.format(//
                        "Undefined %s name: %s.", type.getSimpleName(), name));
        } else {
            Class<K> keyType = metadata.getKeyType();
            IVarConverter<Object> keyConverter = VarConverters.getConverter(keyType);

            if (keyConverter.canConvertTo(val.getClass())) {
                K key = keyConverter.to(val, keyType);
                ret = metadata.ofKey(key);
                if (ret == null && strict) // no such key
                    throw new ParseException(String.format(//
                            "Undefined %s key: %s.", type.getSimpleName(), key));
            } else { // can't convert
                if (strict)
                    throw new ParseException(String.format(//
                            "Invalid %s value type: key=%s, valtype=%s", //
                            type.getSimpleName(), propertyName, val.getClass()));
            }
        }
        if (ret == null)
            ret = defaultValue;
        return ret;
    }

    public static <predef_t extends Predef<predef_t, K>, K extends Comparable<K>> //
    predef_t jsonGet(JsonObject o, String propertyName, Class<predef_t> type, predef_t defaultValue)
            throws ParseException {
        return jsonGet(o, propertyName, type, defaultValue, true);
    }

}
