package net.bodz.mda.xjdoc;

import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;
import net.bodz.mda.xjdoc.util.MethodId;

public abstract class AbstractXjdocProvider
        implements IXjdocProvider {

    private ITagLibrary tagLibrary;

    public AbstractXjdocProvider() {
        tagLibrary = Xjdocs.getDefaultTagLibrary();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public ITagLibrary getTagLibrary() {
        return tagLibrary;
    }

    @Override
    public void setTagLibrary(ITagLibrary tagLibrary) {
        this.tagLibrary = tagLibrary;
    }

    @Override
    public ClassDoc getClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        return loadClassDoc(clazz);
    }

    @Override
    public ClassDoc getOrCreateClassDoc(Class<?> clazz)
            throws XjdocLoaderException {
        ClassDoc classDoc = getClassDoc(clazz);

        if (classDoc == null) {
            // logger.warn("No class doc for " + clazz);
            classDoc = ClassDoc.n_a(clazz.getName());
        }

        return classDoc;
    }

    @Override
    public MethodDoc getMethodDoc(Class<?> clazz, ClassDoc classDoc, Method... methods) {
        return getMethodDoc(clazz, classDoc, Arrays.asList(methods));
    }

    @Override
    public MethodDoc getMethodDoc(Class<?> clazz, ClassDoc classDoc, Iterable<Method> methods) {
        MethodDoc doc = null;
        for (Method method : methods) {
            if (method == null)
                continue;

            Class<?> declClass = method.getDeclaringClass();
            if (declClass != clazz || classDoc == null)
                classDoc = getClassDoc(declClass);

            if (classDoc != null) {
                MethodId methodId = new MethodId(method);
                doc = classDoc.getMethodDoc(methodId);
            }
            break;
        }
        return doc;
    }

}
