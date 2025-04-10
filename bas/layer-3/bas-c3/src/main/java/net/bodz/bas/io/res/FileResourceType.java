package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.meta.decl.NotNull;

public class FileResourceType
        implements IResourceType<File> {

    @Override
    public Path toPath(@NotNull File resource) {
        return resource.toPath();
    }

    @Override
    public boolean delete(@NotNull File resource) {
        return resource.delete();
    }

    @Override
    public boolean exists(@NotNull File resource) {
        return resource.exists();
    }

    @Override
    public boolean in(@NotNull File sub, @NotNull File sup) {
        Path _sub = sub.getAbsoluteFile().toPath();
        Path _sup = sup.getAbsoluteFile().toPath();
        return FileRelation.isChildOf(_sub, _sup);
    }

    @Override
    public boolean createParentDirs(@NotNull File resource) {
        File parentFile = resource.getParentFile();
        if (parentFile == null)
            return true;
        if (parentFile.isDirectory())
            return true;
        // if (parentFile.exists()) return false;
        return parentFile.mkdirs();
    }

    @Override
    public String loadText(@NotNull File resource)
            throws IOException {
        if (!resource.exists() || !resource.isFile())
            return null;
        else
            return ResFn.file(resource).read().readString();
    }

    @NotNull
    @Override
    public List<String> loadLines(@NotNull File resource)
            throws IOException {
        if (!resource.exists() || !resource.isFile())
            return new ArrayList<>();
        else
            return ResFn.file(resource).read().readLines(true);
    }

    @Override
    public void saveText(@NotNull File resource, @NotNull String text)
            throws IOException {
        ResFn.file(resource).write().writeString(text);
    }

    @Override
    public void saveText(@NotNull File resource, @NotNull String text, String encoding)
            throws IOException {
        ResFn.file(resource).charset(encoding).write().writeString(text);
    }

    public static final FileResourceType INSTANCE = new FileResourceType();

}
