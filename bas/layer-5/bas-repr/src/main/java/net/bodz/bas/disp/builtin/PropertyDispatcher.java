package net.bodz.bas.disp.builtin;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;
import net.bodz.bas.err.LazyLoadException;

public class PropertyDispatcher
        extends AbstractDispatcher {

    @Override
    public int getPriority() {
        return DispatchConfig.PRIORITY_FIELD;
    }

    @Override
    public IPathArrival dispatch(IPathArrival context, ITokenQueue tokens)
            throws DispatchException {
        Object obj = context.getTarget();
        if (obj == null)
            return null;

        String propertyName = tokens.peek();
        if (propertyName == null)
            return null;

        Map<String, PropertyDescriptor> propertyMap = classMap.load(obj.getClass());

        PropertyDescriptor propertyDescriptor = propertyMap.get(propertyName);
        if (propertyDescriptor == null)
            return null;

        tokens.shift();

        Method readMethod = propertyDescriptor.getReadMethod();
        if (readMethod == null)
            throw new DispatchException("Property " + propertyName + " isn't readable");

        Object result;
        try {
            result = readMethod.invoke(obj);
        } catch (Exception e) {
            throw new DispatchException(e);
        }

        return new PathArrival(context, result, propertyName, tokens.getRemainingPath());
    }

    static final String CLASS_LOCAL_ID;
    static final ClassLocal<Map<String, PropertyDescriptor>> classMap;
    static {
        classMap = ClassLocals.createMap(new EntryLoader());
        CLASS_LOCAL_ID = classMap.getRegisteredId();
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
