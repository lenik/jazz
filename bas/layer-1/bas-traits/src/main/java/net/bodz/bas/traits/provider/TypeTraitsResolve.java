package net.bodz.bas.traits.provider;

import java.lang.reflect.Method;

import net.bodz.bas.traits.ICommonTraits;
import net.bodz.bas.util.exception.IllegalUsageException;

public class TypeTraitsResolve {

    public static <T> ICommonTraits<? super T> findTraits(Class<T> type) {
        try {
            // XXX: method name configurable in annotation?
            Method method = null;
            Class<? super T> chain = type;
            while (true) {
                try {
                    method = chain.getDeclaredMethod("getTypeTraits");
                    break;
                } catch (NoSuchMethodException e) {
                    chain = chain.getSuperclass();
                    if (chain == null || chain == Object.class)
                        return null;
                }
            }
            method.setAccessible(true);

            @SuppressWarnings("unchecked")
            ICommonTraits<? super T> traits = (ICommonTraits<? super T>) method.invoke(null);

            return traits;
        } catch (Exception e) { // ReflectiveOperationException e) {
            throw new IllegalUsageException("Can't access method getTypeTraits()", e);
        }
    }

}
