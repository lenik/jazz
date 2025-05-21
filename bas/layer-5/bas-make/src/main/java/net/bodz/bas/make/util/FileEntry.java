package net.bodz.bas.make.util;

import java.nio.file.Path;

import net.bodz.bas.make.FileRef;
import net.bodz.bas.make.IDataEntry;
import net.bodz.bas.meta.decl.NotNull;

public class FileEntry
        extends FileRef
        implements IDataEntry<Path, byte[]> {

    public FileEntry(@NotNull Path key) {
        super(key);
    }

    @NotNull
    @Override
    public Class<Path> getKeyType() {
        return Path.class;
    }

    @NotNull
    @Override
    public Path getKey() {
        return file;
    }

    @Override
    public String getDisplayName() {
        return file.getFileName().toString();
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

}
