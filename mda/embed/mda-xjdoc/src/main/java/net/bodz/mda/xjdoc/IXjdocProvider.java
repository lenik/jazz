package net.bodz.mda.xjdoc;

import java.lang.reflect.Method;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.model.MethodDoc;
import net.bodz.mda.xjdoc.taglib.ITagLibrary;

/**
 * Service provider interface.
 * 
 * @see Xjdocs
 */
@IndexedType
public interface IXjdocProvider
        extends IPriority {

    ITagLibrary getTagLibrary();

    void setTagLibrary(ITagLibrary tagLibrary);

    ClassDoc getClassDoc(Class<?> clazz)
            throws XjdocLoaderException;

    ClassDoc getOrCreateClassDoc(Class<?> clazz)
            throws XjdocLoaderException;

    ClassDoc loadClassDoc(Class<?> clazz)
            throws XjdocLoaderException;

    MethodDoc getMethodDoc(Class<?> clazz, ClassDoc classDoc, Method... methods)
            throws XjdocLoaderException;

    MethodDoc getMethodDoc(Class<?> clazz, ClassDoc classDoc, Iterable<Method> methods)
            throws XjdocLoaderException;

}
