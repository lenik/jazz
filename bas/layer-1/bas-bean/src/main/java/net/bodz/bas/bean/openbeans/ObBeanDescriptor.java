package net.bodz.bas.bean.openbeans;

import net.bodz.bas.bean.api.IBeanDescriptor;

import com.googlecode.openbeans.BeanDescriptor;

public class ObBeanDescriptor
        extends ObFeatureDescriptor
        implements
            IBeanDescriptor {

    BeanDescriptor bd;

    public ObBeanDescriptor(BeanDescriptor bd) {
        super(bd);
        this.bd = bd;
    }

    @Override
    public Class<?> getBeanClass() {
        return bd.getBeanClass();
    }

    @Override
    public Class<?> getCustomizerClass() {
        return bd.getCustomizerClass();
    }

    public static ObBeanDescriptor convert(BeanDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObBeanDescriptor(o);
    }

    public static ObBeanDescriptor[] convert(BeanDescriptor[] src) {
        if (src == null)
            return null;
        ObBeanDescriptor[] dst = new ObBeanDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
