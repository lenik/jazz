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
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadException;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader.TempClassLoader;
import net.bodz.bas.loader.UCL;
import net.bodz.bas.snm.SJLibLoader;
import net.bodz.bas.text.interp.Interps;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.util.Annotations;
import net.bodz.bas.types.util.Arrays2;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Strings;

@Doc("Generate program launcher for java applications")
@Version( { 0, 3 })
@RcsKeywords(id = "$Id$")
@ProgramName("mkbat")
public class Mkbat extends BatchEditCLI {

    boolean                 force;

    // private String prefix = "";
    private TextMap<String> varmap;
    private Set<String>     generated;

    private List<URL>       _userlibs;
    private URL[]           userlibs = {};
    private ClassLoader     bootSysLoader;

    public Mkbat() {
        generated = new HashSet<String>();
        varmap = new HashTextMap<String>();
        ClassInfo classInfo = _loadClassInfo();
        String generator = Mkbat.class.getSimpleName() //
                + " " + classInfo.getVersionString(false) //
        // + ", " + classInfo.getDateString()
        ;
        varmap.put("GENERATOR", generator);
    }

    @Option(alias = "l", vnam = "LIBSPEC", doc = "add user lib for locating the class")
    public void addUserLib(String libspec) {
        for (URL url : LoadUtil.find(libspec, true))
            addUserLib(url);
    }

    public void addUserLib(URL url) {
        if (_userlibs == null)
            _userlibs = new ArrayList<URL>();
        _userlibs.add(url);
    }

    static boolean BOOT_DUMP  = false; 
    @Override
    protected void _boot() throws Throwable {
        force = parameters().isForce();

        ClassLoader initSysLoader = Caller.getCallerClassLoader();
        if (_userlibs != null)
            userlibs = _userlibs.toArray(Empty.URLs);
        if (userlibs == null)
            bootSysLoader = initSysLoader;
        else
            bootSysLoader = TempClassLoader.get(userlibs, initSysLoader);
        if (BOOT_DUMP)
            UCL.dump(bootSysLoader, CharOuts.stderr);
    }

    @Override
    protected EditResult doEdit(File file) throws LoadException, IOException {
        String ext = Files.getExtension(file, true);
        if (!".java".equals(ext) && !".class".equals(ext))
            return EditResult.pass();
        String name = Files.getName(file);
        if (name.contains("$")) // ignore inner classes
            return EditResult.pass("inner");

        File bootFile = new File(file.getParentFile(), name + "Boot." + ext);
        if (bootFile.exists())
            return EditResult.pass("boot");

        String className = getRelativeName(file);
        className = className.substring(0, className.length() - ext.length());
        className = className.replace('\\', '/');
        className = className.replace('/', '.');
        if (generated.contains(className))
            return EditResult.pass("repeat");

        Class<?> class0 = null;
        // can found by bootSysLoader?
        try {
            L.d.P("try ", className);
            class0 = bootSysLoader.loadClass(className);
            // class0 = Class.forName(className, false, bootSysLoader);
        } catch (ClassNotFoundException e) {
            return EditResult.err(e, "loadc");
        } catch (NoClassDefFoundError e) {
            return EditResult.err(e, "loadc");
        }

        // is public?
        int modifiers = class0.getModifiers();
        if (!Modifier.isPublic(modifiers))
            return EditResult.pass("local");

        // has main()? [1, bootSysLoader]
        try {
            class0.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            return EditResult.pass("notapp0");
            // return ProcessResult.err(e, "notapp0");
        } catch (NoClassDefFoundError t) {
            // continue search.
        }

        BootProc bootProc = BootProc.get(class0);

        // prepare realSysLoader
        ClassLoader realSysLoader = Caller.getCallerClassLoader();
        if (bootProc != null)
            realSysLoader = bootProc.configSysLoader(realSysLoader);

        // realSysLoader -> configLoader
        Class<?> class1 = DefaultBooter
                .load(realSysLoader, className, userlibs);

        // has main()? [2, configLoader]
        try {
            class1.getMethod("main", String[].class);
            L.i.P("    main-class: ", class1);
        } catch (NoSuchMethodException e) {
            return EditResult.pass("notapp");
            // return ProcessResult.err(e, "notapp");
        } catch (Throwable t) {
            return EditResult.err(t, "loadf");
        }

        generate(class1);

        return EditResult.pass("ok");
    }

