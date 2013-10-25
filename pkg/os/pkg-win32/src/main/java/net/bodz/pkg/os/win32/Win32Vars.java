package net.bodz.pkg.os.win32;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.jna.win32.IWin32;
import net.bodz.pkg.sis.ISisProject;
import net.bodz.pkg.sis.SisVariable;

import com.roxes.win32.Win32;

public class Win32Vars
        implements IWin32 {

    public static final String WINDIR = "WINDIR";
    public static final String SYSDIR = "SYSDIR";

    public static final String PROGRAM_FILES = "PROGRAM_FILES";
    public static final String COMMON_FILES = "COMMON_FILES";
    public static final String COMMON_DESKTOP = "COMMON_DESKTOP";
    public static final String COMMON_DOCUMENTS = "COMMON_DOCUMENTS";
    public static final String COMMON_START_MENU = "COMMON_START_MENU";
    public static final String COMMON_PROGRAMS_MENU = "COMMON_PROGRAMS_MENU";
    public static final String COMMON_AUTOSTART_MENU = "COMMON_AUTOSTART_MENU";
    public static final String COMMON_TEMPLATES = "COMMON_TEMPLATES";
    public static final String PERSONAL_DESKTOP = "PERSONAL_DESKTOP";
    public static final String PERSONAL_FILES = "PERSONAL_FILES";
    public static final String PERSONAL_START_MENU = "PERSONAL_START_MENU";
    public static final String PERSONAL_PROGRAMS_MENU = "PERSONAL_PROGRAMS_MENU";
    public static final String PERSONAL_AUTOSTART_MENU = "PERSONAL_AUTOSTART_MENU";
    public static final String PERSONAL_TEMPLATES = "PERSONAL_TEMPLATES";
    public static final String PERSONAL_SEND_TO = "PERSONAL_SEND_TO";
    public static final String PERSONAL_FAVORITES = "PERSONAL_FAVORITES";

    static Map<String, File> map = new HashMap<String, File>();

    public static File get(String varName) {
        return map.get(varName);
    }

    static {
        load();
    }

    static void load() {
        if (!SystemInfo.isWin32())
            return;

        map.put(WINDIR, //
                new File(Win32.getWindowsDirectory()));
        map.put(SYSDIR, //
                new File(Win32.getWindowsSystemDirectory()));

        map.put(PROGRAM_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PROGRAM_FILES));
        map.put(COMMON_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_FILES));
        map.put(COMMON_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DESKTOP));
        map.put(COMMON_DOCUMENTS, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DOCUMENTS));
        map.put(COMMON_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_START_MENU));
        map.put(COMMON_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_PROGRAMS_MENU));
        map.put(COMMON_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_AUTOSTART_MENU));
        map.put(COMMON_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_TEMPLATES));
        map.put(PERSONAL_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_DESKTOP));
        map.put(PERSONAL_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FILES));
        map.put(PERSONAL_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_START_MENU));
        map.put(PERSONAL_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_PROGRAMS_MENU));
        map.put(PERSONAL_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_AUTOSTART_MENU));
        map.put(PERSONAL_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_TEMPLATES));
        map.put(PERSONAL_SEND_TO, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_SEND_TO));
        map.put(PERSONAL_FAVORITES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FAVORITES));
    }

    public static void populate(ISisProject project) {
        for (Entry<String, File> entry : map.entrySet()) {
            String name = entry.getKey();
            File value = entry.getValue();
            SisVariable variable = new SisVariable(null, value);
            project.setVariable(name, variable);
        }
    }

}
