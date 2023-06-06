package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.repr.form.SortOrder;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class MutableType
        extends AbstractType {

    private Class<?> javaClass;
    private MutablePropertyMap propertyMap;
    private MutableMethodMap methodMap;
    private MutableConstructorMap constructorMap;
    private MutableEventMap eventMap;

    public MutableType(ITypeProvider provider, IType declaringType, Class<?> javaClass, IElementDoc doc) {
        super(provider, declaringType, javaClass.getName(), doc);
        this.javaClass = javaClass;
        propertyMap = new MutablePropertyMap(SortOrder.KEEP);
        methodMap = new MutableMethodMap(SortOrder.KEEP);
        constructorMap = new MutableConstructorMap();
        eventMap = new MutableEventMap(SortOrder.KEEP);
    }

    public void setJavaClass(Class<?> javaClass) {
        if (javaClass == null)
            throw new NullPointerException("javaClass");
        this.javaClass = javaClass;
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getJavaClass() {
        return javaClass;
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
        return javaClass.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return javaClass.getDeclaredAnnotations();
    }

}
