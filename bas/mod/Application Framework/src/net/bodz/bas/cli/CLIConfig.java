package net.bodz.bas.cli;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import net.bodz.bas.cli.util.PathFunctions;
import net.bodz.bas.io.CharOuts;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;
import net.bodz.bas.types.util.Types;

public class CLIConfig {

    // classloader trigger
    public static void load() {
    }

    public static ALog getBootLog(int bootLevel) {
        ALog log = ALogs.get(CharOuts.stderr, "boot");
        log.setLevel(bootLevel);
        return log;
    }

    public static DateFormat   logTimeFormat;

    static {
        logTimeFormat = new SimpleDateFormat("[yyyy-MM-dd hh:mm:sss] ");
    }

    public static final String PROPERTY_LOGLEVEL   = "cli.loglevel";
    public static final String PROPERTY_LIB_LOADED = "cli.lib_loaded";

    static {
        Types.load(PathFunctions.class);
    }

}
