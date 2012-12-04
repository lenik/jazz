package net.bodz.bas.i18n.dom1;

import java.util.Collections;
import java.util.Set;

import net.bodz.bas.c.object.ObjectInfo;
import net.bodz.bas.i18n.dom.DomainString;

public abstract class AbstractElement
        implements IElement {

    @Override
    public String getName() {
        String id = ObjectInfo.getSimpleId(this);
        return id;
    }

    @Override
    public DomainString getDisplayName() {
        return null;
    }

    @Override
    public DomainString getDescription() {
        return null;
    }

    @Override
    public DomainString getHelpDoc() {
        return null;
    }

    @Override
    public int getUserLevel() {
        return 0;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public Set<String> getTagNames() {
        return Collections.emptySet();
    }

}
