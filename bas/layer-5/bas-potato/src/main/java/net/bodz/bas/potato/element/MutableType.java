package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;

public class MutableType
        extends AbstractType {

    private Class<?> type;
    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private MutableConstructorMap constructorMap = new MutableConstructorMap();
    private MutableEventMap eventMap = new MutableEventMap();

    public MutableType(Class<?> type) {
        super(type, type.getName());
        this.type = type;
    }

    public void setType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;

        setDeclaringClass(type);
    }

    /** ⇱ Implementation Of {@link IType}. */
    ;

    @Override
    public Class<?> getType() {
        return type;
    }

    @Override
    public MutablePropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public MutableMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public MutableConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public MutableEventMap getEventMap() {
        return eventMap;
    }

    /** ⇱ Implementation Of {@link IAnnotated}. */
    ;

    @Override
    public Annotation[] getAnnotations() {
        return type.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return type.getDeclaredAnnotations();
    }

}
