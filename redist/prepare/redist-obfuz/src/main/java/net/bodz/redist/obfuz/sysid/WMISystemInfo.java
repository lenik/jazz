package net.bodz.redist.obfuz.sysid;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;

import net.bodz.bas.c.java.io.capture.Processes;
import net.bodz.bas.c.java.lang.Arrays;
import net.bodz.bas.c.system.SystemInfo;
import net.bodz.bas.variant.map.HashVariantMap;

public class WMISystemInfo
        extends HashVariantMap<String> {

    public WMISystemInfo() {
        super(new HashMap<String, Object>());
    }

    /**
     * Query WMI, using wmic syntax.
     * 
     * @throws IOException
     */
    public void query(String prefix, String... args)
            throws WMIException {
        // System.out.println("Query " + prefix + "...");
        String wmiOutput = wmic(args);
        parse(prefix, wmiOutput);
    }

    /**
     * PREFIX.index.PROPERTY=VALUE
     */
    void parse(String prefix, String wmiOutput) {
        String[] groups = wmiOutput.trim().replace("\r", "").split("\n\n\n");
        int index = 0;
        for (String group : groups) {
            String gprefix = prefix + "." + index++ + ".";
            group = group.trim();
            while (group != null) {
                int pos = group.indexOf('\n');
                String line;
                if (pos == -1) {
                    line = group;
                    group = null;
                } else {
                    line = group.substring(0, pos);
                    group = group.substring(pos + 1);
                }
                line = line.trim();
                int eq = line.indexOf('=');
                if (eq != -1) {
                    String key = gprefix + line.substring(0, eq).trim();
                    String value = line.substring(eq + 1).trim();
                    put(key, value);
                }
            }
        }
        put(prefix + ".count", index);
    }

    static boolean wmiSupported;
    static File wmicProgram;
    static {
        if (SystemInfo.isWin32()) {
            wmiSupported = true;
            String systemRoot = System.getenv("SystemRoot");
            wmicProgram = new File(systemRoot, "System32/wbem/wmic.exe");
        } else {
            wmiSupported = false;
        }
    }

    static String wmic(String... args)
            throws WMIException {
        if (!wmiSupported)
            throw new WMIException("WMI isn't supported on this system");
        if (!wmicProgram.canExecute())
            throw new WMIException("wmic utility isn't existed: " + wmicProgram);
        try {
            String[] cmdarray = Arrays.unshift(wmicProgram.getPath(), args);
            Process wmicProcess = Processes.shellExec(cmdarray);
            Charset charset = Charset.defaultCharset();
            String output = Processes.iocap(wmicProcess, charset);
            return output;
        } catch (IOException e) {
            throw new WMIException(e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new WMIException(e.getMessage(), e);
        }
    }

    private static WMISystemInfo instance;

    public static WMISystemInfo getInstance()
            throws WMIException {
        if (instance == null) {
            instance = new WMISystemInfo();
            instance.query("cpu", "cpu", "list", "full");
            instance.query("disk", "diskdrive", "list", "full");
            instance.query("bios", "bios", "list", "full");
            // instance.query("nic", "nic", "list", "full");
        }
        return instance;
    }

}
