package net.bodz.bas.vfs.path;

import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public interface IPathEntries {

    /**
     * Get the number of local entries.
     *
     * @return Count of entries of local path.
     */
    int getEntryCount();

    /**
     * Get local entry element by index.
     *
     * @param index
     *            0-based entry index. Negative integer for reversed index, i.e., <code>-1</code> to
     *            get the last entry.
     * @return The indexed entry.
     * @throws IndexOutOfBoundsException
     *             If <code>index</code> is out of range.
     */
    String getEntry(int index);

    /**
     * Get local entry array.
     *
     * @return Non-<code>null</code> entry array of local path.
     */
    String[] getEntryArray();

    List<String> getEntryList();

    @NotNull
    default String joinEntries() {
        return joinEntries(IPath.SEPARATOR);
    }

    @NotNull
    String joinEntries(String sep);

}
