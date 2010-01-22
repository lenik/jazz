package net.bodz.bas.files;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.exceptions.IllegalArgumentTypeException;
import net.bodz.bas.exceptions.UnexpectedException;

/**
 * @test {@link FilePathTest}
 */
public class FilePath {

    private static String slash;
    static {
        slash = System.getProperty("file.separator");
        if (slash == null)
            slash = "/";
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

    /**
     * @see CWD#get(String)
     */
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

    public static File canoniOf(Object o) {
        if (o == null)
            return null;
        if (o instanceof File)
            return canoniOf((File) o);
        if (o instanceof String)
            return canoniOf((String) o);
        if (o instanceof URI)
            return canoniOf((URI) o);
        if (o instanceof URL)
            return canoniOf((URL) o);
        throw new IllegalArgumentTypeException(o);
    }

    public static File canoniOf(Object parent, String child) {
        File _parent = canoniOf(parent);
        return canoniOf(new File(_parent, child));
    }

    public static String getRelativeName(File file, File start) {
        if (start == null)
            throw new NullPointerException("start");
        file = FilePath.canoniOf(file);
        start = FilePath.canoniOf(start);
        List<String> tails = new ArrayList<String>();
        for (File look = file;; look = look.getParentFile()) {
            if (look == null)
                throw new UnexpectedException(String.format("File %s isn\'t in the start dir %s", file, start));
            if (look.equals(start))
                break;
            tails.add(look.getName());
        }
        StringBuffer buffer = null;
        for (int i = tails.size() - 1; i >= 0; i--) {
            if (buffer == null)
                buffer = new StringBuffer(tails.size() * 16);
            else
                buffer.append(slash);
            buffer.append(tails.get(i));
        }
        if (buffer == null)
            return "";
        return buffer.toString();
    }

    public static File getAbsoluteFile(File start, String relativeName) {
        if (relativeName == null || relativeName.isEmpty())
            return start;
        return FilePath.canoniOf(start, relativeName);
    }

}
