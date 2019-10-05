package net.bodz.bas.t.predef;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.meta.codegen.ExcludedFromIndex;
import net.bodz.bas.t.variant.conv.AbstractVarConverter;
import net.bodz.bas.t.variant.conv.IVarConverter;
import net.bodz.bas.t.variant.conv.VarConverters;

@ExcludedFromIndex
public class PredefVarConverter<T extends Predef<T, K>, K extends Comparable<K>>
        extends AbstractVarConverter<T> {

    Class<K> keyType;
    IVarConverter<K> keyConverter;
    PredefMetadata<T, K> metadata; // weak-ref

    public PredefVarConverter(Class<T> type, Class<K> keyType) {
        super(type);
        this.keyType = keyType;
        this.metadata = PredefMetadata.forClass(type);
    }

    IVarConverter<K> getKeyConverter() {
        if (keyConverter == null) {
            keyConverter = VarConverters.getConverter(keyType);
            if (keyConverter == null)
                throw new NotImplementedException(String.format(//
                        "No converter is defined for key type %s.", keyType));
        }
        return keyConverter;
    }

    @Override
    public T fromNumber(Number in)
            throws TypeConvertException {
        if (in == null)
            return null;
        IVarConverter<K> keyConverter = getKeyConverter();
        K key = keyConverter.from(in);
        T obj = metadata.ofKey(key);
        return obj;
    }

    @Override
    public Number toNumber(T value) {
        if (value == null)
            return null;
        K key = value.getKey();
        Number keyAsNum = keyConverter.toNumber(key);
        return keyAsNum;
    }

    @Override
    public T fromString(String in)
            throws TypeConvertException {
        if (in == null)
            return null;
        T obj = metadata.ofName(in);
        return obj;
    }

    @Override
    public String toString(T value) {
        if (value == null)
            return null;
        String name = value.getName();
        return name;
    }

    @Override
    public boolean toBoolean(T value) {
        return value != null;
    }

}
