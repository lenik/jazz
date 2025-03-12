package net.bodz.bas.io.res;

import java.io.IOException;
import java.util.List;

public interface IResourceType<T> {

    boolean delete(T resource);

    boolean exists(T resource);

    boolean createParentDirs(T resource);

    String loadText(T resource)
            throws IOException;

    List<String> loadLines(T resource)
            throws IOException;

    void saveText(T resource, String text)
            throws IOException;

    void saveText(T resource, String text, String encoding)
            throws IOException;

}
