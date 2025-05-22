package net.bodz.bas.make.util;

import java.nio.file.Path;

import net.bodz.bas.meta.decl.NotNull;

public class FileBin
        extends AbstractFileEntry<byte[]> {

    public FileBin(String name, Path path, byte[] data) {
        super(name, path, data);
    }

    @NotNull
    @Override
    public Class<byte[]> getDataType() {
        return byte[].class;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder
            extends AbstractFileEntry.Builder<Builder, byte[]> {

        public FileBin build() {
            return new FileBin(name, path, data);
        }

    }

}
