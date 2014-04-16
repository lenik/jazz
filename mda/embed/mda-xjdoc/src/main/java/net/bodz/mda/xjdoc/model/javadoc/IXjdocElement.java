package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.i18n.dom1.IElement;
import net.bodz.bas.meta.decl.Internal;
import net.bodz.mda.xjdoc.model.IJavaElementDoc;

public interface IXjdocElement
        extends IElement {

    @Internal
    IJavaElementDoc getXjdoc();

}
