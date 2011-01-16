package net.bodz.bas.potato.provider.bean;

import java.beans.BeanInfo;
import java.beans.MethodDescriptor;

import net.bodz.bas.potato.traits.AbstractMethodMap;
import net.bodz.bas.potato.traits.MethodKey;

public class BeanMethodMap
        extends AbstractMethodMap {

    private static final long serialVersionUID = 1L;

    public BeanMethodMap(BeanInfo beanInfo) {
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();

        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            String methodName = methodDescriptor.getName();
            Class<?>[] parameterTypes = methodDescriptor.getMethod().getParameterTypes();
            MethodKey methodKey = new MethodKey(methodName, parameterTypes);

            BeanMethod beanMethod = new BeanMethod(methodDescriptor);

            put(methodKey, beanMethod);
        }
    }

}
