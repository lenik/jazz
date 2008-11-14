package net.bodz.bas.loader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.snm.SJLibLoader;

public class LoadUtil {

    @Deprecated
    public static URLClassLoader getUcl(ClassLoader cl) {
        if (cl == null)
            throw new NullPointerException("null class loader");
        // return getUcl(ClassLoader.getSystemClassLoader());
        if (cl instanceof URLClassLoader)
            return (URLClassLoader) cl;
        return new DynamicUCL(new URL[0], cl);
    }

    @Deprecated
    public static URLClassLoader getUcl(String loaderClassName,
            ClassLoader parent) {
        Class<?> loaderClass;
        try {
            loaderClass = Class.forName(loaderClassName);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("bad loader class name: "
                    + loaderClassName, e);
        }
        if (!ClassLoader.class.isAssignableFrom(loaderClass))
            throw new IllegalArgumentException("Not a ClassLoader: "
                    + loaderClassName);
        Constructor<?> uclCtor;
        try {
            uclCtor = loaderClass
                    .getConstructor(URL[].class, ClassLoader.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "can't ctor using (URL[], ClassLoader)", e);
        }
        Object loader = Reflects.newInstance(uclCtor, new URL[0], parent);
        return (URLClassLoader) loader;
    }

    private static SJLibLoader sjlibs = SJLibLoader.DEFAULT;

    /**
     * <code>jarname.jar</code> (with extension) is searched and loaded using
     * {@link SJLibLoader}.
     * <p>
     * <code>libname</code> (without extension) are resolved using
     * <code>libraries.ini</code> file, if <code>libname</code> isn't defined in
     * <code>libraries.ini</code> , then <code>libname.jar</code> is used.
     * 
     */
    public static URL findLib(String libspec, boolean errorFail) {
        File libfile;
        if (libspec.contains("."))
            libfile = resolveJar(libspec);
        else
            libfile = resolveLib(libspec);
        if (libfile == null)
            if (errorFail)
                throw new Error("can't resolve lib " + libspec);
            else
                return null;
        try {
            return libfile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new IllegalUsageError("incorrect lib " + libspec + ": "
                    + libfile, e);
        }
    }

    /**
     * @return <code>null</code> if jar isn't exists, or libspec isn't defined.
     */
    public static URL findLib(String libspec) {
        return findLib(libspec, false);
    }

    public static URL[] findLibs(String[] libspecs, boolean errorFail) {
        URL[] urls = new URL[libspecs.length];
        for (int i = 0; i < libspecs.length; i++)
            urls[i] = findLib(libspecs[i], errorFail);
        return urls;
    }

    /**
     * @return URL array with each component set to <code>null</code> if
     *         corresponding jar isn't exists, or libspec isn't defined.
     */
    public static URL[] findLibs(String[] libspecs) {
        return findLibs(libspecs, false);
    }

    static File resolveLib(String name) {
        return sjlibs.findLibraryFile(name);
    }

    static File resolveJar(String jar) {
        return sjlibs.findFile(jar);
    }

    public static void execMain(ClassLoader realLoader, String className,
            String... args) throws Throwable {
        Class<?> clazz = Class.forName(className, true, realLoader);
        execMain(clazz, args);
    }

    public static void execMain(Class<?> clazz, String... args)
            throws Throwable {
        Method mainf = clazz.getMethod("main", String[].class);
        try {
            Control.invoke(mainf, null, (Object) args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

}
