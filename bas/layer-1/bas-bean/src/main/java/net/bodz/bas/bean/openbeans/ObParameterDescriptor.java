package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IParameterDescriptor;

import com.googlecode.openbeans.ParameterDescriptor;

public class ObParameterDescriptor
        extends ObFeatureDescriptor
        implements
            IParameterDescriptor {

    public ObParameterDescriptor(ParameterDescriptor pd) {
        super(pd);
    }

    public static ObParameterDescriptor convert(ParameterDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObParameterDescriptor(o);
    }

    public static ObParameterDescriptor[] convert(ParameterDescriptor[] src) {
        if (src == null)
            return null;
        ObParameterDescriptor[] dst = new ObParameterDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
