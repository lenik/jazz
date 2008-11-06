package net.bodz.bas.loader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.LoadBy;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli._RunInfo;
import net.bodz.bas.lang.Control;
import net.bodz.bas.lang.util.Reflects;

/**
 * The default launcher for {@link LoadBy}.
 */
@Doc("LoadBy Launcher")
@Version( { 0, 1 })
@RcsKeywords(id = "$Id: ClassLauncher.java 29 2008-10-07 13:38:08Z lenik $")
public class LoadByLauncher {

    private static boolean checkLoaded = true;
    private static Method  findLoadedClass;
    static {
        if (checkLoaded) {
            findLoadedClass = Reflects.getDeclaredMethod(ClassLoader.class,
                    "findLoadedClass", String.class);
            findLoadedClass.setAccessible(true);
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Throwable {
        if (args.length < 2)
            throw new Error("Launcher CLASS-LOADER MAIN-CLASS [ARGUMENTS]");
        String loaderClassName = args[0];
        String mainClassName = args[1];

        Class<ClassLoader> loaderClass = (Class<ClassLoader>) Class
                .forName(loaderClassName);
        ClassLoader loader = loaderClass.newInstance();

        if (checkLoaded) {
            Class<?> loaded = (Class<?>) Reflects.invoke(loader,
                    findLoadedClass, mainClassName);
            if (loaded != null)
                System.err.println("Class " + mainClassName
                        + " is already loaded by " + loaded.getClassLoader());
        }
        Class<?> mainClass = loader.loadClass(mainClassName);
        if (checkLoaded) {
            if (mainClass.getClassLoader() != loader)
                System.err.println("Class " + mainClassName
                        + " is loaded by another " //
                        + mainClass.getClassLoader());
        }

        // System.out.println("Class Loaded: " + mainClass);

        _RunInfo runInfo = _RunInfo.parse(mainClass);
        runInfo.loadBoot();
        runInfo.loadLibraries();
        runInfo.loadDelayed();

        Method mainf = mainClass.getMethod("main", String[].class);
        args = Arrays.copyOfRange(args, 2, args.length);
        try {
            Control.invoke(mainf, null, (Object) args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
