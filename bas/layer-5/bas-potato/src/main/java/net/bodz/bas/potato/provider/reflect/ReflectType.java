package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class ReflectType
        extends AbstractReflectType {

    public ReflectType(Class<?> clazz, int infoset, ClassDoc classDoc) {
        super(clazz, infoset, classDoc);

        if ((infoset & ITypeProvider.PROPERTIES) != 0)
            for (Field field : clazz.getFields()) {

                if (field.isSynthetic())
                    continue;

                FieldDoc fieldDoc = null;
                if (classDoc != null)
                    fieldDoc = classDoc.getFieldDoc(field.getName());
                if (fieldDoc == null)
                    fieldDoc = new FieldDoc(classDoc, field.getName());

                ReflectProperty reflectProperty = new ReflectProperty(field, fieldDoc);
                propertyMap.addProperty(reflectProperty);
            }

        if ((infoset & ITypeProvider.METHODS) != 0)
            for (Method method : clazz.getMethods()) {

                if (method.isSynthetic())
                    continue;

                MethodDoc methodDoc = null;
                MethodId methodId = new MethodId(method);
                if (classDoc != null)
                    methodDoc = classDoc.getMethodDoc(methodId);
                if (methodDoc == null)
                    methodDoc = new MethodDoc(classDoc, methodId);

                ReflectMethod reflectMethod = new ReflectMethod(method, methodDoc);
                methodMap.addMethod(reflectMethod);
            }

        if ((infoset & ITypeProvider.CONSTRUCTORS) != 0)
            for (Constructor<?> ctor : clazz.getConstructors()) {

                if (ctor.isSynthetic())
                    continue;

                MethodDoc ctorDoc = null;
                MethodId ctorId = new MethodId(ctor);
                if (classDoc != null)
                    ctorDoc = classDoc.getMethodDoc(ctorId);
                if (ctorDoc == null)
                    ctorDoc = new MethodDoc(classDoc, ctorId);

                ReflectConstructor reflectCtor = new ReflectConstructor(ctor, ctorDoc);
                constructorMap.addConstructor(reflectCtor);
            }
    }

}
