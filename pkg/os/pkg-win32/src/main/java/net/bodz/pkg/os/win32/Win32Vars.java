package net.bodz.pkg.os.win32;

import java.io.File;

import net.bodz.bas.c.system.SystemInfo;
import net.bodz.jna.win32.IWin32;
import net.bodz.pkg.installer.BaseDirVariable;
import net.bodz.pkg.installer.IProject;

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

    public static void setup(IProject project) {
        if (!SystemInfo.isWin32())
            return;

        define(project, WINDIR, //
                new File(Win32.getWindowsDirectory()));
        define(project, SYSDIR, //
                new File(Win32.getWindowsSystemDirectory()));

        define(project, PROGRAM_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PROGRAM_FILES));
        define(project, COMMON_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_FILES));
        define(project, COMMON_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DESKTOP));
        define(project, COMMON_DOCUMENTS, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_DOCUMENTS));
        define(project, COMMON_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_START_MENU));
        define(project, COMMON_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_PROGRAMS_MENU));
        define(project, COMMON_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_AUTOSTART_MENU));
        define(project, COMMON_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_COMMON_TEMPLATES));
        define(project, PERSONAL_DESKTOP, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_DESKTOP));
        define(project, PERSONAL_FILES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FILES));
        define(project, PERSONAL_START_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_START_MENU));
        define(project, PERSONAL_PROGRAMS_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_PROGRAMS_MENU));
        define(project, PERSONAL_AUTOSTART_MENU, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_AUTOSTART_MENU));
        define(project, PERSONAL_TEMPLATES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_TEMPLATES));
        define(project, PERSONAL_SEND_TO, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_SEND_TO));
        define(project, PERSONAL_FAVORITES, //
                Win32.getSpecialDirectory(Win32.SPECIALDIRECTORY_PERSONAL_FAVORITES));
    }

    static void define(IProject project, String name, File file) {
        BaseDirVariable variable = new BaseDirVariable(null, file);
        project.define(name, variable);
    }

}
