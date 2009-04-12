package net.bodz.bas.cli;

import net.bodz.bas.io.CharOuts;
import net.bodz.bas.log.ALog;
import net.bodz.bas.log.ALogs;

public class CLIConfig {

    public static ALog getBootLog(int bootLevel) {
        ALog log = ALogs.get(CharOuts.stderr, "boot"); //$NON-NLS-1$
        log.setLevel(bootLevel);
        return log;
    }

    public static final String PROPERTY_LOGLEVEL    = "cli.loglevel";   //$NON-NLS-1$
    // public static final String PROPERTY_LIB_LOADED = "cli.lib_loaded";
    public static final String PROPERTY_CHECKLOADER = "cli.checkloader"; //$NON-NLS-1$

}
