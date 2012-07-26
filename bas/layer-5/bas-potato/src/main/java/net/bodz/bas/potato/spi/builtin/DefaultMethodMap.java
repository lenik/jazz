package net.bodz.bas.potato.spi.builtin;

import java.beans.BeanInfo;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.c.reflect.MethodSignatureComparator;
import net.bodz.bas.potato.spi.bean.BeanMethod;
import net.bodz.bas.potato.spi.reflect.ReflectMethod;
import net.bodz.bas.potato.traits.IMethod;
import net.bodz.bas.potato.traits.IMethodMap;

public class DefaultMethodMap
        implements IMethodMap {

    Map<MethodSignature, IMethod> map;

    public DefaultMethodMap() {
        map = new TreeMap<MethodSignature, IMethod>(MethodSignatureComparator.getInstance());
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<IMethod> getMethods() {
        return map.values();
    }

    @Override
    public IMethod getMethod(MethodSignature signature) {
        return map.get(signature);
    }

    public DefaultMethodMap addClassMethods(Class<?> clazz) {
        addMethods(clazz.getMethods());
        return this;
    }

    public DefaultMethodMap addBeanMethods(BeanInfo beanInfo) {
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();

        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            String methodName = methodDescriptor.getName();
            Class<?>[] parameterTypes = methodDescriptor.getMethod().getParameterTypes();
            MethodSignature signature = new MethodSignature(methodName, parameterTypes);

            BeanMethod beanMethod = new BeanMethod(methodDescriptor);

            map.put(signature, beanMethod);
        }
        return this;
    }

    public DefaultMethodMap addMethods(Method... methods) {
        for (Method method : methods) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            MethodSignature signature = new MethodSignature(name, parameterTypes);
            ReflectMethod reflectMethod = new ReflectMethod(method);
            map.put(signature, reflectMethod);
        }
        return this;
    }

}
