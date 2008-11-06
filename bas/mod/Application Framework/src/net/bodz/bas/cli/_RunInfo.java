package net.bodz.bas.cli;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.bodz.bas.cli.a.RunInfo;
import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.loader.JavaLibraryLoader;
import net.bodz.bas.types.util.Types;

public class _RunInfo {

    private final String      name;
    private RunInfo           annotation;
    private _RunInfo          next;

    // private Class<?>[] init;

    private String[]          libconds;
    private URL[]             libs;

    // private String[] _load;
    // private String[] load;

    private JavaLibraryLoader libloader;

    public _RunInfo(String name, RunInfo info, _RunInfo next)
            throws CLIException {
        assert info != null;
        this.name = name;
        this.annotation = info;
        this.next = next;
        reset();
    }

    public _RunInfo(String name, RunInfo info) throws CLIException {
        this(name, info, null);
    }

    private int      flags;
    static final int LIBRARIES_LOADED = 1;
    static final int BOOT_LOADED      = 2;
    static final int EXTRA_LOADED     = 4;

    boolean test(int bit) {
        return (flags & bit) != 0;
    }

    private void reset() throws CLIException {
        libloader = JavaLibraryLoader.DEFAULT;
        String[] libN = annotation.lib();
        libconds = new String[libN.length];
        libs = new URL[libN.length];
        for (int i = 0; i < libs.length; i++) {
            String s = libN[i];
            int pipe = s.indexOf('|');
            String libcond, libname;
            if (pipe == -1) {
                libcond = "fortype %" + s;
                libname = s;
            } else {
                libcond = s.substring(0, pipe).trim();
                libname = s.substring(pipe + 1).trim();
            }
            libconds[i] = libcond;
            libs[i] = findLib(libname);
        }
        flags = 0;
    }

    @Override
    public String toString() {
        return "<_RunInfo for " + name + ", flags=" + flags + ">";
    }

    /**
     * <code>jarname.jar</code> (with extension) is searched and loaded using
     * {@link JavaLibraryLoader}.
     * <p>
     * <code>libname</code> (without extension) are resolved using
     * <code>libraries.ini</code> file, if <code>libname</code> isn't defined in
     * <code>libraries.ini</code> , then <code>libname.jar</code> is used.
     * 
     */
    public URL findLib(String libspec) throws CLIException {
        File libfile;
        if (libspec.contains("."))
            libfile = resolveJar(libspec);
        else
            libfile = resolveLib(libspec);
        if (libfile == null)
            throw new Error("can't resolve lib " + libspec);
        try {
            return libfile.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    File resolveLib(String name) throws CLIException {
        return libloader.findLibraryFile(name);
    }

    File resolveJar(String jar) throws CLIException {
        return libloader.findFile(jar);
    }

    public void loadLibraries() throws CLIException {
        if (test(LIBRARIES_LOADED))
            return;
        for (int i = 0; i < libs.length; i++) {
            String cond = libconds[i];
            URL url = libs[i];
            if (cond != null) {
                try {
                    if ((Boolean) CLIConfig.libEval(cond))
                        continue;
                } catch (Exception e) {
                    throw new CLIException("loadLibraries failed on: " + this,
                            e);
                }
            }
            try {
                Classpath.addURL(url);
            } catch (IOException e) {
                throw new CLIException(e.getMessage(), e);
            }
        }
        if (next != null)
            next.loadLibraries();
        flags |= LIBRARIES_LOADED;
    }

    public void loadBoot() throws CLIException {
        if (test(BOOT_LOADED))
            return;
        for (Class<?> init : annotation.init())
            try {
                Class.forName(init.getName());
            } catch (ClassNotFoundException e) {
                throw new CLIException(e.getMessage(), e);
            }

        for (String exp : annotation.load())
            try {
                evalExp(exp);
            } catch (Exception e) {
                throw new CLIException("loadBoot failed on: " + this, e);
            }
        if (next != null)
            next.loadBoot();
        flags |= BOOT_LOADED;
    }

    public void loadDelayed() throws CLIException {
        if (test(EXTRA_LOADED))
            return;
        for (String exp : annotation.loadDelayed())
            try {
                evalExp(exp);
            } catch (Exception e) {
                throw new CLIException("loadDlayed failed on: " + this, e);
            }
        if (next != null)
            next.loadDelayed();
        flags |= EXTRA_LOADED;
    }

    void evalExp(String exp) throws CLIException {
        int pipe = exp.indexOf('|');
        if (pipe != -1) {
            String cond = exp.substring(0, pipe).trim();
            if ((Boolean) CLIConfig.libEval(cond))
                return;
            exp = exp.substring(pipe + 1).trim();
        }
        CLIConfig.libEval(exp);
    }

    private static ClassLocal<_RunInfo> local;
    static {
        local = new ClassLocal<_RunInfo>();
    }

    public static _RunInfo parse(Class<?> clazz, boolean rootFirst)
            throws CLIException {
        if (local.containsKey(clazz))
            return local.get(clazz);
        _RunInfo _info = null;
        for (Class<?> decl : Types.getTypeChain(clazz, !rootFirst)) {
            RunInfo declInfo = decl.getAnnotation(RunInfo.class);
            if (declInfo == null)
                continue;
            if (_info == null)
                _info = new _RunInfo(decl.getName(), declInfo);
            else
                _info = new _RunInfo(decl.getName(), declInfo, _info);
        }
        local.put(clazz, _info);
        return _info;
    }

    public static _RunInfo parse(Class<?> clazz) throws CLIException {
        return parse(clazz, true);
    }

}
