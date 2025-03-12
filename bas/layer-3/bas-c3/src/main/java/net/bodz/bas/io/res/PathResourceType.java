package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PathResourceType
        implements
            IResourceType<Path> {

    @Override
    public boolean delete(Path resource) {
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
    public boolean exists(Path resource) {
        return Files.exists(resource);
    }

    @Override
    public boolean createParentDirs(Path resource) {
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
    public String loadText(Path resource)
            throws IOException {
        return ResFn.path(resource).read().readString();
    }

    @Override
    public List<String> loadLines(Path resource)
            throws IOException {
        return ResFn.path(resource).read().readLines();
    }

    @Override
    public void saveText(Path resource, String text)
            throws IOException {
        ResFn.path(resource).write().writeString(text);
    }

    @Override
    public void saveText(Path resource, String text, String encoding)
            throws IOException {
        ResFn.path(resource).charset(encoding).write().writeString(text);
    }

    public static final PathResourceType INSTANCE = new PathResourceType();

}
