package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.a.BootProc;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.log.LogOut;
import net.bodz.bas.log.LogOuts;
import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Empty;

/**
 * The default booter for {@link BootInfo}.
 */
public class DefaultBooter {

    private static LogOut out = LogOuts.stderr;

    public static Class<?> load(String className, URL... userlibs)
            throws LoadException {
        ClassLoader parent = Caller.getCallerClassLoader();
        return load(parent, className, userlibs);
    }

    /**
     * @param userlibs
     *            use {@link BootInfo#userlibs()}
     */
    public static Class<?> load(ClassLoader sysLoader, String className,
            URL... userlibs) throws LoadException {
        // get class0
        // WORKAROUND: let the boot bit more smooth.
        ClassLoader bootLoader = TempClassLoader.get(userlibs, sysLoader);
        Class<?> class0;
        try {
            class0 = bootLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        }

        // prepare real-loader
        BootProc bootProc = BootProc.get(class0);
        ClassLoader realLoader = sysLoader;
        if (bootProc != null)
            realLoader = bootProc.configLoader(sysLoader);

        // add userlibs to the last
        // these libs are specified by Booter-Main(-l, USERLIB, ..., FQCN, ...)
        // OPT..
        realLoader = UCL.addOrCreate(realLoader, userlibs);

        if (false)
            UCL.dump(realLoader, out);
        Class<?> class1;
        try {
            class1 = Class.forName(className, false, realLoader);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        }
        ClassLoader userLoader = class1.getClassLoader();
        if (userLoader != realLoader) {
            out.println("Warning: class loader cut: ");
            ClassLoader l = realLoader;
            while (l != null && l != userLoader) {
                out.println("  may lose: " + l);
                if (l instanceof URLClassLoader) {
                    URLClassLoader ucl = (URLClassLoader) l;
                    for (URL url : ucl.getURLs())
                        out.println("    " + url);
                }
                l = l.getParent();
            }
        }
        try {
            return Class.forName(className, true, realLoader);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        } catch (NoClassDefFoundError e) {
            throw new LoadException(e);
        }
    }

    /**
     * Booter(-l, USERLIB, ..., --, MAINCLASS, ARGS)
     * 
     * @throws Throwable
     */
    public static void main(String[] args) throws LoadException, Throwable {
        int index = 0;
        List<URL> userlibs = new ArrayList<URL>();
        A: for (; index < args.length; index++) {
            String arg = args[index];
            if (arg.startsWith("-")) {
                if (arg.length() != 2)
                    break;
                if ("--".equals(args)) {
                    index++;
                    break;
                }
                switch (arg.charAt(1)) {
                case 'l':
                    String libspec = args[++index];
                    URL url = LoadUtil.findLib(libspec);
                    userlibs.add(url);
                    break;
                default:
                    break A;
                }
            }
        }
        if (index == args.length)
            throw new IllegalArgumentException(
                    "Main class name isn't specified.");
        String className = args[++index];
        args = Arrays2.copyOf(args, index, args.length - index);

        // reload and exec
        URL[] urls = userlibs.toArray(Empty.URLs);
        Class<?> clazz = load(className, urls);
        LoadUtil.execMain(clazz, args);
    }

}
