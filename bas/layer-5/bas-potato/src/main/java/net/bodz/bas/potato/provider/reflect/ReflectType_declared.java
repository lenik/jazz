package net.bodz.bas.potato.provider.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.potato.ITypeProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.FieldDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public class ReflectType_declared
        extends AbstractReflectType {

    /** @see Modifier#PUBLIC */
    public static final int I_Public = 0x0001;

    /** @see Modifier#PRIVATE */
    public static final int I_Private = 0x0002;

    /** @see Modifier#PROTECTED */
    public static final int I_Protected = 0x0004;

    /** @see Modifier#STATIC */
    public static final int I_Static = 0x0008;

    public static final int I_PackagePrivate = 0x0010;

    /**
     * @param flatten
     *            Include members declared in superclasses.
     */
    public ReflectType_declared(ITypeProvider provider, Class<?> clazz, int infoset, boolean flatten,
            ClassDoc classDoc) {
        super(provider, clazz, infoset, classDoc);

        boolean docs = (infoset & ITypeProvider.I_Docs) != 0;

        int visibilityMask = Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;
        Set<Integer> includeVisibilities = new HashSet<Integer>();
        if ((infoset & I_PackagePrivate) != 0)
            includeVisibilities.add(0);
        if ((infoset & I_Private) != 0)
            includeVisibilities.add(Modifier.PRIVATE);
        if ((infoset & I_Protected) != 0)
            includeVisibilities.add(Modifier.PROTECTED);

        boolean includeStatic = (infoset & I_Static) != 0;

        while (true) {
            if ((infoset & ITypeProvider.I_Properties) != 0)
                for (Field field : clazz.getDeclaredFields()) {

                    if (field.isSynthetic())
                        continue;
                    if (Modifier.isStatic(field.getModifiers()) && !includeStatic)
                        continue;
                    if (!includeVisibilities.contains(field.getModifiers() & visibilityMask))
                        continue;

                    field.setAccessible(true);

                    FieldDoc fieldDoc = null;
                    if (docs) {
                        if (classDoc != null)
                            fieldDoc = classDoc.getFieldDoc(field.getName());
                        if (fieldDoc == null)
                            fieldDoc = FieldDoc.n_a(classDoc, field.getName());
                    }

                    FieldProperty reflectProperty = new FieldProperty(this, field, fieldDoc);
                    propertyMap.addProperty(reflectProperty);
                }

            if ((infoset & ITypeProvider.I_Methods) != 0)
                for (Method method : clazz.getDeclaredMethods()) {

                    if (method.isSynthetic())
                        continue;
                    if (Modifier.isStatic(method.getModifiers()) && !includeStatic)
                        continue;
                    if (!includeVisibilities.contains(method.getModifiers() & visibilityMask))
                        continue;

                    method.setAccessible(true);

                    MethodDoc methodDoc = null;
                    if (docs) {
                        MethodId methodId = new MethodId(method);
                        if (classDoc != null)
                            methodDoc = classDoc.getMethodDoc(methodId);
                        if (methodDoc == null)
                            methodDoc = MethodDoc.n_a(classDoc, methodId);
                    }

                    ReflectMethod reflectMethod = new ReflectMethod(this, method, methodDoc);
                    methodMap.addMethod(reflectMethod);
                }

            if ((infoset & ITypeProvider.I_Constructors) != 0)
                for (Constructor<?> ctor : clazz.getConstructors()) {

                    if (ctor.isSynthetic())
                        continue;
                    if (Modifier.isStatic(ctor.getModifiers()) && !includeStatic)
                        continue;
                    if (!includeVisibilities.contains(ctor.getModifiers() & visibilityMask))
                        continue;

                    ctor.setAccessible(true);

                    MethodDoc ctorDoc = null;
                    if (docs) {
                        MethodId ctorId = new MethodId(ctor);
                        if (classDoc != null)
                            ctorDoc = classDoc.getMethodDoc(ctorId);
                        if (ctorDoc == null)
                            ctorDoc = MethodDoc.n_a(classDoc, ctorId);
                    }

                    ReflectConstructor reflectCtor = new ReflectConstructor(this, ctor, ctorDoc);
                    constructorMap.addConstructor(reflectCtor);
                }

            if (!flatten)
                break;

            clazz = clazz.getSuperclass();
            if (clazz == null)
                break;

            classDoc = Xjdocs.getDefaultProvider().getClassDoc(clazz);
        } // while (clazz != null)
    }

}
