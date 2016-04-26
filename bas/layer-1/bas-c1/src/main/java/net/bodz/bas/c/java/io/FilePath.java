package net.bodz.bas.c.java.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePath {

    private static char fileSeparator;
    static {
        String fs = System.getProperty("file.separator");
        if (fs == null || fs.isEmpty())
            fileSeparator = '/';
        else
            fileSeparator = fs.charAt(0);
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
        return getRelativePath(path, ref, fileSeparator);
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
        return getRelativePath(path, refPath, fileSeparator);
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
     * @param relativeName
     *            If doesn't specified (empty or <code>null</code>), the <code>startFile</code> is
     *            returned.
     * @return The {@link File} of joined path.
     */
    public static File join(File startFile, String relativeName) {
        if (relativeName == null || relativeName.isEmpty())
            return startFile;
        return new File(startFile, relativeName);
    }

    public static String getDirName(String path) {
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash == -1)
            return ".";
        else
            return path.substring(0, lastSlash);
    }

    public static String getBaseName(String path) {
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash == -1)
            return path;
        else
            return path.substring(lastSlash + 1);
    }

    /**
     * Get the extension name from the given path string.
     * 
     * @param path
     *            The path string, can't be <code>null</code>.
     * @param includeDot
     *            Whether dot(.) is included in the return value.
     * @return If file has no extension, returns <code>null</code> if includeDot is
     *         <code>false</code>, or "." if includeDot is <code>true</code>.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    public static String getExtension(String path, boolean includeDot) {
        int dot = path.lastIndexOf('.');
        if (dot == -1)
            return includeDot ? "." : null;
        else
            return path.substring(includeDot ? dot : dot + 1);
    }

    public static String getExtension(File file, boolean includeDot) {
        return getExtension(file.getPath(), includeDot);
    }

    /**
     * @return The extension name without the dot(.). If file has no extension, returns
     *         <code>null</code>.
     */
    public static String getExtension(String path) {
        return getExtension(path, false);
    }

    /**
     * @return The extension name without the dot(.). If file has no extension, returns
     *         <code>null</code>.
     */
    public static String getExtension(File file) {
        return getExtension(file.getPath());
    }

    /**
     * Get the base name without extension
     */
    public static String stripExtension(String name) {
        int dot = name.lastIndexOf('.');
        if (dot != -1)
            return name.substring(0, dot);
        return name;
    }

    /**
     * Get the base name without extension
     */
    public static String stripExtension(File file) {
        String name = file.getName();
        return stripExtension(name);
    }

    /**
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    public static NameExtension splitExtension(String path) {
        int dot = path.lastIndexOf('.');
        if (dot == -1)
            return new NameExtension(path, null);
        return new NameExtension(path.substring(0, dot), path.substring(dot + 1));
    }

    public static String toUnixStyle(String path) {
        if (path == null)
            return null;
        // if (SystemProperties.getOsName().equals("win32"))
        path = path.replace(fileSeparator, '/');
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
        String ps = System.getProperty("path.separator");
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
