package net.bodz.swt.viz;

import net.bodz.bas.potato.ref.IRefEntry;

public interface ISwtGUIRefEntry<T>
        extends IRefEntry<T> {

    MappedSwtVizStyleClass getStyle();

}
