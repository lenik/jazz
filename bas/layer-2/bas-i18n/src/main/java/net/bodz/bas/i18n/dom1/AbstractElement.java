package net.bodz.bas.i18n.dom1;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.i18n.dom.iString;

public abstract class AbstractElement
        implements IElement {

    @Override
    public String getName() {
        String id = ObjectInfo.getSimpleId(this);
        return id;
    }

    @Override
    public iString getLabel() {
        return null;
    }

    @Override
    public iString getDescription() {
        return null;
    }

    @Override
    public iString getHelpDoc() {
        return null;
    }

    @Override
    public int getVerboseLevel() {
        return PUBLIC_LEVEL;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public String toString() {
        return getName();
    }

}
