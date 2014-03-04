package net.bodz.bas.potato.ref;

import net.bodz.bas.gui.dom1.IGUIRefEntry;

public interface IGUIRefEntries
        extends IRefEntries {

    @Override
    <T> IGUIRefEntry<T> get(String name);

}
