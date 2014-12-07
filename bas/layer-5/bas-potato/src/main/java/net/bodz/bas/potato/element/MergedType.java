package net.bodz.bas.potato.element;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import net.bodz.mda.xjdoc.model.IElementDoc;

public class MergedType
        extends AbstractType {

    private final Class<?> clazz;
    List<IType> sourceTypes;

    private IPropertyMap propertyMap;
    private IMethodMap methodMap;
    private IConstructorMap constructorMap;
    private IEventMap eventMap;

    public MergedType(Class<?> clazz, IType[] types, IElementDoc doc) {
        this(clazz, Arrays.asList(types), doc);
    }

    public MergedType(Class<?> clazz, List<IType> types, IElementDoc doc) {
        super(clazz, clazz.getName(), doc);
        this.clazz = clazz;
        this.sourceTypes = types;

        boolean sort = false;

        MutablePropertyMap propertyMap = new MutablePropertyMap(sort);
        MutableMethodMap methodMap = new MutableMethodMap(sort);
        MutableConstructorMap constructorMap = new MutableConstructorMap();
        MutableEventMap eventMap = new MutableEventMap(sort);

        for (IType type : types) {
            for (IProperty property : type.getProperties())
                if (!propertyMap.containsProperty(property.getName()))
                    propertyMap.addProperty(property);

            for (IMethod method : type.getMethods())
                if (!methodMap.containsMethod(method.getSignature()))
                    methodMap.addMethod(method);

            for (IConstructor ctor : type.getConstructors())
                if (!constructorMap.containsConstructor(ctor.getSignature()))
                    constructorMap.addConstructor(ctor);

            for (IEvent event : type.getEvents())
                if (!eventMap.containsEvent(event.getName()))
                    eventMap.addEvent(event);
        }

        this.propertyMap = propertyMap;
        this.methodMap = methodMap;
        this.constructorMap = constructorMap;
        this.eventMap = eventMap;
    }

    /** ⇱ Implementation Of {@link IType}. */
    /* _____________________________ */static section.iface __TYPE__;

    @Override
    public Class<?> getType() {
        return clazz;
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
