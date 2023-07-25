package net.bodz.bas.bean.java;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IPropertyEditor;
import net.bodz.bas.bean.api.IntrospectionException;

public class JbPropertyDescriptor
        extends JbFeatureDescriptor
        implements
            IPropertyDescriptor {

    PropertyDescriptor pd;

    public JbPropertyDescriptor(PropertyDescriptor pd) {
        super(pd);
        this.pd = pd;
    }

    @Override
    public void setWriteMethod(Method setter)
            throws IntrospectionException {
        try {
            pd.setWriteMethod(setter);
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public void setReadMethod(Method getter)
            throws IntrospectionException {
        try {
            pd.setReadMethod(getter);
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public Method getWriteMethod() {
        return pd.getWriteMethod();
    }

    @Override
    public Method getReadMethod() {
        return pd.getReadMethod();
    }

    @Override
    public void setPropertyEditorClass(Class<?> propertyEditorClass) {
        pd.setPropertyEditorClass(propertyEditorClass);
    }

    @Override
    public Class<?> getPropertyType() {
        return pd.getPropertyType();
    }

    @Override
    public Class<?> getPropertyEditorClass() {
        return pd.getPropertyEditorClass();
    }

    @Override
    public void setConstrained(boolean constrained) {
        pd.setConstrained(constrained);
    }

    @Override
    public void setBound(boolean bound) {
        pd.setBound(bound);
    }

    @Override
    public boolean isConstrained() {
        return pd.isConstrained();
    }

    @Override
    public boolean isBound() {
        return pd.isBound();
    }

    @Override
    public IPropertyEditor createPropertyEditor(Object bean) {
        return JbPropertyEditor.convert(pd.createPropertyEditor(bean));
    }

    public static JbPropertyDescriptor convert(PropertyDescriptor o) {
        if (o == null)
            return null;
        else
            return new JbPropertyDescriptor(o);
    }

    public static JbPropertyDescriptor[] convert(PropertyDescriptor[] src) {
        if (src == null)
            return null;
        JbPropertyDescriptor[] dst = new JbPropertyDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
