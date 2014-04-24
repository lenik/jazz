package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.meta.decl.Internal;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public interface IXjdocAware {

    @Internal
    IJavaElementDoc getXjdoc();

    void setXjdoc(IJavaElementDoc xjdoc);

}
