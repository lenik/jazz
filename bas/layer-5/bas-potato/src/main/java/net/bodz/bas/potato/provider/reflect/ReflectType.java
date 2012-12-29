package net.bodz.bas.potato.provider.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.bas.potato.element.*;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class ReflectType
        extends AbstractType {

    private final Class<?> clazz;

    private MutablePropertyMap propertyMap = new MutablePropertyMap();
    private MutableMethodMap methodMap = new MutableMethodMap();
    private MutableConstructorMap constructorMap = new MutableConstructorMap();
    private IEventMap eventMap = NullEventMap.getInstance();

    private final int verboseLevel;

    public ReflectType(Class<?> clazz, int infoset, ClassDoc classDoc) {
        super(clazz.getDeclaringClass(), clazz.getName());

        this.clazz = clazz;

        if ((infoset & ITypeProvider.PROPERTIES) != 0)
            for (Field field : clazz.getFields()) {

                FieldDoc fieldDoc = null;
                if (classDoc != null)
                    fieldDoc = classDoc.getFieldDoc(field.getName());

                ReflectProperty reflectProperty = new ReflectProperty(field, fieldDoc);
                propertyMap.addProperty(reflectProperty);
            }

        if ((infoset & ITypeProvider.METHODS) != 0)
            for (Method method : clazz.getMethods()) {

                MethodDoc methodDoc = null;
                if (classDoc != null) {
                    MethodId methodId = new MethodId(method);
                    methodDoc = classDoc.getMethodDoc(methodId);
                }

                ReflectMethod reflectMethod = new ReflectMethod(method, methodDoc);
                methodMap.addMethod(reflectMethod);
            }

        if ((infoset & ITypeProvider.CONSTRUCTORS) != 0)
            for (Constructor<?> ctor : clazz.getConstructors()) {

                MethodDoc ctorDoc = null;
                if (classDoc != null) {
                    MethodId ctorId = new MethodId(ctor);
                    ctorDoc = classDoc.getMethodDoc(ctorId);
                }

                ReflectConstructor reflectCtor = new ReflectConstructor(ctor, ctorDoc);
                constructorMap.addConstructor(reflectCtor);
            }

        int _modifiers = clazz.getModifiers();
        this.verboseLevel = ReflectModifiers.toVerboseLevel(_modifiers);

        setXjdoc(classDoc);
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

    // -o AnnotatedElement

    @Override
    public Annotation[] getAnnotations() {
        return clazz.getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return clazz.getDeclaredAnnotations();
    }

    @Override
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        return clazz.getAnnotation(annotationClass);
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    // -o IElement

    @Override
    public int getVerboseLevel() {
        return verboseLevel;
    }

}
