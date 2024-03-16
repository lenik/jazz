package net.bodz.bas.t.predef;

import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.meta.source.FnHelper;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

@FnHelper
public class PredefJsonFn<E extends Predef<E, K>, K extends Comparable<K>> {

    final Class<E> type;
    String propertyName; // error hint

    public PredefJsonFn(Class<E> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;
    }

    public PredefJsonFn<E, K> property(String propertyName) {
        this.propertyName = propertyName;
        return this;
    }

    public E parseAny(Object any)
            throws ParseException {
        return parseAny(any, null, true);
    }

    public E parseAny(Object any, E defaultValue) {
        try {
            return parseAny(any, defaultValue, false);
        } catch (ParseException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
    }

    /**
     * @param raiseException
     *            raise error for unknown name/key.
     */
    E parseAny(Object any, E defaultValue, boolean raiseException)
            throws ParseException {
        if (type == null)
            throw new NullPointerException("type");

        PredefMetadata<E, K> metadata = PredefMetadata.forClass(type);
        if (metadata == null)
            throw new IllegalUsageException("Unregistered predef type: " + type);

        if (any == null)
            return defaultValue;

        E ret = null;
        if (any instanceof String) {
            String name = (String) any;
            ret = metadata.ofName(name);
            if (ret == null && raiseException)
                throw new ParseException(String.format(//
                        "Undefined %s name: %s.", type.getSimpleName(), name));
        } else {
            Class<K> keyType = metadata.getKeyType();
            IVarConverter<Object> keyConverter = VarConverters.getConverter(keyType);

            if (keyConverter.canConvertTo(any.getClass())) {
                K key = keyConverter.to(any, keyType);
                ret = metadata.ofKey(key);
                if (ret == null && raiseException) // no such key
                    throw new ParseException(String.format(//
                            "Undefined %s key: %s.", type.getSimpleName(), key));
            } else { // can't convert
                if (raiseException)
                    throw new ParseException(String.format(//
                            "Invalid %s value type: key=%s, valtype=%s", //
                            type.getSimpleName(), propertyName, any.getClass()));
            }
        }
        if (ret == null)
            ret = defaultValue;
        return ret;
    }

}
