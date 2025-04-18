package net.bodz.bas.bean.api;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class AbstractBeanInfo
        implements IBeanInfo {

    Map<String, IPropertyDescriptor> propertyMap;
    Map<String, Map<List<?>, IMethodDescriptor>> methodMap;
    Map<Class<?>, IEventSetDescriptor[]> eventSetMap;

    synchronized void buildPropertyMap() {
        if (propertyMap == null) {
            IPropertyDescriptor[] descriptors = getPropertyDescriptors();
            propertyMap = new LinkedHashMap<>(descriptors.length);
            for (IPropertyDescriptor descriptor : descriptors) {
                propertyMap.put(descriptor.getName(), descriptor);
            }
        }
    }

    synchronized void buildMethodMap() {
        if (methodMap == null) {
            IMethodDescriptor[] descriptors = getMethodDescriptors();
            methodMap = new LinkedHashMap<>(descriptors.length);
            for (IMethodDescriptor descriptor : descriptors) {
                Map<List<?>, IMethodDescriptor> overloadMap = methodMap.computeIfAbsent(descriptor.getName(), k -> new LinkedHashMap<>());
                List<Object> signature = new ArrayList<>(Arrays.asList(descriptor.getMethod().getParameterTypes()));
                overloadMap.put(signature, descriptor);
            }
        }
    }

    synchronized void buildEventSetMap() {
        if (eventSetMap == null) {
            IEventSetDescriptor[] descriptors = getEventSetDescriptors();

            Map<Class<?>, List<IEventSetDescriptor>> cache = new LinkedHashMap<>(descriptors.length);
            for (IEventSetDescriptor descriptor : descriptors) {
                Class<?> listenerType = descriptor.getListenerType();
                List<IEventSetDescriptor> selection = cache.computeIfAbsent(listenerType, k -> new ArrayList<>());
                selection.add(descriptor);
            }

            eventSetMap = new LinkedHashMap<>(cache.size());
            for (Class<?> k : cache.keySet()) {
                IEventSetDescriptor[] array = cache.get(k).toArray(new IEventSetDescriptor[0]);
                eventSetMap.put(k, array);
            }
        }
    }

    @Override
    public IPropertyDescriptor getPropertyDescriptor(String propertyName) {
        buildPropertyMap();
        return propertyMap.get(propertyName);
    }

    @Override
    public synchronized IMethodDescriptor getMethodDescriptors(String methodName, Class<?>... parameterTypes) {
        buildMethodMap();
        Map<List<?>, IMethodDescriptor> overloadMap = methodMap.get(methodName);
        if (overloadMap == null)
            return null;
        List<Object> signature = new ArrayList<>(Arrays.asList(parameterTypes));
        return overloadMap.get(signature);
    }

    @Override
    public IEventSetDescriptor[] getEventSetDescriptors(Class<?> listenerType) {
        buildEventSetMap();
        return eventSetMap.get(listenerType);
    }

}
