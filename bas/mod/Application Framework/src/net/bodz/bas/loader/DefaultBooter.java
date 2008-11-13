package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.a.BootProc;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Empty;

/**
 * The default booter for {@link BootInfo}.
 */
public class DefaultBooter {

    public static Class<?> load(String className, URL... bootlibs)
            throws LoadException {
        ClassLoader parent = Caller.getCallerClassLoader();
        return load(parent, className, bootlibs);
    }

    /**
     * @param bootlibs
     *            use {@link BootInfo#userlibs()}
     */
    public static Class<?> load(ClassLoader parent, String className,
            URL... bootlibs) throws LoadException {
        // get class0
        ClassLoader bootLoader;
        if (bootlibs == null)// || bootlibs.length == 0)
            bootLoader = parent;
        else
            bootLoader = new URLClassLoader(bootlibs, parent);
        Class<?> class0;
        try {
            class0 = bootLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        }

        // prepare real-loader
        BootProc bootProc = BootProc.get(class0);
        ClassLoader realLoader = bootProc.configLoader(parent);
        try {
            return Class.forName(className, true, realLoader);
        } catch (ClassNotFoundException e) {
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
        args = Arrays2.copyOf(args, index, args.length);

        // reload and exec
        URL[] urls = userlibs.toArray(Empty.URLs);
        Class<?> clazz = load(className, urls);
        LoadUtil.execMain(clazz, args);
    }

}
