package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;

import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableType
        extends AbstractType {

    private Class<?> type;
    private MutablePropertyMap propertyMap;
    private MutableMethodMap methodMap;
    private MutableConstructorMap constructorMap;
    private MutableEventMap eventMap;

    public MutableType(Class<?> type, IElementDoc doc) {
        super(type, type.getName(), doc);
        this.type = type;
        propertyMap = new MutablePropertyMap(false);
        methodMap = new MutableMethodMap(false);
        constructorMap = new MutableConstructorMap();
        eventMap = new MutableEventMap(false);
    }

    public void setType(Class<?> type) {
        if (type == null)
            throw new NullPointerException("type");
        this.type = type;

        setDeclaringClass(type);
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

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
    /* _____________________________ */static section.iface __ANNOTATION__;

    @Override
    public Annotation[] getAnnotations() {
        return type.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return type.getDeclaredAnnotations();
    }

}
