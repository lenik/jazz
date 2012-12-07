package net.bodz.bas.repr.path.builtin;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.ClassLocal;
import net.bodz.bas.c.type.ClassLocals;
import net.bodz.bas.err.LazyLoadException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.repr.path.AbstractPathDispatcher;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;

public class PropertyPathDispatcher
        extends AbstractPathDispatcher {

    public static final int PRIORITY = 40;

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public IPathArrival dispatch(IPathArrival context, ITokenQueue tokens)
            throws PathDispatchException {
        Object obj = context.getTarget();
        if (obj == null)
            throw new PathDispatchException("null target.");

        String propertyName = tokens.peek();
        if (propertyName == null)
            throw new UnexpectedException("no more token.");

        Map<String, PropertyDescriptor> propertyMap = classMap.load(obj.getClass());

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
