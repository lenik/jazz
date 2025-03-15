package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import net.bodz.bas.c.java.io.FileRelation;
import net.bodz.bas.meta.decl.NotNull;

public class PathResourceType
        implements IResourceType<Path> {

    @Override
    public Path toPath(@NotNull Path resource) {
        return resource;
    }

    @Override
    public boolean delete(@NotNull Path resource) {
        if (Files.notExists(resource))
            return false;
        try {
            Files.delete(resource);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean exists(@NotNull Path resource) {
        return Files.exists(resource);
    }

    @Override
    public boolean in(@NotNull Path sub, @NotNull Path sup) {
        Path _sub = sub.toAbsolutePath().normalize();
        Path _sup = sup.toAbsolutePath().normalize();
        return FileRelation.isChildOf(_sub, _sup);
    }

    @Override
    public boolean createParentDirs(@NotNull Path resource) {
        Path parent = resource.getParent();
        if (parent == null)
            return true;
        if (Files.isDirectory(parent))
            return true;
        try {
            Files.createDirectories(parent);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String loadText(@NotNull Path resource)
            throws IOException {
        return ResFn.path(resource).read().readString();
    }

    @Override
    public List<String> loadLines(@NotNull Path resource)
            throws IOException {
        return ResFn.path(resource).read().readLines();
    }

    @Override
    public void saveText(@NotNull Path resource, @NotNull String text)
            throws IOException {
        ResFn.path(resource).write().writeString(text);
    }

    @Override
    public void saveText(@NotNull Path resource, @NotNull String text, String encoding)
            throws IOException {
        ResFn.path(resource).charset(encoding).write().writeString(text);
    }

    public static final PathResourceType INSTANCE = new PathResourceType();

}
