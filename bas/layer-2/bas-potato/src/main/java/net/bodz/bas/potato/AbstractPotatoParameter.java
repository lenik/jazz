package net.bodz.bas.potato;

public abstract class AbstractPotatoParameter
        extends AbstractPotatoElement
        implements IPotatoParameter {

    protected final Class<?> parameterType;

    public AbstractPotatoParameter(Class<?> parameterType, String parameterName) {
        super(parameterName);
        if (parameterType == null)
            throw new NullPointerException("parameterType");
        this.parameterType = parameterType;
    }

    @Override
    public Class<?> getType() {
        return parameterType;
    }

}