package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class PublicMembers
        extends AbstractReflectType {

    public PublicMembers(Class<?> clazz, int infoset, ClassDoc classDoc) {
        super(clazz, infoset, classDoc);

        if ((infoset & ITypeProvider.PROPERTIES) != 0)
            for (Field field : clazz.getFields()) {

                if (field.isSynthetic())
                    continue;

                FieldDoc fieldDoc = null;
                if (classDoc != null)
                    fieldDoc = classDoc.getFieldDoc(field.getName());

                ReflectProperty reflectProperty = new ReflectProperty(field, fieldDoc);
                propertyMap.addProperty(reflectProperty);
            }

        if ((infoset & ITypeProvider.METHODS) != 0)
            for (Method method : clazz.getMethods()) {

                if (method.isSynthetic())
                    continue;

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

                if (ctor.isSynthetic())
                    continue;

                MethodDoc ctorDoc = null;
                if (classDoc != null) {
                    MethodId ctorId = new MethodId(ctor);
                    ctorDoc = classDoc.getMethodDoc(ctorId);
                }

                ReflectConstructor reflectCtor = new ReflectConstructor(ctor, ctorDoc);
                constructorMap.addConstructor(reflectCtor);
            }
    }

}
