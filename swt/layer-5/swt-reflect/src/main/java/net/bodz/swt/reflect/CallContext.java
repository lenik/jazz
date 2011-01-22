package net.bodz.swt.reflect;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import net.bodz.bas.lang.ref.IPropertyChangeSupport;
import net.bodz.bas.types.util.Types;
import net.bodz.swt.nls.GUINLS;

/**
 * Property names:
 * <ul>
 * <li>retval
 * <li>params
 * <li>param(index)
 * </ul>
 */
public class CallContext implements IPropertyChangeSupport {

    private Object                retval;
    private Class<?>[]            parameterTypes;
    protected Object[]            parameters;

    private PropertyChangeSupport pcs;

    public CallContext(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        parameters = new Object[parameterTypes.length];
        pcs = new PropertyChangeSupport(this);
    }

    public Object getRetval() {
        return retval;
    }

    public void setRetval(Object retval) {
        Object old = this.retval;
        this.retval = retval;
        pcs.firePropertyChange("retval", old, retval); //$NON-NLS-1$
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        if (parameters == null)
            throw new NullPointerException();
        if (parameters.length != parameterTypes.length) {
            String fmt = GUINLS.getString("CallContext.errParamNum_dd"); //$NON-NLS-1$
            throw new IllegalArgumentException(String.format(fmt, parameterTypes.length,
                    parameters.length));
        }

        Object[] old = this.parameters;
        this.parameters = parameters;
        pcs.firePropertyChange("params", old, parameters); //$NON-NLS-1$
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
            if (!Types.box(type).isInstance(value)) {
                String fmt = GUINLS.getString("CallContext.errParamType_dss"); //$NON-NLS-1$
                throw new ClassCastException(String.format(fmt, parameterIndex, //$NON-NLS-1$
                        type, value.getClass()));
            }
        }
        Object old = parameters[parameterIndex];
        parameters[parameterIndex] = value;
        pcs.fireIndexedPropertyChange("param", parameterIndex, old, value); //$NON-NLS-1$
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    @Override
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(propertyName, listener);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }

    @Override
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(propertyName, listener);
    }

}
