package net.bodz.bas.bean.api;

import java.lang.reflect.Method;

public interface IMethodDescriptor
        extends
            IFeatureDescriptor,
            IDeclaredMember {

    Method getMethod();

    IParameterDescriptor[] getParameterDescriptors();

}