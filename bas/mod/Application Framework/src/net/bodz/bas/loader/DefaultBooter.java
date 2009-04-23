package net.bodz.bas.loader;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.a.BootInfo;
import net.bodz.bas.a.BootProc;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.ControlBreak;
import net.bodz.bas.lang.ControlContinue;
import net.bodz.bas.lang.ControlExit;
import net.bodz.bas.lang.Pred1;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.nls.AppNLS;
import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Empty;

/**
 * The default booter for {@link BootInfo}.
 */
public class DefaultBooter {

    private static CharOut out          = CharOuts.stderr;

    static boolean         LOADFIX_DUMP = false;

    public static Class<?> loadFix(ClassLoader initSysLoader, String className, URL... userlibs)
            throws LoadException {
        ClassLoader bootSysLoader;
        if (userlibs == null)
            bootSysLoader = initSysLoader;
        else
            bootSysLoader = TempClassLoader.get(userlibs, initSysLoader);
        if (LOADFIX_DUMP)
            UCL.dump(bootSysLoader, CharOuts.stderr);

        Class<?> class0 = null;
        // can found by bootSysLoader?
        try {
            class0 = Class.forName(className, false, bootSysLoader);
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

    public static Class<?> load(String className, URL... userlibs) throws LoadException {
        ClassLoader sysLoader = Caller.getCallerClassLoader();
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

        if (LOAD_DUMP)
            UCL.dump(realLoader, out);
        Class<?> class1;
        try {
            class1 = Class.forName(className, false, realLoader);
        } catch (ClassNotFoundException e) {
            throw new LoadException(e);
        }
        ClassLoader userLoader = class1.getClassLoader();
        if (userLoader != realLoader) {
            out.println(AppNLS.getString("DefaultBooter.warnLoaderCut")); //$NON-NLS-1$
            ClassLoader l = realLoader;
            while (l != null && l != userLoader) {
                out.println(AppNLS.getString("DefaultBooter.mayLose") + l); //$NON-NLS-1$
                if (l instanceof URLClassLoader) {
                    URLClassLoader ucl = (URLClassLoader) l;
                    for (URL url : ucl.getURLs())
                        out.println("    " + url); //$NON-NLS-1$
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

    static class Once extends Pred1<Throwable> {
        @Override
        public boolean test(Throwable o) {
            return false;
        }
    }

    static class Repeat extends Pred1<Throwable> {
        @Override
        public boolean test(Throwable o) {
            return true;
        }
    }

    static class Count extends Pred1<Throwable> {
        int     count;
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
    public static void main(String[] args) throws LoadException, Throwable {
        int index = 0;
        List<URL> userlibs = new ArrayList<URL>();
        Pred1<Throwable> loopPred = new Once();
        A: for (; index < args.length; index++) {
            String arg = args[index];
            if (arg.startsWith("-")) { //$NON-NLS-1$
                if (arg.length() != 2)
                    break;
                if ("--".equals(arg)) { //$NON-NLS-1$
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
                                return "1".equals(val); //$NON-NLS-1$
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
            throw new IllegalArgumentException(AppNLS.getString("DefaultBooter.noMainClass")); //$NON-NLS-1$
        String className = args[index++];
        args = Arrays2.copyOf(args, index, args.length - index);

        // reload and exec
        URL[] urls = userlibs.toArray(Empty.URLs);
        Class<?> clazz = load(className, urls);
        while (true) {
            try {
                LoadUtil.execMain(clazz, args);
                if (loopPred.eval(null))
                    continue;
                break;
            } catch (ControlBreak b) {
                break;
            } catch (ControlContinue c) {
                continue;
            } catch (ControlExit exit) {
                System.exit(exit.getStatus());
            } catch (Throwable t) {
                if (loopPred.eval(t))
                    continue;
                throw t;
            }
        }
    }

}
