package net.bodz.xfo.types;

import java.io.File;
import java.util.Comparator;

import net.bodz.bas.types.util.Comparators;

public class FileVersion implements VersionedElement<Long> {

    private final File file;

    public FileVersion(File file) {
        if (file == null)
            throw new NullPointerException("file");
        this.file = file;
    }

    @Override
    public Comparator<? super Long> getComparator() {
        return Comparators.LONG;
    }

    @Override
    public Long getVersion() {
        return file.lastModified();
    }

}
