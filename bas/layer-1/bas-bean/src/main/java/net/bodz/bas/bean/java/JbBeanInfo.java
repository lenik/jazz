package net.bodz.bas.bean.java;

import java.awt.Image;
import java.beans.BeanInfo;
import java.beans.MethodDescriptor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IMethodDescriptor;
import net.bodz.bas.bean.api.IPropertyDescriptor;

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
    public IPropertyDescriptor[] getPropertyDescriptors() {
        return JbPropertyDescriptor.convert(bi.getPropertyDescriptors(), 0);
    }

    @Override
    public int getDefaultPropertyIndex() {
        return bi.getDefaultPropertyIndex();
    }

    @Override
    public IMethodDescriptor[] getMethodDescriptors() {
        Set<Method> beanMethods = new HashSet<>();
        for (MethodDescriptor md : bi.getMethodDescriptors())
            beanMethods.add(md.getMethod());

        Class<?> beanClass = bi.getBeanDescriptor().getBeanClass();
        for (Method method : beanClass.getMethods()) {
            if (! beanMethods.contains(method)) {
//                System.out.println("missing: " + method);
            }
        }

        return JbMethodDescriptor.convert(bi.getMethodDescriptors(), 0);
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
