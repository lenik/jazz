package net.bodz.bas.loader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import sun.dyn.empty.Empty;

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
        return new URL[] { Files.getURL(libfile) };
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
        return urls.toArray(Empty.URLs);
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
            File start = Files.canoniOf("."); 
            File base = EclipseProject.findProjectBase(start);
            if (base == null)
                throw new IllegalArgumentException("can\'t find project base: " 
                        + start);
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

    public static void execMain(ClassLoader realLoader, String className, String... args) throws Throwable {
        Class<?> clazz = Class.forName(className, true, realLoader);
        execMain(clazz, args);
    }

    public static void execMain(Class<?> clazz, String... args) throws Throwable {
        Method mainf = clazz.getMethod("main", String[].class); 
        try {
            Control.invoke(mainf, null, (Object) args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

}
