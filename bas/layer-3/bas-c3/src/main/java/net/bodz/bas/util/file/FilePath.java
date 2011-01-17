package net.bodz.bas.util.file;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

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
     * @param origPath
     *            The start directory where <code>path</code> is originated. Should be in canonical
     *            form, with no trailing slash (/) unless it's the root.
     * @return <code>null</code> if the given <code>path</code> isn't with-in the
     *         <code>origPath</code>, or the relative representation of <code>path</code> relative
     *         to <code>origPath</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativeName(String path, String origPath, char fileSeparator) {
        int len = origPath.length();
        while (origPath.charAt(len - 1) == fileSeparator)
            origPath = origPath.substring(0, --len);
        if (!path.startsWith(origPath))
            return null;
        if (path.length() == len)
            return ".";
        if (path.charAt(len) == fileSeparator) {
            String relative = path.substring(len + 1);
            if (relative.isEmpty())
                relative = ".";
            return relative;
        }
        return null;
    }

    /**
     * @return <code>null</code> if the given <code>path</code> isn't with-in the
     *         <code>origPath</code>, or the relative representation of <code>path</code> relative
     *         to <code>origPath</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativeName(String path, String origPath) {
        return getRelativeName(path, origPath, fileSeparator);
    }

    /**
     * @return <code>null</code> if the given <code>file</code> isn't with-in the
     *         <code>origFile</code>, or the relative representation of <code>file</code> relative
     *         to <code>origPath</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativeName(File file, File origFile) {
        String path = canoniOf(file).getPath();
        String origPath = canoniOf(origFile).getPath();
        return getRelativeName(path, origPath, fileSeparator);
    }

    /**
     * @return The relative URL string, keep encoded.<code>null</code> if the given <code>url</code>
     *         isn't with-in the <code>origUrl</code>, or the relative representation of
     *         <code>url</code> relative to <code>origUrl</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativeName(URL url, URL origUrl) {
        String relativeName = getRelativeName(url.getPath(), origUrl.getPath(), '/');
        return relativeName;
    }

    /**
     * @return The relative URL string, keep encoded.<code>null</code> if the given <code>uri</code>
     *         isn't with-in the <code>origUri</code>, or the relative representation of
     *         <code>uri</code> relative to <code>origUri</code>.
     * @throws NullPointerException
     *             If any parameter is <code>null</code>.
     */
    public static String getRelativeName(URI uri, URI origUri) {
        return getRelativeName(uri.getPath(), origUri.getPath(), '/');
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
     * @throws NullPointerException
     *             If <code>path</code> is <code>null</code>.
     */
    public static NameExtension splitExtension(String path) {
        int dot = path.lastIndexOf('.');
        if (dot == -1)
            return new NameExtension(path, null);
        return new NameExtension(path.substring(0, dot), path.substring(dot + 1));
    }

}
