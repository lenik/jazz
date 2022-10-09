package net.bodz.lily.entity.type;

import net.bodz.mda.xjdoc.IXjdocProvider;
import net.bodz.mda.xjdoc.Xjdocs;
import net.bodz.mda.xjdoc.model.ClassDoc;
import net.bodz.mda.xjdoc.util.MethodId;

public abstract class AbstractSourceDeclaredMethodOrder<T>
        extends AbstractMethodOrder<T> {

    static IXjdocProvider provider = Xjdocs.getDefaultProvider();

    public AbstractSourceDeclaredMethodOrder(Class<?> clazz) {
        super(clazz);
    }

    @Override
    protected void findMethods(Class<?> clazz) {
        ClassDoc classDoc = provider.getClassDoc(clazz);
        if (classDoc == null)
            return;

        for (MethodId m : classDoc.getMethodDocs().keySet()) {
            String methodName = m.getMethodName();
            methodNames.put(methodName, ++nextOrder);
        }
    }

}