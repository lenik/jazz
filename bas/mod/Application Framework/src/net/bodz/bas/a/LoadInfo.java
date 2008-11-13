package net.bodz.bas.a;

import java.net.URL;

import net.bodz.bas.cli.CLIConfig;
import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageError;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Types;

/**
 * @see BootProc
 */
@Deprecated
public class LoadInfo {

    private final LoadInfo             prev;

    private final Class<?>             declaredClass;

    final Class<? extends ClassLoader> loaderClass;
    private ClassLoader                loader;

    /** @see LoadBoot */
    public static final int            BOOT    = 1;
    /** @see LoadBy */
    public static final int            BOOT2   = 2;
    /** @see LoadUser */
    public static final int            USER    = 4;
    /** @see LoadDelayed */
    public static final int            DELAYED = 8;
    private final int                  loadTime;

    private final LoadItem[]           items;

    static Class<? extends ClassLoader> parseLoader(
            Class<? extends ClassLoader> loaderClass) {
        if (loaderClass == ClassLoader.class)
            return null;
        return loaderClass;
    }

    LoadInfo(LoadInfo prev, Class<?> declaredClass, LoadBoot loadBoot) {
        this.prev = prev;
        this.loadTime = BOOT;
        this.declaredClass = declaredClass;
        this.loaderClass = parseLoader(loadBoot.loader());
        this.items = getLibItems(loadBoot.value());
    }

    LoadInfo(LoadInfo prev, Class<?> declaredClass, LoadBy loadBy) {
        this.prev = prev;
        this.loadTime = BOOT2;
        this.declaredClass = declaredClass;
        Class<? extends ClassLoader> clazz = loadBy.value();
        if (clazz == ClassLoader.class) {
            this.loaderClass = null;
            this.items = new LoadItem[0];
        } else {
            this.loaderClass = clazz;
            this.items = new LoadItem[1];
            this.items[0] = new LoadType(declaredClass.getName());
        }
    }

    LoadInfo(LoadInfo prev, Class<?> declaredClass, LoadUser loadUser) {
        this.prev = prev;
        this.loadTime = USER;
        this.declaredClass = declaredClass;
        this.loaderClass = null;
        LoadItem[] libItems = getLibItems(loadUser.value());
        LoadItem[] loadTypes = getLoadTypes(loadUser.types());
        LoadItem[] evalItems = getEvalItems(loadUser.eval());
        this.items = Arrays2.concat(libItems, loadTypes, evalItems);
    }

    LoadInfo(LoadInfo prev, Class<?> declaredClass, LoadDelayed loadDelayed) {
        this.prev = prev;
        this.loadTime = DELAYED;
        this.declaredClass = declaredClass;
        this.loaderClass = null;
        LoadItem[] libItems = getLibItems(loadDelayed.value());
        LoadItem[] loadTypes = getLoadTypes(loadDelayed.types());
        LoadItem[] evalItems = getEvalItems(loadDelayed.eval());
        this.items = Arrays2.concat(libItems, loadTypes, evalItems);
    }

    static LoadInfo shrink(LoadInfo l) {
        if (l == null)
            return l;
        if (l.items == null || l.items.length == 0)
            return l.prev;
        return l;
    }

    public static LoadInfo get(Class<?> clazz, LoadInfo prev) {
        LoadInfo last = prev;
        // String className = clazz.getName();
        LoadBoot loadBoot = clazz.getAnnotation(LoadBoot.class);
        if (loadBoot != null)
            last = shrink(new LoadInfo(last, clazz, loadBoot));
        LoadBy loadBy = clazz.getAnnotation(LoadBy.class);
        if (loadBy != null)
            last = shrink(new LoadInfo(last, clazz, loadBy));
        LoadUser loadUser = clazz.getAnnotation(LoadUser.class);
        if (loadUser != null)
            last = shrink(new LoadInfo(last, clazz, loadUser));
        LoadDelayed loadDelayed = clazz.getAnnotation(LoadDelayed.class);
        if (loadDelayed != null)
            last = shrink(new LoadInfo(last, clazz, loadDelayed));
        return last;
    }

    private static ClassLocal<LoadInfo> local;
    static {
        local = new ClassLocal<LoadInfo>();
    }

    public static LoadInfo get(Class<?> clazz) {
        if (local.containsKey(clazz))
            return local.get(clazz);
        LoadInfo prev = null;
        Class<?> sup = clazz.getSuperclass();
        if (sup != null)
            prev = get(sup);
        LoadInfo loadInfo = get(clazz, prev);
        local.put(clazz, loadInfo);
        return loadInfo;
    }

    public LoadInfo getPrev() {
        return prev;
    }

    public Class<?> getDeclaredClass() {
        return declaredClass;
    }

    public Class<? extends ClassLoader> getLoaderClass() {
        return loaderClass;
    }

    public ClassLoader getLoader() {
        if (loader == null) {
            if (loaderClass != null)
                try {
                    loader = Types.getClassInstance(loaderClass);
                } catch (CreateException e) {
                    throw new IllegalUsageError(e);
                }
        }
        return loader;
    }

