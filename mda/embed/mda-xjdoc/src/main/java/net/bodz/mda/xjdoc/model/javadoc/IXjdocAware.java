package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.meta.decl.Internal;
import net.bodz.mda.xjdoc.model.IElementDoc;

public interface IXjdocAware {

    @Internal
    IElementDoc getXjdoc();

    void setXjdoc(IElementDoc xjdoc);

}
