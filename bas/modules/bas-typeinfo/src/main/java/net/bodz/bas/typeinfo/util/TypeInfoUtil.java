package net.bodz.bas.typeinfo.util;

import java.lang.reflect.Method;

import net.bodz.bas.api.exceptions.IllegalUsageException;
import net.bodz.bas.typeinfo.InstanceStore;
import net.bodz.bas.typeinfo.TypeInfo;

public class TypeInfoUtil {

    public static TypeInfo findTypeInfo(Class<?> type) {
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
            TypeInfo typeInfo = (TypeInfo) method.invoke(null);
            return typeInfo;
        } catch (Exception e) { // ReflectiveOperationException e) {
            throw new IllegalUsageException("Can't access method getTypeInfo()", e);
        }
    }

    public static InstanceStore<?> findInstanceStore(Class<?> type, StoreClass storeClassAnnotation) {
        if (storeClassAnnotation != null) {
            Class<? extends InstanceStore<?>> storeClass = storeClassAnnotation.value();
            try {
                InstanceStore<?> instanceStore = storeClass.newInstance();
                return instanceStore;
            } catch (Exception e) {
                throw new IllegalUsageException("Can't create store instance of: " + storeClass, e);
            }
        } else {
            TypeInfo typeInfo = TypeInfoUtil.findTypeInfo(type);
            if (typeInfo == null)
                return null;
            InstanceStore<?> instanceStore = typeInfo.query(InstanceStore.class);
            return instanceStore;
        }
    }

}
