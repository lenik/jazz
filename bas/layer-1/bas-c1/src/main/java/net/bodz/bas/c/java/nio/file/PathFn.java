package net.bodz.bas.c.java.nio.file;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import net.bodz.bas.c.java.util.stream.iterable.Stream;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class PathFn {

    @Nullable
    public static String baseName(@Nullable Path path) {
        if (path == null)
            return null;

        Path fileName = path.getFileName();
        if (fileName == null)
            return null;

        return fileName.toString();
    }

    @Nullable
    public static String field(@Nullable Path path, int index) {
        if (path == null)
            return null;
        Path name = path.getName(index);
        return name.toString();
    }

    @Nullable
    public static String dirName(@Nullable Path path) {
        if (path == null)
            return null;

        Path parent = path.getParent();
        if (parent == null)
            return null;

        return parent.toString();
    }

    @Nullable
    public static String name(@Nullable Path path) {
        String name = baseName(path);
        if (name == null)
            return null;
        int lastDot = name.lastIndexOf('.');
        return lastDot == -1 ? name : name.substring(0, lastDot);
    }

    @Nullable
    public static String extension(@Nullable Path path) {
        String name = baseName(path);
        if (name == null)
            return null;
        int lastDot = name.lastIndexOf('.');
        return lastDot == -1 ? null : name.substring(lastDot + 1);
    }

    public static boolean notExists(@NotNull Path path, @NotNull LinkOption... options) {
        return Files.notExists(path, options);
    }

    public static boolean isRegularFile(@NotNull Path path, @NotNull LinkOption... options) {
        return Files.isRegularFile(path, options);
    }

    public static boolean deleteIfExists(@NotNull Path path) {
        if (notExists(path))
            return true;
        try {
            Files.deleteIfExists(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean move(@NotNull Path path, Path target, @NotNull CopyOption... options) {
        try {
            Files.move(path, target, options);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Stream<Path> list(Path dir)
            throws IOException {
        return Stream.of(Files.list(dir));
    }

}
