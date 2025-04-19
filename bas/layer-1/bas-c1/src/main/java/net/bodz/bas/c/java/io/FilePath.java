package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.meta.decl.Nullable;

public class FilePath {

    private static final String fileSeparator;
    private static final char fileSeparatorChar;

    static {
        fileSeparator = FileSystems.getDefault().getSeparator();
        if (fileSeparator == null || fileSeparator.isEmpty())
            fileSeparatorChar = '/';
        else
            fileSeparatorChar = fileSeparator.charAt(0);
    }

    public static Path canoniOf(Path f) {
        return f == null ? null : f.toAbsolutePath().normalize();
    }

    public static Path canoniOf(Path f, boolean toRealPath, LinkOption... options)
            throws IOException {
        if (f == null)
            return null;
        if (toRealPath)
            return f.toRealPath(options);
        else
            return f.toAbsolutePath().normalize();
    }

    /**
     * This canonical form is safe to getParentFile().
     */
    public static File canoniOf(File f) {
        if (f == null)
            return null;
        try {
            return f.getCanonicalFile();
        } catch (Exception e) {
            return f.getAbsoluteFile();
        }
    }

    public static File canoniOf(String path)
            throws NullPointerException {
        return canoniOf(new File(path));
    }

    public static File canoniOf(URI uri)
            throws NullPointerException {
        if (uri == null)
            throw new NullPointerException();
        return canoniOf(new File(uri));
    }

    public static File canoniOf(URL url)
            throws NullPointerException {
        if (url == null)
            throw new NullPointerException();
        return canoniOf(url.getFile());
    }

    public static Path canoniOf(Path parent, String childPath) {
        parent = canoniOf(parent);
        return canoniOf(parent.resolve(childPath));
    }

    public static File canoniOf(File parent, String childPath) {
        parent = canoniOf(parent);
        return canoniOf(new File(parent, childPath));
    }

    public static File canoniOf(String parentPath, String childPath) {
        File parent = canoniOf(parentPath);
        return canoniOf(new File(parent, childPath));
    }

    /**
     * This function return a relative path used for href: it treats trailing slash carefully.
     *
     * @param path
     *            Should be in canonical form
     * @param ref
     *            The start directory where <code>path</code> is originated. Should be in canonical
     *            form, with no trailing slash (/) unless it's the root.
     * @return The relative representation of <code>path</code> relative to <code>ref</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativePath(String path, String ref, char fileSeparator) {
        if (ref.startsWith(path))
            if (ref.length() > path.length() && ref.charAt(path.length()) == '/') {
                ref = ref.substring(path.length());
                StringBuilder result = new StringBuilder(ref.length());
                for (int i = 0; i < ref.length(); i++)
                    if (ref.charAt(i) == '/') {
                        if (result.length() != 0)
                            result.append('/');
                        result.append("..");
                    }
                return result.toString();
            }

        int clen = Math.min(path.length(), ref.length());
        for (int i = 0; i < clen; i++) {
            if (path.charAt(i) != ref.charAt(i)) {
                clen = i;
                break;
            }
        }

        int lastSlash = path.lastIndexOf('/', clen - 1);
        if (lastSlash == -1)
            clen = 0;
        else
            clen = lastSlash + 1;

        StringBuilder result = new StringBuilder(ref.length() + path.length() - clen);
        // result.append(path.substring(0, clen));

        path = path.substring(clen);
        ref = ref.substring(clen);

        int refLen = ref.length();
        for (int i = 0; i < refLen; i++)
            if (ref.charAt(i) == '/')
                result.append("../");
        result.append(path);

        if (result.length() == 0)
            return ".";
        else
            return result.toString();
    }

    /**
     * @return <code>null</code> if the given <code>path</code> isn't with-in the
     *         <code>origPath</code>, or the relative representation of <code>path</code> relative
     *         to <code>origPath</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativePath(String path, String ref) {
        return getRelativePath(path, ref, fileSeparatorChar);
    }

    public static String getRelativePath(Path file, Path ref) {
        String path = canoniOf(file).toString();
        String refPath = canoniOf(ref).toString();
        if (Files.isDirectory(ref))
            refPath += fileSeparatorChar;
        return getRelativePath(path, refPath, fileSeparatorChar);
    }

    /**
     * @return <code>null</code> if the given <code>file</code> isn't with-in the
     *         <code>origFile</code>, or the relative representation of <code>file</code> relative
     *         to <code>origPath</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativePath(File file, File ref) {
        String path = canoniOf(file).getPath();
        String refPath = canoniOf(ref).getPath();
        if (ref.isDirectory())
            refPath += fileSeparatorChar;
        return getRelativePath(path, refPath, fileSeparatorChar);
    }

    /**
     * @return The relative URL string, keep encoded.<code>null</code> if the given <code>url</code>
     *         isn't with-in the <code>origUrl</code>, or the relative representation of
     *         <code>url</code> relative to <code>origUrl</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativePath(URL url, URL ref) {
        return getRelativePath(url.getPath(), ref.getPath(), '/');
    }

    /**
     * @return The relative URL string, keep encoded.<code>null</code> if the given <code>uri</code>
     *         isn't with-in the <code>origUri</code>, or the relative representation of
     *         <code>uri</code> relative to <code>origUri</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativePath(URI uri, URI ref) {
        return getRelativePath(uri.getPath(), ref.getPath(), '/');
    }

    /**
     * @param href
     *            If doesn't specified (empty or <code>null</code>), the <code>startFile</code> is
     *            returned.
     */
    public static Path joinHref(Path startFile, String href) {
        if (href == null || href.isEmpty())
            return startFile;

        File test = new File(href);
        if (test.isAbsolute())
            return FileFn.toPath(test);

        Path startDir = startFile;
        if (!Files.isDirectory(startFile))
            startDir = startFile.getParent();
        Path file = startDir.resolve(href);
        return file;
    }

