package net.bodz.bas.cli.boot.win32;

import static net.bodz.bas.c.string.StringQuote.qq;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.*;

import net.bodz.bas.c.java.io.FileDiff;
import net.bodz.bas.c.java.io.FilePath;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.c.java.util.regex.UnixStyleVarProcessor;
import net.bodz.bas.c.loader.ClassResource;
import net.bodz.bas.c.string.StringArray;
import net.bodz.bas.cli.skel.BatchEditCLI;
import net.bodz.bas.cli.skel.EditResult;
import net.bodz.bas.collection.set.ArraySet;
import net.bodz.bas.err.IdentifiedException;
import net.bodz.bas.io.resource.builtin.ByteArrayResource;
import net.bodz.bas.io.resource.builtin.URLResource;
import net.bodz.bas.io.resource.tools.StreamReading;
import net.bodz.bas.io.resource.tools.StreamWriting;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.loader.DefaultBooter;
import net.bodz.bas.loader.LoadException;
import net.bodz.bas.loader.LoadUtil;
import net.bodz.bas.loader.TempClassLoader;
import net.bodz.bas.loader.boot.BootProc;
import net.bodz.bas.meta.build.MainVersion;
import net.bodz.bas.meta.build.RcsKeywords;
import net.bodz.bas.meta.build.ReleaseDescription;
import net.bodz.bas.meta.program.ProgramName;
import net.bodz.bas.meta.program.ProgramNameUtil;
import net.bodz.bas.meta.program.StartMode;
import net.bodz.bas.meta.program.StartModeUtil;
import net.bodz.bas.potato.book.ArtifactDoc;
import net.bodz.bas.sio.BCharOut;
import net.bodz.bas.sio.Stdio;
import net.bodz.bas.snm.SJLibLoader;
import net.bodz.bas.vfs.IFile;
import net.bodz.mda.xjdoc.conv.ClassDocs;

/**
 * Generate program launcher for java applications
 * 
 * @version 0.3
 */
@ProgramName("mkbat")
@RcsKeywords(id = "$Id$")
@MainVersion({ 0, 3 })
public class Mkbat
        extends BatchEditCLI {

    boolean force;

    // private String prefix = "";
    private Map<String, String> varmap;
    private Set<String> generated;

    private List<URL> classpathList;
    private URL[] classpath = {};
    private ClassLoader bootSysLoader;

    private List<String> runtimeLibs;

    public Mkbat() {
        generated = new HashSet<String>();
        varmap = new HashMap<String, String>();

        ArtifactDoc artifactDoc;
        artifactDoc = ClassDocs.loadFromResource(getClass()).as(ArtifactDoc.class);

        ReleaseDescription release = artifactDoc.getReleaseDescription();
        String releaseId = release.getFriendlyId();

        String generator = releaseId;// + ", " + classInfo.getDateString();
        varmap.put("GENERATOR", generator);
    }

    /**
     * Add user lib for locating the class
     * 
     * @option -cp =LIBSPEC
     */
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
    protected void _boot()
            throws Exception {
        force = parameters().isForce();

        ClassLoader initSysLoader = Caller.getCallerClassLoader(0);
        if (classpathList != null)
            classpath = classpathList.toArray(new URL[0]);
        if (classpath == null)
            bootSysLoader = initSysLoader;
        else
            bootSysLoader = TempClassLoader.get(classpath, initSysLoader);
        if (BOOT_DUMP)
            URLClassLoaders.dump(bootSysLoader, Stdio.cerr);
    }

    @Override
    protected EditResult doEdit(IFile file)
            throws LoadException, IOException {
        String ext = file.getPath().getExtension(true, 1);
        if (!".java".equals(ext) && !".class".equals(ext))
            return EditResult.pass();
        String name = file.getPath().getStrippedName();
        if (name.contains("$")) // ignore inner classes
            return EditResult.pass("inner");

        // new File(file.getParentFile(), name + "Boot." + ext);
        IFile bootFile = file.getParentFile().getChild(name + "Boot." + ext);
        if (bootFile.isExisted())
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
            logger._info(2, "try " + className);
            class0 = bootSysLoader.loadClass(className);
            // class0 = Jdk7Reflect.forName(className, false, bootSysLoader);
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
        ClassLoader realSysLoader = Caller.getCallerClassLoader(0);
        if (bootProc != null)
            realSysLoader = bootProc.configSysLoader(realSysLoader);

        // realSysLoader -> configLoader
        Class<?> class1 = DefaultBooter.load(realSysLoader, className, classpath);

        // has main()? [2, configLoader]
        try {
            class1.getMethod("main", String[].class);
            logger.info("    main-class: " + class1);
        } catch (NoSuchMethodException e) {
            return EditResult.pass("notapp");
            // return ProcessResult.err(e, "notapp");
        } catch (Throwable t) {
            return EditResult.err(t, "loadf");
        }

        generate(class1);

        return EditResult.pass("ok");
    }

    protected void generate(Class<?> clazz)
            throws IOException {
        String batName = ProgramNameUtil.getProgramName(clazz, false);

        IFile batFile = getOutputFile(batName + ".bat");
        batFile.getParentFile().createTree();

        String name = clazz.getName();

        // varmap.clear();
        String templBaseName = FilePath.getBaseName(batTempl.getURL().getPath());
        varmap.put("TEMPLATE", batEscape(templBaseName));
        varmap.put("NAME", batEscape(name));

        String start = "";

        Integer startMode = StartModeUtil.getStartMode(clazz);
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

        String launch = "";
        if (bootArgs.length != 0) {
            // booter==null?
            launch = booter + " " + StringArray.join(" ", bootArgs) + " --";
        } else if (booter != null) {
            launch = booter + " --";
        }
        varmap.put("LAUNCH", batEscape(launch));

        BCharOut loadSection = new BCharOut();
        SJLibLoader libloader = SJLibLoader.DEFAULT;
        for (String lib : allRuntimeLibs) {
            File f = libloader.findLibraryFile(lib);
            String fname;
            if (f == null) {
                if (lib.contains("."))
                    fname = lib;
                else
                    fname = lib + ".jar";
                logger.warn("lib ", lib, " => ", fname);
            } else
                fname = f.getName();
            String loadlib = "call :load " + qq(lib) + " " + qq(fname);
            loadSection.println("    " + loadlib);
        }
        // varmap.put("LOADLIBS_0", "");
        varmap.put("LOADLIBS", batEscape(loadSection.toString()));

        UnixStyleVarProcessor ve = new UnixStyleVarProcessor(varmap);

        String expansion = ve.process(batTemplBody);
        byte[] batData = expansion.getBytes();

        ByteArrayResource batDataRes = new ByteArrayResource(batData);
        byte[] batFixed = fix_BatBB.methods().doEditToBuffer(batDataRes);

        ByteArrayResource batFixedRes = new ByteArrayResource(batFixed);

        if (!Arrays.equals(batData, batFixed))
            logger.info("bat label boundary fixed: ", batFile);
        if (force) {
            logger.info("write ", batFile);
            batFile.tooling()._for(StreamWriting.class).writeBytes(batFixed);
        } else if (FileDiff.copyDiff(batFixedRes, batFile.getResource()))
            logger.info("save ", batFile);
    }

    static URLResource batTempl;
    static String batTemplBody;
    static Fix_BatBB fix_BatBB;

    static {
        try {
            batTempl = ClassResource.classData(Mkbat.class, "batTempl");
            batTemplBody = batTempl.tooling()._for(StreamReading.class).readTextContents();
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

    public static void main(String[] args)
            throws Exception {
        new Mkbat().execute(args);
    }

}
