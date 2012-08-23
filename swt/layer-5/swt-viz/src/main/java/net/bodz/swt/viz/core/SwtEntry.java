package net.bodz.swt.viz.core;

import net.bodz.bas.gui.err.GUIValidationException;
import net.bodz.bas.potato.ref.IRefEntry;

public interface SwtEntry<T>
        extends IRefEntry<T> {

    SwtEntryMetadata getMetadata();

    void validate(Object newValue)
            throws GUIValidationException;

}
