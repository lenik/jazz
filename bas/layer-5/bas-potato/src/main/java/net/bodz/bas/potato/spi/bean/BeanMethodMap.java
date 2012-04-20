package net.bodz.bas.potato.spi.bean;

import java.beans.BeanInfo;
import java.beans.MethodDescriptor;

import net.bodz.bas.c.reflect.MethodSignature;
import net.bodz.bas.potato.traits.AbstractMethodMap;

public class BeanMethodMap
        extends AbstractMethodMap {

    private static final long serialVersionUID = 1L;

    public BeanMethodMap(BeanInfo beanInfo) {
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();

        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            String methodName = methodDescriptor.getName();
            Class<?>[] parameterTypes = methodDescriptor.getMethod().getParameterTypes();
            MethodSignature signature = new MethodSignature(methodName, parameterTypes);

            BeanMethod beanMethod = new BeanMethod(methodDescriptor);

            put(signature, beanMethod);
        }
    }

}
