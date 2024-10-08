package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class LinkedType
        extends AbstractType {

    private final Class<?> clazz;

    private LinkedPropertyMap propertyMap;
    private LinkedMethodMap methodMap;
    private LinkedConstructorMap constructorMap;
    private LinkedEventMap eventMap;

    public LinkedType(ITypeProvider provider, IType declaringType, Class<?> clazz, IType[] types, IElementDoc doc) {
        this(provider, declaringType, clazz, Arrays.asList(types), doc);
    }

    public LinkedType(ITypeProvider provider, IType declaringType, Class<?> clazz, List<IType> types, IElementDoc doc) {
        super(provider, declaringType, clazz.getName(), doc);

        this.clazz = clazz;

        int n = types.size();
        List<IPropertyMap> propertyMaps = new ArrayList<IPropertyMap>(n);
        List<IMethodMap> methodMaps = new ArrayList<IMethodMap>(n);
        List<IConstructorMap> constructorMaps = new ArrayList<IConstructorMap>(n);
        List<IEventMap> eventMaps = new ArrayList<IEventMap>(n);

        for (IType type : types) {
            IPropertyMap propertyMap = type.getPropertyMap();
            IMethodMap methodMap = type.getMethodMap();
            IConstructorMap constructorMap = type.getConstructorMap();
            IEventMap eventMap = type.getEventMap();

            propertyMaps.add(propertyMap);
            methodMaps.add(methodMap);
            constructorMaps.add(constructorMap);
            eventMaps.add(eventMap);
        }

        propertyMap = new LinkedPropertyMap(propertyMaps);
        methodMap = new LinkedMethodMap(methodMaps);
        constructorMap = new LinkedConstructorMap(constructorMaps);
        eventMap = new LinkedEventMap(eventMaps);
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getJavaClass() {
        return clazz;
    }

    @Override
    protected IType loadSuperType() {
        return null;
    }

    @Override
    public IPropertyMap getPropertyMap() {
        return propertyMap;
    }

    @Override
    public IMethodMap getMethodMap() {
        return methodMap;
    }

    @Override
    public IConstructorMap getConstructorMap() {
        return constructorMap;
    }

    @Override
    public IEventMap getEventMap() {
        return eventMap;
    }

    /** ⇱ Implementaton Of {@link IAnnotated}. */
    /* _____________________________ */static section.iface __ANNOTATION__;

    // TODO Should merge annotations from all types.

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return clazz.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return clazz.getDeclaredAnnotations();
    }

}
