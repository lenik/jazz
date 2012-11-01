package net.bodz.bas.potato.spi.reflect;

import net.bodz.bas.lang.mi.QueryException;
import net.bodz.bas.potato.model.IConstructorMap;
import net.bodz.bas.potato.model.IEventMap;
import net.bodz.bas.potato.model.IMethodMap;
import net.bodz.bas.potato.model.IPropertyMap;
import net.bodz.bas.potato.model.IType;
import net.bodz.bas.potato.spi.AbstractPotatoProvider;
import net.bodz.bas.potato.spi.builtin.DefaultConstructorMap;
import net.bodz.bas.potato.spi.builtin.DefaultMethodMap;
import net.bodz.bas.potato.spi.builtin.DefaultPropertyMap;
import net.bodz.bas.potato.spi.builtin.NullEventMap;

public class ReflectPotatoProvider
        extends AbstractPotatoProvider {

    @Override
    public IType getType(Class<?> objType)
            throws QueryException {
        return new ReflectType(objType);
    }

    @Override
    public IPropertyMap getPropertyMap(Class<?> objType)
            throws QueryException {
        DefaultPropertyMap propertyMap = new DefaultPropertyMap();
        propertyMap.addClassFields(objType);
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap(Class<?> objType)
            throws QueryException {
        DefaultMethodMap methodMap = new DefaultMethodMap();
        methodMap.addClassMethods(objType);
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap(Class<?> objType)
            throws QueryException {
        DefaultConstructorMap constructorMap = new DefaultConstructorMap();
        constructorMap.addClassConstructors(objType);
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap(Class<?> objType)
            throws QueryException {
        return NullEventMap.getInstance();
    }

}
