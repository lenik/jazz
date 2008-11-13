package net.bodz.bas.cli.util;

import static net.bodz.bas.types.util.ArrayOps.Bytes;
import static net.bodz.bas.types.util.Strings.qq;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import net.bodz.bas.a.ClassInfo;
import net.bodz.bas.a.Doc;
import net.bodz.bas.a.LoadBoot;
import net.bodz.bas.a.LoadBy;
import net.bodz.bas.a.ProgramName;
import net.bodz.bas.a.RcsKeywords;
import net.bodz.bas.a.Version;
import net.bodz.bas.cli.BatchProcessCLI;
import net.bodz.bas.cli.ProcessResult;
import net.bodz.bas.io.Files;
import net.bodz.bas.io.CharOuts.Buffer;
import net.bodz.bas.lang.Caller;
import net.bodz.bas.lang.err.IdentifiedException;
import net.bodz.bas.loader.LoadByLauncher;
import net.bodz.bas.snm.SJLibLoader;
import net.bodz.bas.snm.SJProject;
import net.bodz.bas.text.interp.Interps;
import net.bodz.bas.types.TextMap;
import net.bodz.bas.types.TextMap.HashTextMap;
import net.bodz.bas.types.util.Annotations;
import net.bodz.bas.types.util.Ns;
import net.bodz.bas.types.util.Strings;
import net.bodz.bas.types.util.Types;

@Doc("Generate program launcher for java applications")
@Version( { 0, 1 })
@RcsKeywords(id = "$Id: Rcs.java 784 2008-01-15 10:53:24Z lenik $")
@ProgramName("mkbat")
public class Mkbat extends BatchProcessCLI {

    // private String prefix = "";
    private TextMap<String> varmap;
    private Set<String>     generated;

    public Mkbat() {
        generated = new HashSet<String>();
        varmap = new HashTextMap<String>();
        ClassInfo classInfo = _loadClassInfo();
        varmap.put("GENERATOR", Mkbat.class.getSimpleName() + " "
                + classInfo.getVersionString() + ", "
                + classInfo.getDateString());
    }

    @Override
    protected void _boot() throws Throwable {
        // Classpath.dumpURLs(Caller.getCallerClassLoader(), CharOuts.stderr);
    }

    @Override
    protected ProcessResult doFile(File file) throws Throwable {
        String ext = Files.getExtension(file, true);
        if (!".java".equals(ext) && !".class".equals(ext))
            return ProcessResult.pass();
        String name = Files.getName(file);
        if (name.contains("$")) // ignore inner classes
            return ProcessResult.pass("inner");

        File bootFile = new File(file.getParentFile(), name + "Boot." + ext);
        if (bootFile.exists())
            return ProcessResult.pass("boot");

        String className = getRelativeName(file);
        className = className.substring(0, className.length() - ext.length());
        className = className.replace('\\', '/');
        className = className.replace('/', '.');
        if (generated.contains(className))
            return ProcessResult.pass("repeat");

        Class<?> clazz0 = null;
        try {
            L.d.P("try ", className);
            ClassLoader ccl = Caller.getCallerClassLoader();
            clazz0 = Class.forName(className, false, ccl);
        } catch (Throwable t) {
            return ProcessResult.err(t, "loadc");
        }

        int modifiers = clazz0.getModifiers();
        if (!Modifier.isPublic(modifiers))
            return ProcessResult.pass("local");

        File whichClass = SJProject.findBase(clazz0);
        URL whichClassURL = whichClass.toURI().toURL();
        Class<?> clazz1 = LoadByLauncher.load(className, whichClassURL);

        try {
            clazz1.getMethod("main", String[].class);
            L.i.P("    main-class: ", clazz1);
        } catch (NoSuchMethodException e) {
            return ProcessResult.pass("notapp");
        } catch (Throwable t) {
            return ProcessResult.err(t, "loadf");
        }
        generate(clazz1);
        return ProcessResult.pass("ok");
    }

    protected void generate(Class<?> clazz) throws IOException {
        String name = clazz.getName();

        String batName = (String) Annotations
                .getValue(clazz, ProgramName.class);
        if (batName == null) {
            batName = clazz.getSimpleName();
            batName = batName.toLowerCase(); // option?
        }
        File batf = getOutputFile(batName + ".bat");
        batf.getParentFile().mkdirs();

        // varmap.clear();
        varmap.put("TEMPLATE", new File(batTempl.getPath()).getName());
        varmap.put("NAME", name);

        Class<?> launcherClass = LoadByLauncher.class;
        LoadBy loadBy = Ns.getN(clazz, LoadBy.class);
        if (loadBy != null) {
            Class<?> _launcherClass = loadBy.launcher();
            if (_launcherClass != LoadByLauncher.class)
                launcherClass = _launcherClass;
        }

        String launch = "";
        String[] loadArgs = LoadByLauncher.getLoadArgs(clazz);
        if (loadArgs.length != 0) {
            launch = launcherClass.getName() + " "
                    + Strings.join(" ", loadArgs) + " -";
        } else if (launcherClass != LoadByLauncher.class) {
            launch = launcherClass.getName() + " -";
        }
        varmap.put("LAUNCH", launch);

        Buffer loadlibs = new Buffer();
        SJLibLoader libloader = SJLibLoader.DEFAULT;
        for (Class<?> c : Types.getTypeChain(clazz)) {
            LoadBoot loadBoot = c.getAnnotation(LoadBoot.class);
            if (loadBoot == null)
                continue;
            for (String lib : loadBoot.value()) {
                File f = libloader.findLibraryFile(lib);
                String fname;
                if (f == null) {
                    fname = lib + ".jar";
                    L.w.P("lib ", lib, " => ", fname);
                } else
                    fname = f.getName();
                String loadlib = "call :load " + qq(lib) + " " + qq(fname);
                loadlibs.println("    " + loadlib);
            }
        }
        // varmap.put("LOADLIBS_0", "");
        varmap.put("LOADLIBS", loadlibs.toString());

        String inst = Interps.dereference(batTemplBody, varmap);
        byte[] batData = inst.getBytes();
        byte[] batFixed = fix_BatBB.doFileEdit(batData);
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

    public static void main(String[] args) throws Throwable {
        new Mkbat().run(args);
    }

}
