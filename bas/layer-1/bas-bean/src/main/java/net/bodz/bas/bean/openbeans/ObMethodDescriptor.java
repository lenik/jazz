package net.bodz.bas.bean.openbeans;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IMethodDescriptor;

import com.googlecode.openbeans.MethodDescriptor;
import com.googlecode.openbeans.ParameterDescriptor;

public class ObMethodDescriptor
        extends ObFeatureDescriptor
        implements
            IMethodDescriptor {

    MethodDescriptor md;

    public ObMethodDescriptor(MethodDescriptor md) {
        super(md);
        this.md = md;
    }

    @Override
    public Method getMethod() {
        return md.getMethod();
    }

    @Override
    public ObParameterDescriptor[] getParameterDescriptors() {
        ParameterDescriptor[] array = md.getParameterDescriptors();
        return ObParameterDescriptor.convert(array);
    }

    public static ObMethodDescriptor convert(MethodDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObMethodDescriptor(o);
    }

    public static ObMethodDescriptor[] convert(MethodDescriptor[] src) {
        if (src == null)
            return null;
        ObMethodDescriptor[] dst = new ObMethodDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
