package net.bodz.redist.installer.builtins;

import java.io.File;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.jna.win32.IWin32;
import net.bodz.redist.installer.AbstractProject;

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

    public static void setup(AbstractProject project) {
        if (!SystemInfo.isWin32())
            return;
        project.define(WINDIR, //
                new File(Win32.getWindowsDirectory()));
        project.define(SYSDIR, //
                new File(Win32.getWindowsSystemDirectory()));

        project.define(PROGRAM_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PROGRAM_FILES));
        project.define(COMMON_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_FILES));
        project.define(COMMON_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DESKTOP));
        project.define(COMMON_DOCUMENTS, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DOCUMENTS));
        project.define(COMMON_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_START_MENU));
        project.define(COMMON_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_PROGRAMS_MENU));
        project.define(COMMON_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_AUTOSTART_MENU));
        project.define(COMMON_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_TEMPLATES));
        project.define(PERSONAL_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_DESKTOP));
        project.define(PERSONAL_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FILES));
        project.define(PERSONAL_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_START_MENU));
        project.define(PERSONAL_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_PROGRAMS_MENU));
        project.define(PERSONAL_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_AUTOSTART_MENU));
        project.define(PERSONAL_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_TEMPLATES));
        project.define(PERSONAL_SEND_TO, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_SEND_TO));
        project.define(PERSONAL_FAVORITES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FAVORITES));
    }

}
