package net.bodz.bas.bean.openbeans;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IPropertyEditor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.err.UnexpectedException;

import com.googlecode.openbeans.PropertyDescriptor;

public class ObPropertyDescriptor
        extends ObFeatureDescriptor
        implements
            IPropertyDescriptor {

    PropertyDescriptor pd;
    int position;

    public ObPropertyDescriptor(PropertyDescriptor pd, int position) {
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
    public Type getPropertyGenericType() {
        Type type;
        Method getter = pd.getReadMethod();
        if (getter != null)
            type = getter.getGenericReturnType();
        else {
            Method setter = pd.getWriteMethod();
            if (setter != null) {
                Type[] pv = setter.getGenericParameterTypes();
                type = pv[0];
            } else
                throw new UnexpectedException();
        }
        return type;
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
        } catch (com.googlecode.openbeans.IntrospectionException e) {
            throw ObIntrospectionException.convert(e);
        }
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
    public Method getWriteMethod() {
        return pd.getWriteMethod();
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
        return ObPropertyEditor.convert(pd.createPropertyEditor(bean));
    }

    public static ObPropertyDescriptor convert(PropertyDescriptor o, int position) {
        if (o == null)
            return null;
        else
            return new ObPropertyDescriptor(o, position);
    }

    public static ObPropertyDescriptor[] convert(PropertyDescriptor[] src, int startPosition) {
        if (src == null)
            return null;
        ObPropertyDescriptor[] dst = new ObPropertyDescriptor[src.length];
        for (int i = 0; i < src.length; i++)
            dst[i] = convert(src[i], startPosition + i);
        return dst;
    }

}
