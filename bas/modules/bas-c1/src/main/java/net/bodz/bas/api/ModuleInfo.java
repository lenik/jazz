package net.bodz.bas.api;

/**
 * bas-c1 module
 */
public final class ModuleInfo
        extends AbstractModuleInfo {

    private static ModuleInfo instance = new ModuleInfo();

    public static IModuleInfo getInstance() {
        return instance;
    }

}
