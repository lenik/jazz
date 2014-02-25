package net.bodz.bas.repr.path.builtin;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;

public class PropertyPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 40;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = previous.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String propertyName = tokens.peek();
        if (propertyName == null)
            return previous;

        Map<String, PropertyDescriptor> propertyMap = clsPropertyMap.getOrLoad(obj.getClass());

        PropertyDescriptor propertyDescriptor = propertyMap.get(propertyName);
        if (propertyDescriptor == null)
            return null;

        tokens.shift();

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null)
            throw new PathDispatchException("Property " + propertyName + " isn't readable");

        Object result;
        try {
            result = readMethod.invoke(obj);
        } catch (Exception e) {
            throw new PathDispatchException(e);
        }

        return new PathArrival(previous, result, propertyName, tokens.getRemainingPath());
    }

    static final String CLS_PROPERTY_MAP_ID;
    static final LazyTypeMap<Map<String, PropertyDescriptor>> clsPropertyMap;
    static {
        clsPropertyMap = TypeMapRegistry.createMap(new EntryLoader());
        CLS_PROPERTY_MAP_ID = clsPropertyMap.getRegisteredId();
    }

    static class EntryLoader
            implements IMapEntryLoader<Class<?>, Map<String, PropertyDescriptor>> {

        @Override
        public Map<String, PropertyDescriptor> loadValue(Class<?> type)
                throws LazyLoadException {

            Map<String, PropertyDescriptor> propertyMap = new HashMap<String, PropertyDescriptor>();

            PropertyDescriptor[] propertyDescriptors;
            try {
                propertyDescriptors = Introspector.getBeanInfo(type).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                throw new LazyLoadException(e.getMessage(), e);
            }
            for (PropertyDescriptor p : propertyDescriptors)
                propertyMap.put(p.getName(), p);

            return propertyMap;
        }

    } // EntryLoader

}
