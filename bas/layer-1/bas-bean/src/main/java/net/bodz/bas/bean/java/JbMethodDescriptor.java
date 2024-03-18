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
    int position;

    public JbMethodDescriptor(MethodDescriptor md, int position) {
        super(md);
        this.md = md;
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
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

    public static JbMethodDescriptor convert(MethodDescriptor o, int position) {
        if (o == null)
            return null;
        else
            return new JbMethodDescriptor(o, position);
    }

    public static JbMethodDescriptor[] convert(MethodDescriptor[] src, int startPosition) {
        if (src == null)
            return null;
        JbMethodDescriptor[] dst = new JbMethodDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i], startPosition + i);
        return dst;
    }

}
