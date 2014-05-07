package net.bodz.bas.potato.ref;

import net.bodz.bas.ui.dom1.IUiRef;

public interface IUiRefEntries
        extends IRefEntries {

    @Override
    <T> IUiRef<T> get(String name);

}
