package net.bodz.bas.cli;

import static net.bodz.bas.cli.util.CLIFunctions.global;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import net.bodz.bas.io.Files;
import net.bodz.bas.lang.ClassLocal;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.lang.util.Classpath;
import net.bodz.bas.types.util.Types;

public class _RunInfo {

    private RunInfo  annotation;
    private _RunInfo next;

    // private Class<?>[] init;

    private URL[]    libs;

    // private String[] _load;
    // private String[] load;

    public _RunInfo(RunInfo info, _RunInfo next) throws CLIException {
        assert info != null;
        this.annotation = info;
        this.next = next;
        reset();
    }

    public _RunInfo(RunInfo info) throws CLIException {
        this(info, null);
    }

    private int      flags;
    static final int LIBRARIES_LOADED = 1;
    static final int BOOT_LOADED      = 2;
    static final int EXTRA_LOADED     = 4;

    boolean test(int bit) {
        return (flags & bit) != 0;
    }

    public void reset() throws CLIException {
        libs = new URL[annotation.lib().length];
        for (int i = 0; i < libs.length; i++)
            libs[i] = resolveLib(annotation.lib()[i]);
        flags = 0;
    }

    public static File       libJava;    // == Lapiota.searchJavaLib
    public static Properties libversions;

    static {
        String javalib = System.getenv("JAVA_LIB");
        if (javalib != null) {
            libJava = Files.canoniOf(javalib);
            File verprops = new File(libJava, "versions.property");
            if (verprops.exists())
                try {
                    libversions = Files.loadProperties(verprops);
                } catch (IOException e) {
                    throw new IdentifiedException(e.getMessage(), e);
                }
            else
                libversions = new Properties();
        } else {
            libJava = null; // new File("/");
        }
    }

    URL resolveLib(String lib) throws CLIException {
        return resolveJar(libversions.get("lib" + lib) + ".jar");
    }

    URL resolveJar(String jar) throws CLIException {
        try {
            File libf = new File(libJava, jar);
            return libf.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new CLIException(e.getMessage(), e);
        }
    }

    public void loadLibraries() throws CLIException {
        if (test(LIBRARIES_LOADED))
            return;
        for (URL url : libs)
            try {
                Classpath.addURL(url);
            } catch (IOException e) {
                throw new CLIException(e.getMessage(), e);
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
            global.eval(exp);
        if (next != null)
            next.loadBoot();
        flags |= BOOT_LOADED;
    }

    public void loadDelayed() throws CLIException {
        if (test(EXTRA_LOADED))
            return;
        for (String exp : annotation.loadDelayed())
            global.eval(exp);
        if (next != null)
            next.loadDelayed();
        flags |= EXTRA_LOADED;
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
                _info = new _RunInfo(declInfo);
            else
                _info = new _RunInfo(declInfo, _info);
        }
        local.put(clazz, _info);
        return _info;
    }

    public static _RunInfo parse(Class<?> clazz) throws CLIException {
        return parse(clazz, true);
    }

}