    public int getLoadTime() {
        return loadTime;
    }

    public abstract class LoadItem {

        public static final int LIB      = 0;
        public static final int EVAL     = 1;
        public static final int LOADTYPE = 2;

        public final int        type;
        protected boolean       loaded;
        protected Throwable     loadErr;

        public LoadItem(int type) {
            this.type = type;
        }

        public boolean isLoaded() {
            return loaded;
        }

        public Throwable getLoadErr() {
            return loadErr;
        }

        public synchronized void load() {
            if (loaded)
                return;
            try {
                _load();
                loadErr = null;
                loaded = true;
            } catch (Throwable e) {
                loadErr = e;
            }
        }

        abstract void _load() throws Exception;

        @Override
        public String toString() {
            Buffer buf = new Buffer(40);
            switch (type) {
            case LIB:
                buf.print("LIB");
                break;
            case EVAL:
                buf.print("EVAL");
                break;
            case LOADTYPE:
                buf.print("CLASS");
                break;
            default:
                assert false;
            }
            buf.print(" ");
            desc(buf);
            if (loaded)
                buf.print(": loaded");
            else if (loadErr != null)
                buf.print(": err=" + loadErr);
            return buf.toString();
        }

        abstract void desc(CharOut buf);

    }

    public class LoadLib extends LoadItem {

        public final String condition;
        public final String libspec;

        public LoadLib(String arg) {
            super(LIB);
            int pipe = arg.indexOf('|');
            if (pipe == -1) {
                condition = "fortype %" + arg;
                libspec = arg;
            } else {
                condition = arg.substring(0, pipe).trim();
                libspec = arg.substring(pipe + 1).trim();
            }
        }

        @Override
        void _load() throws Exception {
            if ((Boolean) CLIConfig.libEval(condition))
                return;
            URL url = LoadUtil.findLib(libspec);
            Classpath.addURL(url); // throws IOException
        }

        @Override
        void desc(CharOut buf) {
            if (condition != null)
                buf.print("<" + condition + "> ");
            buf.print(libspec);
        }

    }

    public class LoadEval extends LoadItem {

        public final String condition;
        public final String expr;

        public LoadEval(String arg) {
            super(EVAL);
            int pipe = arg.indexOf('|');
            if (pipe == -1) {
                condition = null;
                expr = arg;
            } else {
                condition = arg.substring(0, pipe).trim();
                expr = arg.substring(pipe + 1).trim();
            }
        }

        @Override
        void _load() throws Exception {
            CLIConfig.libEval(expr);
        }

        @Override
        void desc(CharOut buf) {
            if (condition != null)
                buf.print("<" + condition + "> ");
            buf.print(expr);
        }

    }

    public class LoadType extends LoadItem {

        public final String className;

        public LoadType(String className) {
            super(LOADTYPE);
            this.className = className;
        }

        @Override
        void _load() throws Exception {
            ClassLoader loader = getLoader();
            if (loader == null)
                Class.forName(className);
            else
                Class.forName(className, true, loader);
        }

        @Override
        void desc(CharOut out) {
            ClassLoader loader = getLoader();
            if (loader == null)
                out.print("load " + className + " using system loader");
            else
                out.print("load " + className + " using " + loader);
        }

    }

    LoadItem[] getLibItems(String[] libs) {
        LoadItem[] items = new LoadItem[libs.length];
        for (int i = 0; i < libs.length; i++)
            items[i] = new LoadLib(libs[i]);
        return items;
    }

    LoadItem[] getLoadTypes(Class<?>[] types) {
        LoadItem[] items = new LoadItem[types.length];
        for (int i = 0; i < types.length; i++)
            items[i] = new LoadType(types[i].getName());
        return items;
    }

    LoadItem[] getEvalItems(String[] evals) {
        LoadItem[] items = new LoadItem[evals.length];
        for (int i = 0; i < evals.length; i++)
            items[i] = new LoadEval(evals[i]);
        return items;
    }

    public int getItemCount() {
        return items.length;
    }

    public LoadItem getItem(int index) {
        return items[index];
    }

    public void load(int timeBits) {
        if (prev != null)
            prev.load(timeBits);
        if ((timeBits & loadTime) != 0)
            for (LoadItem item : items)
                item.load();
    }

    void dump(CharOut out, String prefix) {
        out.print(declaredClass + ": ");
        switch (loadTime) {
        case BOOT:
            out.print("BOOT");
            break;
        case BOOT2:
            out.print("BOOT2");
            break;
        case USER:
            out.print("USER");
            break;
        case DELAYED:
            out.print("DELAYED");
            break;
        }
        out.println(" items");
        for (LoadItem item : items)
            out.println(prefix + "    " + item);
        if (prev != null) {
            out.print(prefix + "  prev @" + System.identityHashCode(prev)
                    + ": ");
            prev.dump(out, prefix);
        }
    }

    public void dump(CharOut out) {
        dump(out, "");
    }

    @Override
    public String toString() {
        Buffer buf = new Buffer(10000);
        dump(buf);
        return buf.toString();
    }

}
