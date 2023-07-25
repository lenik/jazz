package net.bodz.bas.bean.java;

import java.beans.BeanDescriptor;

import net.bodz.bas.bean.api.IBeanDescriptor;

public class JbBeanDescriptor
        extends JbFeatureDescriptor
        implements
            IBeanDescriptor {

    BeanDescriptor bd;

    public JbBeanDescriptor(BeanDescriptor bd) {
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

    public static JbBeanDescriptor convert(BeanDescriptor o) {
        if (o == null)
            return null;
        else
            return new JbBeanDescriptor(o);
    }

    public static JbBeanDescriptor[] convert(BeanDescriptor[] src) {
        if (src == null)
            return null;
        JbBeanDescriptor[] dst = new JbBeanDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
