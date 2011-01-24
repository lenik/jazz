package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.closure.alt.Pred1;
import net.bodz.bas.jdk6compat.jdk7emul.ClassNotFoundException;
import net.bodz.bas.jdk6compat.jdk7emul.Jdk7Reflect;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.loader.boot.BootInfo;
import net.bodz.bas.loader.boot.BootProc;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;

/**
 * The default booter for {@link BootInfo}.
 */
public class DefaultBooter {

    private static IPrintOut out = Stdio.cerr;

    static boolean LOADFIX_DUMP = false;

    public static Class<?> loadFix(ClassLoader initSysLoader, String className, URL... userlibs)
            throws LoadException {
        ClassLoader bootSysLoader;
        if (userlibs == null)
            bootSysLoader = initSysLoader;
        else
            bootSysLoader = TempClassLoader.get(userlibs, initSysLoader);
        if (LOADFIX_DUMP)
            UCL.dump(bootSysLoader, Stdio.cerr);

        Class<?> class0 = null;
        // can found by bootSysLoader?
        try {
            class0 = Jdk7Reflect.forName(className, false, bootSysLoader);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        }

        BootProc bootProc = BootProc.get(class0);
        if (bootProc != null) {
            String[] sysLibs = bootProc.getSysLibs();
            URL[] urls = LoadUtil.find(sysLibs);
            Classpath.addURL(initSysLoader, urls);
        }

        // realSysLoader -> configLoader
        return load(initSysLoader, className, userlibs);
    }

    public static Class<?> load(String className, URL... userlibs)
            throws LoadException {
        ClassLoader sysLoader = Caller.getCallerClassLoader(0);
        return load(sysLoader, className, userlibs);
    }

    static boolean LOAD_DUMP = false;

    /**
     * @param userlibs
     *            use {@link BootInfo#userlibs()}
     */
    public static Class<?> load(ClassLoader sysLoader, String className, URL... userlibs)
            throws LoadException {
        // get class0
        // WORKAROUND: let the boot bit more smooth.
        ClassLoader bootLoader = TempClassLoader.get(userlibs, sysLoader);
        Class<?> class0;
        try {
            class0 = Jdk7Reflect.loadClass(bootLoader, className);
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

        if (LOAD_DUMP)
            UCL.dump(realLoader, out);
        Class<?> class1;
        try {
            class1 = Jdk7Reflect.forName(className, false, realLoader);
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
            return Jdk7Reflect.forName(className, true, realLoader);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        } catch (NoClassDefFoundError e) {
            throw new LoadException(e);
        }
    }

    static class Once
            extends Pred1<Throwable> {
        @Override
        public boolean test(Throwable o) {
            return false;
        }
    }

    static class Repeat
            extends Pred1<Throwable> {
        @Override
        public boolean test(Throwable o) {
            return true;
        }
    }

    static class Count
            extends Pred1<Throwable> {
        int count;
        boolean errorContinue;

        public Count(int count, boolean errorContinue) {
            this.count = count;
            this.errorContinue = errorContinue;
        }

        @Override
        public boolean test(Throwable o) {
            if (o != null && !errorContinue)
                return false;
            return --count > 0;
        }
    }

    /**
     * Booter(-l, USERLIB, ..., --, MAINCLASS, ARGS)
     * 
     * @throws Throwable
     */
    public static void main(String[] args)
            throws LoadException, Throwable {
        int index = 0;
        List<URL> userlibs = new ArrayList<URL>();
        Pred1<Throwable> loopPred = new Once();
        A: for (; index < args.length; index++) {
            String arg = args[index];
            if (arg.startsWith("-")) {
                if (arg.length() != 2)
                    break;
                if ("--".equals(arg)) {
                    index++;
                    break;
                }
                switch (arg.charAt(1)) {
                case 'l': // userlib
                    String libspec = args[++index];
                    for (URL url : LoadUtil.find(libspec))
                        userlibs.add(url);
                    break;
                case 'r': // repeat
                    String termCond = args[++index];
                    try {
                        final int count = Integer.parseInt(termCond);
                        if (count == 0)
                            loopPred = new Repeat();
                        else if (count > 0)
                            loopPred = new Count(count, false);
                        else
                            loopPred = new Count(-count, true);
                    } catch (NumberFormatException e) {
                        final String predProp = termCond;
                        loopPred = new Pred1<Throwable>() {
                            @Override
                            public boolean test(Throwable o) {
                                String val = System.getProperty(predProp);
                                return "1".equals(val);
                            }
                        };
                    }
                    break;
                default:
                    break A;
                }
            }
        }
        if (index == args.length)
            throw new IllegalArgumentException("Main class name isn\'t specified.");
        String className = args[index++];
        args = Arrays.copyOfRange(args, index, args.length - index);

        // reload and exec
        URL[] urls = userlibs.toArray(new URL[0]);
        Class<?> clazz = load(className, urls);
        LoadUtil.execMain(clazz, args);
    }

}
