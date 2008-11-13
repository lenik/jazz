package net.bodz.bas.loader;

import static net.bodz.bas.types.util.ArrayOps.Strings;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.Doc;
import net.bodz.bas.a.LoadBoot;
import net.bodz.bas.a.LoadInfo;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.a.LoadInfo.LoadLib;
import net.bodz.bas.a.LoadInfo.LoadType;
import net.bodz.bas.cli.CLIConfig;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.lang.util.Reflects;
import net.bodz.bas.types.util.Empty;

/**
 * The default launcher for {@link LoadBoot}.
 * 
 * @see DefaultBooter
 */
@Deprecated
@Doc("LoadBy Launcher")
@Version( { 0, 1 })
@RcsKeywords(id = "$Id: ClassLauncher.java 29 2008-10-07 13:38:08Z lenik $")
public class LoadByLauncher {

    private static boolean checkLoader;
    private static Method  findLoadedClass;
    static {
        String _checkLoader = System
                .getProperty(CLIConfig.PROPERTY_CHECKLOADER);
        if ("1".equals(_checkLoader))
            checkLoader = true;
        if (checkLoader) {
            findLoadedClass = Reflects.getDeclaredMethod(ClassLoader.class,
                    "findLoadedClass", String.class);
            findLoadedClass.setAccessible(true);
        }
    }

    @Deprecated
    static void checkLoader(String className, ClassLoader loader)
            throws ClassNotFoundException {
        if (checkLoader) {
            Class<?> loaded = (Class<?>) Reflects.invoke(loader,
                    findLoadedClass, className);
            if (loaded != null)
                System.err.println("Class " + className
                        + " is already loaded by " + loaded.getClassLoader());
        }
        Class<?> clazz = loader.loadClass(className);
        if (checkLoader) {
            if (clazz.getClassLoader() != loader)
                System.err.println("Class " + className
                        + " is loaded by another " //
                        + clazz.getClassLoader());
        }
    }

    static void getLoadArgs(LoadInfo li, List<String> args) {
        if (li == null)
            return;
        LoadInfo prev = li.getPrev();
        if (prev != null)
            getLoadArgs(prev, args);
        Class<? extends ClassLoader> loaderClass = li.getLoaderClass();
        if (loaderClass == null)
            return;
        args.add("-l");
        args.add(loaderClass.getName());
        int c = li.getItemCount();
        switch (li.getLoadTime()) {
        case LoadInfo.BOOT:
            for (int i = 0; i < c; i++) {
                LoadLib item = (LoadLib) li.getItem(i);
                args.add("-p");
                args.add(item.libspec);
            }
            break;
        case LoadInfo.BOOT2:
            for (int i = 0; i < c; i++) {
                LoadType item = (LoadType) li.getItem(i);
                args.add("-c");
                args.add(item.className);
            }
        }
    }

    public static String[] getLoadArgs(Class<?> clazz) {
        List<String> args = new ArrayList<String>();
        LoadInfo loadInfo = LoadInfo.get(clazz);
        getLoadArgs(loadInfo, args);
        return args.toArray(Empty.Strings);
    }

    static void bootFix(Class<?> clazz) {
        // System.out.println("Class Loaded: " + clazz);
        LoadInfo loadInfo = LoadInfo.get(clazz);
        if (loadInfo != null) {
            // if boot classpath wasn't set, this is useful.
            loadInfo.load(LoadInfo.BOOT);
        }
    }

    public static Class<?> load(String className, URL... bootUrls)
            throws LoadException, ClassNotFoundException {
        ClassLoader parentLoader = Caller.getCallerClassLoader();
        URLClassLoader loader0 = new URLClassLoader(bootUrls, parentLoader);
        Class<?> clazz0;
        try {
            clazz0 = Class.forName(className, false, loader0);
        } catch (NoClassDefFoundError e) {
            Classpath.dumpURLs(loader0, CharOuts.stderr);
            throw e;
        }
        // add to system classpath, emulate the boot CLASSPATH
        bootFix(clazz0);

        // load types and evals
        LoadInfo loadInfo = LoadInfo.get(clazz0);
        if (loadInfo != null)
            loadInfo.load(LoadInfo.BOOT2 | LoadInfo.USER);

        // prepare the dedicated loader
        String[] loadArgs = getLoadArgs(clazz0);
        ClassLoader loader1 = parse(parentLoader, loadArgs).loader;
        Class<?> clazz1 = Class.forName(className, true, loader1);
        return clazz1;
    }

    static class ParseResult {
        int         parsedArgs;
        ClassLoader loader;
    }

    public static ParseResult parse(String... args) throws LoadException {
        return parse(ClassLoader.getSystemClassLoader(), args);
    }

    public static ParseResult parse(ClassLoader parentLoader, String... args)
            throws LoadException {
        ClassLoader loader = parentLoader;
        URLClassLoader ucl = null;
        if (loader instanceof URLClassLoader)
            ucl = (URLClassLoader) loader;

        int index = 0;
        P: for (; index < args.length; index++) {
            String arg = args[index];
            if (arg.charAt(0) != '-')
                break;
            if ("-".equals(arg)) {
                index++;
                break;
            }
            if (arg.length() == 2)
                switch (arg.charAt(1)) {
                case 'l':
                    loader = ucl = LoadUtil.getUcl(args[++index], loader);
                    break;
                case 'p':
                    if (ucl == null)
                        loader = ucl = new URLClassLoader(new URL[0], loader);
                    String libspec = args[++index];
                    URL url = LoadUtil.findLib(libspec);
                    Classpath.addURL(loader, url);
                    break;
                case 'c':
                    String className = args[++index];
                    try {
                        Class.forName(className, true, loader);
                    } catch (ClassNotFoundException e) {
                        throw new LoadException(e);
                    }
                    break;
                default:
                    break P;
                }
            else
                break P;
        }
        ParseResult result = new ParseResult();
        result.parsedArgs = index;
        result.loader = loader;
        return result;
    }

    public static void main(String[] args) throws Throwable {
        ParseResult result = parse(args);
        if (result.parsedArgs == args.length)
            throw new IllegalUsageError("MAINCLASS isn't specified.");
        String className = args[result.parsedArgs];
        args = Strings.copyOfRange(args, result.parsedArgs, args.length);
        ClassLoader loader = null; // 
        LoadUtil.execMain(loader, className, args);
    }

}
