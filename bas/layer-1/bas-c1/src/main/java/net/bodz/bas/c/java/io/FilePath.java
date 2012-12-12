package net.bodz.bas.c.java.io;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import net.bodz.bas.c.string.StringPart;

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
        try {
            return canoniOf(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
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

        int lastSlash = path.lastIndexOf('/', clen);
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
        return StringPart.afterLast(path, '/');
    }

    /**
     * @return <code>null</code> if file has no extension.
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    public static String getExtension(String path, boolean includeDot) {
        int dot = path.lastIndexOf('.');
        if (dot != -1)
            return path.substring(includeDot ? dot : dot + 1);
        return null;
    }

    public static String getExtension(File file, boolean includeDot) {
        return getExtension(file.getPath(), includeDot);
    }

    /**
     * @return without dot, "" if file has no extension.
     */
    public static String getExtension(String path) {
        return getExtension(path, false);
    }

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

}
