package net.bodz.swt.reflect;

import net.bodz.bas.traits.ValidateException;

public interface SwtEntry<T>
        extends RefEntry<T> {

    SwtEntryMetadata getMetadata();

    void validate(Object newValue)
            throws ValidateException;

}
