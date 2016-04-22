package net.bodz.bas.i18n.dom1;

import net.bodz.bas.i18n.dom.iString;

public class NullElement
        implements IElement {

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public String getName() {
        return "null";
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
        return 0;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    public static final NullElement INSTANCE = new NullElement();

}
