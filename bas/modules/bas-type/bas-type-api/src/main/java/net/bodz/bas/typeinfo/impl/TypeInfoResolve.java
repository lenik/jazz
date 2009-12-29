package net.bodz.bas.typeinfo.impl;

import java.lang.reflect.Method;

import net.bodz.bas.exceptions.IllegalUsageException;
import net.bodz.bas.type.IInstanceStore;
import net.bodz.bas.type.ITypeInfo;
import net.bodz.bas.type.UseInstanceStore;

public class TypeInfoResolve {

    public static ITypeInfo findTypeInfo(Class<?> type) {
        try {
            // XXX: method name configurable in annotation?
            Method method = null;
            while (true) {
                try {
                    method = type.getDeclaredMethod("getTypeInfo");
                    break;
                } catch (NoSuchMethodException e) {
                    type = type.getSuperclass();
                    if (type == null || type == Object.class)
                        return null;
                }
            }
            method.setAccessible(true);
            ITypeInfo typeInfo = (ITypeInfo) method.invoke(null);
            return typeInfo;
        } catch (Exception e) { // ReflectiveOperationException e) {
            throw new IllegalUsageException("Can't access method getTypeInfo()", e);
        }
    }

    public static IInstanceStore<?> findInstanceStore(Class<?> type, UseInstanceStore storeClassAnnotation) {
        if (storeClassAnnotation != null) {
            Class<? extends IInstanceStore<?>> storeClass = storeClassAnnotation.value();
            try {
                IInstanceStore<?> instanceStore = storeClass.newInstance();
                return instanceStore;
            } catch (Exception e) {
                throw new IllegalUsageException("Can't create store instance of: " + storeClass, e);
            }
        } else {
            ITypeInfo typeInfo = TypeInfoResolve.findTypeInfo(type);
            if (typeInfo == null)
                return null;
            IInstanceStore<?> instanceStore = typeInfo.query(IInstanceStore.class);
            return instanceStore;
        }
    }

}
