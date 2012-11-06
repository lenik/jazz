package net.bodz.bas.potato.model.invoke;

public interface IInvocation {

    Class<?> getReturnType();

    Object getReturnValue();

    void setReturnValue(Object returnValue);

    Class<?> getParameterType(int index);

    Object[] getParameters();

    void setParameters(Object[] parameters);

    Object getParameter(int index);

    void setParameter(int index, Object value);

    Object invoke()
            throws Exception;

}
