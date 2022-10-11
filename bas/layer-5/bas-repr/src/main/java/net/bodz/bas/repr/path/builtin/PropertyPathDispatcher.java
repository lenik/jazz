package net.bodz.bas.repr.path.builtin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.t.variant.IVariantMap;

import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class PropertyPathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_PROPERTY;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (source == null)
            throw new PathDispatchException("null source.");

        String propertyName = tokens.peek();
        if (propertyName == null)
            return null;

        Map<String, PropertyDescriptor> propertyMap = clsPropertyMap.getOrLoad(source.getClass());

        PropertyDescriptor propertyDescriptor = propertyMap.get(propertyName);
        if (propertyDescriptor == null)
            return null;

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null)
            throw new PathDispatchException("Property " + propertyName + " isn't readable");

        Object result;
        try {
            result = readMethod.invoke(source);
        } catch (Exception e) {
            throw new PathDispatchException(e);
        }

        return PathArrival.shift(previous, this, result, tokens);
    }

    static final String CLS_PROPERTY_MAP_ID;
    static final LazyTypeMap<Map<String, PropertyDescriptor>> clsPropertyMap;
    static {
        clsPropertyMap = TypeMapRegistry.createMap(new EntryLoader());
        CLS_PROPERTY_MAP_ID = clsPropertyMap.getRegisteredId();
    }

    static class EntryLoader
            implements
                IMapEntryLoader<Class<?>, Map<String, PropertyDescriptor>> {

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
