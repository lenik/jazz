package net.bodz.bas.potato.element;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.potato.element.*;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IPotatoElementProvider
        extends IPriority {

    IProperty getProperty(Class<?> objType, Object obj, String propertyName);

    IMethod getMethod(Class<?> objType, Object obj, MethodSignature signature);

    IConstructor getConstructor(Class<?> objType, Object obj, MethodSignature signature);

    IEvent getEvent(Class<?> objType, Object obj, String eventName);

    void fillProperties(Class<?> objType, Object obj, MutablePropertyMap propertyMap);

    void fillMethods(Class<?> objType, Object obj, MutableMethodMap methodMap);

    void fillConstructor(Class<?> objType, Object obj, MutableConstructorMap constructorMap);

    void fillEventMap(Class<?> objType, Object obj, MutableEventMap eventMap);

}