    protected void generate(Class<?> clazz) throws IOException {
        String batName = (String) Annotations
                .getValue(clazz, ProgramName.class);
        if (batName == null) {
            batName = clazz.getSimpleName();
            batName = batName.toLowerCase(); // option?
        }
        File batf = getOutputFile(batName + ".bat");
        batf.getParentFile().mkdirs();
        String name = clazz.getName();

        // varmap.clear();
        String templName = new File(batTempl.getPath()).getName();
        varmap.put("TEMPLATE", batEscape(templName));
        varmap.put("NAME", batEscape(name));

        String start = "";

        Integer startMode = (Integer) Ns.getValue(clazz, StartMode.class);
        if (startMode != null)
            switch (startMode) {
            case StartMode.CLI:
                break;
            case StartMode.GUI:
                start = "startw";
                break;
            case StartMode.DAEMON:
                break;
            }
        varmap.put("START", start);

        BootProc bootProc = BootProc.get(clazz);
        String booter = null;
        String[] bootArgs = {};
        String[] cplibs = {};
        if (bootProc != null) {
            booter = bootProc.getBooterClassName();
            cplibs = bootProc.getSysLibs();
            if (!bootProc.hasConfig()) {
                // merge syslibs & userlibs if no config.
                String[] userlibs = bootProc.getUserLibs();
                cplibs = Arrays2.concat(cplibs, userlibs);
            } else
                // XXX: anything other then userlib in boot args?
                bootArgs = bootProc.getBootArgs();
        }

        String launch = "";
        if (bootArgs.length != 0) {
            launch = booter + " " + Strings.join(" ", bootArgs) + " --";
        } else if (booter != null) {
            launch = booter + " --";
        }
        varmap.put("LAUNCH", batEscape(launch));

        Buffer loadlibs = new Buffer();
        SJLibLoader libloader = SJLibLoader.DEFAULT;
        for (String lib : cplibs) {
            File f = libloader.findLibraryFile(lib);
            String fname;
            if (f == null) {
                if (lib.contains("."))
                    fname = lib;
                else
                    fname = lib + ".jar";
                L.w.P("lib ", lib, " => ", fname);
            } else
                fname = f.getName();
            String loadlib = "call :load " + qq(lib) + " " + qq(fname);
            loadlibs.println("    " + loadlib);
        }
        // varmap.put("LOADLIBS_0", "");
        varmap.put("LOADLIBS", batEscape(loadlibs.toString()));

        String inst = Interps.dereference(batTemplBody, varmap);
        byte[] batData = inst.getBytes();
        byte[] batFixed = fix_BatBB.methods().doEditToBuffer(batData);
        if (!Bytes.equals(batData, batFixed))
            L.m.P("bat label boundary fixed: ", batf);
        if (force) {
            Files.write(batf, batFixed, batf);
            L.m.P("write ", batf);
        } else if (Files.copyDiff(batFixed, batf))
            L.m.P("save ", batf);
    }

    static URL       batTempl;
    static String    batTemplBody;
    static Fix_BatBB fix_BatBB;

    static {
        try {
            batTempl = Files.classData(Mkbat.class, "batTempl");
            batTemplBody = Files.readAll(batTempl, "utf-8");
        } catch (IOException e) {
            throw new IdentifiedException(e.getMessage(), e);
        }
        fix_BatBB = new Fix_BatBB();
    }

    static String batEscape(String s) {
        s = s.replace("^", "^^");
        s = s.replace("%", "%%");
        return s;
    }

    public static void main(String[] args) throws Throwable {
        new Mkbat().run(args);
    }

}
