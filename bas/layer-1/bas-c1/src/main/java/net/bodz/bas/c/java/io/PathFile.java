package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

import net.bodz.bas.meta.decl.NotNull;

public class PathFile {

    /**
     * Create directory and its parent directories.
     *
     * @return Different from {@link File#mkdirs()}, this function always return true if no error.
     */
    public static boolean mkdirs(Path path, @NotNull FileAttribute<?>... attrs) {
        try {
            Files.createDirectories(path, attrs);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static boolean delete(Path path) {
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean renameTo(Path src, Path dst, CopyOption... options) {
        try {
            Files.move(src, dst, options);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
