package net.bodz.bas.potato.invoke;

public interface IInvocation {

    Class<?> getReturnType();

    Object getReturnValue();

    void setReturnValue(Object returnValue);

    int getParameterCount();

    Class<?>[] getParameterTypes();

    Class<?> getParameterType(int index);

    Object[] getParameters();

    void setParameters(Object[] parameters);

    Object getParameter(int index);

    void setParameter(int index, Object value);

    Object invoke()
            throws Exception;

}
