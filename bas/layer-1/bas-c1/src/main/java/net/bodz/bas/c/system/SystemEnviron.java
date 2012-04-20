package net.bodz.bas.c.system;

public class SystemEnviron {

    /**
     * DBUS_SESSION_BUS_ADDRESS =
     * unix:abstract=/tmp/dbus-mzkyBdPvV1,guid=cdd05023d3450b3baac5994400000714
     */
    public static String getDBUS_SESSION_BUS_ADDRESS() {
        return System.getProperty("DBUS_SESSION_BUS_ADDRESS");
    }

    /** DEFAULTS_PATH = /usr/share/gconf/gnome.default.path */
    public static String getDEFAULTS_PATH() {
        return System.getProperty("DEFAULTS_PATH");
    }

    /** DESKTOP_AUTOSTART_ID = 104dfc8f82998597a6129470760457721200000043790014 */
    public static String getDESKTOP_AUTOSTART_ID() {
        return System.getProperty("DESKTOP_AUTOSTART_ID");
    }

    /** DESKTOP_SESSION = gnome */
    public static String getDESKTOP_SESSION() {
        return System.getProperty("DESKTOP_SESSION");
    }

    /** DISPLAY = :0.0 */
    public static String getDISPLAY() {
        return System.getProperty("DISPLAY");
    }

    /** EDITOR = vi */
    public static String getEDITOR() {
        return System.getProperty("EDITOR");
    }

    /** GDMSESSION = gnome */
    public static String getGDMSESSION() {
        return System.getProperty("GDMSESSION");
    }

    /** GDM_KEYBOARD_LAYOUT = us */
    public static String getGDM_KEYBOARD_LAYOUT() {
        return System.getProperty("GDM_KEYBOARD_LAYOUT");
    }

    /** GDM_LANG = en_US.utf8 */
    public static String getGDM_LANG() {
        return System.getProperty("GDM_LANG");
    }

    /** GNOME_DESKTOP_SESSION_ID = this-is-deprecated */
    public static String getGNOME_DESKTOP_SESSION_ID() {
        return System.getProperty("GNOME_DESKTOP_SESSION_ID");
    }

    /** GNOME_KEYRING_CONTROL = /tmp/keyring-mLHyFz */
    public static String getGNOME_KEYRING_CONTROL() {
        return System.getProperty("GNOME_KEYRING_CONTROL");
    }

    /** GNOME_KEYRING_PID = 4359 */
    public static String getGNOME_KEYRING_PID() {
        return System.getProperty("GNOME_KEYRING_PID");
    }

    /** GPG_AGENT_INFO = /tmp/gpg-t3thaG/S.gpg-agent:4422:1 */
    public static String getGPG_AGENT_INFO() {
        return System.getProperty("GPG_AGENT_INFO");
    }

    /** GTK_IM_MODULE = ibus */
    public static String getGTK_IM_MODULE() {
        return System.getProperty("GTK_IM_MODULE");
    }

    /** GTK_MODULES = canberra-gtk-module */
    public static String getGTK_MODULES() {
        return System.getProperty("GTK_MODULES");
    }

    /** HISTCONTROL = ignoredups:ignorespace */
    public static String getHISTCONTROL() {
        return System.getProperty("HISTCONTROL");
    }

    /** HISTFILESIZE = 100000000 */
    public static String getHISTFILESIZE() {
        return System.getProperty("HISTFILESIZE");
    }

    /** HISTSIZE = 1000000 */
    public static String getHISTSIZE() {
        return System.getProperty("HISTSIZE");
    }

    /** HISTTIMEFORMAT = %F %T */
    public static String getHISTTIMEFORMAT() {
        return System.getProperty("HISTTIMEFORMAT");
    }

    /** HOME = /home/lenik */
    public static String getHOME() {
        return System.getProperty("HOME");
    }

    /** INFOPATH = /usr/local/texlive/2010/texmf/doc/info:/usr/share/info */
    public static String getINFOPATH() {
        return System.getProperty("INFOPATH");
    }

    /** LANG = en_US.utf8 */
    public static String getLANG() {
        return System.getProperty("LANG");
    }

    /** LANGUAGE = zh_CN:zh_TW:zh:ja:ja_JP:en */
    public static String getLANGUAGE() {
        return System.getProperty("LANGUAGE");
    }

