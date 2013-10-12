package net.bodz.bas.potato.ref;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListRefEntries
        implements IRefEntries, Serializable {

    private static final long serialVersionUID = 1L;

    protected final List<IRefEntry<?>> entries = new ArrayList<IRefEntry<?>>();

    @Override
    public int getRefEntryCount() {
        return entries.size();
    }

    @Override
    public IRefEntry<?> getRefEntry(int index) {
        return entries.get(index);
    }

    @Override
    public IRefEntry<?> getRefEntry(String name) {
        if (name == null)
            throw new NullPointerException("name");
        for (IRefEntry<?> entry : entries) {
            String entryName = entry.getName();
            if (name.equals(entryName))
                return entry;
        }
        return null;
    }

    @Override
    public Iterable<IRefEntry<?>> getRefEntries() {
        return entries;
    }

}
