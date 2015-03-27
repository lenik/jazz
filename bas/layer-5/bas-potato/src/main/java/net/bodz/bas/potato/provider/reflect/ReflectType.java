package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class ReflectType
        extends AbstractReflectType {

    public ReflectType(Class<?> clazz, int infoset, ClassDoc classDoc) {
        super(clazz, infoset, classDoc);

        boolean docs = (infoset & ITypeProvider.DOCS) != 0;

        if ((infoset & ITypeProvider.PROPERTIES) != 0)
            for (Field field : clazz.getFields()) {

                if (field.isSynthetic())
                    continue;

                // TODO whether to incldue static fields?
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers))
                    continue;

                FieldDoc fieldDoc = null;
                if (docs) {
                    if (classDoc != null)
                        fieldDoc = classDoc.getFieldDoc(field.getName());
                    if (fieldDoc == null)
                        fieldDoc = FieldDoc.n_a(classDoc, field.getName());
                }

                ReflectProperty reflectProperty = new ReflectProperty(field, fieldDoc);
                propertyMap.addProperty(reflectProperty);
            }

        if ((infoset & ITypeProvider.METHODS) != 0)
            for (Method method : clazz.getMethods()) {

                if (method.isSynthetic())
                    continue;

                MethodDoc methodDoc = null;
                if (docs) {
                    MethodId methodId = new MethodId(method);
                    if (classDoc != null)
                        methodDoc = classDoc.getMethodDoc(methodId);
                    if (methodDoc == null)
                        methodDoc = MethodDoc.n_a(classDoc, methodId);
                }

                ReflectMethod reflectMethod = new ReflectMethod(method, methodDoc);
                methodMap.addMethod(reflectMethod);
            }

        if ((infoset & ITypeProvider.CONSTRUCTORS) != 0)
            for (Constructor<?> ctor : clazz.getConstructors()) {

                if (ctor.isSynthetic())
                    continue;

                MethodDoc ctorDoc = null;
                if (docs) {
                    MethodId ctorId = new MethodId(ctor);
                    if (classDoc != null)
                        ctorDoc = classDoc.getMethodDoc(ctorId);
                    if (ctorDoc == null)
                        ctorDoc = MethodDoc.n_a(classDoc, ctorId);
                }

                ReflectConstructor reflectCtor = new ReflectConstructor(ctor, ctorDoc);
                constructorMap.addConstructor(reflectCtor);
            }
    }

}
