package net.bodz.bas.bean.api;

import java.lang.reflect.Method;

public interface IMethodDescriptor
        extends
            IFeatureDescriptor {

    Method getMethod();

    IParameterDescriptor[] getParameterDescriptors();

}