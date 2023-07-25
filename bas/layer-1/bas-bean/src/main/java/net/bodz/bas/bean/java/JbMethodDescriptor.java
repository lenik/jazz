package net.bodz.bas.bean.java;

import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IMethodDescriptor;

public class JbMethodDescriptor
        extends JbFeatureDescriptor
        implements
            IMethodDescriptor {

    MethodDescriptor md;

    public JbMethodDescriptor(MethodDescriptor md) {
        super(md);
        this.md = md;
    }

    @Override
    public Method getMethod() {
        return md.getMethod();
    }

    @Override
    public JbParameterDescriptor[] getParameterDescriptors() {
        ParameterDescriptor[] array = md.getParameterDescriptors();
        return JbParameterDescriptor.convert(array);
    }

    public static JbMethodDescriptor convert(MethodDescriptor o) {
        if (o == null)
            return null;
        else
            return new JbMethodDescriptor(o);
    }

    public static JbMethodDescriptor[] convert(MethodDescriptor[] src) {
        if (src == null)
            return null;
        JbMethodDescriptor[] dst = new JbMethodDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
