package net.bodz.bas.make.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import net.bodz.bas.make.IKeyData;
import net.bodz.bas.make.IKeyDataBuilder;
import net.bodz.bas.meta.decl.NotNull;

public abstract class AbstractFileEntry<T>
        implements IKeyData<Path, T>,
                   INamed {

    String name;
    Path path;
    T data;

    public AbstractFileEntry(@NotNull Path path) {
        this.path = path;
    }

    public AbstractFileEntry(String name, Path path, T data) {
        this.name = name;
        this.path = path;
        this.data = data;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Override
    public Class<Path> getKeyType() {
        return Path.class;
    }

    @NotNull
    @Override
    public Path getKey() {
        return path;
    }

    @Override
    public String getDisplayName() {
        return path.getFileName().toString();
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public ZonedDateTime getLastModified() {
        if (!Files.exists(path))
            return null;
        FileTime fileTime;
        try {
            fileTime = Files.getLastModifiedTime(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileTime.toInstant().atZone(ZoneId.systemDefault());
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<self_t, T>
            implements IKeyDataBuilder<self_t, Path, T> {

        protected String name;
        protected Path path;
        protected T data;

        public self_t name(String value) {
            this.name = value;
            return (self_t) this;
        }

        public self_t path(Path value) {
            this.path = value;
            return (self_t) this;
        }

        @Override
        public self_t key(@NotNull Path key) {
            path = key;
            return (self_t) this;
        }

        @Override
        public self_t data(T value) {
            this.data = value;
            return (self_t) this;
        }

    }

}
