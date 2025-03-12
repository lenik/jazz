package net.bodz.bas.repr.path.builtin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.IPathDispatcher;
import net.bodz.bas.t.variant.IVariantMap;

public class PropertyPathDispatcher
        implements
            IPathDispatcher {

    public static final int PRIORITY = BuiltinPathDispatcherPriorities.PRIORITY_PROPERTY;
    static final Class<?> ACCEPT_TYPES[] = { Object.class };

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public Class<?>[] getAcceptTypes() {
        return ACCEPT_TYPES;
    }

    @Override
    public IPathArrival dispatch(IPathArrival previous, Object source, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        if (source == null)
            throw new PathDispatchException("null source.");

        String propertyName = tokens.peek();
        if (propertyName == null)
            return null;

        Map<String, IPropertyDescriptor> propertyMap = clsPropertyMap.getOrLoad(source.getClass());

        IPropertyDescriptor propertyDescriptor = propertyMap.get(propertyName);
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
    static final LazyTypeMap<Map<String, IPropertyDescriptor>> clsPropertyMap;
    static {
        clsPropertyMap = TypeMapRegistry.createMap(new EntryLoader());
        CLS_PROPERTY_MAP_ID = clsPropertyMap.getRegisteredId();
    }

    static class EntryLoader
            implements
                IMapEntryLoader<Class<?>, Map<String, IPropertyDescriptor>> {

        @Override
        public Map<String, IPropertyDescriptor> loadValue(Class<?> type)
                throws LazyLoadException {

            Map<String, IPropertyDescriptor> propertyMap = new HashMap<String, IPropertyDescriptor>();

            IPropertyDescriptor[] propertyDescriptors;
            try {
                propertyDescriptors = Introspectors.getBeanInfo(type).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                throw new LazyLoadException(e.getMessage(), e);
            }
            for (IPropertyDescriptor p : propertyDescriptors)
                propertyMap.put(p.getName(), p);

            return propertyMap;
        }

    } // EntryLoader

}
