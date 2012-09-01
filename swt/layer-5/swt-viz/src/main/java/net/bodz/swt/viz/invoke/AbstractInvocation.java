package net.bodz.swt.viz.invoke;

import java.io.Serializable;

import net.bodz.bas.i18n.nls.II18nCapable;
import net.bodz.bas.util.primitive.Primitives;

/**
 * Property names:
 * <ul>
 * <li>retval
 * <li>params
 * <li>param(index)
 * </ul>
 */
public abstract class AbstractInvocation
        implements Serializable, II18nCapable // , IPropertyChangeSource
{

    private static final long serialVersionUID = 1L;

    private int parameterCount;
    // private final Class<?>[] parameterTypes;
    private Object[] parameters;
    private Object returnValue;

    // private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public AbstractInvocation(int parameterCount) {
        this.parameterCount = parameterCount;
        this.parameters = new Object[parameterCount];
    }

    protected abstract Class<?> getReturnType();

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        // Object old = this.returnValue;
        this.returnValue = returnValue;
        // pcs.firePropertyChange("returnValue", old, returnValue);
    }

    protected abstract Class<?> getParameterType(int index);

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        if (parameters == null)
            throw new NullPointerException("parameters");
        if (parameters.length != parameterCount)
            throw new IllegalArgumentException("invalid parameter array length, expected: " + parameterCount);

        // Object[] old = this.parameters;
        this.parameters = parameters;
        // pcs.firePropertyChange("parameters", old, parameters);
    }

    public Object getParameter(int index) {
        return parameters[index];
    }

    public void setParameter(int index, Object value) {
        if (value != null) {
            Class<?> type = getParameterType(index);
            if (!Primitives.box(type).isInstance(value)) {
                String fmt = tr._("Parameter[%d] is %s, but set to %s");
                throw new ClassCastException(String.format(fmt, index, type, value.getClass()));
            }
        }
        // Object old = parameters[index];
        parameters[index] = value;
        // pcs.fireIndexedPropertyChange("parameter[" + index + "], old, value);
    }

    public abstract Object invoke()
            throws Exception;

}
