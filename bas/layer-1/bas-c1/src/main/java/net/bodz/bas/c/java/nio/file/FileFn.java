package net.bodz.bas.c.java.nio.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class FileFn
        extends PathFn {

    @Nullable
    public static Path toPath(@Nullable File file) {
        return file == null ? null : file.toPath();
    }

    @NotNull
    public static Path getAbsoluteFile(@NotNull Path path, LinkOption... options) {
        Path absolutePath = path.toAbsolutePath().normalize();
        return absolutePath;
    }

    @NotNull
    public static String getAbsolutePath(@NotNull Path path) {
        return path.toAbsolutePath().normalize().toString();
    }

    @NotNull
    public static Path getCanonicalFile(@NotNull Path path, LinkOption... options)
            throws IOException {
        Path realPath = path.toRealPath(options);
        return realPath;
    }

    @NotNull
    public static String getCanonicalPath(@NotNull Path path, LinkOption... options)
            throws IOException {
        Path realPath = path.toRealPath(options);
        return realPath.toString();
    }

    /** @ses {@link Files#exists(Path, LinkOption...)} */
    @Deprecated
    public static boolean exists(@NotNull Path path, @NotNull LinkOption... options) {
        return Files.exists(path, options);
    }

    public static boolean isDirectory(@NotNull Path path, @NotNull LinkOption... options) {
        return Files.isDirectory(path, options);
    }

    public static boolean isFile(@NotNull Path path, @NotNull LinkOption... options) {
        return Files.isRegularFile(path, options);
    }

    public static long length(@NotNull Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            return 0L;
        }
    }

    public static boolean canRead(@NotNull Path path) {
        return Files.isReadable(path);
    }

    public static boolean canWrite(@NotNull Path path) {
        return Files.isWritable(path);
    }

    public static boolean canExecute(@NotNull Path path) {
        return Files.isExecutable(path);
    }

    public static boolean isHidden(@NotNull Path path) {
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean setReadable(@NotNull Path path, boolean readable) {
        return setReadable(path, readable, true);
    }

    public static boolean setReadable(@NotNull Path path, boolean readable, boolean ownerOnly) {
        PosixFileAttributeView posix = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        if (posix != null)
            try {
                Set<PosixFilePermission> permissions = new HashSet<>(posix.readAttributes().permissions());
                boolean changed = false;
                if (readable) {
                    changed = permissions.add(PosixFilePermission.OWNER_READ);
                    if (!ownerOnly) {
                        changed |= permissions.add(PosixFilePermission.GROUP_READ);
                        changed |= permissions.add(PosixFilePermission.OTHERS_READ);
                    }
                } else {
                    changed = permissions.remove(PosixFilePermission.OWNER_READ);
                    if (!ownerOnly) {
                        changed |= permissions.remove(PosixFilePermission.GROUP_READ);
                        changed |= permissions.remove(PosixFilePermission.OTHERS_READ);
                    }
                }
                if (changed)
                    posix.setPermissions(permissions);
                return true;
            } catch (IOException e) {
                return false;
            }

        return false;
    }

    /**
     * @see File#setReadOnly()
     */
    public static boolean setReadOnly(@NotNull Path path) {
        return setWritable(path, false);
    }

    public static boolean setWritable(@NotNull Path path, boolean writable) {
        return setWritable(path, writable, true);
    }

    public static boolean setWritable(@NotNull Path path, boolean writable, boolean ownerOnly) {
        PosixFileAttributeView posix = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        if (posix != null)
            try {
                Set<PosixFilePermission> permissions = new HashSet<>(posix.readAttributes().permissions());
                boolean changed = false;
                if (writable) {
                    changed = permissions.add(PosixFilePermission.OWNER_WRITE);
                    if (!ownerOnly) {
                        changed |= permissions.add(PosixFilePermission.GROUP_WRITE);
                        changed |= permissions.add(PosixFilePermission.OTHERS_WRITE);
                    }
                } else {
                    changed = permissions.remove(PosixFilePermission.OWNER_WRITE);
                    if (!ownerOnly) {
                        changed |= permissions.remove(PosixFilePermission.GROUP_WRITE);
                        changed |= permissions.remove(PosixFilePermission.OTHERS_WRITE);
                    }
                }
                if (changed)
                    posix.setPermissions(permissions);
                return true;
            } catch (IOException e) {
                return false;
            }

        DosFileAttributeView dos = Files.getFileAttributeView(path, DosFileAttributeView.class);
        if (dos != null)
            try {
                dos.setReadOnly(!writable);
                return true;
            } catch (IOException e) {
                return false;
            }

        return false;
    }

    public static boolean setExecutable(@NotNull Path path, boolean executable) {
        return setExecutable(path, executable, true);
    }

    public static boolean setExecutable(@NotNull Path path, boolean executable, boolean ownerOnly) {
        PosixFileAttributeView posix = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        if (posix != null)
            try {
                Set<PosixFilePermission> permissions = new HashSet<>(posix.readAttributes().permissions());
                boolean changed = false;
                if (executable) {
                    changed = permissions.add(PosixFilePermission.OWNER_EXECUTE);
                    if (!ownerOnly) {
                        changed |= permissions.add(PosixFilePermission.GROUP_EXECUTE);
                        changed |= permissions.add(PosixFilePermission.OTHERS_EXECUTE);
                    }
                } else {
                    changed = permissions.remove(PosixFilePermission.OWNER_EXECUTE);
                    if (!ownerOnly) {
                        changed |= permissions.remove(PosixFilePermission.GROUP_EXECUTE);
                        changed |= permissions.remove(PosixFilePermission.OTHERS_EXECUTE);
                    }
                }
                if (changed)
                    posix.setPermissions(permissions);
                return true;
            } catch (IOException e) {
                return false;
            }

        return false;
    }

    /**
     * @see File#lastModified()
     */
    public static long lastModified(@NotNull Path path, @NotNull LinkOption... options) {
        try {
            FileTime fileTime = Files.getLastModifiedTime(path, options);
            return fileTime.toMillis();
        } catch (IOException e) {
            return 0L;
        }
    }

    /**
     * @see File#setLastModified(long)
     */
    public static boolean setLastModified(@NotNull Path path, long time) {
        FileTime fileTime = FileTime.fromMillis(time);
        try {
            Files.setLastModifiedTime(path, fileTime);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @return Different from {@link File#delete()}, this function always return true if no error.
     * @see #delete(Path, boolean)
     */
    public static boolean delete(@NotNull Path path) {
        return delete(path, true);
    }

    public static boolean delete(@NotNull Path path, boolean nop) {
        if (Files.notExists(path))
            return nop;
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void deleteOnExit(@NotNull Path path) {
        path.toFile().deleteOnExit();
    }

    public static boolean mkdir(@NotNull Path path) {
        return mkdir(path, true);
    }

    public static boolean mkdir(@NotNull Path path, boolean nop) {
        if (isDirectory(path))
            return nop;
        try {
            Files.createDirectory(path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Create directory and its parent directories.
     *
     * @return Different from {@link File#mkdirs()}, this function always return true if no error.
     * @see #mkdirs(Path, boolean, FileAttribute[])
     */
    public static boolean mkdirs(@NotNull Path path, @NotNull FileAttribute<?>... attrs) {
        return mkdirs(path, true, attrs);
    }

    public static boolean mkdirs(@NotNull Path path, boolean nop, @NotNull FileAttribute<?>... attrs) {
        if (Files.isDirectory(path))
            return nop;
        try {
            Files.createDirectories(path, attrs);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @see File#renameTo(File)
     */
    public static boolean renameTo(@NotNull Path path, Path target, @NotNull CopyOption... options) {
        // if (Files.exists(target)) return false;
        try {
            Files.move(path, target, options);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean createNewFile(@NotNull Path path) {
        try {
            Files.createFile(path);
            return true;
        } catch (FileAlreadyExistsException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    public static long getTotalSpace(@NotNull Path path) {
        try {
            FileStore fileStore = Files.getFileStore(path);
            return fileStore.getTotalSpace();
        } catch (IOException e) {
            return 0L;
        }
    }

    public static long getUsableSpace(@NotNull Path path) {
        try {
            FileStore fileStore = Files.getFileStore(path);
            return fileStore.getUsableSpace();
        } catch (IOException e) {
            return 0L;
        }
    }

    public static long getFreeSpace(@NotNull Path path) {
        try {
            FileStore fileStore = Files.getFileStore(path);
            return fileStore.getUnallocatedSpace();
        } catch (IOException e) {
            return 0L;
        }
    }

    public static String[] listNames(@NotNull Path dir)
            throws IOException {
        try (Stream<Path> stream = Files.list(dir)) {
            List<String> list = new ArrayList<>(); // stream.count() is of type long.
            stream.forEach(p -> {
                list.add(p.getFileName().toString());
            });
            return list.toArray(new String[0]);
        }
    }

    public static String[] listNames(@NotNull Path dir, Predicate<Path> predicate)
            throws IOException {
        try (Stream<Path> stream = Files.list(dir)) {
            List<String> list = new ArrayList<>(); // stream.count() is of type long.
            stream.forEach(p -> {
                if (predicate.test(p))
                    list.add(p.getFileName().toString());
            });
            return list.toArray(new String[0]);
        }
    }

//    public static Path[] listFiles(@NotNull Path dir)
//            throws IOException {
//        try (Stream<Path> stream = Files.list(dir)) {
//            List<Path> list = new ArrayList<>(); // stream.count() is of type long.
//            stream.forEach(p -> {
//                list.add(new IoPath(p));
//            });
//            return list.toArray(new Path[0]);
//        }
//    }
//
//    public static Path[] listFiles(@NotNull Path dir, Predicate<Path> predicate)
//            throws IOException {
//        try (Stream<Path> stream = Files.list(dir)) {
//            List<Path> list = new ArrayList<>(); // stream.count() is of type long.
//            stream.forEach(p -> {
//                IoPath file = new IoPath(p);
//                if (predicate.test(file))
//                    list.add(file);
//            });
//            return list.toArray(new Path[0]);
//        }
//    }

}
