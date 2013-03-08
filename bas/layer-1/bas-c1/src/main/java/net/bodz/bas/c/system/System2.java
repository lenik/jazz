package net.bodz.bas.c.system;

public class System2 {

    static int exitStatus;

    public static void exit(int status) {
        exitStatus = status;
        System.exit(status);
    }

    public static int getExitStatus() {
        return exitStatus;
    }

    public static void setExitStatus(int exitStatus) {
        System2.exitStatus = exitStatus;
    }

}
