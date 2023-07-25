package net.bodz.bas.bean.java;

import java.awt.Image;
import java.beans.BeanInfo;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IMethodDescriptor;

public class JbBeanInfo
        implements
            IBeanInfo {

    BeanInfo bi;

    public JbBeanInfo(BeanInfo bi) {
        if (bi == null)
            throw new NullPointerException("bi");
        this.bi = bi;
    }

    @Override
    public JbBeanDescriptor getBeanDescriptor() {
        return JbBeanDescriptor.convert(bi.getBeanDescriptor());
    }

    @Override
    public JbEventSetDescriptor[] getEventSetDescriptors() {
        return JbEventSetDescriptor.convert(bi.getEventSetDescriptors());
    }

    @Override
    public int getDefaultEventIndex() {
        return bi.getDefaultEventIndex();
    }

    @Override
    public JbPropertyDescriptor[] getPropertyDescriptors() {
        return JbPropertyDescriptor.convert(bi.getPropertyDescriptors());
    }

    @Override
    public int getDefaultPropertyIndex() {
        return bi.getDefaultPropertyIndex();
    }

    @Override
    public IMethodDescriptor[] getMethodDescriptors() {
        return JbMethodDescriptor.convert(bi.getMethodDescriptors());
    }

    @Override
    public JbBeanInfo[] getAdditionalBeanInfo() {
        return JbBeanInfo.convert(bi.getAdditionalBeanInfo());
    }

    @Override
    public Image getIcon(int iconKind) {
        return bi.getIcon(iconKind);
    }

    public static JbBeanInfo convert(BeanInfo o) {
        if (o == null)
            return null;
        else
            return new JbBeanInfo(o);
    }

    public static JbBeanInfo[] convert(BeanInfo[] src) {
        if (src == null)
            return null;
        JbBeanInfo[] dst = new JbBeanInfo[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
