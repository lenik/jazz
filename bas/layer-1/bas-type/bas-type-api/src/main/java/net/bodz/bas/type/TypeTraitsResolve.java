package net.bodz.bas.type;

import java.lang.reflect.Method;

import net.bodz.bas.exceptions.IllegalUsageException;

public class TypeTraitsResolve {

    public static <T> ITypeTraits<? super T> findTraits(Class<T> type) {
        try {
            // XXX: method name configurable in annotation?
            Method method = null;
            Class<? super T> chain = type;
            while (true) {
                try {
                    method = chain.getDeclaredMethod("getTypeInfo");
                    break;
                } catch (NoSuchMethodException e) {
                    chain = chain.getSuperclass();
                    if (chain == null || chain == Object.class)
                        return null;
                }
            }
            method.setAccessible(true);

            @SuppressWarnings("unchecked")
            ITypeTraits<? super T> traits = (ITypeTraits<? super T>) method.invoke(null);

            return traits;
        } catch (Exception e) { // ReflectiveOperationException e) {
            throw new IllegalUsageException("Can't access method getTypeInfo()", e);
        }
    }

}
