package net.bodz.bas.t.variant.conv;

import java.util.Map;

import net.bodz.bas.err.TypeConvertException;
import net.bodz.bas.fn.ITransformer;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IVarConverterExtension<T>
        extends IPriority {

    Class<?> getType();

    Map<Class<?>, ITransformer<Object, T>> getFromMap();

    Map<Class<?>, ITransformer<T, Object>> getToMap();

    boolean canConvertFrom(Class<?> type);

    boolean canConvertTo(Class<?> type);

    T convertFrom(Class<?> type, Object obj)
            throws TypeConvertException;

    <U> U convertTo(T value, Class<U> type)
            throws TypeConvertException;

}
