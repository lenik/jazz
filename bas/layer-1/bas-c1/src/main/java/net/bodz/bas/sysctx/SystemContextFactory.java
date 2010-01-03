package net.bodz.bas.sysctx;

public class SystemContextFactory {

    private static ISystemContext instance;
    static {
        instance = new ThreadLocalSystemContext();
    }

    public static ISystemContext getSystemContext() {
        return instance;
    }

}
