package net.bodz.bas.cli.util;

import static net.bodz.bas.types.util.ArrayOps.Bytes;
import static net.bodz.bas.types.util.Strings.qq;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.bodz.bas.a.A_bas;
import net.bodz.bas.a.BootProc;
import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.ProgramName;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.StartMode;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.BatchEditCLI;
import net.bodz.bas.cli.EditResult;
import net.bodz.bas.cli.a.Option;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.BCharOut;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadException;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader.TempClassLoader;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.snm.SJLibLoader;
import net.bodz.bas.text.util.Interps;
import net.bodz.bas.types.ArraySet;
import net.bodz.bas.types.HashTextMap;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Strings;

@Doc("Generate program launcher for java applications")
@ProgramName("mkbat")
@RcsKeywords(id = "$Id$")
@Version( { 0, 3 })
public class Mkbat extends BatchEditCLI {

    boolean force;

    // private String prefix = "";
    private TextMap<String> varmap;
    private Set<String> generated;

    private List<URL> classpathList;
    private URL[] classpath = {};
    private ClassLoader bootSysLoader;

    private List<String> runtimeLibs;

    public Mkbat() {
        generated = new HashSet<String>();
        varmap = new HashTextMap<String>();
        ClassInfo classInfo = _loadClassInfo();
        String generator = Mkbat.class.getSimpleName() //
                + " " + classInfo.getVersionString(false) // //$NON-NLS-1$
        // + ", " + classInfo.getDateString()
        ;
        varmap.put("GENERATOR", generator); //$NON-NLS-1$
    }

    @Option(alias = "cp", vnam = "LIBSPEC", doc = "add user lib for locating the class")
    public void addClasspath(String entry) {
        for (URL url : LoadUtil.find(entry, true))
            addClasspath(url);
    }

    public void addClasspath(URL url) {
        if (classpathList == null)
            classpathList = new ArrayList<URL>();
        classpathList.add(url);
    }

    public void addRuntimeLib(String libname) {
        if (runtimeLibs == null)
            runtimeLibs = new ArrayList<String>();
        runtimeLibs.add(libname);
    }

    static boolean BOOT_DUMP = false;

    @Override
    protected void _boot() throws Exception {
        force = parameters().isForce();

        ClassLoader initSysLoader = Caller.getCallerClassLoader(0);
        if (classpathList != null)
            classpath = classpathList.toArray(Empty.URLs);
        if (classpath == null)
            bootSysLoader = initSysLoader;
        else
            bootSysLoader = TempClassLoader.get(classpath, initSysLoader);
        if (BOOT_DUMP)
            UCL.dump(bootSysLoader, CharOuts.stderr);
    }

    @Override
    protected EditResult doEdit(File file) throws LoadException, IOException {
        String ext = Files.getExtension(file, true);
        if (!".java".equals(ext) && !".class".equals(ext)) //$NON-NLS-1$ //$NON-NLS-2$
            return EditResult.pass();
        String name = Files.getName(file);
        if (name.contains("$")) // ignore inner classes //$NON-NLS-1$
            return EditResult.pass("inner"); //$NON-NLS-1$

        File bootFile = new File(file.getParentFile(), name + "Boot." + ext); //$NON-NLS-1$
        if (bootFile.exists())
            return EditResult.pass("boot"); //$NON-NLS-1$

        String className = getRelativeName(file);
        className = className.substring(0, className.length() - ext.length());
        className = className.replace('\\', '/');
        className = className.replace('/', '.');
        if (generated.contains(className))
            return EditResult.pass("repeat"); //$NON-NLS-1$

        Class<?> class0 = null;
        // can found by bootSysLoader?
        try {
            L.detail("try " + className); //$NON-NLS-1$
            class0 = bootSysLoader.loadClass(className);
            // class0 = Class.forName(className, false, bootSysLoader);
        } catch (ClassNotFoundException e) {
            return EditResult.err(e, "loadc"); //$NON-NLS-1$
        } catch (NoClassDefFoundError e) {
            return EditResult.err(e, "loadc"); //$NON-NLS-1$
        }

        // is public?
        int modifiers = class0.getModifiers();
        if (!Modifier.isPublic(modifiers))
            return EditResult.pass("local"); //$NON-NLS-1$

        // has main()? [1, bootSysLoader]
        try {
            class0.getMethod("main", String[].class); //$NON-NLS-1$
        } catch (NoSuchMethodException e) {
            return EditResult.pass("notapp0"); //$NON-NLS-1$
            // return ProcessResult.err(e, "notapp0");
        } catch (NoClassDefFoundError t) {
            // continue search.
        }

        BootProc bootProc = BootProc.get(class0);

        // prepare realSysLoader
        ClassLoader realSysLoader = Caller.getCallerClassLoader(0);
        if (bootProc != null)
            realSysLoader = bootProc.configSysLoader(realSysLoader);

        // realSysLoader -> configLoader
        Class<?> class1 = DefaultBooter.load(realSysLoader, className, classpath);

        // has main()? [2, configLoader]
        try {
            class1.getMethod("main", String[].class); //$NON-NLS-1$
            L.info("    main-class: " + class1); //$NON-NLS-1$
        } catch (NoSuchMethodException e) {
            return EditResult.pass("notapp"); //$NON-NLS-1$
            // return ProcessResult.err(e, "notapp");
        } catch (Throwable t) {
            return EditResult.err(t, "loadf"); //$NON-NLS-1$
        }

        generate(class1);

        return EditResult.pass("ok"); //$NON-NLS-1$
    }

