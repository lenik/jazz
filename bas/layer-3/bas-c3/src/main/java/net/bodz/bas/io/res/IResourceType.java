package net.bodz.bas.io.res;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import net.bodz.bas.meta.decl.NotNull;

public interface IResourceType<T> {

    Path toPath(@NotNull T resource);

    boolean delete(@NotNull T resource);

    boolean exists(@NotNull T resource);

    boolean in(@NotNull T sub, @NotNull T sup);

    boolean createParentDirs(@NotNull T resource);

    String loadText(@NotNull T resource)
            throws IOException;

    List<String> loadLines(@NotNull T resource)
            throws IOException;

    void saveText(@NotNull T resource, @NotNull String text)
            throws IOException;

    void saveText(@NotNull T resource, @NotNull String text, String encoding)
            throws IOException;

}
