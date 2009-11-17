package net.bodz.bas.api;

public class ModuleInfo
        extends AbstractModuleInfo {

    private static ModuleInfo instance = new ModuleInfo();

    public static IModuleInfo getInstance() {
        return instance;
    }

}