    /**
     * LD_LIBRARY_PATH =
     * /usr/lib/jvm/java-6-sun-1.6.0.22/jre/lib/i386/server:/usr/lib/jvm/java-6-sun
     * -1.6.0.22/jre/lib/
     * i386:/usr/lib/jvm/java-6-sun-1.6.0.22/jre/../lib/i386:/usr/lib/jvm/java-6-sun
     * -1.6.0.22/jre/lib/
     * i386/client:/usr/lib/jvm/java-6-sun-1.6.0.22/jre/lib/i386:/usr/lib/xulrunner-
     * addons:/usr/lib/xulrunner-addons
     */
    public static String getLD_LIBRARY_PATH() {
        return System.getProperty("LD_LIBRARY_PATH");
    }

    /** LOGNAME = lenik */
    public static String getLOGNAME() {
        return System.getProperty("LOGNAME");
    }

    /** MANDATORY_PATH = /usr/share/gconf/gnome.mandatory.path */
    public static String getMANDATORY_PATH() {
        return System.getProperty("MANDATORY_PATH");
    }

    /** MANPATH = /usr/local/texlive/2010/texmf/doc/man:/usr/share/man */
    public static String getMANPATH() {
        return System.getProperty("MANPATH");
    }

    /** MOZILLA_FIVE_HOME = /usr/lib/xulrunner-addons */
    public static String getMOZILLA_FIVE_HOME() {
        return System.getProperty("MOZILLA_FIVE_HOME");
    }

    /** NLSPATH = /usr/dt/lib/nls/msg/%L/%N.cat */
    public static String getNLSPATH() {
        return System.getProperty("NLSPATH");
    }

    /** ORBIT_SOCKETDIR = /tmp/orbit-lenik */
    public static String getORBIT_SOCKETDIR() {
        return System.getProperty("ORBIT_SOCKETDIR");
    }

    /**
     * PATH =
     * /home/lenik/bin:/usr/local/texlive/2010/bin/i386-linux:/opt/bin:/opt/wine/bin:/usr/local
     * /sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:~/bin:~/sbin
     */
    public static String getPATH() {
        return System.getProperty("PATH");
    }

    /** PWD = /home/lenik */
    public static String getPWD() {
        return System.getProperty("PWD");
    }

    /** QT_IM_MODULE = xim */
    public static String getQT_IM_MODULE() {
        return System.getProperty("QT_IM_MODULE");
    }

    /**
     * SESSION_MANAGER =
     * local/lenik-laptop:@/tmp/.ICE-unix/4379,unix/lenik-laptop:/tmp/.ICE-unix/4379
     */
    public static String getSESSION_MANAGER() {
        return System.getProperty("SESSION_MANAGER");
    }

    /** SHELL = /bin/bash */
    public static String getSHELL() {
        return System.getProperty("SHELL");
    }

    /** SPEECHD_PORT = 7559 */
    public static String getSPEECHD_PORT() {
        return System.getProperty("SPEECHD_PORT");
    }

    /** SSH_AGENT_PID = 4421 */
    public static String getSSH_AGENT_PID() {
        return System.getProperty("SSH_AGENT_PID");
    }

    /** SSH_AUTH_SOCK = /tmp/ssh-Jfgwhp4379/agent.4379 */
    public static String getSSH_AUTH_SOCK() {
        return System.getProperty("SSH_AUTH_SOCK");
    }

    /** USER = lenik */
    public static String getUSER() {
        return System.getProperty("USER");
    }

    /** USERNAME = lenik */
    public static String getUSERNAME() {
        return System.getProperty("USERNAME");
    }

    /** WINDOWPATH = 8 */
    public static String getWINDOWPATH() {
        return System.getProperty("WINDOWPATH");
    }

    /** XAUTHORITY = /var/run/gdm/auth-for-lenik-l6ZeRs/database */
    public static String getXAUTHORITY() {
        return System.getProperty("XAUTHORITY");
    }

    /** XDG_CONFIG_DIRS = /etc/xdg/xdg-gnome:/etc/xdg */
    public static String getXDG_CONFIG_DIRS() {
        return System.getProperty("XDG_CONFIG_DIRS");
    }

    /** XDG_DATA_DIRS = /usr/share/gnome:/usr/local/share/:/usr/share/ */
    public static String getXDG_DATA_DIRS() {
        return System.getProperty("XDG_DATA_DIRS");
    }

    /** XDG_SESSION_COOKIE = 3a050f7ede97f4c3175b261c4cc8842b-1294707603.406827-1104504738 */
    public static String getXDG_SESSION_COOKIE() {
        return System.getProperty("XDG_SESSION_COOKIE");
    }

    /** XFILESEARCHPATH = /usr/dt/app-defaults/%L/Dt */
    public static String getXFILESEARCHPATH() {
        return System.getProperty("XFILESEARCHPATH");
    }

    /** XMODIFIERS = @im=ibus */
    public static String getXMODIFIERS() {
        return System.getProperty("XMODIFIERS");
    }

}
