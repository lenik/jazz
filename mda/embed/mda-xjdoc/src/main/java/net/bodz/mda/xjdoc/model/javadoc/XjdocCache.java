package net.bodz.mda.xjdoc.model.javadoc;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;
import net.bodz.mda.xjdoc.model.IElementDoc;

public class XjdocCache {

    public IElementDoc xjdoc;

    public iString label = iString.NULL;
    public iString description = iString.NULL;
    public iString helpDoc = iString.NULL;

    public int priority = 0;
    public int detailLevel = DetailLevel.NORMAL;

}
