package net.bodz.bas.bean.api;

import java.awt.Image;

import net.bodz.bas.t.model.AbstractDecorator;

import com.googlecode.openbeans.BeanDescriptor;
import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.EventSetDescriptor;
import com.googlecode.openbeans.MethodDescriptor;
import com.googlecode.openbeans.PropertyDescriptor;

public class DecoratedBeanInfo
        extends AbstractDecorator<BeanInfo>
        implements
            BeanInfo {

    private static final long serialVersionUID = 1L;

    public DecoratedBeanInfo(BeanInfo _orig) {
        super(_orig);
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getWrapped().getPropertyDescriptors();
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return getWrapped().getMethodDescriptors();
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getWrapped().getEventSetDescriptors();
    }

    @Override
    public BeanInfo[] getAdditionalBeanInfo() {
        return getWrapped().getAdditionalBeanInfo();
    }

    @Override
    public BeanDescriptor getBeanDescriptor() {
        return getWrapped().getBeanDescriptor();
    }

    @Override
    public Image getIcon(int iconKind) {
        return getWrapped().getIcon(iconKind);
    }

    @Override
    public int getDefaultPropertyIndex() {
        return getWrapped().getDefaultPropertyIndex();
    }

    @Override
    public int getDefaultEventIndex() {
        return getWrapped().getDefaultEventIndex();
    }

}