    /**
     * @param href
     *            If doesn't specified (empty or <code>null</code>), the <code>startFile</code> is
     *            returned.
     * @return The {@link File} of joined path.
     */
    public static File joinHref(File startFile, String href) {
        if (href == null || href.isEmpty())
            return startFile;

        File test = new File(href);
        if (test.isAbsolute())
            return test;

        File startDir = startFile;
        if (!startFile.isDirectory())
            startDir = startFile.getParentFile();
        return new File(startDir, href);
    }

    public static String joinHref(String start, String href) {
        if (href == null || href.isEmpty())
            return start;

        File test = new File(href);
        if (test.isAbsolute())
            return href;

        if (start.endsWith(fileSeparator))
            return start + href;

        int last = start.lastIndexOf(fileSeparatorChar);
        if (last != -1)
            start = start.substring(0, last);
        return start + fileSeparatorChar + href;
    }

    public static String dirName(String path) {
        return dirNameOr(path, ".");
    }

    public static String dirNameOr(String path, String defaultDir) {
        if (path == null)
            return defaultDir;

        int lastSlash = path.lastIndexOf('/');
        if (lastSlash == -1)
            return defaultDir;

        return path.substring(0, lastSlash);
    }

    @NotNull
    public static String baseName(@NotNull String path) {
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash == -1)
            return path;
        else
            return path.substring(lastSlash + 1);
    }

    @NotNull
    public static String name(@NotNull String baseName) {
        int dot = baseName.lastIndexOf('.');
        return dot == -1 ? baseName : baseName.substring(0, dot);
    }

    public static String extension(String baseName) {
        if (baseName == null)
            return null;
        int dot = baseName.lastIndexOf('.');
        return dot == -1 ? null : baseName.substring(dot + 1);
    }

    @NotNull
    public static String dotExtension(@NotNull String baseName) {
        int dot = baseName.lastIndexOf('.');
        return dot == -1 ? "" : baseName.substring(dot);
    }

    public static String extensionOfPath(String path) {
        if (path == null)
            return null;
        String baseName = baseName(path);
        return extension(baseName);
    }

    @NotNull
    public static String dotExtensionOfPath(@NotNull String path) {
        String baseName = baseName(path);
        return dotExtension(baseName);
    }

    public static String extension(Path path) {
        if (path == null)
            return null;
        Path fileName = path.getFileName();
        return fileName == null ? null : extension(fileName.toString());
    }

    public static String extension(File file) {
        return file == null ? null : extension(file.getName());
    }

    public static String extension(URI uri) {
        return uri == null ? null : extensionOfPath(uri.getPath());
    }

    public static String extension(URL url) {
        return url == null ? null : extensionOfPath(url.getPath());
    }

    @NotNull
    public static String dotExtension(@NotNull Path path) {
        Path fileName = path.getFileName();
        return fileName == null ? "" : dotExtension(fileName.toString());
    }

    @NotNull
    public static String dotExtension(@NotNull File file) {
        return dotExtension(file.getName());
    }

    @NotNull
    public static String dotExtension(@NotNull URI uri) {
        return dotExtensionOfPath(uri.getPath());
    }

    @NotNull
    public static String dotExtension(@NotNull URL url) {
        return dotExtensionOfPath(url.getPath());
    }

    @NotNull
    public static String nameOfPath(@NotNull String path) {
        String baseName = baseName(path);
        return name(baseName);
    }

    @Nullable
    public static String name(@NotNull Path path) {
        Path fileName = path.getFileName();
        return fileName == null ? null : nameOfPath(fileName.toString());
    }

    @NotNull
    public static String name1(@NotNull Path pathNotEmpty) {
        Path fileName = pathNotEmpty.getFileName();
        return nameOfPath(fileName.toString());
    }

    @NotNull
    public static String name(@NotNull File file) {
        return name(file.getName());
    }

    @NotNull
    public static String name(@NotNull URI uri) {
        return nameOfPath(uri.getPath());
    }

    @NotNull
    public static String name(@NotNull URL url) {
        return nameOfPath(url.getPath());
    }

    @NotNull
    public static NameExtension splitExtension(@NotNull String path) {
        int dot = path.lastIndexOf('.');
        if (dot == -1)
            return new NameExtension(path, null);
        return new NameExtension(path.substring(0, dot), path.substring(dot + 1));
    }

    public static File dirName(@NotNull File file, @NotNull File newDir) {
        String name = file.getName();
        if (newDir == null)
            return new File(name);
        else
            return new File(newDir, name);
    }

    public static File baseName(@NotNull File file, String newBaseName) {
        File dir = file.getParentFile();
        if (dir == null)
            return new File(newBaseName);
        else
            return new File(dir, newBaseName);
    }

    @NotNull
    public static String extension(@NotNull String path, String newExtension) {
        return dotExtension(path, newExtension == null ? "" : ('.' + newExtension));
    }

    @NotNull
    public static File extension(@NotNull File file, String newExtension) {
        return dotExtension(file, newExtension == null ? "" : ('.' + newExtension));
    }

    @NotNull
    public static Path extension(@NotNull Path path, String newExtension) {
        return dotExtension(path, newExtension == null ? "" : ('.' + newExtension));
    }

    @NotNull
    public static String dotExtension(@NotNull String path, @NotNull String newDotExtension) {
        String dir = dirName(path);
        String baseName = baseName(path);

        String name = name(baseName);
        String newBaseName = name + newDotExtension;
        return dir == null ? newBaseName : (dir + '/' + newBaseName);
    }

    @NotNull
    public static File dotExtension(@NotNull File file, @NotNull String newDotExtension) {
        File dir = file.getParentFile();
        String baseName = file.getName();
        String name = name(baseName);
        String newBaseName = name + newDotExtension;
        return dir == null ? new File(newBaseName) : new File(dir, newBaseName);
    }

    @NotNull
    public static Path dotExtension(@NotNull Path path, @NotNull String newDotExtension) {
        Path fileName = path.getFileName();
        if (fileName == null)
            return path;

        Path dir = path.getParent();
        String baseName = fileName.toString();

        String name = name(baseName);
        String newBaseName = name + newDotExtension;
        return dir == null ? Paths.get(newBaseName) : dir.resolve(newBaseName);
    }

    public static String toUnixStyle(String path) {
        if (path == null)
            return null;
        // if (SystemProperties.getOsName().equals("win32"))
        path = path.replace(fileSeparatorChar, '/');
        // Mac? path = path.replace(":", SLASH);
        return path;
    }

    public static String removeTrailingSlashes(String path) {
        if (path == null)
            throw new NullPointerException("path");
        while (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);
        return path;
    }

    static File[] environPathArray;
    static String[] environPathExtArray;

    static {
        String ps = File.pathSeparator;
        if (ps == null)
            ps = ":";
        String pathenv = System.getenv("PATH");
        if (pathenv == null)
            environPathArray = new File[0];
        else {
            String[] v = pathenv.split(ps);
            environPathArray = new File[v.length];
            for (int i = 0; i < v.length; i++)
                environPathArray[i] = new File(v[i]);
        }

        String pathextenv = System.getenv("PATHEXT");
        if (pathextenv == null)
            environPathExtArray = new String[0];
        else {
            String[] v = pathextenv.split(ps);
            // sysExts = new String[v.length];
            // for (int i = 0; i < v.length; i++)
            // sysExts[i] = "." + v[i];
            environPathExtArray = v;
        }
    }

    /**
     * Find program using system default PATHEXT (win32 only).
     *
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, File... paths) {
        return which(name, environPathExtArray, paths);
    }

    /**
     * @return <code>null</code> if couldn't find name.
     */
    public static File which(String name, String[] pathExts, File... paths) {
        if (paths == null || paths.length == 0)
            paths = environPathArray;
        for (File path : paths) {
            File f = new File(path, name);
            if (f.isFile())
                return f;
            if (pathExts != null)
                for (String ext : pathExts) {
                    f = new File(path, name + ext);
                    if (f.isFile())
                        return f;
                }
        }
        return null;
    }

    public static boolean createLink(File linkFile, String targetSpec, boolean symbolic)
            throws IOException {
        if (linkFile.exists())
            return false;

        Path _link = linkFile.toPath();
        Path _target = Paths.get(targetSpec);
        if (symbolic)
            Files.createSymbolicLink(_link, _target);
        else
            Files.createLink(_link, _target);
        return true;
    }

    public static String readSymbolicLink(File linkFile)
            throws IOException {
        Path link = linkFile.toPath();
        Path target = Files.readSymbolicLink(link);
        return target.toString();
    }

    public static String readSymbolicLink(String pathstr)
            throws IOException {
        Path path = Paths.get(pathstr);
        Path target = Files.readSymbolicLink(path);
        return target.toString();
    }

}
