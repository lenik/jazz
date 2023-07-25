package net.bodz.bas.bean.java;

import java.beans.ParameterDescriptor;

import net.bodz.bas.bean.api.IParameterDescriptor;

public class JbParameterDescriptor
        extends JbFeatureDescriptor
        implements
            IParameterDescriptor {

    public JbParameterDescriptor(ParameterDescriptor pd) {
        super(pd);
    }

    public static JbParameterDescriptor convert(ParameterDescriptor o) {
        if (o == null)
            return null;
        else
            return new JbParameterDescriptor(o);
    }

    public static JbParameterDescriptor[] convert(ParameterDescriptor[] src) {
        if (src == null)
            return null;
        JbParameterDescriptor[] dst = new JbParameterDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
