package net.bodz.bas.ui.util;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.util.AbstractFactory;

public class VoidFactory_String
        extends AbstractFactory<String> {

    @Override
    public Class<? extends String> getType() {
        return null;
    }

    @Override
    public String _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        return null;
    }

}
