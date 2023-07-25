package net.bodz.bas.bean.openbeans;

import java.lang.reflect.Method;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IPropertyEditor;
import net.bodz.bas.bean.api.IntrospectionException;

import com.googlecode.openbeans.PropertyDescriptor;

public class ObPropertyDescriptor
        extends ObFeatureDescriptor
        implements
            IPropertyDescriptor {

    PropertyDescriptor pd;

    public ObPropertyDescriptor(PropertyDescriptor pd) {
        super(pd);
        this.pd = pd;
    }

    @Override
    public void setWriteMethod(Method setter)
            throws IntrospectionException {
        try {
            pd.setWriteMethod(setter);
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
        }
    }

    @Override
    public void setReadMethod(Method getter)
            throws IntrospectionException {
        try {
            pd.setReadMethod(getter);
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
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
        return ObPropertyEditor.convert(pd.createPropertyEditor(bean));
    }

    public static ObPropertyDescriptor convert(PropertyDescriptor o) {
        if (o == null)
            return null;
        else
            return new ObPropertyDescriptor(o);
    }

    public static ObPropertyDescriptor[] convert(PropertyDescriptor[] src) {
        if (src == null)
            return null;
        ObPropertyDescriptor[] dst = new ObPropertyDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i]);
        return dst;
    }

}
