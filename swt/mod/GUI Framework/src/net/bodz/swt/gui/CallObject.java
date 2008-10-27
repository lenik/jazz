package net.bodz.swt.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;

import net.bodz.bas.lang.err.ReflectException;
import net.bodz.bas.lang.ref.IPropertyChangeSupport;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.util.Types;

/**
 * Property names:
 * <ul>
 * <li>retval
 * <li>params
 * <li>param(index)
 * </ul>
 */
public class CallObject implements IPropertyChangeSupport {

    private final Object          object;
    private final Method          method;

    private Object                retval;
    private Class<?>[]            parameterTypes;
    private Object[]              parameters;

    private PropertyChangeSupport pcs;

    public CallObject(Object object, Method method) {
        this.object = object;
        this.method = method;
        parameterTypes = method.getParameterTypes();
        parameters = new Object[parameterTypes.length];
        pcs = new PropertyChangeSupport(this);
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

    public Object getRetval() {
        return retval;
    }

    public void setRetval(Object retval) {
        Object old = this.retval;
        this.retval = retval;
        pcs.firePropertyChange("retval", old, retval);
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        if (parameters == null)
            throw new NullPointerException();
        if (parameters.length != parameterTypes.length)
            throw new IllegalArgumentException(
                    "invalid number of parameters: expected "
                            + parameterTypes.length + ", but specified "
                            + parameters.length);
        Object[] old = this.parameters;
        this.parameters = parameters;
        pcs.firePropertyChange("params", old, parameters);
    }

    public int size() {
        return parameters.length;
    }

    public Object get(int parameterIndex) {
        return parameters[parameterIndex];
    }

    /**
     * @throw {@link ClassCastException}
     */
    public void set(int parameterIndex, Object value) {
        if (value != null) {
            Class<?> type = parameterTypes[parameterIndex];
            if (!Types.box(type).isInstance(value))
                throw new ClassCastException("parameter[" + parameterIndex
                        + "] is " + type + ", but set to " + value.getClass());
        }
        Object old = parameters[parameterIndex];
        parameters[parameterIndex] = value;
        pcs.fireIndexedPropertyChange("param", parameterIndex, old, value);
    }

    public Object invoke() throws ReflectException {
        Object retval = Reflects.invoke(object, method, parameters);
        setRetval(retval);
        return retval;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName,
            PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

}
