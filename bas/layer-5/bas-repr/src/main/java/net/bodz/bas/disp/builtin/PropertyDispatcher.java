package net.bodz.bas.disp.builtin;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.disp.AbstractDispatcher;
import net.bodz.bas.disp.DispatchConfig;
import net.bodz.bas.disp.DispatchException;
import net.bodz.bas.disp.IPathArrival;
import net.bodz.bas.disp.ITokenQueue;
import net.bodz.bas.disp.PathArrival;

public class PropertyDispatcher
        extends AbstractDispatcher {

    public PropertyDispatcher() {
        super();
    }

    public PropertyDispatcher(String name) {
        super(name);
    }

    @Override
    public int getOrder() {
        return DispatchConfig.ORDER_FIELD;
    }

    private transient ClassMap<String, PropertyDescriptor> classMap;
    {
        classMap = new ClassMap<String, PropertyDescriptor>();
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

        Class<? extends Object> contextClass = obj.getClass();

        Map<String, PropertyDescriptor> propertyMap = classMap.get(contextClass);
        if (propertyMap == null) {
            propertyMap = new HashMap<String, PropertyDescriptor>();

            PropertyDescriptor[] propertyDescriptors;
            try {
                propertyDescriptors = Introspector.getBeanInfo(contextClass).getPropertyDescriptors();
            } catch (IntrospectionException e) {
                throw new DispatchException(e.getMessage(), e);
            }
            for (PropertyDescriptor p : propertyDescriptors)
                propertyMap.put(p.getName(), p);

            classMap.put(contextClass, propertyMap);
        }

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

}
