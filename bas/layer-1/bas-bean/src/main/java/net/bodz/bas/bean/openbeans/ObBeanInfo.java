package net.bodz.bas.bean.openbeans;

import java.awt.Image;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IMethodDescriptor;

import com.googlecode.openbeans.BeanInfo;

public class ObBeanInfo
        implements
            IBeanInfo {

    BeanInfo bi;

    public ObBeanInfo(BeanInfo bi) {
        if (bi == null)
            throw new NullPointerException("bi");
        this.bi = bi;
    }

    @Override
    public ObBeanDescriptor getBeanDescriptor() {
        return ObBeanDescriptor.convert(bi.getBeanDescriptor());
    }

    @Override
    public ObEventSetDescriptor[] getEventSetDescriptors() {
        return ObEventSetDescriptor.convert(bi.getEventSetDescriptors());
    }

    @Override
    public int getDefaultEventIndex() {
        return bi.getDefaultEventIndex();
    }

    @Override
    public ObPropertyDescriptor[] getPropertyDescriptors() {
        return ObPropertyDescriptor.convert(bi.getPropertyDescriptors());
    }

    @Override
    public int getDefaultPropertyIndex() {
        return bi.getDefaultPropertyIndex();
    }

    @Override
    public IMethodDescriptor[] getMethodDescriptors() {
        return ObMethodDescriptor.convert(bi.getMethodDescriptors());
    }

    @Override
    public ObBeanInfo[] getAdditionalBeanInfo() {
        return ObBeanInfo.convert(bi.getAdditionalBeanInfo());
    }

    @Override
    public Image getIcon(int iconKind) {
        return bi.getIcon(iconKind);
    }

    public static ObBeanInfo convert(BeanInfo o) {
        if (o == null)
            return null;
        else
            return new ObBeanInfo(o);
    }

    public static ObBeanInfo[] convert(BeanInfo[] src) {
        if (src == null)
            return null;
        ObBeanInfo[] dst = new ObBeanInfo[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
