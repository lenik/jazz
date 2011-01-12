package net.bodz.bas.loader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.files.FilePath;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jdk6compat.jdk7emul.ReflectiveOperationException;
import net.bodz.bas.snm.EclipseProject;
import net.bodz.bas.snm.SJLibLoader;
import net.bodz.bas.util.exception.ParseException;

public class LoadUtil {

    private static SJLibLoader sjlibs = SJLibLoader.DEFAULT;

    /**
     * <code>jarname.jar</code> (with extension) is searched and loaded using {@link SJLibLoader}.
     * <p>
     * <code>libname</code> (without extension) are resolved using <code>libraries.ini</code> file,
     * if <code>libname</code> isn't defined in <code>libraries.ini</code> , then
     * <code>libname.jar</code> is used.
     * 
     * @return <code>null</code> if can't resolve the libspec
     */
    public static URL[] find(String libspec, boolean errorFail) {
        if (libspec.startsWith("%"))
            return findPack(libspec.substring(1));
        File libfile;
        if (libspec.contains("."))
            libfile = _findJar(libspec);
        else
            libfile = _findLib(libspec);
        if (libfile == null)
            if (errorFail)
                throw new Error("can\'t resolve lib " + libspec);
            else
                return null;
        URL url;
        try {
            url = libfile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return new URL[] { url };
    }

    /**
     * @return <code>null</code> if jar isn't exists, or libspec isn't defined.
     */
    public static URL[] find(String libspec) {
        return find(libspec, false);
    }

    public static URL[] find(String[] libspecs, boolean errorFail) {
        assert libspecs != null : "null libspecs";
        List<URL> urls = new ArrayList<URL>(libspecs.length);
        for (int i = 0; i < libspecs.length; i++) {
            String libspec = libspecs[i];
            if (libspec == null)
                throw new NullPointerException("libspecs[" + i + "]");
            URL[] specUrls = find(libspec, errorFail);
            if (specUrls == null) // according to errorFail.
                continue;
            for (URL url : specUrls)
                urls.add(url);
        }
        return urls.toArray(new URL[0]);
    }

    /**
     * @return URL array with each component set to <code>null</code> if corresponding jar isn't
     *         exists, or libspec isn't defined.
     */
    public static URL[] find(String[] libspecs) {
        return find(libspecs, false);
    }

    static URL[] findPack(String name) {
        if ("project".equals(name)) {
            File start = FilePath.canoniOf(".");
            File base = EclipseProject.findProjectBase(start);
            if (base == null)
                throw new IllegalArgumentException("can\'t find project base: " + start);
            try {
                return new EclipseProject(base).getURLClasspath();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else
            throw new IllegalArgumentException("unsupported pack name: " + name);
    }

    static File _findLib(String name) {
        return sjlibs.findLibraryFile(name);
    }

    static File _findJar(String jar) {
        return sjlibs.findFile(jar);
    }

    public static void execMain(ClassLoader realLoader, String className, String... args)
            throws Throwable {
        Class<?> clazz = Class.forName(className, true, realLoader);
        execMain(clazz, args);
    }

    public static void execMain(Class<?> clazz, String... args)
            throws ReflectiveOperationException {
        Method mainf = Jdk7Reflect.getMethod(clazz, "main", String[].class);
        Jdk7Reflect.invoke(mainf, null, (Object) args);
    }

}