    protected void generate(Class<?> clazz) throws IOException {
        String batName = A_bas.getProgramName(clazz, false);
        File batf = getOutputFile(batName + ".bat"); //$NON-NLS-1$
        batf.getParentFile().mkdirs();
        String name = clazz.getName();

        // varmap.clear();
        String templName = new File(batTempl.getPath()).getName();
        varmap.put("TEMPLATE", batEscape(templName)); //$NON-NLS-1$
        varmap.put("NAME", batEscape(name)); //$NON-NLS-1$

        String start = ""; //$NON-NLS-1$

        Integer startMode = (Integer) Ns.getValue(clazz, StartMode.class);
        if (startMode != null)
            switch (startMode) {
            case StartMode.CLI:
                break;
            case StartMode.GUI:
                start = "startw"; //$NON-NLS-1$
                break;
            case StartMode.DAEMON:
                break;
            }
        varmap.put("START", start); //$NON-NLS-1$

        BootProc bootProc = BootProc.get(clazz);
        String booter = null;
        String[] bootArgs = {};
        Set<String> allRuntimeLibs = new ArraySet<String>();
        if (bootProc != null) {
            booter = bootProc.getBooterClassName();
            for (String sysLib : bootProc.getSysLibs())
                allRuntimeLibs.add(sysLib);
            if (!bootProc.hasConfig()) {
                // merge syslibs & userlibs if no config.
                for (String userlib : bootProc.getUserLibs())
                    allRuntimeLibs.add(userlib);
            } else
                // XXX: anything other then userlib in boot args?
                bootArgs = bootProc.getBootArgs();
        }
        if (runtimeLibs != null)
            for (String runtimeLib : runtimeLibs)
                allRuntimeLibs.add(runtimeLib);

        String launch = ""; //$NON-NLS-1$
        if (bootArgs.length != 0) {
            // booter==null?
            launch = booter + " " + Strings.join(" ", bootArgs) + " --"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        } else if (booter != null) {
            launch = booter + " --"; //$NON-NLS-1$
        }
        varmap.put("LAUNCH", batEscape(launch)); //$NON-NLS-1$

        BCharOut loadSection = new BCharOut();
        SJLibLoader libloader = SJLibLoader.DEFAULT;
        for (String lib : allRuntimeLibs) {
            File f = libloader.findLibraryFile(lib);
            String fname;
            if (f == null) {
                if (lib.contains(".")) //$NON-NLS-1$
                    fname = lib;
                else
                    fname = lib + ".jar"; //$NON-NLS-1$
                L.warn("lib ", lib, " => ", fname); //$NON-NLS-1$ //$NON-NLS-2$
            } else
                fname = f.getName();
            String loadlib = "call :load " + qq(lib) + " " + qq(fname); //$NON-NLS-1$ //$NON-NLS-2$
            loadSection.println("    " + loadlib); //$NON-NLS-1$
        }
        // varmap.put("LOADLIBS_0", "");
        varmap.put("LOADLIBS", batEscape(loadSection.toString())); //$NON-NLS-1$

        String inst = Interps.dereference(batTemplBody, varmap);
        byte[] batData = inst.getBytes();
        byte[] batFixed = fix_BatBB.methods().doEditToBuffer(batData);
        if (!Bytes.equals(batData, batFixed))
            L.info("bat label boundary fixed: ", batf); //$NON-NLS-1$
        if (force) {
            Files.write(batf, batFixed, batf);
            L.info("write ", batf); //$NON-NLS-1$
        } else if (Files.copyDiff(batFixed, batf))
            L.info("save ", batf); //$NON-NLS-1$
    }

    static URL batTempl;
    static String batTemplBody;
    static Fix_BatBB fix_BatBB;

    static {
        try {
            batTempl = Files.classData(Mkbat.class, "batTempl"); //$NON-NLS-1$
            batTemplBody = Files.readAll(batTempl, "utf-8"); //$NON-NLS-1$
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
        fix_BatBB = new Fix_BatBB();
    }

    static String batEscape(String s) {
        s = s.replace("^", "^^"); //$NON-NLS-1$ //$NON-NLS-2$
        s = s.replace("%", "%%"); //$NON-NLS-1$ //$NON-NLS-2$
        return s;
    }

    public static void main(String[] args) throws Exception {
        new Mkbat().run(args);
    }

}
