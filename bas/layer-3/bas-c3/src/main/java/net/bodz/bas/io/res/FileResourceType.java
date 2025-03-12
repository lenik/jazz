package net.bodz.bas.io.res;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileResourceType
        implements
            IResourceType<File> {

    @Override
    public boolean delete(File resource) {
        return resource.delete();
    }

    @Override
    public boolean exists(File resource) {
        return resource.exists();
    }

    @Override
    public boolean createParentDirs(File resource) {
        File parentFile = resource.getParentFile();
        if (parentFile == null)
            return true;
        if (parentFile.isDirectory())
            return true;
        // if (parentFile.exists()) return false;
        return parentFile.mkdirs();
    }

    @Override
    public String loadText(File resource)
            throws IOException {
        return ResFn.file(resource).read().readString();
    }

    @Override
    public List<String> loadLines(File resource)
            throws IOException {
        return ResFn.file(resource).read().readLines();
    }

    @Override
    public void saveText(File resource, String text)
            throws IOException {
        ResFn.file(resource).write().writeString(text);
    }

    @Override
    public void saveText(File resource, String text, String encoding)
            throws IOException {
        ResFn.file(resource).charset(encoding).write().writeString(text);
    }

    public static final FileResourceType INSTANCE = new FileResourceType();

}
