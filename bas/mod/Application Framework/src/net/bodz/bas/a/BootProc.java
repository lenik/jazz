package net.bodz.bas.a;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.bodz.bas.io.CharOut;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadConfig;
import net.bodz.bas.loader.LoadException;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Strings;

public class BootProc {

    private final String   description;

    private final BootProc prev;

    private String[]       syslibs;
    private String[]       userlibs;

    /**
     * <pre>
     * scl (syslibs)
     * +-- booter : scl
     *     +-- bcl (userlibs)
     *         +-- mainclass : bcl
     *             +-- @BootInfo.booter : scl
     * </pre>
     */
    private String         booterClassName;

    private ConfigParam[]  configs;

    public BootProc(String description, BootProc prev, BootInfo info) {
        this.description = description;
        this.prev = prev;

        syslibs = info.syslibs();
        userlibs = info.userlibs();

        Class<?> booter = info.booter();
        // if (booter != DefaultBooter.class)
        if (!booter.getName().equals(DefaultBooter.class.getName()))
            booterClassName = booter.getName();

        Class<? extends LoadConfig>[] configs = info.configs();
        this.configs = new ConfigParam[configs.length];
        String[] args = info.args();
        for (int i = 0; i < configs.length; i++) {
            this.configs[i] = new ConfigParam(configs[i], args);
        }
    }

    public static BootProc get(Class<?> clazz, BootProc prev) {
        Class<?> sup = clazz.getSuperclass();
        if (sup != null)
            prev = get(sup, prev);
        BootInfo info = clazz.getAnnotation(BootInfo.class);
        if (info == null)
            return prev;
        String desc = clazz.getName() + "@" + System.identityHashCode(clazz);
        return new BootProc(desc, prev, info);
    }

    public static BootProc get(Class<?> clazz) {
        return get(clazz, null);
    }

    public BootProc getPrev() {
        return prev;
    }

    void dumpSysLibs(List<String> list) {
        if (prev != null)
            prev.dumpSysLibs(list);
        for (String libspec : syslibs)
            list.add(libspec);
    }

    void dumpUserLibs(List<String> list) {
        if (prev != null)
            prev.dumpUserLibs(list);
        for (String libspec : userlibs)
            list.add(libspec);
    }

    public String[] getSysLibs() {
        List<String> buf = new ArrayList<String>();
        dumpSysLibs(buf);
        return buf.toArray(Empty.Strings);
    }

    public String[] getUserLibs() {
        List<String> buf = new ArrayList<String>();
        dumpUserLibs(buf);
        return buf.toArray(Empty.Strings);
    }

    public String getBooterClassName() {
        if (booterClassName != null)
            return booterClassName;
        if (prev != null)
            return prev.getBooterClassName();
        return DefaultBooter.class.getName();
    }

    /**
     * if no config, the userlibs can be merged into syslibs.
     */
    public boolean hasConfig() {
        if (configs != null && configs.length != 0)
            return true;
        if (prev != null)
            return prev.hasConfig();
        return false;
    }

    static class ConfigParam {
        Class<? extends LoadConfig> clazz;
        String[]                    args;
        LoadConfig                  instance;

        public ConfigParam(Class<? extends LoadConfig> clazz, String[] args) {
            this.clazz = clazz;
            this.args = args;
        }

        public LoadConfig getInstance() throws CreateException {
            if (instance == null) {
                instance = create();
                clazz = null;
                args = null;
            }
            return instance;
        }

        LoadConfig create() throws CreateException {
            if (args == null || args.length == 0)
                try {
                    return clazz.newInstance();
                } catch (InstantiationException e) {
                    // try ctor(String[])
                    return createv();
                } catch (Exception e) {
                    throw new CreateException(e);
                }
            Class<?>[] lv = new Class<?>[args.length];
            Arrays.fill(lv, String.class);
            try {
                Constructor<? extends LoadConfig> lctor = clazz
                        .getConstructor(lv);
                return lctor.newInstance((Object[]) args);
            } catch (NoSuchMethodException e) {
                return createv();
            } catch (Exception e) {
                throw new CreateException(e);
            }
        }

        LoadConfig createv() throws CreateException {
            try {
                Constructor<? extends LoadConfig> vctor = clazz
                        .getConstructor(String[].class);
                return vctor.newInstance((Object) args);
            } catch (Exception e) {
                throw new CreateException(e);
            }
        }

    }

    public ClassLoader configLoader(ClassLoader parent) throws LoadException {
        if (prev != null)
            parent = prev.configLoader(parent);
        for (ConfigParam configParam : configs) {
            try {
                LoadConfig config = configParam.getInstance();
                parent = config.getLoader(parent);
            } catch (CreateException e) {
                throw new LoadException(e);
            }
        }
        return parent;
    }

    public void load(int stageFrom, int stageTo) throws LoadException {
        if (prev != null)
            prev.load(stageFrom, stageTo);
        for (ConfigParam configParam : configs) {
            try {
                LoadConfig config = configParam.getInstance();
                config.load(stageFrom, stageTo);
            } catch (CreateException e) {
                throw new LoadException(e);
            }
        }
    }

    void dumpBootArgs(List<String> args) {
        if (prev != null)
            prev.dumpBootArgs(args);
        for (String libspec : userlibs) {
            args.add("-l");
            args.add(libspec);
        }
    }

    public ClassLoader configSysLoader() {
        ClassLoader parent = Caller.getCallerClassLoader();
        return configSysLoader(parent);
    }

    public ClassLoader configSysLoader(ClassLoader parent) {
        URL[] syslibs = LoadUtil.findLibs(getSysLibs());
        return new URLClassLoader(syslibs, parent);
    }

    public String[] getBootArgs() {
        List<String> buf = new ArrayList<String>();
        dumpBootArgs(buf);
        if (!buf.isEmpty())
            buf.add("--");
        return buf.toArray(Empty.Strings);
    }

    void dump(CharOut out) {
        out.println("BootProc: " + description);
        if (syslibs.length != 0)
            out.println("    syslibs=" + Strings.join(", ", syslibs));
        if (userlibs.length != 0)
            out.println("    userlibs=" + Strings.join(", ", userlibs));
        if (booterClassName != null)
            out.println("    booter=" + booterClassName);
        if (configs != null && configs.length != 0) {
            out.println("    Configs: ");
            for (ConfigParam config : configs) {
                out.print("        " + config.clazz);
                out.println("(" + Strings.join(", ", config.args) + ")");
            }
        }
        if (prev != null) {
            out.print("prev ");
            prev.dump(out);
        }
    }

    @Override
    public String toString() {
        Buffer buffer = new Buffer(10000);
        dump(buffer);
        return buffer.toString();
    }

}
