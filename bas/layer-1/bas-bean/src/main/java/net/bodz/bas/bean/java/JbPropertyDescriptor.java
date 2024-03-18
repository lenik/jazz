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
    int position;

    public JbPropertyDescriptor(PropertyDescriptor pd, int position) {
        super(pd);
        this.pd = pd;
        this.position = position;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Class<?> getPropertyType() {
        return pd.getPropertyType();
    }

    @Override
    public Method getReadMethod() {
        return pd.getReadMethod();
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
    public void setWriteMethod(Method setter)
            throws IntrospectionException {
        try {
            pd.setWriteMethod(setter);
        } catch (java.beans.IntrospectionException e) {
            throw JbIntrospectionException.convert(e);
        }
    }

    @Override
    public boolean isConstrained() {
        return pd.isConstrained();
    }

    @Override
    public void setConstrained(boolean constrained) {
        pd.setConstrained(constrained);
    }

    @Override
    public boolean isBound() {
        return pd.isBound();
    }

    @Override
    public void setBound(boolean bound) {
        pd.setBound(bound);
    }

    @Override
    public Class<?> getPropertyEditorClass() {
        return pd.getPropertyEditorClass();
    }

    @Override
    public void setPropertyEditorClass(Class<?> propertyEditorClass) {
        pd.setPropertyEditorClass(propertyEditorClass);
    }

    @Override
    public IPropertyEditor createPropertyEditor(Object bean) {
        return JbPropertyEditor.convert(pd.createPropertyEditor(bean));
    }

    public static JbPropertyDescriptor convert(PropertyDescriptor o, int position) {
        if (o == null)
            return null;
        else
            return new JbPropertyDescriptor(o, position);
    }

    public static JbPropertyDescriptor[] convert(PropertyDescriptor[] src, int startPosition) {
        if (src == null)
            return null;
        JbPropertyDescriptor[] dst = new JbPropertyDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i], startPosition + i);
        return dst;
    }

}
