package net.bodz.bas.i18n.dom1;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.bean.DetailLevel;

public abstract class AbstractElement
        implements IElement {

    @Override
    public String getName() {
        String id = ObjectInfo.getSimpleId(this);
        return id;
    }

    @Override
    public iString getLabel() {
        return iString.NULL;
    }

    @Override
    public iString getDescription() {
        return iString.NULL;
    }

    @Override
    public iString getHelpDoc() {
        return iString.NULL;
    }

    @Override
    public int getDetailLevel() {
        return DetailLevel.NORMAL;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }

}
