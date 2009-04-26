package net.bodz.dist.ins.builtins;

import java.io.File;

import net.bodz.bas.sys.SystemInfo;
import net.bodz.dist.ins._Project;
import net.bodz.dist.ins.builtins.Win32VarsTest;

import com.roxes.win32.Win32;

/**
 * @test {@link Win32VarsTest}
 */
public class Win32Vars {

    public static final String WINDIR                  = "WINDIR"; //$NON-NLS-1$
    public static final String SYSDIR                  = "SYSDIR"; //$NON-NLS-1$

    public static final String PROGRAM_FILES           = "PROGRAM_FILES"; //$NON-NLS-1$
    public static final String COMMON_FILES            = "COMMON_FILES"; //$NON-NLS-1$
    public static final String COMMON_DESKTOP          = "COMMON_DESKTOP"; //$NON-NLS-1$
    public static final String COMMON_DOCUMENTS        = "COMMON_DOCUMENTS"; //$NON-NLS-1$
    public static final String COMMON_START_MENU       = "COMMON_START_MENU"; //$NON-NLS-1$
    public static final String COMMON_PROGRAMS_MENU    = "COMMON_PROGRAMS_MENU"; //$NON-NLS-1$
    public static final String COMMON_AUTOSTART_MENU   = "COMMON_AUTOSTART_MENU"; //$NON-NLS-1$
    public static final String COMMON_TEMPLATES        = "COMMON_TEMPLATES"; //$NON-NLS-1$
    public static final String PERSONAL_DESKTOP        = "PERSONAL_DESKTOP"; //$NON-NLS-1$
    public static final String PERSONAL_FILES          = "PERSONAL_FILES"; //$NON-NLS-1$
    public static final String PERSONAL_START_MENU     = "PERSONAL_START_MENU"; //$NON-NLS-1$
    public static final String PERSONAL_PROGRAMS_MENU  = "PERSONAL_PROGRAMS_MENU"; //$NON-NLS-1$
    public static final String PERSONAL_AUTOSTART_MENU = "PERSONAL_AUTOSTART_MENU"; //$NON-NLS-1$
    public static final String PERSONAL_TEMPLATES      = "PERSONAL_TEMPLATES"; //$NON-NLS-1$
    public static final String PERSONAL_SEND_TO        = "PERSONAL_SEND_TO"; //$NON-NLS-1$
    public static final String PERSONAL_FAVORITES      = "PERSONAL_FAVORITES"; //$NON-NLS-1$

    public static void setup(_Project project) {
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
