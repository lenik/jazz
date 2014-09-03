package net.bodz.bas.potato.ref;

import java.util.Set;

public interface IRefEntries {

    Set<String> keySet();

    <T> IRefEntry<T> getEntry(String name);

}
