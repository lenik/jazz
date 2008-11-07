package net.bodz.bas.cli;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.bodz.bas.cli.util.CLILibrary;
import net.bodz.bas.cli.util.Conditions;
import net.bodz.bas.cli.util.FindPath;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.io.Files;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.sys.SystemInfo;
import net.bodz.bas.types.TextMap.HashTextMap;

public class CLIConfig {

    public static ALog getBootLog(int bootLevel) {
        ALog log = ALogs.get(CharOuts.stderr, "boot");
        log.setLevel(bootLevel);
        return log;
    }

    public static DateFormat       logTimeFormat;

    static {
        logTimeFormat = new SimpleDateFormat("[yyyy-MM-dd hh:mm:sss] ");
    }

    public static final String     PROPERTY_LOGLEVEL    = "cli.loglevel";
    public static final String     PROPERTY_LIB_LOADED  = "cli.lib_loaded";
    public static final String     PROPERTY_CHECKLOADER = "cli.checkloader";

    public static final CLILibrary lib;
    public static final FindPath   findPath;
    public static final Conditions conds;

    public static Object libEval(String exp) throws CLIException {
        // System.out.println("eval: " + exp);
        Object ret = lib.eval(exp);
        // System.out.println("  ret = " + ret);
        return ret;
    }

    public static Object libInvoke(String name, String... args)
            throws CLIException {
        return lib.invoke(name, args);
    }

    static {
        lib = new CLILibrary();

        lib.addMethods(findPath = new FindPath());
        findPath.defaultRoot = new File("/");
        if (SystemInfo.isWin32()) {
            String programFiles = System.getenv("ProgramFiles");
            if (programFiles == null)
                programFiles = "C:\\Program Files";
            findPath.defaultRoot = Files.canoniOf(programFiles);
        }
        findPath.namedRoots = new HashTextMap<File>();

        lib.addMethods(conds = new Conditions());

        conds.setAlias("bodz_bas", ALog.class.getName());
    }

}
